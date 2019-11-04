package com.sirc.hbase.controller;


import com.sirc.hbase.Service.MyBatisService;
import com.sirc.hbase.Service.OracleService;
import com.sirc.hbase.Service.SyndataService;
import com.sirc.hbase.base.BaseParam;
import com.sirc.hbase.pojo.CustomMap;
import com.sirc.hbase.pojo.People;
import com.sirc.hbase.pojo.ResJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/syndata")
public class SyndataController {

    @Autowired
    private SyndataService syndataService;

    @Autowired
    private OracleService oracleService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MyBatisService myBatisService;

    @RequestMapping(value = "/query",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Map<String, String>> query(@RequestParam String tableName,@RequestParam(required = false) String family,@RequestParam(required = false) String qualifiers,@RequestParam(required = false) String condition)
    {
        long time1 = System.currentTimeMillis();
        BaseParam baseParam = new BaseParam();
        ResJson resJson = null;
        baseParam.setTableName(tableName);
        System.out.println("开始");
        if(family != null)
        {
            baseParam.setFamily(family);
        }
        if(qualifiers != null)
        {
            baseParam.setQualifiers(qualifiers);
        }
        if(condition != null)
        {
            baseParam.setCondition(condition);
            resJson = syndataService.queryByCondition(baseParam);
        }else {
            System.out.println(baseParam);
            resJson = syndataService.query(baseParam);
        }
        CustomMap comMap =  resJson.getAttribute();
        List<Map<String, String>> datalist = (List<Map<String, String>>)comMap.get("datalist");
        //oracleService.update(tableName,datalist);
        myBatisService.insertPeople(tableName,datalist);

        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        return datalist;
    }

    @RequestMapping("/mybatis")
    public int getPeople(String tableName,String rowkey)
    {
        int count = myBatisService.getPeople(tableName,rowkey);
        return  count;

    }

}
