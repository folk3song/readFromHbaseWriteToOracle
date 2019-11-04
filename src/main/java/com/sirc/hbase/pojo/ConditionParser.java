package com.sirc.hbase.pojo;

import com.sirc.hbase.Enum.CompareOpEnum;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;

import java.util.ArrayList;
import java.util.List;

public class ConditionParser {
    private String family;
    private String condition;

    public ConditionParser(String condition,String family)
    {
        this.condition = condition;
        this.family = family;
    }

    public String getFamily() {
        return family;
    }

    public String getCondition() {
        return condition;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    private  List<Filter> getSymbol(List<String> compareList, List<String> normList,String family)
    {
        List<Filter> filters = new ArrayList<Filter>();
        SingleColumnValueFilter singleColumnValueFilter = null;

        for (String norm: normList)
        {
            CompareFilter.CompareOp compareOp = null;
            String [] fields = norm.split(":");
            fields[0] = fields[0].substring(1,fields[0].length()-1);
            fields[1] = fields[1].substring(1,fields[1].length()-1);
            singleColumnValueFilter = new SingleColumnValueFilter(family.getBytes(),fields[0].getBytes(), CompareFilter.CompareOp.EQUAL,fields[1].getBytes());
            singleColumnValueFilter.setFilterIfMissing(true);
            filters.add(singleColumnValueFilter);
        }

        for(String compare : compareList)
        {
            CompareFilter.CompareOp compareOp= null;
            String [] fields = null;
            SymbolPojo symbolManager = getSymbolManager(compare);
            if(symbolManager != null)
            {
                fields = symbolManager.getKeys();
                compareOp = symbolManager.getCompareOp();
                singleColumnValueFilter = new SingleColumnValueFilter(
                        "cf".getBytes(),fields[0].getBytes(), compareOp,fields[1].getBytes()
                );
                singleColumnValueFilter.setFilterIfMissing(true);
                filters.add(singleColumnValueFilter);
            }else {
                throw new RuntimeException("参数无法匹配");
            }
        }

        return filters;
    }


    public  List<Filter> getFilter()
    {
        //condition = condition.substring(1,condition.length()-1);
        String [] fields = condition.split(",");
        List<String> normList = new ArrayList<String>();
        List<String> compareList = new ArrayList<String>();
        for (String field:fields)
        {
            if(field.contains(":"))
            {
                normList.add(field);
            }else{
                compareList.add(field);
            }
        }
        List<Filter> filters = getSymbol(compareList,normList,family);
        return filters;
    }

    private  SymbolPojo getSymbolManager(String key)
    {
        SymbolPojo symbolPojo = new SymbolPojo();
        for(CompareOpEnum symbol:CompareOpEnum.values())
        {
            if(key.contains(symbol.getKey()))
            {
                String[] keys = key.split(symbol.getKey());
                symbolPojo.setKeys(keys);
                symbolPojo.setCompareOp(symbol.getValue());
                return symbolPojo;
            }
        }
        return null;

    }

}
