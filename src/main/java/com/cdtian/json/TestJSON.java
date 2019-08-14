package com.cdtian.json;

public class TestJSON {
    public static void main(String[] args) {
        ObjectToJson objectToJson = new ObjectToJson();
        Person person = new Person();
        person.setAddress("chengdu");
        person.setName("dahuang");
        person.setAge(22);
        person.setCompany("lenovo");
        System.out.println("-----------");
        System.out.println(objectToJson.convertToJSON(person));
    }
}
