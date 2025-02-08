package br.com.halas.lambda_and_stream.service;

import br.com.halas.lambda_and_stream.exceptions.DataConverterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverterService implements DataConvertable {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new DataConverterException(e);
        }
    }
}
