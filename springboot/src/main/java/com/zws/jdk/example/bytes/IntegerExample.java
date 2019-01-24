package com.zws.jdk.example.bytes;

import com.zws.jdk.util.BinaryUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/23
 * <p>
 *
 * Integer  4个字节   32位
 *
 * 表示的二进制
 * 小于，等于这个数的10000000000000000000000000000000 为正数
 * 大于10000000000000000000000000000000 为负数
 */
public class IntegerExample {

    public static void main(String[] args) {

        System.out.println(printBinary(0x80000000));

        int n = 99;

        System.out.println(Integer.toBinaryString(n));

        String nBinary =  printBinary(n);
        System.out.println(printBinary(n));
        System.out.println(BinaryUtil.twoToTen(nBinary));



    }


    /**
     * 打印补码
     * @param n
     * @return
     */
    public static String printBinary(Integer n) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            int t = (n & 0x80000000 >>> i) >>> (31 - i);
            str.append(t);
        }
        return str.toString();
    }





}
