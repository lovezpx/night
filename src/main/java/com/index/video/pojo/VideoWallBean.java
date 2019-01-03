package com.index.video.pojo;

import com.index.video.model.VideoGroup;

/**
 * @Auther: Index
 * @Date: 2018/11/30 10:38
 * @Description:
 */
public class VideoWallBean extends VideoGroup {
    private String coverSrc;

    public String getCoverSrc() {
        return coverSrc;
    }

    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }
}
