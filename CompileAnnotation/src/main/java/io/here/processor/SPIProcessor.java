package io.here.processor;


import io.here.annotition.SPI;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * Author:  柳汝滕
 * Email:   fingthinking@qq.com
 * Created: 2017年06月22日 13时24分
 */
//方法1,使用注解来定义
@SupportedSourceVersion(SourceVersion.RELEASE_6) // 支持版本
@SupportedAnnotationTypes("io.here.annotition.SPI") // 支持的注解类型
public class SPIProcessor extends AbstractProcessor {
    private static final String PREFIX = "META-INF/services/";

    /**
     * 处理方法
     *
     * @param annotations
     * @param roundEnv
     * @return 如果返回true不再要求后续的处理器处理, 返回false后续的处理器还会继续处理
     */
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 获得该注解
        for (TypeElement anno : annotations) {
            // 获得该被该元素注解的元素
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(anno);
            for (Element ele : elements) {
                // 判断该元素的作用类型
                if (ele.getKind() == ElementKind.CLASS) { // 类上,在此处可以忽略
                    TypeElement typeElement = (TypeElement) ele; // 类型元素
                    SPI spi = typeElement.getAnnotation(SPI.class);
                    String implName = spi.name();  //  实现名
                    if (implName.equals("")) {
                        implName = typeElement.getSimpleName().toString();
                    }
                    String implClazz = typeElement.getQualifiedName().toString(); // 获得全限定名
                    List<? extends TypeMirror> interfaces = typeElement.getInterfaces(); // 获得所有的直接扩展的接口类型
                    for (TypeMirror mirror : interfaces) {
                        String clazz = mirror.toString();
                        try {
                            createOrInput(clazz, implName, implClazz);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return false;
    }


    private void createOrInput(String clazz, String implName, String implClazz) throws IOException {
        File file = getOrNewFile(clazz);// 获取到文件
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // 追加文件
        String line = "\n" + implName + "=" + implClazz; // 写入一行文件
        writer.write(line);
        writer.flush();
        writer.close();

    }


    private File getOrNewFile(String fileName) throws IOException {
        URL url = this.getClass().getClassLoader().getResource("");
        URL l = ClassLoader.getSystemResource("");
        String pathName = url == null ? l.getFile()+ PREFIX : url.getFile() + PREFIX;
        File path = new File(pathName);
        if (path.exists() || path.mkdirs()) {
            return new File(path, fileName);
        }
        throw new FileNotFoundException("Cannot find or create file: " + fileName);
    }
}
