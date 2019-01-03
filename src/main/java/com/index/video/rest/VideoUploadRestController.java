package com.index.video.rest;

import com.alibaba.fastjson.JSONObject;
import com.index.common.ResultMap;
import com.index.utils.PropertiesUtil;
import com.index.utils.TimeUtil;
import com.index.utils.UUID;
import com.index.video.model.Video;
import com.index.video.pojo.VideoUploadBean;
import com.index.video.service.VideoUploadService;
import com.index.video.service.impl.VideoServiceImpl;
import com.index.video.utils.VideoCodecsUtil;
import com.index.video.utils.VideoUploadUtil;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

import static com.index.configurer.ConstantVarSet.SUPER_ADMIN_USER_ID;
import static com.index.configurer.ConstantVarSet.VIDEO_CODECS_STATUS;

/**
 * @Auther: Index
 * @Date: 2018/12/5 12:22
 * @Description:
 */
@RestController
@RequestMapping("/video/videoUploadServer")
public class VideoUploadRestController {
    @Autowired
    private VideoUploadService videoUploadService;
    @Autowired
    private VideoUploadUtil videoUploadUtil;
    @Autowired
    private VideoCodecsUtil videoCodecsUtil;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private VideoServiceImpl videoServiceImpl;

    @Value("${localfile.uploadVideoPath}")
    private String uploadVideoPath;

    private TimeUtil timeUtil = new TimeUtil();

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap<String> upload(String videoGroupStr) {
        VideoUploadBean videoGroupBean = JSONObject.parseObject(videoGroupStr, VideoUploadBean.class);
        return videoUploadService.upload(videoGroupBean);
    }

    @RequestMapping(value = "uploadSingleFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap<Video> uploadSingleFile(@RequestParam(value = "file", required = false) MultipartFile file) {
        ResultMap<Video> result = new ResultMap<Video>();
        Video video = videoUploadUtil.uploadSingleFile(file, propertiesUtil.GetValueByKey("video-upload-subDirectories"));
        if (video != null) {
            result.setSuccess(true);
            result.setData(video);
        } else {
            result.setSuccess(false);
        }

        return result;
    }

    @RequestMapping(value = "videoTransCoding")
    @ResponseBody
    public ResultMap<Video> videoTransCoding(String avid, String videoPath) throws Exception {
        ResultMap<Video> result = new ResultMap<Video>();

        videoPath = videoPath.replace("/f/video/", "");
        File sourceFile = new File(uploadVideoPath + videoPath);
        if (!sourceFile.exists()) {
            result.setSuccess(false);
            result.setMessage("视频文件已删除！");
            return result;
        }

        MultimediaInfo multimediaInfo = videoCodecsUtil.readVideoInfo(uploadVideoPath + videoPath);
        if (!multimediaInfo.getVideo().getDecoder().equals("h264")) {
            String prefix = sourceFile.getName();
            prefix = prefix.substring(0, prefix.lastIndexOf("."));

            String codcFilePath = "夜色网/夜色视频网/上传视频/" + timeUtil.getCurDayDate() + "/" + prefix + "_decoding.mp4";
            videoUploadUtil.checkParentFile(uploadVideoPath + codcFilePath);
            videoCodecsUtil.exchangeToMp4(videoPath, codcFilePath);

            Video video = new Video();
            video.setId(avid);
            video.setUrl("/f/video/" + codcFilePath);
            video.setVideoStatus(VIDEO_CODECS_STATUS);
            video.setUpdateby(SUPER_ADMIN_USER_ID);
            video.setUpdatetime(new Date());

            videoServiceImpl.updateByPrimaryKeySelective(video);

            result.setSuccess(true);
            result.setData(video);
            result.setMessage("视频正在审核，请稍等！");

            return result;
        } else {
            result.setSuccess(false);
            result.setMessage("哎呀！视频源出现致命问题，请等待管理员修复！");
            return result;
        }
    }

    @RequestMapping(value = "doProcessImg")
    @ResponseBody
    public String doProcessImg(String videoPath, String timestamp) {
        return videoCodecsUtil.processImg(videoPath, timestamp);
    }
}
