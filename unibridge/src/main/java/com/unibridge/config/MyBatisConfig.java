package com.unibridge.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConfig {
   private static SqlSessionFactory sqlSessionFactory;
   
   static {
      try {
         String resource = "./com/unibridge/config/config.xml";
         
         Reader reader = Resources.getResourceAsReader(resource);
         
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
         
      } catch (IOException e) {
         System.out.println("MyBastisConfig.java 초기화 문제 발생");
         e.printStackTrace();
      }
   }
   
   public static SqlSessionFactory getSqlSessionFactory() {
      return sqlSessionFactory;
   }

}
