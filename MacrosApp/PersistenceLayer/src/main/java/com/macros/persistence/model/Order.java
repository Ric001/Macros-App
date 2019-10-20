package com.macros.persistence.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer id;
    private String content;
    private LocalDateTime requestedDate;
    private LocalDateTime parsedDate;

    public String getName() {
        return name;
    }

    public LocalDateTime getParsedDate() {
        return parsedDate;
    }

    public void setParsedDate(LocalDateTime parsedDate) {
        this.parsedDate = parsedDate;
    }

    public LocalDateTime getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDateTime requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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

    @Override
    public String toString() {
        return "Order [content=" + content + ", id=" + id + ", name=" + name + ", parsedDate=" + parsedDate
                + ", requestedDate=" + requestedDate + "]";
    }


   
    
}