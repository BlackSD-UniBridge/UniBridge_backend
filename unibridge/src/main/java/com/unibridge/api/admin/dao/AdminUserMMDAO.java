package com.unibridge.api.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.api.admin.dto.AdminUserMMDTO;
import com.unibridge.config.MyBatisConfig;

public class AdminUserMMDAO {
    public SqlSession sqlSession;

    public AdminUserMMDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // 자동 커밋 설정
    }
    
    public List<AdminUserMMDTO>selectAll() {
    	return this.sqlSession.selectList("api.adminUserMM.selectAll"); 
    }
}
