package com.sirc.hbase.Service.impl;

import com.sirc.hbase.Service.SyndataService;
import com.sirc.hbase.base.BaseParam;
import com.sirc.hbase.hbase.GenericHbaseTemplate;
import com.sirc.hbase.pojo.ResJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyndataServiceImpl implements SyndataService {

    @Autowired
    GenericHbaseTemplate hbaseTemplate;


    @Override
    public ResJson query(BaseParam param) {
        return hbaseTemplate.query(param);
    }

    @Override
    public ResJson queryByCondition(BaseParam param)
    {
        return hbaseTemplate.queryByCondition(param);
    }


}
