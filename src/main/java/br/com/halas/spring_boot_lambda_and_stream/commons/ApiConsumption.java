package br.com.halas.spring_boot_lambda_and_stream.commons;

import br.com.halas.spring_boot_lambda_and_stream.commons.exception.ApiConsumptionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsumption {

    public String getData(String endereco) {

        try (var client = HttpClient.newHttpClient()) {

            var request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            return client
                    .send(request, HttpResponse.BodyHandlers.ofString())
                    .body();

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiConsumptionException(e);
        }

    }

}
