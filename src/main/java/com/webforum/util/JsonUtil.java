package com.webforum.util;

import java.lang.reflect.Field;
import java.util.List;

public class JsonUtil {
    public static String jsonObject(Object obj) {
        if (obj == null) {
            return "null";
        }
        StringBuilder jsonObj = new StringBuilder("{");
        try {
            Class<?> sc = obj.getClass();
            Field[] fields = sc.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(obj);
                jsonObj.append("\"");
                jsonObj.append(name);
                jsonObj.append("\":");
                if (value instanceof String) {
                    jsonObj.append("\"");
                    jsonObj.append(value);
                    jsonObj.append("\"");
                } else if (value instanceof Integer) {
                    jsonObj.append(value);
                } else {
                    jsonObj.append(jsonObject(value));
                }
                if (i < fields.length - 1) {
                    jsonObj.append(",");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            jsonObj.append("}");
        }
        return jsonObj.toString();
    }

    public static String jsonArray(List list) {
        StringBuilder jsonArr = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            jsonArr.append(jsonObject(list.get(i)));
            if (i < list.size()-1) {
                jsonArr.append(",");
            }
        }
        jsonArr.append("]");
        return jsonArr.toString();
    }
}
