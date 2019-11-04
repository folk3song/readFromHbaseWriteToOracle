package com.sirc.hbase.pojo;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetExtractorImpl implements ResultSetExtractor {

    private boolean flag;
    public ResultSetExtractorImpl(){};

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        flag = resultSet.next();
        return flag;
    }
}
