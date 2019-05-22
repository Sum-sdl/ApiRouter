package com.sum.router.complier;

import com.google.auto.service.AutoService;
import com.sum.router.annotation.ApiRouter;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * Created by sdl on 2019-05-22.
 */
//注册APT
@AutoService(Processor.class)
//指定apt支持的java版本
@SupportedSourceVersion(SourceVersion.RELEASE_7)
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
        if (!annotations.isEmpty()) {
            try {
                print("process start ---------------------- " + annotations.size());

                //获取所有注解的类
                Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(ApiRouter.class);

                print("process: " + elements.size());

                HashMap<String, String> map = new HashMap<>();

                String pack = null;
                String module;

                for (Element element : elements) {
                    TypeElement typeElement = (TypeElement) element;

                    String name = typeElement.getQualifiedName().toString();
                    print("find name->" + name);

                    ApiRouter annotation = typeElement.getAnnotation(ApiRouter.class);
                    module = annotation.value();
                    if (module.equals("")) {
                        return false;
                    }
                    if (map.containsKey(module)) {
                        return false;
                    }
                    //添加key和java的文件路径
                    map.put(module, name);
                    if (pack == null) {
                        pack = elementUtils.getPackageOf(element).getEnclosingElement().toString();
                        print("get pack:" + pack);
                    }
                    //生成java文件
                    createJavaFile(map, pack, module);
                }
                print("process end ---------------------- ");

            } catch (Exception e) {
                print(e);
            }
            return true;
        }
        print("process error ---------------------- ");
        return false;
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
            writer.write(createFile(file));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createFile(String file) {
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


}
