package com.sum.router.complier;

import com.google.auto.service.AutoService;

import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Created by sdl on 2019-05-22.
 */
//注册APT
@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.sum.router.annotation.ApiRouter","com.sum.router.annotation.ApiImpl"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedOptions({ProcessorConfig.MODULE_NAME, ProcessorConfig.MODULE_PACKAGE})
public class ApiProcessor extends BaseProcessor {

    private AbstractCreateClass mClassCreate;

    //初始化
    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        Map<String, String> options = processingEnv.getOptions();
        print("module->" + options.get("MODULE_NAME"));
        //构造方案生成类
        mClassCreate = new RouterCreateOld(env);
//        mClassCreate = new RouterCreatePoet(env);

    }

    //开始文件
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //构造类
        return mClassCreate.process(annotations, roundEnv);
    }
}
