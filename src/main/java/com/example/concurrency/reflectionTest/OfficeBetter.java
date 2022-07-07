package com.example.concurrency.reflectionTest;

/**
 * @Description
 * @Author gaobin
 * @Date 2022-05-09 10:21
 */
public class OfficeBetter {


      void officeStart(String name){
          try {
              Class c = Class.forName(name);
              OfficeAble officeAble = (OfficeAble) c.newInstance();
              officeAble.start();

          } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
              e.printStackTrace();
          }
      }


    public static void main(String[] args) {
    OfficeBetter officeBetter = new OfficeBetter();
    officeBetter.officeStart("com.example.concurrency.reflectionTest.Word");

    }
}


