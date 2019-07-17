package com.cdtian.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

public class ReflectTest1 {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        ReflectTest1 reflectTest1 = new ReflectTest1();
        reflectTest1.getDeclareAnnotation(A.class).forEach(t -> {
            System.out.println(t.annotationType());
        });
        System.out.println("-------------------");
        reflectTest1.getDeclareField(A.class).forEach(t -> {
            System.out.println(t.getName());
        });
        Object object = reflectTest1.reflectConstructor(A.class).get();
        A result;
        if (object instanceof A) {
            result = (A) object;
            result.setDefaultField("default");
            result.setIntField(1);
            result.setPublicField("public");
            result.setPrivateField("private");
            System.out.println("----------before setting---------");
            reflectTest1.getDeclareMethod(A.class).filter(t -> t.getName().startsWith("get")).forEach(
                    t -> {
                        try {
                            System.out.println(t.getName() + "\tresult:\t" + t.invoke(result, null));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
            );

            reflectTest1.getDeclareMethod(A.class).
                    filter(method -> method.getName().toLowerCase().startsWith("set")).
                    forEach(method -> {
//                    System.out.println(method.getParameterTypes()[0].toGenericString());
                        try {
                            if (method.getParameterTypes()[0].toGenericString().endsWith("String")) {
                                method.invoke(result, "randomStr");

                            } else if (method.getParameterTypes()[0].toGenericString().endsWith("Integer")) {
                                method.invoke(result, 1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("----------after setting---------");
            reflectTest1.getDeclareMethod(A.class).filter(t -> t.getName().startsWith("get")).forEach(
                    t -> {
                        try {
                            System.out.println(t.getName() + "\tresult:\t" + t.invoke(result, null));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }


    }

    public Optional reflectConstructor(Class clazz) throws IllegalAccessException, InstantiationException {
        return Optional.ofNullable(clazz.newInstance());
    }


    public Stream<Method> getDeclareMethod(Class clazz) {
        return Stream.of(clazz.getDeclaredMethods());
    }

    public Stream<Field> getDeclareField(Class clazz) {
        return Stream.of(clazz.getDeclaredFields());
    }

    public Stream<Annotation> getDeclareAnnotation(Class clazz) {
        return Stream.of(clazz.getAnnotations());
    }

}

class A {
    private String privateField;
    public String publicField;
    String defaultField;
    Integer intField;

    public String getPrivateField() {
        return privateField;
    }

    public void setPrivateField(String privateField) {
        this.privateField = privateField;
    }

    public String getPublicField() {
        return publicField;
    }

    public void setPublicField(String publicField) {
        this.publicField = publicField;
    }

    public String getDefaultField() {
        return defaultField;
    }

    public void setDefaultField(String defaultField) {
        this.defaultField = defaultField;
    }

    public Integer getIntField() {
        return intField;
    }

    public void setIntField(Integer intField) {
        this.intField = intField;
    }

    @Override
    public String toString() {
        return "A{" +
                "privateField='" + privateField + '\'' +
                ", publicField='" + publicField + '\'' +
                ", defaultField='" + defaultField + '\'' +
                ", intField=" + intField +
                '}';
    }
}


