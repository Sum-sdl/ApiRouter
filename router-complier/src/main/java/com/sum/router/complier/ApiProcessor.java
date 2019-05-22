package com.sum.router.complier;

import com.google.auto.service.AutoService;
import com.sum.router.annotation.ApiRouter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * Created by sdl on 2019-05-22.
 */
//注册APT
@AutoService(Processor.class)
public class ApiProcessor extends BaseProcessor {

    //包名
    private static final String PACKAGE_NAME = "com.sum.router.map.processor";
    private static final String CLASS_NAME = "RouterMapImpl";


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new LinkedHashSet<>();
        set.add(ApiRouter.class.getCanonicalName());
        return set;
    }

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        print(" ApiProcessor init ---------------------- ");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        if (!annotations.isEmpty()) {
//            try {
//                print("process start ---------------------- " + annotations.size());
//
//                //获取所有注解的类
//                Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(ApiRouter.class);
//
//                print("process: " + elements.size());
//
//                HashMap<String, String> map = new HashMap<>();
//
//                String pack = null;
//                String module;
//
//                for (Element element : elements) {
//                    TypeElement typeElement = (TypeElement) element;
//
//                    String name = typeElement.getQualifiedName().toString();
//                    print("find name->" + name);
//
//                    ApiRouter annotation = typeElement.getAnnotation(ApiRouter.class);
//                    module = annotation.value();
//                    if (module.equals("")) {
//                        return false;
//                    }
//                    if (map.containsKey(module)) {
//                        return false;
//                    }
//                    //添加key和java的文件路径
//                    map.put(module, name);
//                    if (pack == null) {
//                        pack = elementUtils.getPackageOf(element).getEnclosingElement().toString();
//                        print("get pack:" + pack);
//                    }
//                    //生成java文件
//                    createJavaFile(map, pack, module);
//                }
//                print("process end ---------------------- ");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                print(e);
//            }
//            return true;
//        }

        Set<? extends Element> routeElements = roundEnv.getElementsAnnotatedWith(ApiRouter.class);
        generatedClass(routeElements);
        print("process error ---------------------- ");
        return true;
    }

    private void createJavaFile(HashMap<String, String> map, String pack, String moudle) {
        if (map.isEmpty()) {
            return;
        }
        print("createJavaFile:" + map.size());
        String file = PACKAGE_NAME + "." + CLASS_NAME + "$" + moudle;
        try {
            JavaFileObject jfo = filer.createSourceFile(file);
            Writer writer = jfo.openWriter();
            writer.write(code(file));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String code(String file) {
        StringBuilder sb = new StringBuilder();
        sb.append("package com.enjoy.router;\n");
        sb.append("import java.util.Map;\n");
        //类
        sb.append("public class ");
        sb.append(file);
        sb.append("{\n");
        sb.append("}\n");
        sb.append("}");
        return sb.toString();
    }


    private void generatedClass(Set<? extends Element> routeElements) {
        StringBuilder sb = new StringBuilder();

        sb.append("package com.enjoy.router;\n");
        sb.append("import android.app.Activity;\n");
        sb.append("import java.util.Map;\n");
        //导入 WMActivity、FoodActivity
        for (Element element : routeElements) {
            TypeElement typeElement = (TypeElement) element;
            sb.append("import ");
            sb.append(typeElement.getQualifiedName());
            sb.append(";\n");
        }

        //类
        sb.append("public class ");
        sb.append(moduleName);
        sb.append("_Route {\n");
        sb.append("\n");
        sb.append("public void onLoad(Map<String, Class<? extends Activity>> routes){\n");


        for (Element element : routeElements) {
            //获得注解
            ApiRouter route = element.getAnnotation(ApiRouter.class);
            //函数体 paths.put(xx,xx.class)
            sb.append("routes.put(\"");
            sb.append(route.value());
            sb.append("\",");
            sb.append(element.getSimpleName());
            sb.append(".class);\n");
        }
        sb.append("}\n");
        sb.append("}");
        try {
            //创建 Java文件
            JavaFileObject sourceFile = filer.createSourceFile("com.enjoy.router." + moduleName +
                    "_Route");
            //输出字符串
            OutputStream outputStream = sourceFile.openOutputStream();
            outputStream.write(sb.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
