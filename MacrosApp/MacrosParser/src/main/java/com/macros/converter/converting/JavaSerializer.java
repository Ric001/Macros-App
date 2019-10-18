package com.macros.converter.converting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import com.ricks.utils.string.Strings;

public class JavaSerializer<T> implements IConverter<T> {


    private final static Logger log = Logger.getLogger(JavaSerializer.class.getName());

    @Override
    public T parse(String value, Optional<Class> clazz) {
        log.info("[ENTERING T parse(string value, Optional<Class> clazz)]");

        T parsedObj = null;
        if (Strings.nonNullOrEmpty(value)) {
            parsedObj = handleException(value);
        }

        log.info("[RETURNING FROM T parse(string value, Optional<Class> clazz) " + parsedObj + "]");
        return parsedObj;
    }

    @Override
    public String serialize(T objToConvert) {
        log.info("[ENTERING String serialize(T objToConvert)]");

        String serializedAsString = "";
        if (Objects.nonNull(objToConvert))
        {
            serializedAsString = handleException(objToConvert);
        }

        log.info("[ENDING String serializable(T objToConvert)]");
        return serializedAsString;
    }

    private String handleException(T value) {
        try {
            final ByteArrayOutputStream serializedTDD = new ByteArrayOutputStream();
            final ObjectOutputStream serializer = new ObjectOutputStream(serializedTDD);
            serializer.writeObject(value);
            serializer.close();
            return Base64.getEncoder().encodeToString(serializedTDD.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private T handleException(final String tddToParse)
    {
        try {
            final byte[] tdd = Base64.getDecoder().decode(tddToParse);
            final ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(tdd));
            @SuppressWarnings("unchecked")
            final T parsedTDD = (T) inputStream.readObject();
            inputStream.close();
            return parsedTDD;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
       return null;
    }

    
    
}