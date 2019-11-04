package com.sirc.hbase.util;

import com.sirc.hbase.Enum.CompareOpEnum;
import com.sirc.hbase.pojo.SymbolPojo;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class Utils {

    public  Map<String,Object> getResultMap(String family, String[] qualifiers, Result result)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("rowkey", Bytes.toString(result.getRow()));
        if (qualifiers != null) {
            System.out.println(qualifiers.length);
            for (int i = 0; i < qualifiers.length; i++) {
                System.out.println(family+".."+qualifiers[0]);
                List<Cell> celllist = result.getColumnCells(Bytes.toBytes(family), Bytes.toBytes(qualifiers[i]));

                System.out.println(celllist.size());
                if (celllist.size() > 0) {
                    Cell cell = celllist.get(0);
                    String qualifierString = Bytes.toString(CellUtil.cloneQualifier(cell));
                    System.out.println("qualifierString"+qualifierString);
                    String valueString = Bytes.toString(CellUtil.cloneValue(cell));
                    System.out.println("valueString"+valueString);
                    map.put(qualifierString, valueString);
                } else {
                    map.put(qualifiers[i], "");
                }
            }
        } else {
            for (Cell cell : result.listCells()) {
                String qualifierString = Bytes.toString(CellUtil.cloneQualifier(cell));
                String valueString = Bytes.toString(CellUtil.cloneValue(cell));
                map.put(qualifierString, valueString);


            }
        }
        return map;

    }
}
