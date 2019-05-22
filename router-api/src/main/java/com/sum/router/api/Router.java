package com.sum.router.api;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * 全局路由管理类
 */
public class Router {

    private static String RootPath = "com.sum.router.map.processor.RouterMapImpl$";

    private HashMap<String, Class<?>> classHashMap = new HashMap<>();

    private Router() {
    }

    private static Router router = new Router();

    public static Router getInstance() {
        return router;
    }

    public Class findClassByRouter(String tag) {
        if (tag == null || tag.equals("")) {
            return null;
        }
        if (classHashMap.containsKey(tag)) {
            return classHashMap.get(tag);
        } else {
            try {
                IRouterMap route = (IRouterMap) Class.forName(RootPath + tag).getConstructor().newInstance();
                String filePath = route.getFilePathByTag(tag);
                Class<?> aClass = Class.forName(filePath);
                classHashMap.put(tag, aClass);
                return aClass;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
