package com.sirc.hbase.hbase;

import com.sirc.hbase.base.BaseParam;
import com.sirc.hbase.pojo.ResJson;

import java.util.List;
import java.util.Map;

public interface HbaseQueryDao {
    public ResJson query(BaseParam param);
    public ResJson queryByCondition(BaseParam param);
}
