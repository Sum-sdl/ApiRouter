package com.sum.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义接口实现类的标识
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ApiRouter {
    /**
     * @return 接口实现类的唯一标识
     */
    String value();
}
