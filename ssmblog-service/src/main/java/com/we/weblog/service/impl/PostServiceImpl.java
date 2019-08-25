package com.we.weblog.service.impl;

import com.we.weblog.domain.Category;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.result.Result;
import com.we.weblog.domain.enums.PostStatus;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.domain.modal.YearBlog;
import com.we.weblog.domain.util.TimeUtil;
import com.we.weblog.mapper.PostMapper;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 *  文章处理服务
 */
@Service
public class PostServiceImpl implements PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

    @Resource
    private PostMapper postMapper;

    @Resource
    private TagService tagService;

    /**
     * 得到分类总数
     *
     * @return
     */
    @Override
   public Result countCategory() {
        Result result = new Result(false);
        //todo 优化 用count(*)
        int count = postMapper.findAllCategory().size();
        if (count > 0) {
            result.setData(count);
        }
        result.setSuccess();
        return result;
   }

    @Override
    public Result queryByCategory(){
        Result result;
        Post post = new Post();
        post.setType(Types.ARTICLE);
        List<Post> posts = null;
        try {
            posts = postMapper.queryPost(post);
            result = getBlogsFromTags(posts);
        } catch (Exception e) {
            LOGGER.error("queryByCategory error,param" + post);
            throw new RuntimeException("queryByCategory error" + post);
        }
        return result;
    }

    /**
     * 得到所有分类名
     *
     * @return
     */
    public List<String> getCategories(){
        return postMapper.findAllCategory();
    }

    /**
     * 根据分类分配到相应的类中
     * @return
     */
    public Result getBlogsFromTags(List<Post> posts){
        Result result = new Result();
        List<Category> categoryList = new ArrayList<>();
        Map<String, Category>  maps = new HashMap<>();
        for(Post post:posts){
            if(maps.containsKey(post.getCategories())){
                //如果已有该标签
                maps.get(post.getCategories()).getBlogs().add(post);
            } else {
                Category category = new Category(post.getCategories(),new ArrayList<Post>());
                maps.put(post.getCategories(),category);
                category.getBlogs().add(post);
                categoryList.add(category);
            }
        }
        if (!CollectionUtils.isEmpty(categoryList)) {
            result.setData(categoryList);
        }
        result.setSuccess();

        return  result;
    }


    /**
     * 查询Id之前的文章
     *
     * @return List
     */
    @Override
    public Result findPreviousPost(int uid) {

        Result result = new Result();
        Post post = null;
        try {
            post = postMapper.findPreviousPost(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(post == null)
            return result;

        post.setMonth(TimeUtil.getFormatClearToDay(post.getCreated()));
        result.setData(post);
        return result;
    }

    /**
     * 查询Id之后的文章
     *
     * @return List
     */
    @Override
    public Result findNextPost(int uid) {
        Result result = new Result();

        Post post = null;
        try {
            post = postMapper.findNextPost(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(post == null)
            return result;
        post.setMonth(TimeUtil.getFormatClearToDay(post.getCreated()));
        result.setData(post);
        return result;
    }

    @Override
    public Result findAuthor() throws Exception {
        Result result = new Result();
        Post post = new Post();
        post.setTitle("关于我");
        //todo 这里可能空指针 异常优化
        try {
            post = postMapper.queryPost(post).get(0);
            if (post == null) {
                throw new Exception("关于我没创建");
            }
            post.setMonth(TimeUtil.getFormatClearToDay(post.getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        result.setData(post);
        return result;
    }



    /**
     * 根据年份和月份查询文章
     * @param page
     * @return
     */
    @Override
    public Result findPostByYearAndMonth(int page) throws IOException {
        Result result = new Result();
        int start = (page - 1) * 12;
        List<Post> list = null;
        try {
            list = postMapper.findPostByYearAndMonth(start);
            if(CollectionUtils.isEmpty(list)) {
                LOGGER.info("findPostByYearAndMonth 没有查询到数据, param" + page);
                return result;
            }
        } catch (Exception e) {
           result.setErrMsg("服务端错误");
           LOGGER.info("findPostByYearAndMonth error, param" + page);
        }
        result.setData(sortBlogsByYears(list));
        result.setSuccess();
        return result;
    }

    /**
     * 得到博客总数
     * @return
     */
    @Override
    public Result findPostCount() {
        Result result = new Result();
        try {
            int count = postMapper.countPost(new Post());
            if (count > 0) {
                result.setData(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    /**
     * 得到页面管理的信息
     * @return
     */
    @Override
    public Result getArticlePages(){
        Result result = new Result();
        Post post = new Post();
        post.setType(Types.PAGE);
        try {
            sortPostDate(postMapper.queryPost(post));
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    @Override
    public Result removePostCategory(String name) {
        Result result = new Result();
        try {
            postMapper.removePostCategory(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();

        return result;
    }


    /**
     * 访问量增加
     *
     * @return
     */
    @Override
    public Result updatePostVisit(Post post){
        Result result = new Result();
        Post request = new Post();
        request.setHits(post.getHits() + 1);
        request.setUid(post.getUid());
        try {
            postMapper.updateVisit(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();

        return result;
    }

    /**
     * 新增文章
     *
     * @param post Post
     * @return Post
     */
    @Override
    public Result saveByPost(Post post) throws SQLException {
        Result result = new Result();
        //校验
        //默认没有分类则创建分类
        if(StringUtils.isEmpty(post.getCategories())){
            post.setCategories("默认分类");
        }
        //初始化访问量是0
        post.setHits(0);
        post.setPublish(Types.PUBLISH);
        post.setStatus(PostStatus.PUBLISHED.getCode());
        post.setCreated(new Date(System.currentTimeMillis()));
        try {
            postMapper.savePost(post);
            tagService.addBlogTags(post.getTags(), post.getUid());

        } catch (Exception e) {
            LOGGER.info("saveByPost error");
            throw new SQLException("添加博客失败");
        }

        result.setSuccess();
        return result;
    }

    @Override
    public Result queryPost(Post post) {
        Result result = new Result();
        if (post == null) {
            result.setErrMsg("Post不能为空");
            return result;
        }
        List<Post> postList;
        try {
             postList = postMapper.queryPost(post);
             if (!CollectionUtils.isEmpty(postList)) {
                 result.setData(post);
             }
        } catch (Exception e) {
            result.setErrMsg("queryPost error");

        }
        result.setSuccess();
        return result;

    }

    /**
     * 根据编号删除文章
     *
     * @param postId postId
     * @return Post
     */
    @Override
    public Result removeByPostId(Integer postId) {
        Result result = new Result();
        try {
            postMapper.removePost(postId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    @Override
    public Result updatePost(Post post) {
        Result result = new Result();
        if (post == null) {
            return result;
        }
        try {
            postMapper.updatePost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    @Override
    public Result findByPostId(int postId) {
        Result result = new Result();
        Post post = new Post();
        post.setUid(postId);
        try {
            List<Post> posts = postMapper.queryPost(post);
            if(!CollectionUtils.isEmpty(posts)){
                post = posts.get(0);
            }
            post.setMonth(TimeUtil.getFormatClearToDay(post.getCreated()));
            result.setData(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    /**
     * 查询前五条数据
     *
     * @return List
     */
    @Override
    public Result findHotPosts(int limit) {
        Result result = new Result();
        if (limit < 0 || limit > 20) {
            limit = 10;
        }
        List<Post> postList;
        try {
            postList = sortPostDate(postMapper.querylimitPost(new Post()));
            if (!CollectionUtils.isEmpty(postList)) {
                result.setData(postList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();


        return result ;
    }

    @Override
    public Result updatePostStatus(Integer postId, Integer status) {
        Result result = new Result();
        Post post = new Post();
        post.setUid(postId);
        post.setStatus(status);
        try {
            postMapper.updatePost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    @Override
    public Result findAllPosts(int page) {
        Result result = new Result();
        if (page < 0 || page > 10){
            page = 1;
        }
        page = page * 10;
        try {
            sortPostDate(postMapper.findRecentPosts(page));
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();

        return result;
    }

    @Override
    public Result findAllPosts() {
        Result result = new Result();
        Post post = new Post();
        post.setType("post");
        try {
            sortPostDate(postMapper.queryPost(post));
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();

        return result;

    }

    @Override
    public Result findAllPostsByStatus(int status) {
        Result result = new Result();
        Post post = new Post();
        post.setStatus(status);
        post.setType(Types.PAGE);
        try {
           List<Post> postList =  sortPostDate(postMapper.queryPost(post));
           if (!CollectionUtils.isEmpty(postList)) {
               result.setData(postList);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    /**
     * 将Date变成年月份
     * @param pages
     * @return
     */
    private  List<Post> sortPostDate(List<Post> pages) {
        for(Post page: pages){
            page.setMonth(TimeUtil.getFormatClearToDay(page.getCreated()));
        }
        return pages;
    }

    private List<YearBlog> sortBlogsByYears(List<Post> bloglist) {
        List<YearBlog> yearBlogs = new ArrayList<>();
        Map<Integer,YearBlog> yearMap = new HashMap<>();

        for (Post post : bloglist) {
            post.setMonth(TimeUtil.getEdate(post.getCreated()));
            Integer year = TimeUtil.getYear(post.getCreated());
            if (yearMap.containsKey(year)) {
                yearMap.get(year).getYearContexts().add(post);
            } else {
                YearBlog yearBlog = new YearBlog(year, new ArrayList<>());
                yearMap.put(year,yearBlog);
                yearBlog.getYearContexts().add(post);
                yearBlogs.add(yearBlog);
            }
        }
        return yearBlogs;
    }


}