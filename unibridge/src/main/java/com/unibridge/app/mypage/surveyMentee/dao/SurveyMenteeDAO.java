package com.unibridge.app.mypage.surveyMentee.dao;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.mypage.surveyMentee.dto.SurveyMenteeDTO;
import com.unibridge.config.MyBatisConfig;

public class SurveyMenteeDAO {

	public SurveyMenteeDTO selectMenteeSurvey(int memberNumber) {
	    try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
	        return session.selectOne("survey.selectMenteeSurvey", memberNumber);
	    }
	}
}