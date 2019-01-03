package com.index.video.service.impl;

import com.index.utils.UUID;
import com.index.video.mapper.VideoTrailerMapper;
import com.index.video.model.VideoTrailer;
import com.index.video.pojo.VideoTrailerBean;
import com.index.video.service.VideoTrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/4 10:34
 * @Description:
 */
@Service
public class VideoTrailerServiceImpl implements VideoTrailerService {
    @Autowired
    private VideoTrailerMapper videoTrailerMapper;

    public int deleteByPrimaryKey(String id){
        return videoTrailerMapper.deleteByPrimaryKey(id);
    }

    public int deleteAll(){
        return videoTrailerMapper.deleteAll();
    }

    public void insertSelective(List<VideoTrailer> records){
        for(int i=0;i<records.size();i++){
            VideoTrailer record = records.get(i);
            record.setId(UUID.captchaChar(32,false));
            videoTrailerMapper.insertSelective(record);
        }
    }

    public int updateByPrimaryKeySelective(VideoTrailer record){
        return videoTrailerMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(VideoTrailer record){
        return videoTrailerMapper.updateByPrimaryKey(record);
    }

    public VideoTrailerBean selectByPrimaryKey(String id){
        return videoTrailerMapper.selectByPrimaryKey(id);
    }

    public List<VideoTrailerBean> selectAll(){
        return videoTrailerMapper.selectAll();
    }

    public List<VideoTrailerBean> selectVideoGroupForTrailerByRange(Integer start, Integer end){
        return videoTrailerMapper.selectVideoGroupForTrailerByRange(start, end);
    }
}
