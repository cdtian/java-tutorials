package com.cdtian.serde;

import java.io.*;
import java.util.Base64;

public class SerialiableMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableObject serializableObject =
                new SerializableObject("zhangsan", "chengdu");

        String seriStr = SerialiableMain.serMethod(serializableObject);
        System.out.println(seriStr);
        System.out.println(seriStr.length());
        Object object = desMethod(seriStr);
        if (object instanceof SerializableObject) {
            System.out.println(object.toString());
        }
    }

    public static String serMethod(Serializable object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static Object desMethod(String desStr) throws IOException, ClassNotFoundException {
        byte[] decode = Base64.getDecoder().decode(desStr.getBytes());
        ByteArrayInputStream bais = new ByteArrayInputStream(decode);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
}
