package com.sirc.hbase.Service;

import com.sirc.hbase.pojo.People;

import java.util.List;
import java.util.Map;

public interface MyBatisService {
    public int getPeople(String tableName,String rowkey);
    public void insertPeople(String tableName, List<Map<String, String>> datalist);
}
