package com.idlefish.flutterboost;

import com.idlefish.flutterboost.containers.BoostFlutterActivity.SerializableMap;
import java.util.Map;

public class SerializableCompatUtil {

    private final static String TRUE = "<boolean>:" + Boolean.TRUE;
    private final static String FALSE = "<boolean>:" + Boolean.FALSE;

    private static void compatValue(Map<Object, Object> map) {
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Boolean) {
                if (Boolean.TRUE.equals(value)) {
                    map.put(key, "<boolean>:" + Boolean.TRUE);
                } else if (Boolean.FALSE.equals(value)) {
                    map.put(key, "<boolean>:" + Boolean.FALSE);
                } else {
                    throw new IllegalArgumentException("not support " + value);
                }
            } else if (value instanceof Map) {
                compatSerializableValue((Map<Object, Object>) value);
            }
        }
    }

    public static SerializableMap compatMap(Map<String, Object> map) {
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Boolean) {
                if (Boolean.TRUE.equals(value)) {
                    map.put(key, "<boolean>:" + Boolean.TRUE);
                } else if (Boolean.FALSE.equals(value)) {
                    map.put(key, "<boolean>:" + Boolean.FALSE);
                } else {
                    throw new IllegalArgumentException("not support " + value);
                }
            } else if (value instanceof Map) {
                compatValue((Map<Object, Object>) value);
            }
        }

        SerializableMap serializableMap = new SerializableMap();
        serializableMap.setMap(map);
        return serializableMap;
    }

    private static void compatSerializableValue(Map<Object, Object> map) {
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof String) {
                if (TRUE.equals(value)) {
                    map.put(key, Boolean.TRUE);
                } else if (FALSE.equals(value)) {
                    map.put(key, Boolean.FALSE);
                }
            } else if (value instanceof Map) {
                compatSerializableValue((Map<Object, Object>) value);
            }
        }
    }

    public static Map<String, Object> compatSerializableMap(SerializableMap serializableMap) {
        Map<String, Object> map = serializableMap.getMap();

        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof String) {
                if (TRUE.equals(value)) {
                    map.put(key, Boolean.TRUE);
                } else if (FALSE.equals(value)) {
                    map.put(key, Boolean.FALSE);
                }
            } else if (value instanceof Map) {
                compatSerializableValue((Map<Object, Object>) value);
            }
        }

        return map;
    }

}
