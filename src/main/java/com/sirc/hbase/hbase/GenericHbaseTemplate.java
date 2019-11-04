package com.sirc.hbase.hbase;

import com.sirc.hbase.base.BaseParam;
import com.sirc.hbase.hbase.api.HbaseTemplate;
import com.sirc.hbase.hbase.api.TableCallback;
import com.sirc.hbase.pojo.ConditionParser;
import com.sirc.hbase.pojo.ResJson;
import com.sirc.hbase.util.Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.apache.hadoop.hbase.filter.*;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class GenericHbaseTemplate extends HbaseTemplate implements HbaseQueryDao {

    @Autowired
    private Utils utils;

    public GenericHbaseTemplate(Configuration configuration)
    {
        super(configuration);
    }

    @Override
    public  ResJson query(BaseParam param) {
        Assert.notNull(param.getTableName(),"tableName must not be null");
        return this.execute(param.getTableName(), new TableCallback<ResJson>() {
            @Override
            public ResJson doInTable(Table table) throws Throwable {
                System.out.println("do in table name");
                ResJson resJson = new ResJson();
                List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
                Scan scan = new Scan();
                System.out.println("增加列族之前");
                scan.addFamily(Bytes.toBytes(param.getFamily()));
                System.out.println("获取列之前");
                String[] qualifiers = null;
                if(param.getQualifiers()!=null)
                {
                    System.out.println("进入判断");
                    qualifiers = param.getQualifiers().split(",");
                }
                PageFilter pageFilter = new PageFilter(20);
                scan.setFilter(pageFilter);
                ResultScanner resultScanner = table.getScanner(scan);
                System.out.println("得到结果");
                for(Result result : resultScanner)
                {
                    Map<String, Object> map = utils.getResultMap(param.getFamily(), qualifiers, result);
                    dataList.add(map);
                }
                 resJson.addAttribute("datalist",dataList);
                 return resJson;

            }
        });
    }

    @Override
    public ResJson queryByCondition(BaseParam param) {
        Assert.notNull(param.getTableName(),"tableName must not be null");
        Assert.notNull(param.getFamily(),"family must not be null");
        Assert.notNull(param.getCondition(), "condition must not be null");

        return this.execute(param.getTableName(), new TableCallback<ResJson>() {
            @Override
            public ResJson doInTable(Table table) throws Throwable {
                ResJson resJson = new ResJson();
                List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
                Scan scan = new Scan();
                scan.addFamily(Bytes.toBytes(param.getFamily()));
                List<Filter> filters = new ConditionParser(param.getCondition(),param.getFamily()).getFilter();
                FilterList filterList = new FilterList(filters);
                scan.setFilter(filterList);
                System.out.println("scan 创建前"+System.currentTimeMillis());
                ResultScanner resultScanner = table.getScanner(scan);
                System.out.println("scan 创建后"+System.currentTimeMillis());

                String[] qualifiers = null;
                if(param.getQualifiers()!=null)
                {
                    qualifiers = param.getQualifiers().split(",");
                }

                for(Result result : resultScanner)
                {
                    Map<String, Object> map = utils.getResultMap(param.getFamily(), qualifiers, result);
                    dataList.add(map);
                }
                resJson.addAttribute("datalist",dataList);
                return resJson;
            }
        });
    }



}
