## 编译时注解

1. 编写注解类型:io.here.annotition.SPI
2. 编写处理工具类: SPIProcessor,继承自AbstractProcessor
3. 在META-INF.services目录下,创建javax.annotation.processing.Processor文件,内容为io.here.processor.SPIProcessor