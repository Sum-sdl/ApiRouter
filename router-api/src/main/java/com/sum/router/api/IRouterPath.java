package com.sum.router.api;

import java.util.Map;

/**
 * Created by sdl on 2020/3/22
 */
public interface IRouterPath {


    /**
     * 定义一个模块中,对外开放的路由集合
     */
     Map<String, RouterBean> getPathMap();

}
