package com.zws.jdk.example.classloader;

import com.zws.jdk.util.BinaryUtil;
import com.zws.jdk.util.ClassToBytes;
import sun.misc.Launcher;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/21
 */
public class OrderClassLoader extends  ClassLoader {
    private String path;   //类的加载路径
    private String name;   //类加载器的名字

    public OrderClassLoader() {}
    public OrderClassLoader(String path,String name){
        this.path = path;
        this.name = name;
    }

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        byte[] b = ClassToBytes.loadClassBytes(path+name.replace(".","/")+".class");
        if(b==null){
            return getParent().loadClass(name);
        }
       return super.defineClass(name,b,0, b.length);
    }




    public String getName() {
        return name;
    }

    public void setPath(String path) {
        this.path = path;
    }



}
