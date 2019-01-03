package com.index.picture.rest;

import com.index.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Auther: Index
 * @Date: 2019/1/3 14:35
 * @Description:
 */
@RestController
@RequestMapping("/picture/pictureServer")
public class PictureRestController {

    @Autowired
    private PictureService pictureService;

    @Value("${localfile.uploadPicturePath}")
    private String uploadPicturePath;

    @RequestMapping("/findPicSrcByPrimaryKey")
    public void findPicSrcByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String src = pictureService.findPicSrcByPrimaryKey(request.getParameter("id")).replace("/f/picture/", uploadPicturePath);
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(src);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
