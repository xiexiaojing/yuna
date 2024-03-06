package com.brmayi.yuna.solution;

import com.brmayi.yuna.solution.util.ArrayToTree;
import com.brmayi.yuna.solution.util.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.brmayi.yuna.solution.util.ArrayToList.transferArrayToIntList;

public class SnapshotArray {
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> preSum = new HashMap<>();
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode p = head, preHead = pre;
        int sum = 0;
        while(p != null) {
            if(sum == p.val) {
                preSum = new HashMap<>();
                preHead.next = p.next;
                p = p.next;
                sum = 0;
                continue;
            }
            if(preSum.containsKey(sum-p.val)) {
                preSum.get(sum-p.val).next = p.next;
                preSum.remove(sum-p.val);
                p = p.next;
                continue;
            }
            sum += p.val;
            preSum.put(sum, p);
            p = p.next;
        }
        return preHead.next;
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray();
        int[] arr = {1,2,-3,3,1};
        snapshotArray.removeZeroSumSublists(transferArrayToIntList(arr));
    }
}
