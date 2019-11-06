package com.macros.persistence.dao.constants;

import java.time.format.DateTimeFormatter;

public class DateFormatters {

    public transient final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss a";
    public transient final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    
}