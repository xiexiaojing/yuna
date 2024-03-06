package com.brmayi.yuna.solution;

import java.util.*;

public class LRUCache {
    static int refreshCount = 0;
    public static int orangesRotting(int[][] grid) {
        int total = grid.length*grid[0].length;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]==1) {
                    refreshCount++;
                }
                if(grid[i][j]==0) {
                    total--;
                }
            }
        }

        int minutes = 2;
        while(refreshCount>0) {
            int lastRefreshCount = refreshCount;
            for(int i=0; i<grid.length; i++) {
                for(int j=0; j<grid[0].length; j++) {
                    if(grid[i][j]==minutes) {
                        dfs(grid, i, j, minutes+1);
                    }
                }
            }
            if(lastRefreshCount==refreshCount) {
                return -1;
            }
            minutes++;
        }

        return minutes-2;
    }

    private static void dfs(int[][] grid, int i, int j, int minutes) {
        if(i>0 && grid[i-1][j]==1) {
            dealGrid(grid, i-1, j, minutes);
        }
        if(i<grid.length-1 && grid[i+1][j]==1) {
            dealGrid(grid, i+1, j, minutes);
        }
        if(j>0 && grid[i][j-1]==1) {
            dealGrid(grid, i, j-1, minutes);
        }
        if(j<grid[0].length-1 && grid[i][j+1]==1) {
            dealGrid(grid, i, j+1, minutes);
        }
    }

    private static void dealGrid(int[][] grid, int i, int j, int minutes) {
        grid[i][j] = minutes;
        refreshCount--;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> pres = new HashMap<>();
        for(int i=0; i<prerequisites.length; i++) {
            pres.computeIfAbsent(prerequisites[i][0], k->new HashSet<Integer>()).add(prerequisites[i][1]);
        }

        Set<Integer> courses = new HashSet<>();
        for(int i=0; i<numCourses; i++) {
            if(!pres.containsKey(i)) {
                courses.add(i);
            }
        }
        if(courses.size()==0) {
            return false;
        }

        int cSize;
        while((cSize = courses.size()) < numCourses) {
            Iterator<Integer> preIterator = pres.keySet().iterator();
            while(preIterator.hasNext()) {
                Integer pre = preIterator.next();
                Set<Integer> onePres = pres.get(pre);
                Iterator<Integer> oneIterator = onePres.iterator();
                while(oneIterator.hasNext()) {
                    Integer one = oneIterator.next();
                    if(courses.contains(one)) {
                        oneIterator.remove();
                        if(onePres.size()==0) {
                            preIterator.remove();
                            courses.add(pre);
                            break;
                        }
                    }
                }
            }
            if(cSize==courses.size()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1000000000);
//        root.left = new TreeNode(1000000000);
////        root.right = new TreeNode(1);
//        root.left.left = new TreeNode(294967296);
////        root.left.right = new TreeNode(2);
////        root.right.left = new TreeNode(1000000000);
////        root.right.right = new TreeNode(-1);
//        root.left.left.left = new TreeNode(1000000000);
////        root.left.left.right = new TreeNode(1);
////        root.left.right.left = new TreeNode(-1);
////        root.left.right.right = new TreeNode(0);
////        root.right.left.left = new TreeNode(-1);
////        root.right.left.right = new TreeNode(0);
////        root.right.right.left = new TreeNode(1);
////        root.right.right.right = new TreeNode(0);
//        root.left.left.left.left = new TreeNode(1000000000);
//        root.left.left.left.left.left = new TreeNode(1000000000);
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
    }
}
