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
    public List<com.unibridge.app.mentorSearch.dto.MentorSearchDTO> selectAllMentors() {
        // mapper의 namespace.id 형식으로 호출합니다.
        return sqlSession.selectList("mentorSearch.selectAllMentors");
    }
    
    public MentorSearchDTO selectMentorDetail(long memberNumber) {
        return sqlSession.selectOne("mentorSearch.selectMentorDetail", memberNumber);
    }
}