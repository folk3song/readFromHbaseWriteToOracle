package com.sirc.hbase.hbase.api;

import org.springframework.dao.UncategorizedDataAccessException;


public class HbaseSystemException extends UncategorizedDataAccessException {

    public HbaseSystemException(Exception cause) {
        super(cause.getMessage(), cause);
    }

    public HbaseSystemException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }
}
