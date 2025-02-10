package br.com.halas.lambda_and_stream.model;

import br.com.halas.lambda_and_stream.model.data.EpisodeData;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {

    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate releasedDate;

    public Episode(Integer season, EpisodeData e) {
        this.season = season;
        this.title = e.title();
        this.episodeNumber = e.episode();



        try {
            this.rating = Double.valueOf(e.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }

        try {
            this.releasedDate = LocalDate.parse(e.releasedDate());
        } catch (DateTimeParseException ex) {
            this.releasedDate = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    @Override
    public String toString() {
        return  "season=" + season +
                ", title='" + title + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", rating=" + rating +
                ", releasedDate=" + releasedDate;
    }
}
