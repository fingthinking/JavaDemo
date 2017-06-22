package io.here.processor;


import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Author:  柳汝滕
 * Email:   fingthinking@qq.com
 * Created: 2017年06月22日 13时24分
 */
// 方法1,使用注解来定义
// @SupportedSourceVersion(SourceVersion.RELEASE_6) // 支持版本
// @SupportedAnnotationTypes("io.here.annotition.SPI") // 支持的注解类型
public class SPIProcessor extends AbstractProcessor {
    /**
     * 处理方法
     * @param annotations
     * @param roundEnv
     * @return 如果返回true不再要求后续的处理器处理,返回false后续的处理器还会继续处理
     */
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println(annotations);
        for(TypeElement anno : annotations){
            System.out.println(anno.getSimpleName());
        }
        return false;
    }

    // 方法2, 重载函数
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_6;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<String>(){
            {
                add("io.here.annotition.SPI");
            }
        };
    }
}
