package com.zws.jdk.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/21
 */
public interface ClassToBytes {

     static byte[] loadClassBytes(String name){
        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(name));
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return cLassBytes;
    }
}
