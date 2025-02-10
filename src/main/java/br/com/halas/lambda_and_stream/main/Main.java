package br.com.halas.lambda_and_stream.main;

import br.com.halas.lambda_and_stream.model.Episode;
import br.com.halas.lambda_and_stream.model.data.EpisodeData;
import br.com.halas.lambda_and_stream.model.data.SeasonData;
import br.com.halas.lambda_and_stream.model.data.SerieData;
import br.com.halas.lambda_and_stream.service.ApiConsumptionService;
import br.com.halas.lambda_and_stream.service.DataConverterService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        var serieData = dataConverterService.getData(serieJson, SerieData.class);

        var seasonDataList = new ArrayList<SeasonData>();
        for (int i = 1; i < serieData.totalSeasons(); i++) {
            var seasonUrl = URL + serieName.replace(" ", "+") + SEASON + i + API_KEY;
            var seasonJson = apiConsumptionService.getData(seasonUrl);
            var seasonData = dataConverterService.getData(seasonJson, SeasonData.class);
            seasonDataList.add(seasonData);
        }

        System.out.println("\nSerie: " + serieData.title());
        seasonDataList.forEach(seasonData -> {
            System.out.println("\nSeason " + seasonData.season() + ": ");
            seasonData.episodeData().forEach(episodeData -> System.out.println(episodeData.title() + " - " + episodeData.releasedDate()));
        });

        var episodeDataList = seasonDataList.stream()
                .flatMap(s -> s.episodeData().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episodeDataList:");
        episodeDataList.stream()
                .filter(episodeData -> !episodeData.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::rating).reversed())
                .limit(5)
                .forEach(System.out::println);

        var episodes = seasonDataList.stream()
                .flatMap(s -> s.episodeData().stream()
                        .map(e -> new Episode(s.season(), e))
                ).collect(Collectors.toList());

        episodes.forEach(System.out::println);

        System.out.println("Enter the 'YEAR' of the serie episodes");
        var year = scanner.nextInt();
        scanner.nextLine();

        var searchDate = LocalDate.of(year, 1, 1);

        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodes.stream()
                .filter(episode -> episode.getReleasedDate() != null && episode.getReleasedDate().isAfter(searchDate))
                .forEach(episode -> System.out.println(
                        "Season: " + episode.getSeason() +
                                " Episode: " + episode.getTitle() +
                                " Released Date: " + dateTimeFormatter.format(episode.getReleasedDate())));

    }

}
