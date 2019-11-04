package com.sirc.hbase.Service;

import com.sirc.hbase.base.BaseParam;
import com.sirc.hbase.pojo.ResJson;

public interface SyndataService {
    public ResJson query(BaseParam param);
    public ResJson queryByCondition(BaseParam param);
}
