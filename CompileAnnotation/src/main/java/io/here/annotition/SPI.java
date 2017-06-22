package io.here.annotition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:  柳汝滕
 * Email:   fingthinking@qq.com
 * Created: 2017年06月22日 13时18分
 *
 * 本注解的功能是在编译的时候自动的生成spi文件
 */
// 编译时注解
@Retention(RetentionPolicy.CLASS)
// 标记在类上
@Target(ElementType.TYPE)
public @interface SPI {

    /**
     * @return 实现类的名字,默认为类名
     */
    String name() default "";
}
