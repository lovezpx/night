package com.index.picture.service.impl;

import com.index.picture.mapper.PictureGroupMapper;
import com.index.picture.mapper.PictureMapper;
import com.index.picture.model.Picture;
import com.index.picture.pojo.PictureGroupBean;
import com.index.picture.service.PictureUploadService;
import com.index.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.index.configurer.ConstantVarSet.VIDEO_COVER_PICTURE_NUMCODE;

/**
 * @Auther: Index
 * @Date: 2018/12/11 16:05
 * @Description:
 */
@Service
public class PictureUploadServiceImpl implements PictureUploadService {
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private PictureGroupMapper pictureGroupMapper;

    public void uploadSingleFile(Picture picture) {
        // 导入视频集封面
        String picid = UUID.captchaChar(32);

        PictureGroupBean pictureGroup = pictureGroupMapper.selectByNumCode(VIDEO_COVER_PICTURE_NUMCODE);
        picture.setPgid(pictureGroup.getId());
        pictureMapper.insertSelective(picture);
    }
}
