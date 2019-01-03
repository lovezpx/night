package com.index.video.rest;

import com.alibaba.fastjson.JSONObject;
import com.index.common.ResultMap;
import com.index.management.model.DicPictureType;
import com.index.video.model.VideoTrailer;
import com.index.video.pojo.VideoTrailerBean;
import com.index.video.pojo.VideoWallBean;
import com.index.video.service.VideoTrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/4 10:38
 * @Description:
 */
@RestController
@RequestMapping("/video/videoTrailerServer")
public class VideoTrailerRestController {
    @Autowired
    private VideoTrailerService videoTrailerService;

    @RequestMapping("deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return videoTrailerService.deleteByPrimaryKey(id);
    }

    @RequestMapping("deleteAll")
    public int deleteAll(){
        return videoTrailerService.deleteAll();
    }

    @RequestMapping("insertSelective")
    public ResultMap<String> insertSelective(String rows) {
        ResultMap<String> result = new ResultMap<String>();
        try{
            videoTrailerService.deleteAll();// 首先清空新片速达表

            List<VideoTrailer> records = (List<VideoTrailer>) JSONObject.parseArray(rows, VideoTrailer.class);
            videoTrailerService.insertSelective(records);

            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping("updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(VideoTrailer record) {
        return videoTrailerService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("updateByPrimaryKey")
    public int updateByPrimaryKey(VideoTrailer record){
        return videoTrailerService.updateByPrimaryKey(record);
    }

    @RequestMapping("selectByPrimaryKey")
    public VideoTrailerBean selectByPrimaryKey(String id) {
        return videoTrailerService.selectByPrimaryKey(id);
    }

    @RequestMapping("selectAll")
    public List<VideoTrailerBean> selectAll() {
        return videoTrailerService.selectAll();
    }

    @RequestMapping("selectVideoGroupForTrailerByRange")
    public List<VideoTrailerBean> selectVideoGroupForTrailerByRange(@RequestParam(value="start",defaultValue="0",required=false) Integer start, Integer end) {
        return videoTrailerService.selectVideoGroupForTrailerByRange(start,end);
    }
}