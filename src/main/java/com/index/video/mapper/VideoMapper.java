package com.index.video.mapper;

import com.index.video.model.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByVgid(String vgid);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKeyWithBLOBs(Video record);

    int updateByPrimaryKey(Video record);

    int updateVideoStatus(Video record);
}