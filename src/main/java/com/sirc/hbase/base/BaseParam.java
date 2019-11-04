package com.sirc.hbase.base;

import org.apache.commons.lang.StringUtils;

public class BaseParam {
    private String tableName;
    private String family = "cf";
    private String qualifiers;
    private String condition;


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFamily(String family) {
        if(StringUtils.isNotEmpty(family))
        {
            this.family = family;
        }
    }

    public void setQualifiers(String qualifiers) {
        this.qualifiers = qualifiers;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTableName() {
        return tableName;
    }

    public String getFamily() {
        return family;
    }

    public String getQualifiers() {
        return qualifiers;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString()
    {
        return "tablename:"+tableName+"family:"+family+"qulifiers"+qualifiers+"condition:"+condition;
    }


}
