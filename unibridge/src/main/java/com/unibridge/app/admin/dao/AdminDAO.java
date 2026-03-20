package com.unibridge.app.admin.dao;


import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.admin.dto.AdminDTO;
import com.unibridge.config.MyBatisConfig;

public class AdminDAO {
   public SqlSession sqlSession;

   public AdminDAO() {
      sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
   }
   
   // 로그인
   public Integer login(AdminDTO adminDTO) {
      Integer admin = sqlSession.selectOne("admin.adlogin", adminDTO);
      return admin == null? -1 : admin;
   }
   
   
   
}
