package com.liuyetech.onlinecinemamanager.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CastCrewEntity {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("cast")
    private List<CastDTO> cast;
    @JsonProperty("crew")
    private List<CrewDTO> crew;

    @NoArgsConstructor
    @Data
    public static class CastDTO {
        @JsonProperty("adult")
        private Boolean adult;
        @JsonProperty("gender")
        private Integer gender;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("known_for_department")
        private String knownForDepartment;
        @JsonProperty("name")
        private String name;
        @JsonProperty("original_name")
        private String originalName;
        @JsonProperty("popularity")
        private Double popularity;
        @JsonProperty("profile_path")
        private String profilePath;
        @JsonProperty("cast_id")
        private Integer castId;
        @JsonProperty("character")
        private String character;
        @JsonProperty("credit_id")
        private String creditId;
        @JsonProperty("order")
        private Integer order;
    }

    @NoArgsConstructor
    @Data
    public static class CrewDTO {
        @JsonProperty("adult")
        private Boolean adult;
        @JsonProperty("gender")
        private Integer gender;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("known_for_department")
        private String knownForDepartment;
        @JsonProperty("name")
        private String name;
        @JsonProperty("original_name")
        private String originalName;
        @JsonProperty("popularity")
        private Double popularity;
        @JsonProperty("profile_path")
        private Object profilePath;
        @JsonProperty("credit_id")
        private String creditId;
        @JsonProperty("department")
        private String department;
        @JsonProperty("job")
        private String job;
    }
}
