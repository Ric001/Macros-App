package com.ricks.utils.arrays_stuff;

import java.util.Objects;

import com.ricks.utils.string.Strings;

public class ArrayUtils {

    private ArrayUtils() {}

    public static boolean nonNullOrEmpty(String... array) 
    {
       final boolean result = isNullOrEmpty(array) ? false : true;
        return result;
    }

    public static boolean isNullOrEmpty(String... array) 
    {
        return (Objects.isNull(array) || areElementsNullOrEmpty(array));
    }

    private static boolean areElementsNullOrEmpty(String... array) 
    {
        if (array.length == 0)
            return true;
        for (String value : array)
        {
            if (Strings.isNullOrEmpty(value))
                return true;
        }
        return false;
    }

   
    public static boolean nonNullOrEmpty(int... array)
    {
        return Objects.nonNull(array);
    }

    public static boolean isNullOrEmpty(int... array)
    {
        return (Objects.isNull(array) || array.length == 0);
    }

    public static boolean isNullOrEmpty(final double... array)
    {
        return (Objects.isNull(array) || array.length == 0);
    }

    public static boolean isNullOrEmpty(final float... array)
    {
        return (Objects.isNull(array) || array.length == 0);
    }

    public static boolean isNullOrEmpty(final char... array)
    {
        return (Objects.isNull(array) || array.length == 0);
    }

    public static boolean isNullOrEmpty(final boolean... array) 
    {
        return (Objects.isNull(array) || array.length == 0);
    }
}