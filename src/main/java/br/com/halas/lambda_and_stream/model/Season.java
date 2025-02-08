package br.com.halas.lambda_and_stream.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Season(@JsonAlias("Season") Integer season,
                     @JsonAlias("Episodes") List<Episode> episodes) {
}
