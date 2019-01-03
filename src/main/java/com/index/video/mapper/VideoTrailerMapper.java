package com.index.video.mapper;

import com.index.video.model.VideoTrailer;
import com.index.video.pojo.VideoTrailerBean;
import com.index.video.pojo.VideoWallBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoTrailerMapper {
    int deleteByPrimaryKey(String id);

    int deleteAll();

    int insert(VideoTrailer record);

    int insertSelective(VideoTrailer record);

    VideoTrailerBean selectByPrimaryKey(String id);

    List<VideoTrailerBean> selectAll();

    List<VideoTrailerBean> selectVideoGroupForTrailerByRange(@Param("start")Integer start, @Param("end")Integer end);

    int updateByPrimaryKeySelective(VideoTrailer record);

    int updateByPrimaryKey(VideoTrailer record);
}