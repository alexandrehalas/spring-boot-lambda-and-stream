package br.com.halas.lambda_and_stream;

import br.com.halas.lambda_and_stream.model.Episode;
import br.com.halas.lambda_and_stream.model.Season;
import br.com.halas.lambda_and_stream.model.Serie;
import br.com.halas.lambda_and_stream.service.ApiConsumptionService;
import br.com.halas.lambda_and_stream.service.DataConverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

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

        var seasons = new ArrayList<Season>();
        for (int i = 1; i < serie.totalSeasons(); i++) {
            var seasonJson = apiConsumptionService.getData("https://www.omdbapi.com/?t=supernatural&season=" + i + "&apikey=d7239a2d");
            var season = dataConverterService.getData(seasonJson, Season.class);
            seasons.add(season);
        }
        seasons.forEach(System.out::println);
    }
}
