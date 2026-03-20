package com.unibridge.app.admin.dao;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.config.MyBatisConfig;

public class AdminReportDeleteDAO {
	public SqlSession sqlSession;

	public AdminReportDeleteDAO() {
	   sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public int deleteReportByReportNumber(int reportNumber) {
		return sqlSession.delete("mvc.adminReportDelete.deleteReport", reportNumber);
	}
}
