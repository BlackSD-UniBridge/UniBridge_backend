package com.unibridge.app.noticeBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.noticeBoard.dto.NoticeBoardListDTO;
import com.unibridge.config.MyBatisConfig;

public class NoticeBoardDAO {
	public SqlSession sqlSession;

	public NoticeBoardDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	//게시글 총 개수 가져오는 메소드
	public int getTotal() {
		System.out.println("게시글 총 개수 조회 - getTotal 메소드 실행");
		return sqlSession.selectOne("noticeBoard.getTotal");
	}
	
	//조회수 증가 메소드
	public void updateClick(int fileNumber) {
		System.out.println("조회수 업데이트 실행 : " + fileNumber);
		int result = sqlSession.update("noticeBoard.updateClick", fileNumber);
		System.out.println("조회수 업데이트 결과 : " + result);
	}
	
	
	//게시글 상세 페이지 조회 메소드
	public NoticeBoardListDTO selectBoard(int fileNumber) {
		System.out.println("게시글 상세 페이지 조회(1건조회) - selectBoard 메소드 실행");
		return sqlSession.selectOne("noticeBoard.select", fileNumber);
	}
	
	//대회정보 조회
	public NoticeBoardListDTO selectByContestNumber(int contestNumber) {
        System.out.println("대회번호로 공지사항 조회(contestNumber=" + contestNumber + ") - selectByContestNumber 실행");
        return sqlSession.selectOne("noticeBoard.selectByContestNumber", contestNumber);
    }
	
	//모든 게시글 가져오기
	public List<NoticeBoardListDTO> selectAll(Map<String, Integer> pageMap){
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<NoticeBoardListDTO> list = sqlSession.selectList("noticeBoard.selectAll", pageMap);
		System.out.println("조회 결과 : " + list);
		return list;
	}
}








