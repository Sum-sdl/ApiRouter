package com.sum.router.complier;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by sdl on 2019-05-22.
 */
abstract class BaseProcessor extends AbstractProcessor {
    Elements elementUtils;
    Filer filer;
    Types typeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        elementUtils = env.getElementUtils();
        filer = env.getFiler();
        typeUtils = env.getTypeUtils();

        print(getClass().getSimpleName() + " init");
    }


    void print(Object o) {
        System.out.println(o);
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
}
