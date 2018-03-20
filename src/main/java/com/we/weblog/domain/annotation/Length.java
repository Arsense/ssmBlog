package com.we.weblog.domain.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 长度验证
 * 当注解值的长度大于value
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface Length {
    String message() default "Length exceeds the limit";

    int max() default Integer.MAX_VALUE;

    int min() default -1;
}
