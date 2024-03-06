package com.brmayi.yuna.solution.util;

import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ArrayToList {
    public static ListNode transferArrayToIntList(int[] arr) {
        ListNode root = new ListNode();
        ListNode p = root;
        for(int a : arr) {
            p.next = new ListNode();
            p.next.val = a;
            p = p.next;
        }
        return root.next;
    }
}
