package br.com.halas.lambda_and_stream.exceptions;

public class DataConverterException extends RuntimeException {

    public DataConverterException(Exception e) {
        super(e);
    }

}
