package com.macros.utils.stringutils;

import java.util.Objects;

public class Strings 
{
    private Strings() {}

    public static boolean isNullOrEmpty(String valueToValidate)
    {
        if (Objects.isNull(valueToValidate) || valueToValidate.isEmpty())
            return true;
        return false;
    }
}