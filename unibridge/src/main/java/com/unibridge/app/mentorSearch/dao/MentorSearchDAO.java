package com.unibridge.app.mentorSearch.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.mentorSearch.dto.MentorSearchDTO;
import com.unibridge.config.MyBatisConfig;

public class MentorSearchDAO {
	public SqlSession sqlSession;

	public MentorSearchDAO() {
		// MyBatis 설정 파일(sqlMapConfig.xml)을 읽어 세션을 생성합니다.
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 멘토 전체 목록 조회 메서드
	public List<MentorSearchDTO> selectAllMentors() {
	    System.out.println(">>> [LOG] DAO: DB 조회를 시작합니다.");
	    List<MentorSearchDTO> list = sqlSession.selectList("mentorSearch.selectAllMentors");
	    if (list == null || list.isEmpty()) {
	        System.out.println(">>> [LOG] DAO: 조회된 데이터가 0건입니다. DB 조건을 확인하세요.");
	    } else {
	        System.out.println(">>> [LOG] DAO: 조회 성공! 데이터 수: " + list.size());
	    }
	    return list;
	}

	public MentorSearchDTO selectMentorDetail(long memberNumber) {
		return sqlSession.selectOne("mentorSearch.selectMentorDetail", memberNumber);
	}
}