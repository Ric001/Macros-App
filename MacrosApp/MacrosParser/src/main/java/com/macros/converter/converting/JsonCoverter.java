package com.macros.converter.converting;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricks.utils.string.Strings;

public class JsonCoverter<T> implements IConverter<T> {

    private final static Logger log = Logger.getLogger(JsonCoverter.class.getName());
    private Optional<ObjectMapper> jsonMapper;

    public JsonCoverter(Optional<ObjectMapper> jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public T parse(String value, Optional<Class> clazz) {
        log.info("[ENTERING parse(string value)]");

        T parsed = null;
        if (Strings.nonNullOrEmpty(value) && jsonMapper.isPresent() && clazz.isPresent())
        {
            parsed = hanlingException(value, clazz.get());
        }

        log.info("[RETURNING FROM parse(string value)]");
        return parsed;
    }

    @Override
    public String serialize(T objToConvert) {
        log.info("[ENTERING String serialize(T objTConvert)]");

        String jsonyfiedObj = "";

        if (Objects.nonNull(objToConvert) && jsonMapper.isPresent()) 
        {
            jsonyfiedObj = handlingException(objToConvert);
        }

        log.info("[RETURNING String serialize(T objToConvert)]");
        return jsonyfiedObj;
    }

    private String handlingException(T objTConvert) {
        try {
            return jsonMapper.get().writeValueAsString(objTConvert);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private T hanlingException(String value, Class clazz) {
        try {
            @SuppressWarnings("unchecked")
            final T parsedTDD = (T) jsonMapper.get().readValue(value, clazz);
            return parsedTDD;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}