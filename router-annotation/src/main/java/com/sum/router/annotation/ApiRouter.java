package com.sum.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义路由类的唯一标识
 * <p>
 * eg: '/main/page1'
 * 必须以'/'开头，并且第一字符串必须是字母开头
 */
@Target(ElementType.TYPE)//在类生效
@Retention(RetentionPolicy.CLASS)//编译器生效
public @interface ApiRouter {
    /**
     * @return 路由类的唯一标识
     */
    String value();

}
