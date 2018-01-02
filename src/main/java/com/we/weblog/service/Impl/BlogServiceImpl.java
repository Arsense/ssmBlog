package com.we.weblog.service.Impl;


import com.we.weblog.domain.YearBlog;
import com.we.weblog.mapping.BlogMapper;
import com.we.weblog.mapping.TagMapper;
import com.we.weblog.service.BlogService;
import com.we.weblog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class BlogServiceImpl implements BlogService {
    private BlogMapper blogMapper;
    private TagMapper tagMapper;

    /**
     * 构造函数
     * @param blogMapper
     */
    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper,TagMapper tagMapper) {
        this.tagMapper = tagMapper;
        this.blogMapper = blogMapper;
}
    /**
     *  将tags拆分放到数组里
     * @param tagString
     * @return
     */
    public List<String> getTagList(String tagString){
        List<String> tagList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(tagString,",");
        while (st.hasMoreTokens()){
            tagList.add(st.nextToken());
        }

        return tagList;


    }

    /**
     * 添加博客标签
     * @param tags
     * @param id
     */
    public  void addBlogTags(String tags,int id){
        List<String> tagList = getTagList(tags);
        for(String tag:tagList){
            tagMapper.insertBlogTag(tag,id);
        }
    }


    /**
     * 更新博客标签 实际是删除重插入
     * @param tags
     * @param id
     */
    public  void updateBlogTag(String tags,int id){
        tagMapper.deleteTagsById(id);
        List<String> tagList = getTagList(tags);
        for(String tag:tagList){
            tagMapper.insertBlogTag(tag,id);
        }

    }


    /**
     * 添加博客 同时把tags添加进去
     * @param blog
     */
    @Override
    public void addBlog(Blog blog) {
        blog.setTime(new Date(System.currentTimeMillis()));
        blogMapper.insertBlog(blog);
        addBlogTags(blog.getTags(),blog.getBlogId());

    }

    @Override
    public void updateBlog(Blog blog) {
        blogMapper.updateBlog(blog);
        updateBlogTag(blog.getTags(),blog.getBlogId());
        updateBlogTag(blog.getTags(),blog.getBlogId());
    }

    @Override
    public void deleteBlogById(int id) {
        blogMapper.deleteBlogById(id);
    }


    @Override
    public List<Blog> showBlogs(int page) {
       page = page*10;
       List<Blog> blogs =  new ArrayList<>();
       blogs = blogMapper.getTenBlogs(page);

       return blogs;

    }

    @Override
    public int getBlogsPages() {
        return 0;
    }

    @Override
    public List<String> getAllTags() {
        return blogMapper.getAllTags();
    }

    @Override
    public Blog getBlogHtml() {
        return null;
    }

    @Override
    public List<Blog> getBlogsByTag(String tagName) {
        return null;
    }


    @Override
    public Blog getPreviousBlog(int blogId) {
        return blogMapper.getPreviousBlog(blogId);
    }

    @Override
    public Blog getNextBlog(int blogId) { return blogMapper.getNextBlog(blogId);
    }

    @Override
    public Blog getBlogById(int id) {
        return blogMapper.getBlogById(id);
    }


    /**
     * 根据页码显示每个年份的博客
     * @param page
     * @return
     */
    @Override
    public List<YearBlog> getYearBlog(int page) {
        int start = (page-1)*12;
        List<Blog> list = blogMapper.selectBlogsByYear(start);
        return sortBlogsByYears(list);
    }



    @Override
    public List<YearBlog> sortBlogsByYears(List<Blog> bloglist) {
        List<YearBlog> yearBlogs = new ArrayList<>();
        Map<Integer,YearBlog> yearMap = new HashMap<>();
        for(Blog blog : bloglist){
            Date date= blog.getDate();
            blog.setMonth(TimeTool.getEdate(date));
            int year = TimeTool.getYear(date);
            if(yearMap.containsKey(year)){
                yearMap.get(year).getYearBlogs().add(blog);
            }else{
                YearBlog yearBlog = new YearBlog(year,new ArrayList<Blog>());
                yearMap.put(year,yearBlog);
                yearBlog.getYearBlogs().add(blog);
                yearBlogs.add(yearBlog);
            }

    }

        return yearBlogs;
    }



    @Override
    public int getNumberOfYearBlog() {
        //先只处理一页吧
        return 0;
    }


}