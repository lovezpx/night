package com.index.picture.rest;

import com.index.common.ResultMap;
import com.index.picture.model.Picture;
import com.index.picture.pojo.PictureGroupBean;
import com.index.picture.service.PictureGroupService;
import com.index.picture.utils.PictureUploadUtil;
import com.index.utils.PropertiesUtil;
import com.index.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import static com.index.configurer.ConstantVarSet.SUPER_ADMIN_USER_ID;
import static com.index.configurer.ConstantVarSet.VIDEO_COVER_PICTURE_NUMCODE;

/**
 * @Auther: Index
 * @Date: 2018/12/5 12:22
 * @Description:
 */
@RestController
@RequestMapping("/picture/pictureUploadServer")
public class PictureUploadRestController {
    @Autowired
    private PictureUploadUtil pictureUploadUtil;
    @Autowired
    private PropertiesUtil propertiesUtil;

    @RequestMapping(value = "uploadSingleFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap<String> uploadSingleFile(@RequestParam(value = "file", required = false) MultipartFile file) {
        ResultMap<String> result = new ResultMap<String>();
        String picSrc = pictureUploadUtil.uploadSingleFile(file, propertiesUtil.GetValueByKey("picture-upload-subDirectories"));
        if (picSrc != null) {
            result.setSuccess(true);
            result.setData(picSrc);
        } else {
            result.setSuccess(false);
        }

        return result;
    }
}
