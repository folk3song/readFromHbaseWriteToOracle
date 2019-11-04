package com.sirc.hbase.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResJson implements Serializable {
    private CustomMap attribute = new CustomMap();

    public ResJson(){}

    public Object getAttribute(Object key) {
        return attribute.get(key);
    }

    public void addAttribute(String key, Object value) {
        attribute.addAttribute(key, value);
    }

    public void setAttribute(CustomMap attribute) {
        this.attribute = attribute;
    }

    public CustomMap getAttribute() {
        return attribute;
    }


    public void removeAttr(Object key){
        this.attribute.remove(key);
    }
}
