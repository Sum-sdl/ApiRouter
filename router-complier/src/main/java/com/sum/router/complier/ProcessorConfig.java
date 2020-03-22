package com.sum.router.complier;

/**
 * Created by sdl on 2020/3/22
 */
public interface ProcessorConfig {

    //生成路由文件的包名
//    String PACKAGE_NAME = "com.sum.router.processor";
    String PACKAGE_NAME = "com.sum.apirouter.processortest";

    String Router_Group = PACKAGE_NAME + ".IRouterGroup";
    String Router_Path = PACKAGE_NAME + ".IRouterPath";

    //Gradle传入的参
    //定义模块名称
    String MODULE_NAME = "module_name";
    //定义模块包名
    String MODULE_PACKAGE = "module_package";
}
