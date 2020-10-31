根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结

串行GC：
单线程，在GC时，暂停所有的工作线程，简单高效
java -XX:+UseSerialGC -XX:+PrintGCDetails -Xms256m -Xmx256m GCLogAnalysis 共生成对象次数:4367
java -XX:+UseSerialGC -XX:+PrintGCDetails -Xms512m -Xmx512m GCLogAnalysis 共生成对象次数:8300
java -XX:+UseSerialGC -XX:+PrintGCDetails -Xms1g -Xmx1g GCLogAnalysis 9026

并行GC：
多个线程执行GC，在多CPU的环境下吞吐量更大，在minor和full GC的时候都会暂停应用的运行
java -XX:+UseParallelGC -XX:+PrintGCDetails -Xms512m -Xmx512m GCLogAnalysis 共生成对象次数:7532
java -XX:+UseParallelGC -XX:+PrintGCDetails -Xms1g -Xmx1g GCLogAnalysis 共生成对象次数:11286

CMS GC：
系统和垃圾回收一起执行，系统不会暂停
java -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xms256m -Xmx256m GCLogAnalysis 共生成对象次数:4057
java -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xms512m -Xmx512m GCLogAnalysis 共生成对象次数:8914
java -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xms1g -Xmx1g GCLogAnalysis  共生成对象次数:10260

GC GC：
G1把堆分成多个区域，使用多个线程来扫描这些区域，优先扫描最多垃圾的区域
java -XX:+UseG1GC -XX:+PrintGCDetails -Xms256m -Xmx256m GCLogAnalysis 共生成对象次数:2588
java -XX:+UseG1GC -XX:+PrintGCDetails -Xms512m -Xmx512m GCLogAnalysis 共生成对象次数:7885
java -XX:+UseG1GC -XX:+PrintGCDetails -Xms1g -Xmx1g GCLogAnalysis 共生成对象次数:10565