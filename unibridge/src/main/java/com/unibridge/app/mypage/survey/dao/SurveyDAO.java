package com.unibridge.app.mypage.survey.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.unibridge.app.file.dto.FileDTO;
import com.unibridge.app.mypage.surveyMentee.dto.SurveyMenteeDTO;
import com.unibridge.app.mypage.surveyMentor.dto.SurveyMentorDTO;
import com.unibridge.config.MyBatisConfig;

public class SurveyDAO {
	private SqlSessionFactory factory = MyBatisConfig.getSqlSessionFactory();		

	public void insertMenteeSurvey(SurveyMenteeDTO menteeDTO, FileDTO fileDTO) {
	    try (SqlSession session = factory.openSession(false)) { // 수동 커밋 모드
	        try {
	            // 1단계: 파일이 있으면 저장 후 fileNumber 세팅
	            if (fileDTO != null) {
	                session.insert("file.insertFile", fileDTO);
	                menteeDTO.setFileNumber(fileDTO.getFileNumber()); 
	            }

	            // 2단계: UB_SURVEY 공통 저장 (selectKey로 surveyNumber 채워짐)
	            session.insert("survey.insertSurvey", menteeDTO);

	            // 3단계: UB_SURVEY_MENTEE 상세 저장
	            session.insert("survey.insertMenteeSurvey", menteeDTO);

	            session.commit(); // 전체 트랜잭션 확정
	        } catch (Exception e) {
	            session.rollback(); // 에러 시 롤백
	            e.printStackTrace();
	        }
	    }
	}
    
    public void insertMentorSurvey(SurveyMentorDTO mentorDTO, FileDTO fileDTO) {
        try (SqlSession session = factory.openSession(false)) {
        	try {
                // 2. 파일이 첨부되었다면 파일 정보 먼저 저장 (UB_FILE)
        		Integer fileNumber = null; // 🔥 핵심

        		if (fileDTO != null) {
        		    session.insert("file.insertFile", fileDTO);
        		    System.out.println("[DB] 1단계: 파일 정보 저장 완료 (FileNo: " + fileDTO.getFileNumber() + ")");
        		    fileNumber = fileDTO.getFileNumber();
        		}

        		mentorDTO.setFileNumber(fileNumber); // 🔥 반드시 실행

                // 3. 설문 공통 정보 저장 (UB_SURVEY)
                // 매퍼의 selectKey에 의해 menteeDto에 surveyNumber가 채워짐
                session.insert("survey.insertSurvey", mentorDTO);
                System.out.println("[DB] 2단계: 설문 공통 정보 저장 완료 (SurveyNo: " + mentorDTO.getSurveyNumber() + ")");

                // 4. 멘티 상세 정보 저장 (UB_SURVEY_MENTEE)
                // 위에서 채워진 surveyNumber를 외래키로 사용함
                session.insert("survey.insertMentorSurvey", mentorDTO);
                System.out.println("[DB] 3단계: 멘토 상세 정보 저장 완료");

                // 5. 모든 과정 성공 시 commit
                session.commit();
            } catch (Exception e) {
                session.rollback(); // 에러 발생 시 전체 취소
                System.out.println("[DB] 에러 발생 - ROLLBACK 실행");
                e.printStackTrace();
            }
        }
    }
    
 

}
