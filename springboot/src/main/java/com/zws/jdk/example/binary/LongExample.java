package com.zws.jdk.example.binary;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/7
 */
public class LongExample {

    private  Long number=0L;

    public static void main(String[] args) {

        LongExample example = new LongExample();
        System.out.println("first:"+example.getFirst());
        System.out.println("second:"+example.getSecond());
        System.out.println("third:"+example.getThird());


        System.out.println();

        example.addFirst(10L);
        example.addSecond(20L);
        example.addThird(30L);


        System.out.println("first:"+example.getFirst());
        System.out.println("second:"+example.getSecond());
        System.out.println("third:"+example.getThird());

    }


    private  Long getFirst() {
        return number&0x1fffff;
    }

    private Long getSecond() {
        return number >> 21&0x1fffff;
    }

    private Long getThird() {
        return number >> 42;
    }


    private void addFirst(Long i) {
        if (i + getFirst() > 2097152) {
            throw new IllegalArgumentException("Too big number");
        }
        number += i;

    }

    private void addSecond(Long i) {
        if (i + getSecond() > 2097152) {
            throw new IllegalArgumentException("Too big number");
        }
        number += (i << 21);

    }

    private void addThird(Long i) {
        if (i + getThird() > 2097152) {
            throw new IllegalArgumentException("Too big number");
        }
         number += (i << 42);
    }
}
