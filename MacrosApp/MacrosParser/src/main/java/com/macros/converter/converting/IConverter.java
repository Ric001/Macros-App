package com.macros.converter.converting;

import java.util.Optional;

public interface IConverter<T> 
{

    T parse(final String value, Optional<Class<T>> clazz);
    String serialize(final T objToConvert);
    
}