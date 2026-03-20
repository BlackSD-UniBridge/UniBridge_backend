package com.unibridge.app.mentorBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.mentorBoard.dto.MentorBoardDTO;
import com.unibridge.app.mentorBoard.dto.MentorBoardListDTO;
import com.unibridge.config.MyBatisConfig;

public class MentorBoardDAO {
	public SqlSession sqlSession;

	public MentorBoardDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	// 게시글 총 개수
	public int getTotal() {
		System.out.println("게시글 총 개수 조회 - getTotal 메소드 실행");
		return sqlSession.selectOne("Mentorboard.getTotal");
	}
	
	// 조회수 증가
	public void updateClick(int mentorBoardNumber) {
		System.out.println("조회수 업데이트 실행 : " + mentorBoardNumber);
		int result = sqlSession.update("Mentorboard.updateClick", mentorBoardNumber);
		System.out.println("조회수 업데이트 결과 : " + result);
	}
	
	// 게시글 추가
	public int insertBoard(MentorBoardDTO boardDTO) {
		System.out.println("게시글 작성 - insertBoard 메소드 실행");
		int insert = sqlSession.insert("Mentorboard.insert", boardDTO);
		System.out.println(boardDTO + "===출력");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 MentorBoardNumber : " + boardDTO.getMentorBoardNumber());
		return boardDTO.getMentorBoardNumber();
	}
	
	// 댓글 삭제 (게시글 삭제 전)
	public void deleteCommentByBoard(int mentorBoardNumber) {
		System.out.println("댓글 삭제 - deleteCommentByBoard 메소드 실행");
		sqlSession.delete("Mentorboard.deleteCommentByBoard", mentorBoardNumber);
	}
	
	// 게시글 삭제
	public void deleteBoard(int mentorBoardNumber) {
		System.out.println("게시글 삭제 - deleteBoard 메소드 실행");
		sqlSession.delete("Mentorboard.delete", mentorBoardNumber);
		System.out.println("게시글 삭제 쿼리 실행 완료");
	}
	
	// 게시글 수정
	public void updateBoard(MentorBoardDTO boardDTO) {
		System.out.println("게시글 수정 - updateBoard 메소드 실행");
		sqlSession.update("Mentorboard.update", boardDTO);
		System.out.println("게시글 수정 쿼리 실행 완료");
	}
	
	// 게시글 단건 조회
	public MentorBoardListDTO selectBoard(int mentorBoardNumber) {
		System.out.println("게시글 상세 페이지 조회(단건조회) - selectBoard 메소드 실행");
		return sqlSession.selectOne("Mentorboard.select", mentorBoardNumber);
	}
	
	// 전체 게시글 조회
	public List<MentorBoardListDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<MentorBoardListDTO> list = sqlSession.selectList("Mentorboard.selectAll", pageMap);
		System.out.println("조회 결과 : " + list);
		return list;
	}
}
