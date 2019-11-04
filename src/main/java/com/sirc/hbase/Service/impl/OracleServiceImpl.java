package com.sirc.hbase.Service.impl;

import com.sirc.hbase.Service.OracleService;
import com.sirc.hbase.dao.MyBatisPeopleDao;
import com.sirc.hbase.dao.OracleDao;
import com.sirc.hbase.pojo.ResultSetExtractorImpl;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OracleServiceImpl implements OracleService {

    @Autowired
    private OracleDao oracleDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MyBatisPeopleDao myBatisPeopleDao;

    @Override
    public void update(String tableName,List<Map<String, String>> datalist) {
        System.out.println("update start");
        List<Object> valueList = new ArrayList<Object>();
        String qualifiers = null;
        String columnKey = "";
        Object columnValue = null;
        //String sql = "select * from "+tableName+" where rowkey = ";
        boolean flag;
        for (Map<String,String> map:datalist)
        {
            String rowkey = "'"+map.get("rowkey")+"'";
            //System.out.println(rowkey);
            //String query = sql + rowkey;
            //System.out.println(query);
            //flag = (boolean)jdbcTemplate.query(query,new ResultSetExtractorImpl());
            //System.out.println(flag);
            int count = myBatisPeopleDao.getPeople(tableName,rowkey);
            //if(flag==true)
            if(count > 0)
                continue;
            for(Map.Entry<String,String> entry:map.entrySet())
            {
                if(qualifiers == null)
                {
                     columnKey += entry.getKey()+",";
                }

                columnValue = entry.getValue();
                valueList.add(columnValue);
            }
            qualifiers = columnKey;
            System.out.println("update end");
            oracleDao.update(tableName,qualifiers,valueList);
            valueList.clear();
        }


    }

}
