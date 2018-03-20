package com.we.weblog.domain.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 邮箱验证
 * 当注解值不是一个email格式
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface Email {

    String message() default "Email format is not correct";
}
