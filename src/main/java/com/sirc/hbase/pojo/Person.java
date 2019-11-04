package com.sirc.hbase.pojo;




public class Person {
    private String rowkey;
    private String name;
    private String age;

    public String getRowkey() {
        return rowkey;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
