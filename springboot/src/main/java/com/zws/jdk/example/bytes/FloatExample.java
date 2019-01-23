package com.zws.jdk.example.bytes;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/23
 *
 * 浮点数在二进制中的表示以及计算方式
 *
 * 定点数：小数点固定在某个位置上的数据。 就好像 0.0000001 ，0.0001111；
 * 浮点数：小数点位置可以浮动的数据。就像数学中的 1222.2*10^3也可以表示为1.2222*10^6；
 *
 *
 * 浮点数表达式：N=M*R^E；
 *   n ：浮点数  m:尾数（mantissa）  E：阶码，指数（exponent） ，R为阶的基数，也就是底数啦，通常为2，8，16
 *
 * IEEE754标准规定，浮点数由“符号”、“指数”和“尾数”3部分构成：
 *   因为浮点数4个字节，所以有32位
 *   第一位表示：符号（+-）
 *   第二位到第九位表示：指数 （E）
 *   其余位：尾数（M）
 *   R：2
 *
 *  100.2f 的二进制  0 10000101 10010000110011001100110
 *
 *
 *
 */
public class FloatExample {


    public static void main(String[] args) {
        System.out.println( printComplementCode(100.2f));


    }


    public static String printComplementCode(float f){
        int val = Float.floatToIntBits(f);
        return f>0?"0"+Integer.toBinaryString(val):Integer.toBinaryString(val);
    }
}
