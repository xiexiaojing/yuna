package com.brmayi.yuna.model;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Comparator;
import java.util.Spliterator;

import static org.apache.commons.lang.StringUtils.EMPTY;

@Data
public class Version implements Comparator<String> {
    private final static String VERSION_PREFIX = "v";
    private final static String SPLITER_DOT = ".";

    private String lowerVersion;
    private String higherVersion;

    public Version(String o1, String o2) {
        if (compare(o1, o2) > NumberUtils.INTEGER_ZERO) {
            higherVersion = o1;
            lowerVersion = o2;
        } else {
            higherVersion = o2;
            lowerVersion = o1;
        }
    }

    @Override
    public int compare(String o1, String o2) {
        String v1[] = StringUtils.split(o1.replace(VERSION_PREFIX, EMPTY), SPLITER_DOT);
        String v2[] = StringUtils.split(o2.replace(VERSION_PREFIX, EMPTY), SPLITER_DOT);
        int min = Math.min(v1.length, v2.length);
        for (int i = 0; i < min; i++) {
            int n1 = NumberUtils.toInt(v1[i]);
            int n2 = NumberUtils.toInt(v2[i]);
            if (n1 != n2) {
                return n1 - n2;
            }
        }
        return v1.length - v2.length;
    }

}
