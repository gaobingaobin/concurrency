package com.example.concurrency.base;

/**
 * @Description
 * @Author gaobin
 * @Date 2021-11-06 15:30
 */
public class Test {
    Integer a = 124;


    public static void main(String[] args) {
        String a = "123";
        String b = new String("123");
        String c = b;
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);
        System.out.println(a.equals(b)); // true
        System.out.println(a.equals(c)); // true
        System.out.println(b.equals(c)); // true
    }
}
