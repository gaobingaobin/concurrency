package com.example.concurrency;

import java.util.*;

public class ArrayTest {
    public static void main(String[] args) {
        Integer [] arr ={1,2,4,4,2,5};
        ArrayTest h = new ArrayTest();
        h.getTopSum(arr,2,4);
        System.out.println("结果："+h.getTopSum(arr,2,3));
    }


    /**
     *  找出数组中 第K大的与第m大的数字之和
     *  示例： Integer [] arr ={1,2,4,4,3,5}; k = 2 m = 4
     *        第2大的出现2次 第4大的出现一次 结果是 10
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public Integer getTopSum(Integer [] arr,Integer k,Integer m){
        List list = Arrays.asList(arr);
        Set set = new HashSet(list);
        Integer limit = set.size();
        if(k>limit){
            throw new ArrayIndexOutOfBoundsException("参数异常");
        }
        if(m>limit){
            throw new ArrayIndexOutOfBoundsException("参数异常");
        }
        Integer[] tempArr = new Integer[set.size()];
        set.toArray(tempArr);
        Arrays.sort(tempArr);
        Integer kNum = tempArr[tempArr.length-k];
        Integer mNum = tempArr[tempArr.length-m];
        Integer kNumSum = 0;
        Integer mNumSum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++){
            if(kNum.equals(arr[i])){
                kNumSum += arr[i];
            }
            if(mNum.equals(arr[i])){
                mNumSum += arr[i];
            }
        }
        return kNumSum+mNumSum;
    }
}
