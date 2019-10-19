package com.ricks.utils.string;

import java.util.Objects;

public class Strings 
{

    private Strings() {}

    public static boolean isNullOrEmpty(final String value) 
    {
        return (Objects.isNull(value) || value.isEmpty());
    }

    public static boolean nonNullOrEmpty(final String value) 
    {
        if (isNullOrEmpty(value))
        {
            return false;
        }
        return true;
    }

}

