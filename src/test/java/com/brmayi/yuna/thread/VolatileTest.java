package com.brmayi.yuna.thread;

import com.brmayi.yuna.model.Version;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

public class VolatileTest {
    private boolean endRun = false;

    @Test
    public void noVolatile() throws Exception {
        Runnable r1 = new Runnable() {
            public void run() {
                int i = 0;
                while (!endRun) {
                    System.out.println("I am still running" + i++);
                }
            }
        };
        Runnable r2 = new Runnable() {
            public void run() {
                endRun = true;
            }
        };
        new Thread(r1).start();
        new Thread(r2).start();
        Thread.sleep(9000);
        System.out.println("end run");
    }


    @Test
    public void run() {
        Version v = new Version("v", "v1.2.22.1");
        System.out.println(v.getHigherVersion());
    }
}