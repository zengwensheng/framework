package com.zws.jdk.example.lexical;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/22
 *
 *
 * java 词法结构
 *
 * Int
 *  0 2 0372 0xDada_Cafe 1996 0x00_FF__00_FF
 *
 * Long
 *  0l 0777L 0x100000000L 2_147_483_648L 0xC0B0L
 *
 * Float
 *  1e1f 2.f .3f 0f 3.14f 6.022137e+23f
 *
 * Double
 *  1e1 2. .3 0.0 3.14 1e-9d 1e137
 *
 * 操作
 *  +=  -=  *=  /=  &=  |=  ^=  %=  <<=  >>=  >>>=
 *
 */
public class LexicalStructExample {
    /**
     * 整数如果太长可以用下划线进行分割,此处是16进制数
     */
    private static int a =0xDada_Cafe;
    private static int b =0xDadaCafe;


    /**
     *  要加ox
     *  private static float c =1.ffffeP+127f;
     */
    private static float d =0x1.ffffeP+127f;

    private static float e=1996;

    /**
     * 不合法的float
     * private static float f = 1996.3;
     */


   // private static int g= 9999e2;

    private static double h = 33e2;

   private static float i = 0x1.fffep-12f;

  //  private static float j = 1.fffep-12f; 不合法e的p次方

    private static long k = 0b1_1_1_0_1;

   // private static long l=0b1_1_1_0_2; 不合法    二进制数字没有2









    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(d);
        System.out.println(e);
        System.out.println(h);
        System.out.println(i);
        System.out.println(k);
    }
}
