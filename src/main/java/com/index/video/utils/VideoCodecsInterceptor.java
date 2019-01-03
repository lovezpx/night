package com.index.video.utils;

import com.index.utils.SpringUtil;
import com.index.video.model.Video;
import com.index.video.service.impl.VideoServiceImpl;

import java.io.InputStream;

import static com.index.configurer.ConstantVarSet.VIDEO_CODECS_FAILURE_STATUS;
import static com.index.configurer.ConstantVarSet.VIDEO_CODECS_STATUS;
import static com.index.configurer.ConstantVarSet.VIDEO_CODECS_SUCCESS_STATUS;

/**
 * 视频转码拦截
 *
 * @Auther: Index
 * @Date: 2018/12/25 17:03
 * @Description: 对转码进程进行监听，当读入流关闭时维护视频状态字段
 */
public class VideoCodecsInterceptor extends Thread {
    private InputStream inputStream;
    private String url;
    private Video video = new Video();

    private VideoServiceImpl videoServiceImpl = SpringUtil.getBean(VideoServiceImpl.class);

    public VideoCodecsInterceptor(InputStream is, String cp) {
        this.inputStream = is;
        this.url = cp;
    }

    public void run() {
        try {
            while (this != null) {
                int _ch = inputStream.read();
                if (_ch != -1)
                    System.out.print((char) _ch);
                else {
                    System.out.print("转码完成！");
                    System.out.println();

                    if (url != null) {
                        video.setVideoStatus(VIDEO_CODECS_SUCCESS_STATUS);
                        video.setUrl(url);

                        videoServiceImpl.updateVideoStatus(video);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            if (url != null) {
                video.setVideoStatus(VIDEO_CODECS_FAILURE_STATUS);
                video.setUrl(url);

                videoServiceImpl.updateVideoStatus(video);
            }
            e.printStackTrace();
        }
    }
}
