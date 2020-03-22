package com.sum.router.api;

/**
 * Created by sdl on 2020/3/22
 */
public class RouterBean {

    //定义当前路由的类型
    public enum Type {
        Activity, Api
    }

    private Type typeEnum;//当前的路由类型

    private String path;//定义一个分组下的一个路径 app/main

    private String group;//定义的分组

    private Class<?> targetClass;//目标类型

    public Type getTypeEnum() {
        return typeEnum;
    }

    public String getPath() {
        return path;
    }

    public String getGroup() {
        return group;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    private RouterBean() {
    }


    //构造一个路由信息
    public static class Builder {

        private Type typeEnum;//当前的路由类型

        private String path;//定义一个分组下的一个路径 app/main

        private String group;//定义的分组

        private Class<?> targetClass;//目标类型

        public Builder addType(Type typeEnum) {
            this.typeEnum = typeEnum;
            return this;
        }

        public Builder addPath(String path) {
            this.path = path;
            return this;
        }

        public Builder addGroup(String group) {
            this.group = group;
            return this;
        }

        public Builder addPath(Class<?> targetClass) {
            this.targetClass = targetClass;
            return this;
        }

        public RouterBean build() {
            RouterBean bean = new RouterBean();
            bean.typeEnum = typeEnum;
            bean.group = group;
            bean.path = path;
            bean.targetClass = targetClass;
            return bean;
        }
    }

}
