package com.sirc.hbase.pojo;

import org.apache.ibatis.type.Alias;

@Alias(value = "people")
public class People {
    private String rowkey;
    private String name;
    private String age;

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
