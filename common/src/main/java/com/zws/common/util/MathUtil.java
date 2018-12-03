package com.zws.common.util;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/23
 */
public class MathUtil {

    /**
     * 辗转相除法求最大公约数
     * @param n1
     * @param n2
     * @return
     */
    public static int getGcdByDivide(Integer n1,Integer n2){
        int max = n1>n2?n1:n2;
        int min = n1<n2?n1:n2;
        if(max%min==0){
            return min;
        }else{
            return getGcdByDivide(min,max%min);
        }
    }

    /**
     * 最小公倍数
     * @param a
     * @param b
     * @return
     */
    public static int getLcm(int a, int b) {
        return a * b / getGcdByDivide(a, b);
    }

    /**
     * 更相减损术求最大公约数
     * @param n1
     * @param n2
     * @return
     */
    public static int getGcdByLess(Integer n1,Integer n2){
        int max = n1>n2?n1:n2;
        int min = n1<n2?n1:n2;

        if(max-min==min){
            return min;
        }else{
            return getGcdByLess(min,max-min);
        }
    }
    
}
