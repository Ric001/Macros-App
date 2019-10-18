package com.macros.converter.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ParsedOrder implements Serializable 
{

    private static final long serialVersionUID = 1L;
    private LocalDateTime conversionDateTime;
    private String conversionContent;

    public ParsedOrder() {
    }

    public ParsedOrder(LocalDateTime conversionDateTime, String conversionContent) {
        this.conversionDateTime = conversionDateTime;
        this.conversionContent = conversionContent;
    }

    public LocalDateTime getConversionDateTime() {
        return conversionDateTime;
    }

    public void setConversionDateTime(LocalDateTime conversionDateTime) {
        this.conversionDateTime = conversionDateTime;
    }

    public String getConversionContent() {
        return conversionContent;
    }

    public void setConversionContent(String conversionContent) {
        this.conversionContent = conversionContent;
    }

    @Override
    public String toString() {
        return "ParsedOrder [conversionContent=" + conversionContent + ", conversionDateTime=" + conversionDateTime
                + "]";
    }

}