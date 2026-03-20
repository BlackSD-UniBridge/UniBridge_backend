package com.unibridge.app.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.admin.dto.AdMenteeBoardListDTO;
import com.unibridge.config.MyBatisConfig;

public class AdMenteeBoardDAO {
	
	   public SqlSession sqlSession;

	   public AdMenteeBoardDAO() {
	      sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	   }

		public int getTotal() {
			System.out.println("게시글 총 개수 조회 - getTotal 메소드 실행");
			return sqlSession.selectOne("admin.menteeGetTotal");
		}
	   
		//멘티 게시판 전체 목록 확인
		public List<AdMenteeBoardListDTO> selectAll(Map<String, Integer> pageRow){
			System.out.println("모든 게시글 조회하기");
			List<AdMenteeBoardListDTO> list = sqlSession.selectList("admin.menteeSelectAll", pageRow);
			System.out.println("조회 결과 " + list);
			return list;
		}
		
		
	   //멘티 게시판 필터링 목록 확인
		public  List<AdMenteeBoardListDTO> selectFilter(Map<String, Integer> pagefilter){
			System.out.println("랜더링 게시글 조회하기 - selectFilter 메소드 실행 : " + pagefilter);
			List<AdMenteeBoardListDTO> list = sqlSession.selectList("admin.menteeSelectFilter", pagefilter);
			System.out.println("조회 결과 : " + list);
			return list;
		}
		
		//멘티 게시판 조회수
		public void menteeUpdateReadCount(int boardNumber) {
			System.out.println("조회수 업데이트 실행");
			int result = sqlSession.update("admin.menteeUpdateReadCount", boardNumber);
			System.out.println("조회수 업데이트 완료" + result);
		}
		
		
}
