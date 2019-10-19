package com.ricks.utils.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringsTests {

    @Test 
    public void isNullOrEmptyTest() 
    {
        final String nullString = null;
        final String emptyString = "";
        final String fullString = "full-string";

        assertTrue(Strings.isNullOrEmpty(nullString));
        assertTrue(Strings.isNullOrEmpty(emptyString));
        assertFalse(Strings.isNullOrEmpty(fullString));
    }

    @Test 
    public void nonNullOrEmptyTest() 
    {
        final String nullString = null;
        final String emptyString = "";
        final String fullString = "full-string";

        assertFalse(Strings.nonNullOrEmpty(nullString));
        assertFalse(Strings.nonNullOrEmpty(emptyString));
        assertTrue(Strings.nonNullOrEmpty(fullString));
    }
}