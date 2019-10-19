package com.macros.StringUtils;

import java.util.Objects;

public class Strings 
{
    private Strings() {}

    public static boolean isNullOrEmpty(final String value)
    {
        if (Objects.nonNull(value) || value.isEmpty())
            return true;
        return false;
    } 
}