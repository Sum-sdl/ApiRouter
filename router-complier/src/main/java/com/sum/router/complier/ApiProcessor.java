package com.sum.router.complier;

import com.google.auto.service.AutoService;
import com.sum.router.annotation.ApiRouter;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * Created by sdl on 2019-05-22.
 */
//注册APT
@AutoService(Processor.class)
public class ApiProcessor extends BaseProcessor {

    //包名
    private static final String PACKAGE_NAME = "com.sum.router.processor";
    private static final String CLASS_NAME = "RouterMapImpl";


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new LinkedHashSet<>();
        set.add(ApiRouter.class.getCanonicalName());
        return set;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        print("process start");
        //annotations记录了getSupportedAnnotationTypes中的注解类型
        if (!annotations.isEmpty()) {
            try {
                //获取所有注解的类
                Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(ApiRouter.class);

                print("ApiRouter elements size: " + elements.size());


                HashMap<String, HashMap<String, String>> moduleMap = new HashMap<>();

                //添加多个类型模块
                for (Element element : elements) {
                    if (element.getKind() != ElementKind.CLASS) {
                        continue;
                    }
                    TypeElement typeElement = (TypeElement) element;
                    ApiRouter annotation = typeElement.getAnnotation(ApiRouter.class);
                    String value = annotation.value();
                    String module = checkAnnotation(value);
                    if (module == null) {
                        continue;
                    }
                    //添加一组类型的map
                    if (!moduleMap.containsKey(module)) {
                        moduleMap.put(module, new HashMap<String, String>());
                    }
                }

                //给module添加类的路径
                for (Element element : elements) {
                    if (element.getKind() != ElementKind.CLASS) {
                        continue;
                    }
                    TypeElement typeElement = (TypeElement) element;
                    //class类的路径
                    String classPath = typeElement.getQualifiedName().toString();

                    ApiRouter annotation = typeElement.getAnnotation(ApiRouter.class);
                    String value = annotation.value();
                    String module = checkAnnotation(value);
                    if (module == null) {
                        return false;
                    }
                    HashMap<String, String> map = moduleMap.get(module);
                    if (map.containsKey(value)) {
                        printError("exit same value !!! ->" + value);
                        return false;
                    }
                    print("class path->" + classPath + " ,className->" + typeElement.getSimpleName() + " ,module:" + module);
                    //添加key和java的文件路径
                    map.put(value, classPath);
                }
                //生成多个class类
                for (Map.Entry<String, HashMap<String, String>> mapEntry : moduleMap.entrySet()) {
                    //生成java文件
                    createJavaFile(mapEntry.getValue(), mapEntry.getKey());
                }
                print("process end !! create class size:" + moduleMap.size());
            } catch (Exception e) {
                e.printStackTrace();
                printError(e);
            }
            return true;
        }
        print("process error !! annotations is empty");
        return true;
    }

    // eg: /main111/login
    private String checkAnnotation(String value) {
        Pattern pattern = Pattern.compile("/([a-z|_|[0-9]]{3,50})/([a-z|A-Z|_|[0-9]]{1,50})");
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        printError("router value not match \"/([a-z|_|[0-9]]{3,50})/([a-z|A-Z|_|[0-9]]{1,50})\") eg:/main1/login ");
        return null;
    }

    //创建文件
    private void createJavaFile(HashMap<String, String> map, String moudle) {
        if (map.isEmpty()) {
            return;
        }
        print("createJavaFile start ->" + moudle + " ,router size:" + map.size());
        String className = CLASS_NAME + "$" + moudle;
        String file = PACKAGE_NAME + "." + className;
        try {
            JavaFileObject jfo = filer.createSourceFile(file);
            Writer writer = jfo.openWriter();
            writer.write(code(className, map));
            writer.flush();
            writer.close();
            print("createJavaFile finish -> " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String code(String className, HashMap<String, String> classPath) {
        StringBuilder code = new StringBuilder();
        code.append("package " + PACKAGE_NAME + ";\n");
        code.append("import java.util.HashMap;\n");
        code.append("import com.sum.router.api.IRouterMap;\n");
        //类
        code.append("public class ").append(className).append(" implements IRouterMap { \n\n");
        code.append("private static HashMap<String,String> routers=new HashMap<String,String>();\n\n");
        code.append("static {\n");
        for (Map.Entry<String, String> entry : classPath.entrySet()) {
            code.append("routers.put(\"").append(entry.getKey()).append("\",\"").append(entry.getValue()).append("\");\n");
        }
        code.append("}\n");
        code.append("public String getFilePathByTag(String tag){\n");
        code.append("return routers.get(tag);\n");
        code.append("}\n");
        code.append("}");
        return code.toString();
    }
}
