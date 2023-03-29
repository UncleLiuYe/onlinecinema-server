package com.liuyetech.onlinecinemamanager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoInfo {
    private Long videoId;
    private String videoName;
    private Long videoDuration;
    private String videoUrl;
    private String videoPic;
}
