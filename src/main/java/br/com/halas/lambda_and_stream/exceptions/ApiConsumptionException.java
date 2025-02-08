package br.com.halas.lambda_and_stream.exceptions;

public class ApiConsumptionException extends RuntimeException {

    public ApiConsumptionException(Exception e) {
        super(e);
    }

}
