package com.sirc.hbase.pojo;


import org.springframework.core.Conventions;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomMap extends LinkedHashMap<String,Object> {
    public CustomMap() {}

    public CustomMap(String attributeName, Object attributeValue) {
        addAttribute(attributeName, attributeValue);
    }

    public CustomMap(Object attributeValue) {
        addAttribute(attributeValue);
    }

    public CustomMap addAttribute(String attributeName, Object attributeValue) {
        Assert.notNull(attributeName, "Model attribute name must not be null");
        put(attributeName, attributeValue);
        return this;
    }

    public CustomMap addAttribute(Object attributeValue) {
        Assert.notNull(attributeValue, "Model object must not be null");
        if (attributeValue instanceof Collection && ((Collection<?>) attributeValue).isEmpty()) {
            return this;
        }
        return addAttribute(Conventions.getVariableName(attributeValue), attributeValue);
    }

    public CustomMap addAllAttributes(Collection<?> attributeValues) {
        if (attributeValues != null) {
            for (Object attributeValue : attributeValues) {
                addAttribute(attributeValue);
            }
        }
        return this;
    }

    public CustomMap addAllAttributes(Map<String, ?> attributes) {
        if (attributes != null) {
            putAll(attributes);
        }
        return this;
    }

    public CustomMap mergeAttributes(Map<String, ?> attributes) {
        if (attributes != null) {
            for (Map.Entry<String, ?> entry : attributes.entrySet()) {
                String key = entry.getKey();
                if (!containsKey(key)) {
                    put(key, entry.getValue());
                }
            }
        }
        return this;
    }

    public boolean containsAttribute(String attributeName) {
        return containsKey(attributeName);
    }
}
