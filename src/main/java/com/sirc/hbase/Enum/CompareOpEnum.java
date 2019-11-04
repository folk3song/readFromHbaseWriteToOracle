package com.sirc.hbase.Enum;

import org.apache.hadoop.hbase.filter.CompareFilter;

public enum CompareOpEnum {
    GREATER_OR_EQUAL(">=", CompareFilter.CompareOp.GREATER_OR_EQUAL),
    GREATER(">", CompareFilter.CompareOp.GREATER),
    LESS_OR_EQUAL("<=",CompareFilter.CompareOp.LESS_OR_EQUAL),
    LESS("<",CompareFilter.CompareOp.LESS),
    EQUAL("=",CompareFilter.CompareOp.EQUAL),
    NOT_EQUAL("<>", CompareFilter.CompareOp.NOT_EQUAL);

    private String key;
    private CompareFilter.CompareOp value;
    private CompareOpEnum(String key,CompareFilter.CompareOp value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public CompareFilter.CompareOp getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(CompareFilter.CompareOp value) {
        this.value = value;
    }
}
