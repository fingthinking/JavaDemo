## 编译时注解

1. 编写注解类型:io.here.annotition.SPI
2. 编写处理工具类: SPIProcessor,继承自AbstractProcessor
3. 在META-INF.services目录下,创建javax.annotation.processing.Processor文件,内容为io.here.processor.SPIProcessor
4. 在maven的pom文件中,compile-plugin增加`<compilerArgument>-proc:none</compilerArgument>`,否则会报错.

注意,编译时注解需要单独打jar包给其他模块使用,在该模块内无法使用.