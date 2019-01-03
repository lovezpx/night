package com.index.video.utils;

import com.index.utils.TimeUtil;
import com.index.utils.UUID;
import com.index.video.mapper.VideoMapper;
import com.index.video.model.Video;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.index.configurer.ConstantVarSet.SUPER_ADMIN_USER_ID;
import static com.index.configurer.ConstantVarSet.VIDEO_CODECS_STATUS;
import static com.index.configurer.ConstantVarSet.VIDEO_NORMAL_STATUS;

/**
 * @Auther: Index
 * @Date: 2018/12/11 16:53
 * @Description:
 */
@Component
public class VideoUploadUtil {
    @Value("${localfile.uploadVideoPath}")
    private String uploadVideoPath;

    private TimeUtil timeUtil = new TimeUtil();

    @Autowired
    private VideoCodecsUtil videoCodecsUtil;
    @Autowired
    private VideoMapper videoMapper;

    /**
     * @return
     * @function 单文件上传
     */
    public Video uploadSingleFile(MultipartFile file, String saveUrl) {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String prefix = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        String videoPath = saveUrl + timeUtil.getCurDayDate() + "/" + prefix + suffix;

        try {
            Video video = new Video();
            video.setId(UUID.getVideoPipelined());
            video.setName(prefix);
            video.setUpdatetime(new Date());
            video.setUpdateby(SUPER_ADMIN_USER_ID);
            video.setUploadtime(new Date());
            video.setUploadby(SUPER_ADMIN_USER_ID);

            file.transferTo(checkParentFile(uploadVideoPath + videoPath));
            MultimediaInfo multimediaInfo = videoCodecsUtil.readVideoInfo(uploadVideoPath + videoPath);
            if (!multimediaInfo.getVideo().getDecoder().equals("h264")) {
                String codcFilePath = saveUrl + timeUtil.getCurDayDate() + "/" + prefix + "_decoded" + ".mp4";
                videoCodecsUtil.exchangeToMp4(videoPath, codcFilePath);

                video.setVideoStatus(VIDEO_CODECS_STATUS);
                video.setUrl("/f/video/" + codcFilePath);
            } else {
                video.setVideoStatus(VIDEO_NORMAL_STATUS);
                video.setUrl("/f/video/" + videoPath);
            }

            videoMapper.insertSelective(video);

            return video;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检查父目录是否存在
     *
     * @param fileUrl
     */
    public File checkParentFile(String fileUrl) {
        File file = new File(fileUrl);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (file.exists()) {
            file.delete();
        }

        return file;
    }
}
