package com.index.configurer;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @Auther: Index
 * @Date: 2018/12/11 17:20
 * @Description:
 */
@Configuration
public class FileUploadConfiguration {
    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("10240000KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400000KB");
        return factory.createMultipartConfig();
    }
}
