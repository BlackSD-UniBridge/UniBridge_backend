package com.unibridge.app.menteeBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.menteeBoard.dto.MenteeBoardDTO;
import com.unibridge.app.menteeBoard.dto.MenteeBoardListDTO;
import com.unibridge.config.MyBatisConfig;

public class MenteeBoardDAO {
	public SqlSession sqlSession;

	public MenteeBoardDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	//게시글 총 개수 가져오는 메소드
	public int getTotal() {
		System.out.println("게시글 총 개수 조회 - getTotal 메소드 실행");
		return sqlSession.selectOne("Menteeboard.getTotal");
	}
	
	//조회수 증가 메소드
	public void updateClick(int MenteeboardNumber) {
		System.out.println("조회수 업데이트 실행 : " + MenteeboardNumber);
		int result = sqlSession.update("Menteeboard.updateClick", MenteeboardNumber);
		System.out.println("조회수 업데이트 결과 : " + result);
	}
	
	//게시글 추가 후 자동으로 생성된 boardNumber 반환 -> 파일 테이블에서도 써야되기 때문에
	public int insertBoard(MenteeBoardDTO boardDTO) {
		System.out.println("게시글 작성 - insertBoard 메소드 실행");
		int insert = sqlSession.insert("Menteeboard.insert", boardDTO);
		System.out.println(boardDTO + "===출력");
//		System.out.println(boardDTO.getBoardContent() + "===출력");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 MenteeboardNumber : " + boardDTO.getMenteeBoardNumber());
		return boardDTO.getMenteeBoardNumber();
	}
	
	//댓글 삭제 메소드
	public void deleteCommentByBoard(int menteeBoardNumber) {
	    System.out.println("댓글 삭제 - deleteCommentByBoard 메소드 실행");
	    sqlSession.delete("Menteeboard.deleteCommentByBoard", menteeBoardNumber);
	}
	
	//게시글 삭제 메소드
	public void deleteBoard(int MenteeboardNumber) {
		System.out.println("게시글 삭제 - deleteBoard 메소드 실행");
		sqlSession.delete("Menteeboard.delete", MenteeboardNumber);
		System.out.println("게시글 삭제 쿼리 실행 완료");
	}
	
	//게시글 수정 메소드
	public void updateBoard(MenteeBoardDTO boardDTO) {
		System.out.println("게시글 수정 - updateBoard 메소드 실행");
		sqlSession.update("Menteeboard.update", boardDTO);
		System.out.println("게시글 수정 쿼리 실행 완료");
	}
	
	//게시글 상세 페이지 조회 메소드
	public MenteeBoardListDTO selectBoard(int MenteeboardNumber) {
		System.out.println("게시글 상세 페이지 조회(단건조회) - selectBoard 메소드 실행");
		return sqlSession.selectOne("Menteeboard.select", MenteeboardNumber);
	}
	
	//모든 게시글 가져오기
	public List<MenteeBoardListDTO> selectAll(Map<String, Integer> pageMap){
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<MenteeBoardListDTO> list = sqlSession.selectList("Menteeboard.selectAll", pageMap);
		System.out.println("조회 결과 : " + list);
		return list;
	}
}








