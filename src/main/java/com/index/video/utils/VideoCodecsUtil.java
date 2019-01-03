package com.index.video.utils;

import com.index.utils.TimeUtil;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/24 11:02
 * @Description:
 */
@Component
public class VideoCodecsUtil {
    private Logger logger = LoggerFactory.getLogger(VideoCodecsUtil.class);

    @Value("${localfile.uploadVideoPath}")
    private String uploadVideoPath;
    @Value("${localfile.uploadPicturePath}")
    private String uploadPicturePath;
    @Value("${localfile.ffmpegPath}")
    private String ffmpegPath;

    private TimeUtil timeUtil = new TimeUtil();

    /**
     * 视频转码 (PC端MP4)
     *
     * @param upFilePath   用于指定要转换格式的文件,要截图的视频源文件
     * @param codcFilePath 格式转换后的的文件保存路径
     * @return
     * @throws Exception
     */
    public boolean exchangeToMp4(String upFilePath, String codcFilePath) throws Exception {
        // 创建List集合来保存转换视频文件为flv格式的命令
        List<String> convert = new ArrayList<String>();
        convert.add(ClassUtils.getDefaultClassLoader().getResource("").getPath() + ffmpegPath); // 添加转换工具路径
        convert.add("-i");
        convert.add(uploadVideoPath + upFilePath);
        convert.add("-c:v");
        convert.add("h264");
        convert.add(uploadVideoPath + codcFilePath);

        boolean mark = true;

        try {
            Process videoProcess = new ProcessBuilder(convert).redirectErrorStream(true).start();
            new VideoCodecsInterceptor(videoProcess.getInputStream(), "/f/video/" + codcFilePath).start();
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }

    public MultimediaInfo readVideoInfo(String videoPath) {
        File source = new File(videoPath);
        Encoder encoder = new Encoder();
        FileChannel fc = null;
        MultimediaInfo multimediaInfo = new MultimediaInfo();
        try {
            multimediaInfo = encoder.getInfo(source);
            long ls = multimediaInfo.getDuration();

            System.out.println("此视频时长为:" + ls / 60000 + "分" + (ls) / 1000 + "秒！");

            //视频帧宽高
            System.out.println("此视频高度为:" + multimediaInfo.getVideo().getSize().getHeight());
            System.out.println("此视频宽度为:" + multimediaInfo.getVideo().getSize().getWidth());
            System.out.println("此视频格式为:" + multimediaInfo.getFormat());
            System.out.println("此视频编码为:" + multimediaInfo.getVideo().getDecoder());

            FileInputStream fis = new FileInputStream(source);
            fc = fis.getChannel();
            BigDecimal fileSize = new BigDecimal(fc.size());
            String size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";
            System.out.println("此视频大小为" + size);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return multimediaInfo;
    }

    /**
     * 将指定时间的一帧作为封面图片
     *
     * @param videoPath
     * @param timestamp
     * @return
     */
    public String processImg(String videoPath, String timestamp) {
        videoPath = videoPath.replace("/f/video/", "");
        File file = new File(uploadVideoPath + videoPath);
        if (!file.exists()) {
            System.err.println("路径[" + videoPath + "]对应的视频文件不存在!");
            return null;
        }

        String prefix = file.getName();
        prefix = prefix.substring(0, prefix.lastIndexOf("."));
        String picturnSrc = "夜色视频网/上传图片/" + timeUtil.getCurDayDate() + "/" + prefix + "_printscreen.jpg";

        List<String> commands = new java.util.ArrayList<String>();

        commands.add(this.getClass().getResource("/") + ffmpegPath);
        commands.add("-i");
        commands.add(uploadVideoPath + videoPath);
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-ss");
        commands.add(timestamp);//这个参数是设置截取视频多少秒时的画面
        commands.add("-t");
        commands.add("0.001");
        commands.add("-s");
        commands.add("1920x1080");//宽X高
        commands.add(uploadPicturePath + picturnSrc);

        try {
            Process process = new ProcessBuilder(commands).redirectErrorStream(true).start();
            System.out.println("截取成功:" + picturnSrc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "/f/picture/" + picturnSrc;
    }
}
