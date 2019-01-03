package com.index.video.rest;

import com.alibaba.fastjson.JSONObject;
import com.index.common.ResultMap;
import com.index.management.model.DicVideoType;
import com.index.management.service.DicVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 16:51
 * @Description:
 */
@RestController
@RequestMapping("/video/dicVideoType")
public class VideoTypeRestController {
    @Autowired
    private DicVideoTypeService dicVideoTypeService;

    @RequestMapping("selectByIsUsing")
    public List<DicVideoType> selectByIsUsing(Integer isusing) {
        return dicVideoTypeService.selectByIsUsing(isusing);
    }
}
