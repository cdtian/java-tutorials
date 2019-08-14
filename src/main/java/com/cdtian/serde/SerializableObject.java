package com.cdtian.serde;

import java.io.Serializable;

public class SerializableObject implements Serializable {
    private String name;
    private String addres;

    public SerializableObject(String name, String addres) {
        this.name = name;
        this.addres = addres;
    }

    @Override
    public String toString() {
        return "SerializableObject{" +
                "name='" + name + '\'' +
                ", addres='" + addres + '\'' +
                '}';
    }
}
