package com.sirc.hbase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OracleDaoImpl implements  OracleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void update(String tableName, String qualifiers, List<Object> valueList) {

        qualifiers = qualifiers.substring(0,qualifiers.length()-1);
        String sql = "insert into "+tableName+" (" +qualifiers+") values(";
        String values = "";
        for(Object value : valueList)
        {
            values = values + "'"+value+"',";
        }
        values = values.substring(0,values.length()-1)+")";
        sql = sql + values;
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }
}
