package com.ricks.utils.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayUtilsTests {
    @Test
    public void isNullOrEmptyTest() {
        final String[] nullArr = null;
        final String[] emptyArr = new String[2];
        final String[] fullEmptyArr = { "", "" };
        final String[] fullArr = { "Nuestras Ideas", "Son libres" };

        assertTrue("The String Array is Not Null", ArrayUtils.isNullOrEmpty(nullArr));
        assertTrue("The Array is Not Empty", ArrayUtils.isNullOrEmpty(emptyArr));
        assertTrue("The Array Is Not Null And The elements are Not Empty", ArrayUtils.isNullOrEmpty(fullEmptyArr));
        assertFalse("The Array may be null, empty or the individuals strings are null or empty",
                ArrayUtils.isNullOrEmpty(fullArr));
    }

    @Test
    public void nonNullOrEmptyTest() {
        final String[] nullArr = null;
        final String[] emptyArr = new String[2];
        final String[] fullEmptyArr = { "", "" };
        final String[] fullArr = { "Nuestras Ideas", "Son libres" };
        
        assertFalse("The String array is not null", ArrayUtils.nonNullOrEmpty(nullArr));
        assertFalse("The String array is empty", ArrayUtils.nonNullOrEmpty(emptyArr));
        assertFalse("The String array's elements are not empty", ArrayUtils.nonNullOrEmpty(fullEmptyArr));
        assertTrue("The String array may be null or empty, perhaps it's elements are null or empty",
                ArrayUtils.nonNullOrEmpty(fullArr));
        
    }
}