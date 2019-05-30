package com.sum.router.api;

/**
 * Created by sdl on 2019-05-22.
 * Processor使用的接口，用作反射查找类的路径方法
 */
public interface IRouterMap {
    /**
     * @param tag 标记的路由
     * @return 返回标记对应的class的路径，通过反射创建无参构造实例
     */
    String getClassPathByTag(String tag);
}
