2.（必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
MyFeatherClassLoader.java

3.（必做）画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。
1.png
-Xms：jvm启动时分配的内存
-Xmx：jvm运行过程中分配的最大内存
-Xss：jvm启动的每个线程分配的内存大小
-Xmn：年轻代的大小
Metaspace：不在堆空间上，而是在本地内存中
DirectMemory：堆外内存
Xss < Xmn < Xms <= Xmx