package com.zws.jdk.example.configuration;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/18
 *
 * Jvm配置
 *
 * Trace跟踪参数
 *
 *   打印GC信息：  -verbose:gc
 *               -XX:+PrintGC   注：在TraceClassLoadingjdk1.8中这个参数已经被弃用，请选择-verbose:gc
 *               -XX:+PrintGCDetails   打印GC详细信息   注：在jdk1.8中这个参数已经被弃用 请选择-Xlog:gc*
 *               -XX:+PrintGCTimeStamps  打印CG发生的时间戳
 *               -Xloggc:log/gc.log   指定GC log的位置，以文件输出   注：在jdk1.8中这个参数已经被弃用 请选择-Xlog:gc:log/gc.log
 *   -XX:+TraceClassLoading 监控类的加载
 *   -XX:+PrintClassHistogram 按下Ctrl+Break后，打印类的信息，分别显示：序号、实例数量、总大小、类型
 *
 */
public class TraceConfigurationExample {

    public static void main(String[] args) {

        for(int i = 0;i<100000000;i++){
            byte[] bytes = new byte[1024*1024];
        }
    }
}
