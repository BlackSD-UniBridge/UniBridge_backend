package com.unibridge.app.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.main.dto.MainDTO.CompanyDTO;
import com.unibridge.app.main.dto.MainDTO.ContestDTO;
import com.unibridge.app.main.dto.MainDTO.MentorCardDTO;
import com.unibridge.config.MyBatisConfig;

public class MainDAO {

    private SqlSession sqlSession;

    public MainDAO() {
        // true: auto-commit (SELECT만 사용하므로 문제없음)
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    /**
     * 메인 페이지에 표시할 진행중인 대회 목록을 조회합니다.
     */
    public List<ContestDTO> getContestList() {
        return sqlSession.selectList("main.getContestList");
    }

    /**
     * 메인 페이지에 표시할 추천 멘토 목록을 조회합니다.
     */
    public List<MentorCardDTO> getMentorCardList() {
        return sqlSession.selectList("main.getMentorCardList");
    }
    
    //취업 회사 목록 조회
    public List<CompanyDTO> getCompanyList() {
        return sqlSession.selectList("main.getCompanyList");
    }
}
