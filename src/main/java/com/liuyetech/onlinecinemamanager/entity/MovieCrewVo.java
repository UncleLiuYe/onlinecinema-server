package com.liuyetech.onlinecinemamanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MovieCrewVo {
    private Integer mid;
    private List<String> crewsIds;
}
