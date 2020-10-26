package com.brmayi.yuna.controller;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.time.chrono.MinguoDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GuavaCacheTest {
    private class Ref {
        byte[] bytes = new byte[1024*1024*10];
        int index;

        public Ref(int index) {
            this.index = index;
        }

        public byte[] getBytes() {
            return bytes;
        }

        @Override
        public void finalize() {
            System.out.println("index " + index + "'s " + bytes.length + "is going to be GG");
        }
    }

    @Test
    public void testStrong() throws Exception{
        Cache<Integer, Ref> cache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.DAYS).build();
        for(int i=0; i<10000; i++) {
            Thread.sleep(10000L);
            cache.put(i, new Ref(i));
            System.out.println(cache.getIfPresent(i));
        }
        System.out.println(cache.stats().loadSuccessCount());
    }

    @Test
    public void testSoft() {
        Cache<Integer, Ref> cache = CacheBuilder.newBuilder().softValues().build();
        for(int i=0; i<100; i++) {
            cache.put(i, new Ref(i));
            System.out.println(cache.getIfPresent(i));
        }
    }


    @Test
    public void testWeak() {
        Cache<Integer, Ref> cache = CacheBuilder.newBuilder().weakKeys().weakValues().build();
        for(int i=0; i<100; i++) {
            cache.put(i, new Ref(i));
            System.out.println(cache.getIfPresent(i));
        }
    }

    @Test
    public void  testRawStrong() {
        List<Ref> list = Lists.newArrayList();
        for(int i=0; i<100; i++) {
            list.add(new Ref(i));
            System.out.println(list.get(i));
        }
    }

    @Test
    public void  testRawSoft() throws Exception {
        List<SoftReference<Ref>> list = Lists.newArrayList();
        for(int i=0; i<100; i++) {
            list.add(new SoftReference<>(new Ref(i)));
            System.out.println(list.get(i).get());
            Thread.sleep(100L);
        }
    }


    @Test
    public void  testRawWeak() {
        List<WeakReference<Ref>> list = Lists.newArrayList();
        for(int i=0; i<100; i++) {
            list.add(new WeakReference<>(new Ref(i)));
            System.out.println(list.get(i).get());
        }
    }

    @Test
    public void  testRawPhantom() {
        ReferenceQueue q = new ReferenceQueue();
        List<PhantomReference<Ref>> list = Lists.newArrayList();
        for(int i=0; i<100; i++) {
            list.add(new PhantomReference<>(new Ref(i), q));
            System.out.println(list.get(i).get());
        }
    }
}
