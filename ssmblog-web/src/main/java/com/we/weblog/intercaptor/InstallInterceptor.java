package com.we.weblog.intercaptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 *     公共常量
 * </pre>
 *
 * @author Clay
 * @date 2018/10/23 18:11
 */
@Component
public class InstallInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO 先不拦截吧
        return true;
//        if(StringUtils.equals("true", BaseConfigUtil.OPTIONS.get(PropertyEnum.IS_INSTALL.getProp()))) {
//            return true;
//        }
//       response.sendRedirect("/install");
//       return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }
}
