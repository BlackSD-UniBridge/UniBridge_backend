package com.unibridge.app.admin.dao;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.config.MyBatisConfig;

public class AdminReportDetailDAO {
	public SqlSession sqlSession;

	public AdminReportDetailDAO() {
	   sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public String selectSubjectBySubjectNumber(int subjectNumber) {
		return sqlSession.selectOne("mvc.adminReportDetail.searchSubjectName", subjectNumber);
	}
}
