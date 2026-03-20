package com.unibridge.app.mypage.surveyMentor.dao;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.mypage.surveyMentor.dto.SurveyMentorDTO;
import com.unibridge.config.MyBatisConfig;

public class SurveyMentorDAO {

	public SurveyMentorDTO selectMentorSurvey(int memberNumber) {
	    try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
	        return session.selectOne("survey.selectMentorSurvey", memberNumber);
	    }
	}
}