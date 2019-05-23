package com.sum.router.api;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * 全局路由管理类
 */
public class Router {

    private static final String RootPath = "com.sum.router.processor.RouterMapImpl$";

    private HashMap<String, Class<?>> classHashMap = new HashMap<>();
    private HashMap<String, Object> implHashMap = new HashMap<>();

    private Router() {
    }

    private static Router router = new Router();

    public static Router getInstance() {
        return router;
    }

    //根据module+tag查找对应的class路径
    public Class findClassByRouter(String tag) {
        if (tag == null || tag.equals("")) {
            return null;
        }
        //module定义了管理路径的类的名称
        String module = tag.split("/")[1];
        if (classHashMap.containsKey(module)) {
            return classHashMap.get(module);
        } else {
            try {
                IRouterMap route = (IRouterMap) Class.forName(RootPath + module).getConstructor().newInstance();
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

    public <T> T findApiImpl(String tag) {
        if (implHashMap.containsKey(tag)) {
            return (T) implHashMap.get(tag);
        } else {
            Class targetApiImpl = findClassByRouter(tag);
            try {
                if (targetApiImpl != null) {
                    Object o = targetApiImpl.newInstance();
                    implHashMap.put(tag, o);
                    return (T) o;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
