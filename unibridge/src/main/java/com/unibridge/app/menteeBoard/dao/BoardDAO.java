package com.example.app.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.example.app.board.dto.BoardDTO;
import com.example.app.board.dto.BoardListDTO;
import com.example.config.MyBatisConfig;

public class BoardDAO {
	public SqlSession sqlSession;

	public BoardDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	//게시글 총 개수 가져오는 메소드
	public int getTotal() {
		System.out.println("게시글 총 개수 조회 - getTotal 메소드 실행");
		return sqlSession.selectOne("board.getTotal");
	}
	
	//조회수 증가 메소드
	public void updateReadCount(int boardNumber) {
		System.out.println("조회수 업데이트 실행 : " + boardNumber);
		int result = sqlSession.update("board.updateReadCount", boardNumber);
		System.out.println("조회수 업데이트 결과 : " + result);
	}
	
	//게시글 추가 후 자동으로 생성된 boardNumber 반환 -> 파일 테이블에서도 써야되기 때문에
	public int insertBoard(BoardDTO boardDTO) {
		System.out.println("게시글 작성 - insertBoard 메소드 실행");
		int insert = sqlSession.insert("board.insert", boardDTO);
		System.out.println(boardDTO + "===출력");
//		System.out.println(boardDTO.getBoardContent() + "===출력");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 boardNumber : " + boardDTO.getBoardNumber());
		return boardDTO.getBoardNumber();
	}
	
	//게시글 삭제 메소드
	public void deleteBoard(int boardNumber) {
		System.out.println("게시글 삭제 - deleteBoard 메소드 실행");
		sqlSession.delete("board.delete", boardNumber);
		System.out.println("게시글 삭제 쿼리 실행 완료");
	}
	
	//게시글 수정 메소드
	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("게시글 수정 - updateBoard 메소드 실행");
		sqlSession.update("board.update", boardDTO);
		System.out.println("게시글 수정 쿼리 실행 완료");
	}
	
	//게시글 상세 페이지 조회 메소드
	public BoardListDTO selectBoard(int boardNumber) {
		System.out.println("게시글 상세 페이지 조회(단건조회) - selectBoard 메소드 실행");
		return sqlSession.selectOne("board.select", boardNumber);
	}
	
	//모든 게시글 가져오기
	public List<BoardListDTO> selectAll(Map<String, Integer> pageMap){
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<BoardListDTO> list = sqlSession.selectList("board.selectAll", pageMap);
		System.out.println("조회 결과 : " + list);
		return list;
	}
}








