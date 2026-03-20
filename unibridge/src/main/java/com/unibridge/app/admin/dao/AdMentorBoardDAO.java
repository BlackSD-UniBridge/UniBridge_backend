package com.unibridge.app.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.admin.dto.AdMentorBoardListDTO;
import com.unibridge.config.MyBatisConfig;

public class AdMentorBoardDAO {
	
	   public SqlSession sqlSession;

	   public AdMentorBoardDAO() {
	      sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	   }

		public int getTotal() {
			System.out.println("게시글 총 개수 조회 - getTotal 메소드 실행");
			return sqlSession.selectOne("admin.mentorGetTotal");
		}
	   
		//멘토 게시판 전체 목록 확인
		public List<AdMentorBoardListDTO> selectAll(Map<String, Integer> pageRow){
			System.out.println("모든 게시글 조회하기");
			List<AdMentorBoardListDTO> list = sqlSession.selectList("admin.mentorSelectAll", pageRow);
			System.out.println("조회 결과 " + list);
			return list;
		}
		
		
	   //멘토 게시판 필터링 목록 확인
		public  List<AdMentorBoardListDTO> selectFilter(Map<String, Integer> pagefilter){
			System.out.println("랜더링 게시글 조회하기 - selectFilter 메소드 실행 : " + pagefilter);
			List<AdMentorBoardListDTO> list = sqlSession.selectList("admin.mentorSelectFilter", pagefilter);
			System.out.println("조회 결과 : " + list);
			return list;
		}
		
		
}
