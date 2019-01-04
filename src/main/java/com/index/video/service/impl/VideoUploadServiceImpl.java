package com.index.video.service.impl;

import com.index.common.ResultMap;
import com.index.picture.mapper.PictureGroupMapper;
import com.index.picture.mapper.PictureMapper;
import com.index.picture.model.Picture;
import com.index.picture.pojo.PictureGroupBean;
import com.index.security.pojo.SecurityUser;
import com.index.utils.UUID;
import com.index.video.mapper.VideoGroupMapper;
import com.index.video.mapper.VideoMapper;
import com.index.video.mapper.VideoTipMapper;
import com.index.video.model.Video;
import com.index.video.model.VideoGroup;
import com.index.video.model.VideoTip;
import com.index.video.pojo.VideoUploadBean;
import com.index.video.service.VideoUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.index.configurer.ConstantVarSet.SUPER_ADMIN_USER_ID;
import static com.index.configurer.ConstantVarSet.VIDEO_COVER_PICTURE_NUMCODE;
import static com.index.configurer.ConstantVarSet.VIDEO_NORMAL_STATUS;

/**
 * @Auther: Index
 * @Date: 2018/12/5 10:20
 * @Description: 视频上传接口
 */
@Service
public class VideoUploadServiceImpl implements VideoUploadService {
    @Autowired
    private VideoGroupMapper videoGroupMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoTipMapper videoTipMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private PictureGroupMapper pictureGroupMapper;

    @Transactional(rollbackFor = Exception.class, timeout = 36000)
    public ResultMap<String> upload(VideoUploadBean videoGroupBean) {
        ResultMap<String> result = new ResultMap<String>();
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            // 导入视频集封面
            Picture picture = videoGroupBean.getPicture();
            String picid = UUID.getPicturePipelined();
            picture.setId(picid);

            PictureGroupBean pictureGroup = pictureGroupMapper.selectByNumCode(VIDEO_COVER_PICTURE_NUMCODE);
            picture.setPgid(pictureGroup.getId());
            Date curDate = new Date();
            picture.setUpdatetime(curDate);
            picture.setUpdateby(securityUser.getId());
            picture.setUploadtime(curDate);
            picture.setUploadby(securityUser.getId());

            pictureMapper.insertSelective(picture);

            // 导入视频集
            String vgid = UUID.getVideoGroupPK();

            VideoGroup videoGroup = videoGroupBean;
            videoGroup.setId(vgid);
            videoGroup.setCoverid(picid);

            videoGroup.setUploadby(securityUser.getId());
            videoGroup.setUploadtime(curDate);
            videoGroup.setUpdateby(securityUser.getId());
            videoGroup.setUpdatetime(curDate);
            videoGroupMapper.insertSelective(videoGroup);

            List<Video> videos = videoGroupBean.getVideoList();
            if (videos != null) {
                for (int i = 0; i < videos.size(); i++) {
                    Video video = videos.get(i);
                    video.setVgid(vgid);
                    videoMapper.updateByPrimaryKeySelective(video);
                }
            }

            List<VideoTip> videoTips = videoGroupBean.getVideoTipList();
            if (videoTips != null) {
                for (int i = 0; i < videoTips.size(); i++) {
                    VideoTip videoTip = videoTips.get(i);
                    videoTip.setId(UUID.captchaChar(32));
                    videoTip.setVgid(vgid);
                    videoTip.setCreateby(videoGroupBean.getUploadby());
                    videoTip.setCreatetime(videoGroupBean.getUploadtime());
                    videoTipMapper.insertSelective(videoTip);
                }
            }

            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(new Date() + "  Error ---[VideoUploadServiceImpl--upload]:" + e.getMessage());
        }

        return result;
    }
}
