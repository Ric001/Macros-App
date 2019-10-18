package com.macros.converter.converting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import com.ricks.utils.string.Strings;

public class JavaSerializer<T> implements IConverter<T> {
    private Optional<OutputStream> serializerStream;
    private Optional<InputStream> parserStream;

    private final static Logger log = Logger.getLogger(JavaSerializer.class.getName());

    @Override
    public T parse(String value, Optional<Class> clazz) {
        T parsedObj = null;
        if (Strings.nonNullOrEmpty(value) && parserStream.isPresent() && clazz.isPresent()) {
            //return parsedObj = handleException(value, clazz.get());
        }
        return parsedObj;
    }

    @Override
    public String serialize(T objToConvert) {
        log.info("[ENTERING String serialize(T objToConvert)]");

        String serializedAsString = "";
        if (Objects.nonNull(objToConvert) && serializerStream.isPresent())
        {
            serializedAsString = handleException(objToConvert);
        }

        log.info("[ENDING String serializable(T objToConvert)]");
        return serializedAsString;
    }

    private String handleException(T value) {
        try {
            return new ObjectOutputStream(serializerStream.get()).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
}