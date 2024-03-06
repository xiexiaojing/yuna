package com.brmayi.yuna.solution.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.*;

public class ArrayToTree {
    public static TreeNode transferArrayToIntTree(String arr) {
        String[] s = arr.substring(1, arr.length() - 1).split(",");
        Queue<TreeNode> q = new ArrayDeque<>();
        TreeNode root = new TreeNode(NumberUtils.toInt(s[0]));
        q.offer(root);
        int index = 1;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode treeNode = q.poll();
                if (s[index] != null && !s[index].equals("null")) {
                    treeNode.left = new TreeNode(NumberUtils.toInt(s[index]));
                    q.offer(treeNode.left);
                }
                ++index;
                if (index == s.length) {
                    return root;
                }
                if (s[index] != null && !s[index].equals("null")) {
                    treeNode.right = new TreeNode(NumberUtils.toInt(s[index]));
                    q.offer(treeNode.right);
                }
                ++index;
                if (index == s.length) {
                    return root;
                }
            }
        }
        return root;
    }

    public static String transferTreeToString(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = q.poll();
                if (treeNode.left != null) {
                    q.offer(treeNode.left);
                    ans.add(treeNode.left.val);
                } else {
                    ans.add(null);
                }
                if (treeNode.right != null) {
                    q.offer(treeNode.right);
                    ans.add(treeNode.right.val);
                } else {
                    ans.add(null);
                }
            }
        }
        while (ans.get(ans.size() - 1) == null) {
            ans.remove(ans.size() - 1);
        }
        return ans.toString();
    }
}
