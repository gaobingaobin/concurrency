package com.example.concurrency.interfaceDemo;

public class test extends abstractClass{
    /**
     * 静态代码块只会执行一次
     */
    static {
        System.out.println("静态代码块");
    }
    {
        System.out.println("非静态代码块");
    }
    public test(int i){
        System.out.println("构造方法 " + i);
    }
    @Override
    public int add() {
        return 0;
    }

    public static void main(String[] args) {
        test  tt = new test(1);
        test  t = new test(2);
        String a = new String("ab"); //a为一个引用
        String b = new String("ab"); //b 为另一个引用，对象内容相同
        String aa = "ab";// 放在常量池中
        String bb = "ab";// 从常量池中查找
        if(aa == bb) // true 指向同一个常量
            System.out.println("aa=bb");
        if (a==b) // false 非同一对象 ，只是对象的内容一样。指向不同的内存地址
            System.out.println("a=b");
        if (a.equals(b)) //
            System.out.println("a EQ b");
        if (a==aa)
            System.out.println("a == aa");

    }
}

