package com.sirc.hbase.dao;

import java.util.List;

public interface OracleDao {
    public void update(String tableName, String qualifiers, List<Object> valueList);
}
