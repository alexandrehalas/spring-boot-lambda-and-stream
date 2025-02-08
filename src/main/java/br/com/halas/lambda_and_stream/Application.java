package br.com.halas.lambda_and_stream;

import br.com.halas.lambda_and_stream.model.Serie;
import br.com.halas.lambda_and_stream.service.ApiConsumptionService;
import br.com.halas.lambda_and_stream.service.DataConverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var apiConsumptionService = new ApiConsumptionService();
        var dataConverterService = new DataConverterService();

        var serieUrl = "https://www.omdbapi.com/?t=supernatural&apikey=d7239a2d";
        var serieJson = apiConsumptionService.getData(serieUrl);

        var serie = dataConverterService.getData(serieJson, Serie.class);

        System.out.println(serie);
    }
}
