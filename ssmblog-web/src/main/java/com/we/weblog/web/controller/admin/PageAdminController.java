package com.we.weblog.web.controller.admin;


import com.we.weblog.web.controller.core.BaseController;
import com.we.weblog.domain.Link;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.domain.util.AddressUtil;
import com.we.weblog.service.LinkService;
import com.we.weblog.service.LogsService;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 *  * <pre>
 *     公共常量
 * </pre>
 */
@Controller
@RequestMapping("/admin/page")
public class PageAdminController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PostService postService;
    @Resource
    private TagService tagService;
    @Resource
    private LogsService logService;
    @Resource
    private LinkService linkService;


    @PostMapping("/publish")
    @ResponseBody
    public void createPages(Post context, HttpServletResponse response) throws Exception {
        try {
            context.setType(Types.PAGE);
            context.setTags("test");  //tags not null
            postService.saveByPost(context);
            Log loginLog =new Log(LogActions.ADD_PAGES,"admin", AddressUtil.getIpAddress(request),1);
            if (logService.saveByLogs(loginLog)<0) {
                throw new Exception("添加新页面失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/admin_pages.html");
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deletePages(@PathVariable  int id){
        postService.removeByPostId(id);
        tagService.deleteTag(id);
    }



    /**
     * 获取友情链接列表并渲染页面
     *
     * @return 模板路径admin/admin_page_link
     */
    @GetMapping(value = "/links")
    public String links() {
        return "admin/admin_page_link";
    }


    /**
     * 跳转到修改页面
     *
     * @param model  model
     * @param linkId linkId 友情链接编号
     * @return String 模板路径admin/admin_page_link
     */
    @GetMapping(value = "/links/edit")
    public String toEditLink(Model model, @RequestParam("linkId") Long linkId) {
//        Link link = linkService.findByLinkId(linkId);
//        model.addAttribute("updateLink", link);
        return "admin/admin_page_link";
    }
    /**
     * 处理添加/修改友链的请求并渲染页面
     *
     * @param link Link实体
     * @return 重定向到/admin/page/links
     */
    @PostMapping(value = "/links/save")
    public String saveLink(@ModelAttribute Link link) {
        try {
            linkService.saveByLink(link);
        } catch (Exception e) {
            logger.error("保存/修改友情链接失败：{}", e.getMessage());
        }
        return "redirect:/admin/page/links";
    }

    /**
     * 处理删除友情链接的请求并重定向
     *
     * @param linkId 友情链接编号
     * @return 重定向到/admin/page/links
     */
    @GetMapping(value = "/links/remove")
    public String removeLink(@RequestParam("linkId") Long linkId) {
        try {
            linkService.removeByLinkId(linkId);
        } catch (Exception e) {
            logger.error("删除友情链接失败：{}", e.getMessage());
        }
        return "redirect:/admin/page/links";
    }


    /**
     * 图库管理
     *
     * @param model model
     * @param page  当前页码
     * @param size  每页显示的条数
     * @return 模板路径admin/admin_page_gallery
     */
    @GetMapping(value = "/galleries")
    public String gallery(Model model,
                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "size", defaultValue = "18") Integer size) {
//        Gallery galleries = galleryService.findAllGalleries(galleryId);
//        model.addAttribute("galleries", galleries);
        return "admin/admin_page_gallery";
    }


//    /**
//     * 保存图片
//     *
//     * @param gallery gallery
//     * @return 重定向到/admin/page/gallery
//     */
//    @PostMapping(value = "/gallery/save")
//    public String saveGallery(@ModelAttribute Gallery gallery) {
//        try {
//            if (StringUtils.isEmpty(gallery.getGalleryThumbnailUrl())) {
//                gallery.setGalleryThumbnailUrl(gallery.getGalleryUrl());
//            }
//            galleryService.saveByGallery(gallery);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/admin/page/galleries";
//    }
//
//    /**
//     * 处理获取图片详情的请求
//     *
//     * @param model     model
//     * @param galleryId 图片编号
//     * @return 模板路径admin/widget/_gallery-detail
//     */
//    @GetMapping(value = "/gallery")
//    public String gallery(Model model, @RequestParam("galleryId") Long galleryId) {
//        Optional<Gallery> gallery = galleryService.findByGalleryId(galleryId);
//        model.addAttribute("gallery", gallery.get());
//        return "admin/widget/_gallery-detail";
//    }
//
//    /**
//     * 删除图库中的图片
//     *
//     * @param galleryId 图片编号
//     * @return UIModel
//     */
//    @GetMapping(value = "/gallery/remove")
//    @ResponseBody
//    public UIModel removeGallery(@RequestParam("galleryId") Long galleryId) {
//        try {
//            galleryService.removeByGalleryId(galleryId);
//        } catch (Exception e) {
//            log.error("删除图片失败：{}", e.getMessage());
//            return new UIModel(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.common.delete-failed"));
//        }
//        return new UIModel(ResultCodeEnum.SUCCESS.getCode(), localeMessageUtil.getMessage("code.admin.common.delete-success"));
//    }
//
//
//    /**
//     * 跳转到新建页面
//     *
//     * @return 模板路径admin/admin_page_md_editor
//     */
//    @GetMapping(value = "/new")
//    public String newPage() {
//        return "admin/admin_page_md_editor";
//    }
//
//    /**
//     * 发表页面
//     *
//     * @param post    post
//     * @param session session
//     */
//    @PostMapping(value = "/new/push")
//    @ResponseBody
//    public UIModel pushPage(@ModelAttribute Post post, HttpSession session) {
//        String msg = localeMessageUtil.getMessage("code.admin.common.save-success");
//        try {
//            post.setPostDate(DateUtil.date());
//            //发表用户
//            User user = (User) session.getAttribute(BaseConfigUtil.USER_SESSION_KEY);
//            post.setUser(user);
//            post.setPostType(PostTypeEnum.POST_TYPE_PAGE.getDesc());
//            if (null != post.getPostId()) {
//                post.setPostViews(postService.findByPostId(post.getPostId()).get().getPostViews());
//                post.setPostDate(postService.findByPostId(post.getPostId()).get().getPostDate());
//                post.setPostUpdate(DateUtil.date());
//                msg = localeMessageUtil.getMessage("code.admin.common.update-success");
//            } else {
//                post.setPostDate(DateUtil.date());
//                post.setPostUpdate(DateUtil.date());
//            }
//            //当没有选择文章缩略图的时候，自动分配一张内置的缩略图
//            if (StringUtils.equals(post.getPostThumbnail(), BlogPropertiesEnum.DEFAULT_THUMBNAIL.getProp())) {
//                post.setPostThumbnail("/static/images/thumbnail/thumbnail-" + RandomUtil.randomInt(1, 10) + ".jpg");
//            }
//            postService.saveByPost(post);
//            logsService.saveByLogs(new Logs(LogsRecord.PUSH_PAGE, post.getPostTitle(), ServletUtil.getClientIP(request), DateUtil.date()));
//            return new UIModel(ResultCodeEnum.SUCCESS.getCode(), msg);
//        } catch (Exception e) {
//            log.error("保存页面失败：{}", e.getMessage());
//            return new UIModel(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.common.save-failed"));
//        }
//    }
//
//    /**
//     * 跳转到修改页面
//     *
//     * @param pageId 页面编号
//     * @param model  model
//     * @return admin/admin_page_md_editor
//     */
//    @GetMapping(value = "/edit")
//    public String editPage(@RequestParam("pageId") Long pageId, Model model) {
//        Optional<Post> post = postService.findByPostId(pageId);
//        model.addAttribute("post", post.get());
//        return "admin/admin_page_md_editor";
//    }
//
//    /**
//     * 检查该路径是否已经存在
//     *
//     * @param postUrl postUrl
//     * @return UIModel
//     */
//    @GetMapping(value = "/checkUrl")
//    @ResponseBody
//    public UIModel checkUrlExists(@RequestParam("postUrl") String postUrl) {
//        Post post = postService.findByPostUrl(postUrl, PostTypeEnum.POST_TYPE_PAGE.getDesc());
//        if (null != post) {
//            return new UIModel(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.common.url-is-exists"));
//        }
//        return new UIModel(ResultCodeEnum.SUCCESS.getCode(), "");
//    }

}
