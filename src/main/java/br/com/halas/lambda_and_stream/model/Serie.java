package br.com.halas.lambda_and_stream.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Serie(@JsonAlias("Title") String title,
                    Integer totalSeasons,
                    String imdbRating) {

}
