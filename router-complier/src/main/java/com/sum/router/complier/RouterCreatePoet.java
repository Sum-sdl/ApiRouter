package com.sum.router.complier;

import com.sum.router.annotation.ApiRouter;
import com.sum.router.api.RouterBean;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by sdl on 2020/3/22
 * 依赖Poet生成对象
 */
class RouterCreatePoet extends AbstractCreateClass {

    RouterCreatePoet(ProcessingEnvironment environment) {
        super(environment);
    }

    HashMap<String, RouterBean> mPath = new HashMap<>();

    @Override
    boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        print("process start,size:" + annotations.size());
        //注解种类集合
        if (annotations.isEmpty()) {
            return false;
        }

        //每个被注解标记的类
        for (TypeElement element : annotations) {
            Name simpleName = element.getSimpleName();
            print("注解类型->" + simpleName);
        }

        //全部集合
        //获取所有注解的类
        Set<? extends Element> routerElements = roundEnv.getElementsAnnotatedWith(ApiRouter.class);
        print("ApiRouter elements size: " + routerElements.size());
        //处理路由业务
        for (Element element : routerElements) {
            //必须是具体的类
            if (element.getKind() != ElementKind.CLASS) {
                continue;
            }
            //当前包名
            PackageElement packageOf = elementTool.getPackageOf(element);
            //父类路径
            String parentPath = packageOf.getQualifiedName().toString();
            print(parentPath);
            //当前类名
            String className = packageOf.getSimpleName().toString();
            print(className);
        }

        print("-------------------------");

//        Set<? extends Element> implElements = roundEnv.getElementsAnnotatedWith(ApiImpl.class);
//        print("ApiImpl elements size: " + implElements.size());
//        //接口实现类业务
//        for (Element element : implElements) {
//            //必须是具体的类
//            if (element.getKind() != ElementKind.CLASS) {
//                continue;
//            }
//
//            //当前类
//            TypeElement typeElement = (TypeElement) element;
//            //class类的路径
//            String classPath = typeElement.getQualifiedName().toString();
//            print(classPath);
//            print(typeElement.getSimpleName());
//
//            TypeMirror superclass = typeElement.getSuperclass();
//            Element superClass = typeTool.asElement(superclass);
//            print(superClass.getSimpleName());
//        }
        printError("JavaPoet finish");
        return false;
    }

    // 取出来分组标识, eg: /main/login
    private String checkAnnotation(String value) {
        Pattern pattern = Pattern.compile("/([a-z|_|[0-9]]{3,50})/([a-z|A-Z|_|[0-9]]{1,50})");
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        printError("路由的值必须以/开头,首字母不能为数字, eg:/main/login ");
        return null;
    }

}
