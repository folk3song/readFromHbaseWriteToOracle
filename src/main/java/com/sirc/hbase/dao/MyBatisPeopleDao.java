package com.sirc.hbase.dao;

import com.sirc.hbase.pojo.People;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBatisPeopleDao {
    public int getPeople(String tableName,String rowkey);
    public void insertPeople(String tableName,String qualifiers,String values);
}
