package com.example.concurrency.reflectionTest;


import com.example.concurrency.reflectionTest.Fool;

/**
 * @Description 反射学习
 * @Author gaobin
 * @Date 2022-05-07 21:25
 */
public class TestReflection {


    public static void main(String[] args) {
        // Fool 的实例对象
        Fool fool = new Fool();
        // 任何一个类都是Class的实例对象
        // 任何一个类都有一个隐含的静态成员变量class
        Class f1 = Fool.class;
        // 已知该类的对象通过getClass方法
        Class f2 = fool.getClass();

        System.out.println(f1==f2);

        // f1 f2 表示了Fool类的类类型 class type
        Class f3 = null;

        {
            try {
                f3 = Class.forName("com.example.concurrency.reflectionTest.Fool");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println(f2==f3);

        try {
            //需要有无参数的构造方法
            Fool fool1 = (Fool) f1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }





}


