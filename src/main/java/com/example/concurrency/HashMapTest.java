package com.example.concurrency;

import java.util.*;

public class HashMapTest {
    public static void main(String[] args) {
        Integer [] arr ={1,2,4,4,3,5};
        HashMapTest h = new HashMapTest();
        h.getTopSum(arr,2,4);
        System.out.println("结果："+h.getTopSum(arr,2,4));
    }

    public Integer getTopSum(Integer [] arr,Integer k,Integer m){
        List list = Arrays.asList(arr);
        Set set = new HashSet(list);
        Integer[] tempArr = new Integer[set.size()];
        set.toArray(tempArr);
        Arrays.sort(tempArr);
        Integer k_num = tempArr[tempArr.length-k-1];
        Integer m_num = tempArr[tempArr.length-m-1];
        Integer k_num_sum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++){
            if(k_num.equals(arr[i])){
                k_num_sum += arr[i];
            }
        }
        return k_num_sum+m_num;

    }
}
