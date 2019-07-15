package com.example.concurrency.example;

public class test {
    public static void main(String[] args) {
        int[] nums= {1,3,2,5,6,6};
        int temp ;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]>nums[i]){
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }

            }
        }
        for (int i=0;i<nums.length;i++){
            System.out.println(nums[i]+"\t");
        }

    }
}
