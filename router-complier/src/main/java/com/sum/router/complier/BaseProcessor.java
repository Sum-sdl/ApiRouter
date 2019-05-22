package com.sum.router.complier;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * Created by sdl on 2019-05-22.
 */
abstract class BaseProcessor extends AbstractProcessor {
    Elements elementUtils;
    Filer filer;
    Types typeUtils;
    private Messager messager;
    String moduleName;


    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        elementUtils = env.getElementUtils();
        filer = env.getFiler();
        typeUtils = env.getTypeUtils();
        messager = env.getMessager();
        // Attempt to get user configuration [moduleName]
        Map<String, String> options = processingEnv.getOptions();
        moduleName = options.get("MODULE_NAME");
        print(getClass().getSimpleName() + " init:" + moduleName);
    }


    void print(Object o) {
        System.out.println(o);
        messager.printMessage(Diagnostic.Kind.NOTE, o.toString());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //支出的Annotation的类型，通过注解已经完成
        return super.getSupportedAnnotationTypes();
    }

    @Override
    public Set<String> getSupportedOptions() {
        return new HashSet<String>() {{
            this.add("MODULE_NAME");
        }};
    }
}
