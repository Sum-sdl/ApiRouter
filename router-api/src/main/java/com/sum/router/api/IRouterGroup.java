package com.sum.router.api;

import java.util.Map;

/**
 * Created by sdl on 2020/3/22
 * 定义一组路由
 */
public interface IRouterGroup {


    /**
     * @return 获取一个模块的全部路由
     */
    Map<String, Class<? extends IRouterPath>> getGroup();

}
