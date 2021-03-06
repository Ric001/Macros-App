package com.macros.persistence.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.macros.persistence.dao.constants.DateFormatters;


public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer id;
    private String content;
    private LocalDateTime requestedDate;
    private LocalDateTime parsedDate;

    public Order() {
    }

    public Order(String name, Integer id, String content, LocalDateTime requestedDate, LocalDateTime parsedDate) {
        this.name = name;
        this.id = id;
        this.content = content;
        this.requestedDate = requestedDate;
        this.parsedDate = parsedDate;
    }

    public Order(String name, String content, LocalDateTime requestedDate, LocalDateTime parsedDate) {
        this.name = name;
        this.content = content;
        this.requestedDate = requestedDate;
        this.parsedDate = parsedDate;
    }

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

    public String formatDateTime(final LocalDateTime localDateTime) {
        String datetime = "NOT A DATE TIME INSTANCE PROVIDED";
        if (Objects.nonNull(localDateTime))
            datetime = localDateTime.format(DateFormatters.DATE_TIME_FORMATTER);
        return datetime;
    }

    @Override
    public String toString() {
        return "Order [content=" + content + ", id=" + id + ", name=" + name + ", parsedDate=" + formatDateTime(parsedDate)
                + ", requestedDate=" + formatDateTime(requestedDate) + "]";
    }
}