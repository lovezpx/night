package com.index.video.service;

import com.index.common.ResultMap;
import com.index.video.model.Video;
import com.index.video.pojo.VideoUploadBean;

/**
 * @Auther: Index
 * @Date: 2018/12/5 10:18
 * @Description:
 */
public interface VideoUploadService {
    public ResultMap<String> upload(VideoUploadBean videoUploadBean);
}
