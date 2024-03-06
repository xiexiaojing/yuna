package com.brmayi.yuna.solution;

import com.brmayi.yuna.solution.util.ListNode;

import java.util.*;

public class SubarraySumSolution {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int min = nums1[0]+nums2[0];
        int max = nums1[nums1.length-1]+nums2[nums2.length-1];

        while(min<max) {
            int mid = ((min+max)>>1);
            int res = check(nums1, nums2, mid, k);
            if(res==0) {
                min = mid;
                break;
            }
            if(res==-1) {
                if(min == mid) {
                    min++;
                } else {
                    min = mid;
                }
            } else {
                max = mid;
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<List<Integer>> lastAns = new ArrayList<>();
        for(int i=0; i<nums1.length; i++) {
            int j=0;
            while(j<nums2.length && nums1[i]+nums2[j]<=min) {
                List<Integer> oneAns = new ArrayList<>();
                oneAns.add(nums1[i]);
                oneAns.add(nums2[j]);
                if(nums1[i]+nums2[j]==min) {
                    lastAns.add(oneAns);
                } else {
                    ans.add(oneAns);
                }
                j++;
            }
        }
        if(lastAns.size()>0) {
            ans.addAll(lastAns.subList(0, k-ans.size()));
        }
        return ans;
    }

    private static int check(int[] nums1, int[] nums2, int m, int k) {
        int count = 0;
        for(int i=0; i<nums1.length; i++) {
            int j=nums2.length-1;
            while(j>=0 && nums1[i]+nums2[j]>m) {
                j--;
            }
            count += (j+1);
            if(count>k) {
                return 1;
            }
        }
        return count==k?0:-1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        System.out.println(sortList(head));

    }

    public static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private static ListNode sortList(ListNode head, ListNode tail) {
        if(head.next==tail) {
            head.next = null;
            return head;
        }

        ListNode fast = head, slow = head;
        while(fast != tail) {
            if(fast.next == tail) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode h1 = sortList(head, slow);
        ListNode h2 = sortList(slow, tail);
        ListNode preHead = new ListNode();
        ListNode pre = preHead;
        while(h1 != null && h2 != null) {
            if(h1.val<h2.val) {
                pre.next = h1;
                h1 = h1.next;
            } else {
                pre.next = h2;
                h2 = h2.next;
            }

            pre = pre.next;
        }

        if(h1 != null) {
            pre = h1;
        }
        if(h2 != null) {
            pre = h2;
        }

        return preHead.next;
    }
}
