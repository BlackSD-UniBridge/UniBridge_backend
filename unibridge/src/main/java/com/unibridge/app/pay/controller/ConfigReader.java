package com.unibridge.app.pay.controller;

import java.util.Properties;
import java.io.InputStream;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        String fileName = "context.properties"; 
        
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is != null) {
                props.load(is);
                System.out.println(">>> [SUCCESS] context.properties 로드 성공!");
            } else {
                System.err.println(">>> [ERROR] " + fileName + " 파일을 찾을 수 없습니다. (위치: src/main/resources)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}