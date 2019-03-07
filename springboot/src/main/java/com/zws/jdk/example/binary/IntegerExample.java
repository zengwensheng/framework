package com.zws.jdk.example.binary;

import com.zws.jdk.util.BinaryUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/23
 * <p>
 * <p>
 * 0x80000000   10000000000000000000000000000000
 * <p>
 * Integer  4个字节   32位
 * <p>
 * 二进制：
 * 整数：原码 = 反码 = 补码
 * 负数：这个数的绝对值原码——》反码--》补码
 */
public class IntegerExample {

    public static void main(String[] args) {

        int n = 100;
        System.out.println(n);

        String binary = printBinary(n);
        System.out.println("Integer(" + n + ")的二进制:" + printBinary(n));
        System.out.println("Integer的二进制（" + binary + "）的Integer:" + binaryToInteger(binary));


    }


    /**
     * Integer 转二进制
     *
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

        //return Integer.toBinaryString(n)
    }


    /**
     * 二进制转Integer
     *
     * @param binary
     * @return
     */
    public static String binaryToInteger(String binary) {
        if (binary.substring(0, 1).equals("1")) {
            return binaryToIntegerByNegative(binary);
        }
        return BinaryUtil.twoToInteger(binary).toString();
    }

    public static String binaryToIntegerByNegative(String binary) {
        StringBuffer binaryBuffer = new StringBuffer();
        for (int i = 0; i < binary.length(); i++) {
            String strBinary = binary.substring(i, i + 1);
            if (strBinary.equals("0")) {
                binaryBuffer.append("1");
            }
            if (strBinary.equals("1")) {
                binaryBuffer.append("0");
            }
        }
        Integer ten = BinaryUtil.twoToInteger(binaryBuffer.toString());
        return "-" + (ten + 1);
    }




}