package com.zws.jdk.example.configuration;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/18
 *
 * 如果你要做一个Java的桌面产品，需要绑定JRE，但是JRE又很大，你如何做一下JRE的瘦身呢？
 *
 * 思路：
 *  首先了解到：Java运行主要引赖于bin和Lib目录，bin目录主要存储了java命令和需要的dll
 *  lib目录是java虚拟机要用到的class和配置文件。
 *
 *  bin目录精简的方式：
 * 1、bin目录最主要的工具是java.exe,它用来执行class文件.
 * 如果只是为了单纯运行Java程序的话,其他可执行文件一般都是用不到的(可剔除).
 *
 * 2、 bin目录里面的动态链接库文件dll是java.exe执行class文件过程中调用的.
 * 执行class文件,java.exe需要哪个库文件就加载哪个dll,不需用的可以剔除.
 * 查看java用到那个dll的，可以通过windows的任务管理器，查看进程号，再用其它工具（如360）
 * 查看引用到的dll
 *
 * lib精简方式：
 * 这里面我给出一个精简rt.jar的程序，自己写的.（这里主要是给出了精简rt.jar的程序）
 * 主要思想就是：
 * 1、把程序运行所需要的class文件通过-XX:TraceClassLoading打印到文本文件
 * 2、用自己写的程序把需要的class和rt路径，精简rt存放的路径设置好
 * 3、然后将rt1里面的目录和文件打包成rt.zip,改名为rt.jar，然后替换原来的rt.jar
 * 4、可以达到精简的作用，再将Java.exe和对应的dll copy到相应的目录，
 * 5、写一个批处理命令，用于自带的Java去执行jar包。
 *
 *
 *
 */
public class SlimmingJreExample {


}




class CutJre {
    private String needClazz = "d:\\needClazz.txt";//需要的class
    private String rtPath = "D:\\Program Files\\Java\\jre6\\lib";//rt存放路径
    private String dstRtPath = "D:/cutJar/";//精简后的路径
    private JarFile rtJar;

    public static void main(String[] args) throws Exception {
        CutJre cutJre = new CutJre();
        cutJre.rtJar = new JarFile(cutJre.rtPath + "\\rt.jar");
        cutJre.copyClass("[Loaded sun.reflect.FieldAccessor from sda]");
//		 cutJre.execute();
    }

    private void execute() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(needClazz)));
        String string = br.readLine();
        while (string != null) {
            string = br.readLine();
        }
    }

    private boolean copyClass(String traceStr) throws IOException {
        if (traceStr.startsWith("[Loaded")) {
            String className = traceStr.split(" ")[1];
            //不是rt里面的Jar包，是自己有的
            if(className.contains("zh")){
                return true;
            }
            copyFile(className);
        }
        return false;
    }

    private void copyFile(String className) throws IOException {
        String classFile = className.replace(".", "/") + ".class";
        String classDir = classFile.substring(0,classFile.lastIndexOf("/"));

        File dir=new File(dstRtPath+classDir);
        System.out.println(dir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        JarEntry jarEntry = rtJar.getJarEntry(classFile);
        InputStream ins = rtJar.getInputStream(jarEntry);
        File file = new File(dstRtPath+ classFile);
        System.out.println(file);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        IOUtils.copy(ins, fos);
        ins.close();
        fos.close();

    }
}
