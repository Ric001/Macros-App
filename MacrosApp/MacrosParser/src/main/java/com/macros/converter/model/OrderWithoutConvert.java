package com.macros.converter.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

public class OrderWithoutConvert implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime requestedConversionDate;
    private String[] ordersArray;

    
    public OrderWithoutConvert() {
    }

    public OrderWithoutConvert(LocalDateTime requestedConversionDate, String[] ordersArray) {
        this.requestedConversionDate = requestedConversionDate;
        this.ordersArray = ordersArray;
    }

    public LocalDateTime getRequestedConversionDate() {
        return requestedConversionDate;
    }

    public String[] getOrdersArray() {
        return ordersArray;
    }

    public void setOrdersArray(String[] ordersArray) {
        this.ordersArray = ordersArray;
    }

    public void setRequestedConversionDate(LocalDateTime requestedConversionDate) {
        this.requestedConversionDate = requestedConversionDate;
    }

    @Override
    public String toString() {
        return "OrderWithoutConvert [ordersArray=" + Arrays.toString(ordersArray) + ", requestedConversionDate="
                + requestedConversionDate + "]";
    }


}