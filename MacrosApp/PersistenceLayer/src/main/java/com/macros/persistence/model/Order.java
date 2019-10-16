package com.macros.persistence.model;

import java.io.Serializable;
import java.util.Arrays;

public class Order implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer id;
    private String[] content;

    public String getName() {
        return name;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String concatContent() {
        final StringBuffer buffer = new StringBuffer();
        
        for (String orderline : content)
            buffer.append(orderline + "|");
        
        return buffer.toString();
    }

    public void setConcatContent(String content) {
        this.content = content.split("|");
    }

    @Override
    public String toString() {
        return "Order [content=" + Arrays.toString(content) + ", id=" + id + ", name=" + name + "]";
    }

    
}