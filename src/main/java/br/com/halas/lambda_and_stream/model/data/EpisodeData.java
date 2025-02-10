package br.com.halas.lambda_and_stream.model.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer episode,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("Released") String releasedDate) {
}
