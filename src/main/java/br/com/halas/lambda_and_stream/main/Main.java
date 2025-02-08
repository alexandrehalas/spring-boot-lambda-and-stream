package br.com.halas.lambda_and_stream.main;

import br.com.halas.lambda_and_stream.model.Season;
import br.com.halas.lambda_and_stream.model.Serie;
import br.com.halas.lambda_and_stream.service.ApiConsumptionService;
import br.com.halas.lambda_and_stream.service.DataConverterService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String URL = "https://www.omdbapi.com/?t=";
    public static final String API_KEY = "&apikey=d7239a2d";
    public static final String SEASON = "&season=";

    private ApiConsumptionService apiConsumptionService = new ApiConsumptionService();
    private DataConverterService dataConverterService = new DataConverterService();

    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {

        System.out.println("Enter the 'NAME' of the serie");
        var serieName = scanner.nextLine();

        var serieUrl = URL + serieName.replace(" ", "+") + API_KEY;
        var serieJson = apiConsumptionService.getData(serieUrl);
        var serie = dataConverterService.getData(serieJson, Serie.class);

        var seasons = new ArrayList<Season>();
        for (int i = 1; i < serie.totalSeasons(); i++) {
            var seasonUrl = URL + serieName.replace(" ", "+") + SEASON + i + API_KEY;
            var seasonJson = apiConsumptionService.getData(seasonUrl);
            var season = dataConverterService.getData(seasonJson, Season.class);
            seasons.add(season);
        }

        System.out.println("\nSerie: " + serie.title());
        seasons.forEach(season -> {
            System.out.println("\nSeason " + season.season() + ": ");
            season.episodes().forEach(episode -> System.out.println(episode.title()));
        });
    }

}
