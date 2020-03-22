package com.sum.apirouter.test;

import com.sum.router.api.IRouterPath;
import com.sum.router.api.RouterBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdl on 2020/3/22
 */
public class Path1 implements IRouterPath {
    @Override
    public Map<String, RouterBean> getPathMap() {
        HashMap<String, RouterBean> result = new HashMap<>();
        result.put("/app/main", new RouterBean.Builder().build());
        return result;
    }
}
