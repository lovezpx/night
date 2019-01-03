package com.index.video.service;

import com.index.video.model.VideoTrailer;
import com.index.video.pojo.VideoTrailerBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/15 07:24
 * @Description:
 */
public interface VideoTrailerService {
    public int deleteByPrimaryKey(String id);

    public int deleteAll();

    public void insertSelective(List<VideoTrailer> records);

    public VideoTrailerBean selectByPrimaryKey(String id);

    public List<VideoTrailerBean> selectAll();

    public List<VideoTrailerBean> selectVideoGroupForTrailerByRange(Integer start, Integer end);

    public int updateByPrimaryKeySelective(VideoTrailer record);

    public int updateByPrimaryKey(VideoTrailer record);
}
