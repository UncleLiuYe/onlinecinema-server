package com.liuyetech.onlinecinemamanager.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MovieEntity {

    @JsonProperty("adult")
    private Boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("belongs_to_collection")
    private BelongsToCollectionDTO belongsToCollection;
    @JsonProperty("budget")
    private Integer budget;
    @JsonProperty("genres")
    private List<GenresDTO> genres;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("popularity")
    private Double popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("production_companies")
    private List<ProductionCompaniesDTO> productionCompanies;
    @JsonProperty("production_countries")
    private List<ProductionCountriesDTO> productionCountries;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("revenue")
    private Double revenue;
    @JsonProperty("runtime")
    private Integer runtime;
    @JsonProperty("spoken_languages")
    private List<SpokenLanguagesDTO> spokenLanguages;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tagline")
    private String tagline;
    @JsonProperty("title")
    private String title;
    @JsonProperty("video")
    private Boolean video;
    @JsonProperty("vote_average")
    private Double voteAverage;
    @JsonProperty("vote_count")
    private Integer voteCount;

    @NoArgsConstructor
    @Data
    public static class BelongsToCollectionDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("poster_path")
        private String posterPath;
        @JsonProperty("backdrop_path")
        private String backdropPath;
    }

    @NoArgsConstructor
    @Data
    public static class GenresDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
    }

    @NoArgsConstructor
    @Data
    public static class ProductionCompaniesDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("logo_path")
        private String logoPath;
        @JsonProperty("name")
        private String name;
        @JsonProperty("origin_country")
        private String originCountry;
    }

    @NoArgsConstructor
    @Data
    public static class ProductionCountriesDTO {
        @JsonProperty("iso_3166_1")
        private String iso31661;
        @JsonProperty("name")
        private String name;
    }

    @NoArgsConstructor
    @Data
    public static class SpokenLanguagesDTO {
        @JsonProperty("english_name")
        private String englishName;
        @JsonProperty("iso_639_1")
        private String iso6391;
        @JsonProperty("name")
        private String name;
    }
}
