package com.sirc.hbase.Service;

import java.util.List;
import java.util.Map;

public interface OracleService {
    public void update(String tableName,List<Map<String, String>> datalist);
}
