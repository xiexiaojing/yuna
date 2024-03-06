package com.brmayi.yuna.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TimeMap {
    class TimeValue {
        int timestamp;
        String value;
        TimeValue(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }
    Map<String, List<TimeValue>> map = new HashMap<>();
    public TimeMap() {
    }

    public void set(String key, String value, int timestamp) {
        TimeValue tv = new TimeValue(timestamp, value);
        map.computeIfAbsent(key, (Function<? super String, ? extends List<TimeValue>>) new ArrayList<TimeValue>()).add(tv);
    }

    public String get(String key, int timestamp) {
        List<TimeValue> tvs = map.get(key);
        if(tvs==null) {
            return "";
        }
        int left = 0;
        int right = tvs.size()-1;
        String max = "";
        while(left<=right) {
            int mid = (right-left)/2+left;
            TimeValue tv = tvs.get(mid);
            if(tv.timestamp==timestamp) {
                return tv.value;
            }
            if(tv.timestamp > timestamp) {
                right = mid - 1;
            } else {
                left = mid + 1;
                max = tv.value;
            }
        }
        return max;
    }
}
