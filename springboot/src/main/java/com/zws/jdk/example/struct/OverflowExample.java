package com.zws.jdk.example.struct;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/15
 *
 * 你能想到有什么办法，可以让一个程序的函数调用层次变的更深。比如，你在一个递归调用中，发生了stack的溢出，你可以做哪些方面的尝试，使系统尽量不溢出？
 *
 * 首先了解到线程在调用每个方法的时候，都会创建相应的栈，在退出方法的时候移出栈桢，并且栈是私用的，也需要占用空间，所以让一个程序的函数调用层次变的更深
 *     减少栈贞的空间很必要。或者增大线程的线的大小。
 *     通过volatile增加调用层次深度。线程会对一个没有volatile的变量进行临时存储，这就导致线程栈的空间增大，如果对一个变量增加volatile修饰，可以适当增加深度
 *
 *
 */
public class OverflowExample {



    private  static byte[] b = new byte[1000];
	private  static int  count=0;

    /**
     *  -Xss100k
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws  Exception {
        OverflowExample o=new OverflowExample();
        try {

            //o.deepTest1(0,0,0);
            //o.deepTest2();
        } catch (StackOverflowError e) {
            System.out.println("over flow deep:"+o.count);
        }
    }


    private void deepTest1(int c,int b,int a) {
        ++c;
        ++b;
        ++a;
        ++count;
        deepTest1(c,b,a);
    }

    private void deepTest2() {
        ++count;
        deepTest2();
    }

}
