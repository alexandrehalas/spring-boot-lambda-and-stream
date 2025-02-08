package br.com.halas.lambda_and_stream.service;

import br.com.halas.lambda_and_stream.exceptions.ApiConsumptionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsumptionService {

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
