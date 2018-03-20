package com.we.weblog.domain.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 验证是否为空
 * 当注解值为null或空
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface NotEmpty {

    String message() default "Object not is empty";
}
