package com.sirc.hbase.Service.impl;

import com.sirc.hbase.Service.MyBatisService;
import com.sirc.hbase.dao.MyBatisPeopleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class MyBatisServiceImpl implements MyBatisService {
    @Autowired
    private MyBatisPeopleDao myBatisPeopleDao = null;


    @Override
    public int getPeople(String tableName, String rowkey) {
        return myBatisPeopleDao.getPeople(tableName,rowkey);
    }

    @Override
    public void insertPeople(String tableName, List<Map<String, String>> datalist) {
        for (Map<String,String> map:datalist)
        {
            String rowkey = map.get("rowkey");

            int count = myBatisPeopleDao.getPeople(tableName,rowkey);

            if(count > 0)
                continue;
            String columnKey = "";
            String columnValue = "";
            for(Map.Entry<String,String> entry:map.entrySet())
            {

                columnKey += entry.getKey()+",";
                columnValue = columnValue+"'"+entry.getValue()+"',";

            }
            columnKey = columnKey.substring(0,columnKey.length()-1);
            System.out.println(columnKey);
            columnValue = columnValue.substring(0,columnValue.length()-1);
            System.out.println(columnValue);

            myBatisPeopleDao.insertPeople(tableName,columnKey,columnValue);
            System.out.println("update end");
        }

    }


}
