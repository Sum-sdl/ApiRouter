package com.sum.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 给接口定义实现类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ApiImpl {
    /**
     * @return 类的唯一标识
     */
    String value();
}
