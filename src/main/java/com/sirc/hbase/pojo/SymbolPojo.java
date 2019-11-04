package com.sirc.hbase.pojo;

import org.apache.hadoop.hbase.filter.CompareFilter;

public class SymbolPojo {
    private String[] keys;
    private CompareFilter.CompareOp compareOp;

    public String[] getKeys() {
        return controlkey();
    }

    public CompareFilter.CompareOp getCompareOp() {
        return compareOp;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public void setCompareOp(CompareFilter.CompareOp compareOp) {
        this.compareOp = compareOp;
    }

    private String[] controlkey()
    {
        keys[0] = keys[0].substring(1,keys[0].length()-1);
        keys[1] = keys[1].substring(1,keys[1].length()-1);
        return keys;
    }
}
