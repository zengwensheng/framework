package com.zws.jdk.example.command;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/28
 *
 * jvm 指令
 *
 *
 *
 *
 */
public class CommandExample {

    /**
     *        0: invokestatic  #2                  // Method spin:()V
     *        3: return
     */
    public static void main(String[] args) {
        spin();
    }


    /**
     *        0: iconst_0
     *        1: istore_0
     *        2: iload_0
     *        3: sipush        1000
     *        6: if_icmpge     15
     *        9: iinc          0, 1
     *       12: goto          2
     *       15: return
     */
    static void spin(){
        int i;
        for(i=0;i<1000;i++){
        }
    }

}
