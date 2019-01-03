package com.index.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: Index
 * @Date: 2018/12/5 12:22
 * @Description: 读取配置文件公共方法
 */
@Component
public class PropertiesUtil {

    @Value("${configfile}")
    private String filePath;

    /**
     * @param key
     * @return String
     * @throws
     * @Title: GetValueByKey
     * @Description: 根据Key读取Value
     */
    public String GetValueByKey(String key) {
        Properties properties = new Properties();
        try {
            InputStreamReader inputStream = new InputStreamReader(ClassLoader.getSystemResourceAsStream(filePath), "UTF-8");
            properties.load(inputStream);
            String value = properties.getProperty(key);
            return value;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return Map<String ,   String>
     * @throws IOException
     * @Title: GetAllProperties
     * @Description: 读取Properties的全部信息
     */
    public Map<String, String> GetAllProperties() throws IOException {
        Properties properties = new Properties();
        InputStreamReader inputStream = new InputStreamReader(ClassLoader.getSystemResourceAsStream(filePath), "UTF-8");
        properties.load(inputStream);
        Enumeration en = properties.propertyNames(); // 得到配置文件的名字

        Map<String, String> map = new HashMap<String, String>();
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = properties.getProperty(strKey);

            map.put(strKey, strValue);
        }

        return map;
    }
}
