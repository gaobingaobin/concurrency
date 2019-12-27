package com.example.concurrency;

import java.util.*;

public class ArrayTest {
    public static void main(String[] args) {
        Integer [] arr ={1,2,4,4,2,5};
        ArrayTest h = new ArrayTest();
        h.getTopSum(arr,2,4);
        System.out.println("结果1："+h.getTopSum(arr,2,3));
        int[] nums = {3,2,4};
        System.out.println("结果2："+h.twoSum(nums,6).toString());
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

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int[] result = new int[2];
        for(int i = 0;i<len;i++){
            for(int j = 0;j<len-i-1;j++){
                if(i==j){
                    continue;
                }
                if(nums[i]+nums[j]==target){
                 result = new int[]{i, j};
                 return result;
                }
            }
        }
        return result;
    }
}
