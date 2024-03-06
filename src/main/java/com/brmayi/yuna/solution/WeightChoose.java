package com.brmayi.yuna.solution;

import java.util.Random;

public class WeightChoose {
    int[] pres;
    int sum = 0;
    Random random;
    public WeightChoose(int[] w) {
        random = new Random();
        pres = new int[w.length];
        for(int i=0; i<w.length; i++) {
            sum += w[i];
            pres[i] = sum-1;
        }
    }

    public int pickIndex() {
        int num = random.nextInt(sum);
        int left = 0;
        int right = pres.length - 1;
        int ans = -1;
        while(left<=right) {//求满足条件的最小值
            int mid = left + (right-left)/2;
            if(pres[mid]==num) {
                return mid;
            }
            if(pres[mid]>num) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] w = {1,3,1};
        WeightChoose wc = new WeightChoose(w);
        System.out.println(wc.pickIndex());
    }
}
