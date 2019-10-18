package com.ricks.utils.array;

import java.util.Objects;

import com.ricks.utils.string.Strings;

public class ArrayUtils {

    private ArrayUtils() {}

    public static boolean nonNullOrEmpty(String... array) 
    {
        return (Objects.nonNull(array) || validateByElements(array));
    }

    public static boolean isNullOrEmpty(String... array) 
    {
        return (Objects.isNull(array) || validateByElements(array));
    }

    private static boolean validateByElements(String... array) 
    {
        for (String value : array)
        {
            if (Strings.isNullOrEmpty(value))
                return false;
        }
        return true;
    }

    public static boolean nonNullOrEmpty(int... array)
    {
        return (Objects.nonNull(array) || array.length == 0);
    }

    public static boolean isNullOrEmpty(int... array)
    {
        return (Objects.isNull(array) || array.length == 0);
    }

}