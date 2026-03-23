package com.unibridge.app.admin.dao;


import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.admin.dto.AdminUserMMDetailDTO;
import com.unibridge.config.MyBatisConfig;

public class AdminUserMMDetailDAO {
	public SqlSession sqlSession;

	public AdminUserMMDetailDAO() {
	   sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public AdminUserMMDetailDTO selectUserMMDetail(int userNumber) {
		return sqlSession.selectOne("mvc.adminUserMMDetail.selectUserDetail", userNumber);
	}
}
