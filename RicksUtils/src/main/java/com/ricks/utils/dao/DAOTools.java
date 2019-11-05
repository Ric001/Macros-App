package com.ricks.utils.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.ricks.utils.string.Strings;

public class DAOTools {
    
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private DAOTools() {
    }

    public static Timestamp toSqlTimestamp(final LocalDateTime dateTime) {
        if (Objects.nonNull(dateTime)) 
            return Timestamp.valueOf(dateTime);
        return null;
    } 

    public static Timestamp toSqlTimestamp(final String localDatetime) {
        if (Strings.nonNullOrEmpty(localDatetime))
            return toSqlTimestamp(LocalDateTime.parse(localDatetime));
        return null;
    }

    public static LocalDateTime toLocalDatetime(final Date date) {
        if (Objects.nonNull(date) || Strings.isNullOrEmpty(date.toString())) {
            final String formattedDate = formatter.format(date);
            final Timestamp timestamp = Timestamp.valueOf(formattedDate);
            return timestamp.toLocalDateTime();
        } 
        return null;
    }

    public static LocalDateTime toLocalDateTime(final Timestamp timestamp) {
        if (Objects.nonNull(timestamp)) {
            final LocalDateTime localDateTimeFromTimestamp = timestamp.toLocalDateTime();
            return localDateTimeFromTimestamp;
        }

        return null;
    }

    public static Date toSqlDate(final LocalDate localDate) {
        if (Objects.nonNull(localDate)) {
            final Date convertedDate = Date.valueOf(localDate);
            return convertedDate;
        }
        return null;
    }

    public static Date toSqlDate(final String localDate) {
        if (Strings.nonNullOrEmpty(localDate)) {
            final Date convertedFromString = Date.valueOf(LocalDate.parse(localDate));
            return convertedFromString;
        }
        return null;
    }
}