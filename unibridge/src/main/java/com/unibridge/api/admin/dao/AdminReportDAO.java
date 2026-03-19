package com.unibridge.api.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.api.admin.dto.AdminReportDTO;
import com.unibridge.config.MyBatisConfig;

public class AdminReportDAO {
	public SqlSession sqlSession;

	public AdminReportDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public List<AdminReportDTO> selectAll() {
		return this.sqlSession.selectList("api.adminReport.selectAll");
	}
}
