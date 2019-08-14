package com.cdtian.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectToJson {

    public String convertToJSON(Object object) {
        checkSerializable(object);
        init(object);
        return getJSONString(object);
    }

    private void checkSerializable(Object object) {
        if (Objects.isNull(object)) {
            return;
        }
        Class objClazz = object.getClass();
        if (!objClazz.isAnnotationPresent(JsonSerialiable.class)) {
            throw new JsonSerializableException("cannot serializable object");
        }
    }

    private void init(Object object) {
        Class clazz = object.getClass();
        Stream.of(clazz.getMethods()).forEach(
                t -> {
                    if (t.isAnnotationPresent(Init.class)) {
                        t.setAccessible(true);
                        try {
                            t.invoke(object);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private String getJSONString(Object object) {
        Class clazz = object.getClass();
        Map<String, Object> jsonMap = new HashMap<>();
        Stream.of(clazz.getDeclaredFields()).forEach(t -> {
            if (t.isAnnotationPresent(JsonElement.class)) {
                try {
                    t.setAccessible(true);
                    jsonMap.put(getKeys(t), t.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        String jsonStr = jsonMap.entrySet().stream().
                map(entry -> "\"" + entry.getKey() + "\":" + "\"" + entry.getValue() + "\"").collect(Collectors.joining(","));
        System.out.println(jsonStr);
        return "{" + jsonStr + "}";
    }

    private String getKeys(Field field) {
        String values = field.getAnnotation(JsonElement.class).keys();
        field.setAccessible(true);
        return !values.isEmpty() ? values : field.getName();
    }
}
