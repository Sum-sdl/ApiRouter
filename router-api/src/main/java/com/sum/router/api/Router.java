package com.sum.router.api;

import java.util.HashMap;

/**
 * 全局路由管理类,根据指定名称找到对应Class
 */
public class Router {

    //统一的文件路径
    private static final String RootPath = "com.sum.router.processor.RouterMapImpl$";

    //class集合类,一般是Activity
    private HashMap<String, Class<?>> classHashMap = new HashMap<>();
    private HashMap<String, Object> implHashMap = new HashMap<>();

    private Router() {
    }

    private static Router router = new Router();

    public static Router getInstance() {
        return router;
    }

    /**
     * 根据module+tag查找对应的class路径
     * <p>
     * eg: @ApiRouter("/home/page1")
     * 表示：home模块的管理类和page1的具体类
     *
     * @param tag 目标类的唯一标识
     * @return 目标类不存在返回null
     */
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
                String filePath = route.getClassPathByTag(tag);
                Class<?> aClass = Class.forName(filePath);
                classHashMap.put(tag, aClass);
                return aClass;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 接口实现类必须无参构造
     *
     * @param classOfT API接口类
     * @param <T>      泛型
     * @return 存在返回实现类实例，不存在返回null
     */
    public <T> T findApiImpl(Class classOfT) {
        return findApiImpl(classOfT.getName());
    }

    /**
     * 接口实现类必须无参构造
     *
     * @param tag API接口实现类的唯一标识
     * @param <T> 泛型
     * @return 存在返回实现类实例，不存在返回null
     */
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

    /**
     * @param tag API接口实现类的标识
     * @return 被移除的对象实例
     */
    public Object removeApiImpl(String tag) {
        return implHashMap.remove(tag);
    }
}
