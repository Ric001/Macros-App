package com.macros.persistence.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.macros.persistence.dao.constants.DateFormatters;

public class ExecutedOrder {

    private Integer id;
    private Order executedOrder;
    private LocalDateTime executionDatetime;

    public ExecutedOrder() {
    }

     public ExecutedOrder(Integer id,Order executedOrder, LocalDateTime executionDatetime) {
        this.id = id;
        this.executedOrder = executedOrder;
        this.executionDatetime = executionDatetime;
    }

    public Order getExecutedOrder() {
        return executedOrder;
    }

    public void setExecutedOrder(Order executedOrder) {
        this.executedOrder = executedOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getExecutionDatetime() {
        return executionDatetime;
    }

    public Order getOrder() {
        return executedOrder;
    }

    public void setOrder(Order order) {
        this.executedOrder = order;
    }

    public void setExecutionDatetime(LocalDateTime executionDatetime) {
        this.executionDatetime = executionDatetime;
    }

    public String formatExecutionDateTime() {
        String datetime = "NOT A DATE TIME PROVIDED";
        if (Objects.nonNull(executionDatetime))
            datetime = executionDatetime.format(DateFormatters.DATE_TIME_FORMATTER);
        return datetime;
    }

    @Override
    public String toString() {
        return "ExecutedOrder [executedOrder=" + executedOrder + ", executionDatetime=" + formatExecutionDateTime() + ", id="
                + id + "]";
    }
}