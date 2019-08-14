package com.cdtian.reflect;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

public class ReflectTest2 {
    public static void main(String[] args) throws IntrospectionException {
        PropertyDescriptor[] propDescArr = Introspector.getBeanInfo(A.class, Object.class).getPropertyDescriptors();
        Arrays.stream(propDescArr).forEach(t -> {
            System.out.println(t.getName() + "\t" + t.getDisplayName() + "\t" +
                    t.getShortDescription() + "\t" + t.getPropertyType() + "\t" +
                    t.getPropertyEditorClass() + "\t" + t.getReadMethod() + "\t" + t.getWriteMethod());
        });


    }
}
