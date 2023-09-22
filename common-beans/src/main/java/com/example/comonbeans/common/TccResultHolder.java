package com.example.comonbeans.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TccResultHolder {
    private static Map<Class<?>, Map<String, String>> map = new ConcurrentHashMap<>();

    public static void setResult(Class<?> actionClass, String xid, String v) {
        Map<String, String> results = map.get(actionClass);
        if (results == null) {
            synchronized (map) {
                if (results == null) {
                    results = new ConcurrentHashMap<>();
                    map.put(actionClass, results);
                }
            }
        }
        results.put(xid, v);
    }

    public static String getResult(Class<?> actionClass, String xid) {
        Map<String, String> result = map.get(actionClass);
        if (result != null) {
            return result.get(xid);
        }
        return null;
    }

    public static void removeResult(Class<?> actionClass, String xid) {
        Map<String, String> result = map.get(actionClass);
        if (result != null) {
            result.remove(xid);
        }
    }

}
