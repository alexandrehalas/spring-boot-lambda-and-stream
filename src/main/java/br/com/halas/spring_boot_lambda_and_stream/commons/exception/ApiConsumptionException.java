package br.com.halas.spring_boot_lambda_and_stream.commons.exception;

public class ApiConsumptionException extends RuntimeException {

    public ApiConsumptionException(Exception e) {
        super(e);
    }
}
