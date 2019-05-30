package com.sum.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义路由类的唯一标识
 * <p>
 * eg: '/main/page1'
 * 必须以'/'开头，第一字符串必须是字母开头
 * error: '/1main/1page2'
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ApiRouter {
    /**
     * @return 类的唯一标识
     */
    String value();
}
