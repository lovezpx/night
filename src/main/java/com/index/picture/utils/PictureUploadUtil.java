package com.index.picture.utils;

import com.index.utils.TimeUtil;
import com.index.utils.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/11 14:28
 * @Description:
 */
@Component
public class PictureUploadUtil {
    @Value("${localfile.uploadPicturePath}")
    private String uploadPicturePath;

    private TimeUtil timeUtil = new TimeUtil();

    /**
     * @return
     * @function 单文件上传
     */
    public String uploadSingleFile(MultipartFile file, String saveUrl) {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String prefix = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        String fileName = prefix + suffix;
        String picturePath = saveUrl + timeUtil.getCurDayDate() + "/" + fileName;

        File saveFile = new File(uploadPicturePath + picturePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return "/f/picture/" + picturePath;
    }

    /**
     * @return
     * @function 多文件上传
     */
    public List<String> uploadMultipartFile(MultipartFile[] files, String saveUrl, String fileType) {
        List<String> picList = new ArrayList<String>();
        String fileUrl = saveUrl + "/" + timeUtil.getCurDayDate() + "/";

        File saveDir = new File(uploadPicturePath + fileUrl);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file != null) {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileName = UUID.getPicturePipelined() + suffix;
                String picturePath = fileUrl + fileName;
                File saveFile = new File(uploadPicturePath + picturePath);
                try {
                    file.transferTo(saveFile);
                    picList.add("/f/picture/" + picturePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("上传失败");
                }
            }
        }
        return picList;
    }
}
