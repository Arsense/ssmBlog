package com.we.weblog.exception;

import com.we.weblog.controller.core.BaseController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *  *  * <pre>
 *     公共常量
 * </pre>
 * @author tangwei
 * @date 2018/12/7 15:10
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler extends BaseController {
    /**
     * 全局异常的控制拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public void exception(Exception e) {
        // 添加自己的异常处理逻辑，如日志记录　　　
        logger.error("捕获Controller以下层级中的没有处理的运行时异常[{}]", e);
    }
}
