package com.index.video.pojo;

import com.index.picture.model.Picture;
import com.index.video.model.Video;
import com.index.video.model.VideoGroup;
import com.index.video.model.VideoTip;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/5 10:22
 * @Description:
 */
public class VideoUploadBean extends VideoGroup {

    private Picture picture;

    private List<Video> videoList;

    private List<VideoTip> videoTipList;

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public List<VideoTip> getVideoTipList() {
        return videoTipList;
    }

    public void setVideoTipList(List<VideoTip> videoTipList) {
        this.videoTipList = videoTipList;
    }
}
