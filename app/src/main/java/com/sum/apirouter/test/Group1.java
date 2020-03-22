package com.sum.apirouter.test;

import com.sum.router.api.IRouterGroup;
import com.sum.router.api.IRouterPath;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdl on 2020/3/22
 */
public class Group1 implements IRouterGroup {
    @Override
    public Map<String, Class<? extends IRouterPath>> getGroup() {

        HashMap<String, Class<? extends IRouterPath>> map = new HashMap<>();
        map.put("app", Path1.class);

        return map;
    }
}
