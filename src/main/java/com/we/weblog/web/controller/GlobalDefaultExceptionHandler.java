package com.we.weblog.web.controller;

import com.we.weblog.web.controller.core.BaseController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author tangwei
 * @date 2018/12/7 15:10
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler extends BaseController{
    /**
     * 全局异常的控制拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public void exception(Exception e) {
        // 添加自己的异常处理逻辑，如日志记录　　　
        LOG.error("捕获Controller以下层级中的没有处理的运行时异常[{}]", e);
    }
}
