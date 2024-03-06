package com.brmayi.yuna.solution;

import com.brmayi.yuna.solution.util.ListNode;
import com.brmayi.yuna.solution.util.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.brmayi.yuna.solution.util.ArrayToTree.transferArrayToIntTree;
import static com.brmayi.yuna.solution.util.ArrayToTree.transferTreeToString;

public class Trie {
    public static int[] findRightInterval(int[][] intervals) {
        Map<int[], Integer> indexMap = new HashMap<>();
        for(int i=0; i<intervals.length; i++) {
            indexMap.put(intervals[i], i);
        }

        Arrays.sort(intervals, (o1, o2)-> o1[0]-o2[0]);

        int[] ans = new int[intervals.length];
        int end = intervals.length-2;
        for(int i=0; i<=end; i++) {
            int left = i+1;
            int right = end+1;
            int min = -1;
            while(left<=right) {
                int mid = left + (right-left)/2;
                if(intervals[i][1]==intervals[mid][0]) {
                    min = mid;
                    break;
                }
                if(intervals[i][1]<intervals[mid][0]) {
                    min = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = min==-1?-1:indexMap.get(intervals[min]);
        }
        ans[end+1] = -1;

        int[] orginalAns = new int[ans.length];
        for(int i=0; i<ans.length; i++) {
            orginalAns[indexMap.get(intervals[i])] = ans[i];
        }

        return orginalAns;
    }

    public static void main(String[] args) {
//        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
//        System.out.println(partition("abbab"));
//        int[] nums = {5,1,-1,-8,-7,8,-5,0,1,10,8,0,-4,3,-1,-1,4,-5,4,-3,0,2,2,2,4,-2,-4,8,-7,-7,2,-8,0,-8,10,8,-8,-2,-9,4,-7,6,6,-1,4,2,8,-3,5,-9,-3,6,-8,-5,5,10,2,-5,-1,-5,1,-3,7,0,8,-2,-3,-1,-5,4,7,-9,0,2,10,4,4,-4,-1,-1,6,-8,-9,-1,9,-9,3,5,1,6,-1,-2,4,2,4,-6,4,4,5,-5};
//        sortColors(nums);
//        Arrays.binarySearch()
//        System.out.println(Arrays.toString(nums));
//        StringBuilder sb = new StringBuilder();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(3);
//        head.next.next.next.next = new ListNode(2);
//        head.next.next.next.next.next = new ListNode(1);
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(2);
//        root.right.right = new TreeNode(7);
//        int[] nums = {0,1,1,1,0,1,1,0,1};
        int[][] grid = {{1,1},{3,4}};
//        Map<String, Integer> map = new HashMap<>();
//        map.put("1",0);
//        map.put("2",0);
        String[] words = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
//        System.out.println(minMutation("AACCGGTT", "AAACGGTA", bank));
//        System.out.println("a  computer.  Art".length());
        int[] nums = {5,7,7,8,8,10};
        System.out.println(findRightInterval(grid));
    }
}
