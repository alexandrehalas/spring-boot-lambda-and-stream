package br.com.halas.lambda_and_stream.model.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(@JsonAlias("Season") Integer season,
                         @JsonAlias("Episodes") List<EpisodeData> episodeData) {
}
