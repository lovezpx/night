package com.index.picture.service;

import com.index.common.ResultMap;
import com.index.picture.model.Picture;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: Index
 * @Date: 2018/12/11 16:05
 * @Description:
 */
public interface PictureUploadService {
    public void uploadSingleFile(Picture picture);
}
