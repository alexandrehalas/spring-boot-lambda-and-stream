package br.com.halas.lambda_and_stream.service;

public interface DataConvertable {

    <T> T getData(String json, Class<T> clazz);
}
