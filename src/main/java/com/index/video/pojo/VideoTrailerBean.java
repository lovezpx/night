package com.index.video.pojo;

import com.index.video.model.VideoTrailer;

/**
 * @Auther: Index
 * @Date: 2018/12/4 10:06
 * @Description:
 */
public class VideoTrailerBean extends VideoTrailer {
    private String coverSrc;

    private String typeName;

    public String getCoverSrc() {
        return coverSrc;
    }

    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
