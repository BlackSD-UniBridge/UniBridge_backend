package com.unibridge.app.mypage.mentoring.dao;

import org.apache.ibatis.session.SqlSession;
import com.unibridge.app.mypage.mentoring.dto.MentoringDTO;
import com.unibridge.config.MyBatisConfig;

public class MentoringDAO {
	public SqlSession sqlSession;
	
	public MentoringDAO() {
		try {
			sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
			System.out.println("[DAO] SqlSession 생성 성공");
		} catch (Exception e) {
			System.out.println("[DAO] SqlSession 생성 실패: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	// 멘토링 등록
	public void insert(MentoringDTO mentoringDTO) {
	    try {
	        sqlSession.insert("mentoring.insert", mentoringDTO);
	    } catch (Exception e) {
	        System.out.println("[DAO] insert 중 오류 발생!");
	        e.printStackTrace();
	        throw e; // ★ 중요: 에러를 위로 던져야 컨트롤러가 멈춥니다!
	    }
	}
	
	// 멘토링 상세 조회
	public MentoringDTO select(int interanlId) {
		System.out.println("[DAO] select 호출됨 - ID: " + interanlId);
		MentoringDTO dto = null;
		try {
			dto = sqlSession.selectOne("mentoring.select", interanlId);
			if(dto != null) {
				System.out.println("[DAO] select 성공 - 조회된 제목: " + dto.getMentoringTitle());
			} else {
				System.out.println("[DAO] select 결과 없음 - 해당 ID: " + interanlId);
			}
		} catch (Exception e) {
			System.out.println("[DAO] select 중 오류 발생!");
			e.printStackTrace();
		}
		return dto;
	}
	
	// 멘토링 수정
	public void update(MentoringDTO mentoringDTO) {
		System.out.println("[DAO] update 호출됨 - 수정 대상 ID: " + mentoringDTO.getInternalId());
		try {
			int result = sqlSession.update("mentoring.update", mentoringDTO);
			System.out.println("[DAO] update 성공 여부 (영향받은 행 수): " + result);
		} catch (Exception e) {
			System.out.println("[DAO] update 중 오류 발생!");
			e.printStackTrace();
		}
	}
	
	// 멘토링 삭제
	public void delete(int interanlId) {
		System.out.println("[DAO] delete 호출됨 - 삭제 대상 ID: " + interanlId);
		try {
			int result = sqlSession.delete("mentoring.delete", interanlId);
			System.out.println("[DAO] delete 성공 여부 (영향받은 행 수): " + result);
		} catch (Exception e) {
			System.out.println("[DAO] delete 중 오류 발생!");
			e.printStackTrace();
		}
	}
	
	
}