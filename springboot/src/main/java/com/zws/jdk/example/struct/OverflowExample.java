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
 * 实践测试下来没有效果
 * 原因可能是：
 *     为了提高代码效率，被volatile修饰的变量，编译器在判断只是在单线程占有的情况下，会取消volatile修饰，所以还是有工作内存
 *
 */
public class OverflowExample {

    private volatile int i=0;
    private volatile int b=0;
    private volatile int c=0;

//	private  int i=0;
//	private  int b=0;
//	private  int c=0;

    public static void main(String[] args) {
        OverflowExample o=new OverflowExample();
        try {
            o.deepTest();
        } catch (Throwable e) {
            System.out.println("over flow deep:"+o.i);
            e.printStackTrace();
        }
    }
    private void deepTest() {
        ++i;
        ++b;
        ++c;
        deepTest();
    }
}
