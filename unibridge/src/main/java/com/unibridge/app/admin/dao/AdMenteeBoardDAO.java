package com.unibridge.app.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.admin.dto.AdMenteeBoardCommentDTO;
import com.unibridge.app.admin.dto.AdMenteeBoardDTO;
import com.unibridge.app.admin.dto.AdMenteeBoardListDTO;
import com.unibridge.config.MyBatisConfig;

public class AdMenteeBoardDAO {

	public SqlSession sqlSession;

	public AdMenteeBoardDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 총 개수 조회
	public int getTotal() {
		System.out.println("게시글 총 개수 조회 - getTotal");
		return sqlSession.selectOne("admin.menteeGetTotal");
	}

	// 필터링 개수 조회
	public int getRenderingTotal(Map<String, Integer> pagefilter) {
		System.out.println("게시글 필터 후 개수 조회 - getRenderingTotal");
		return sqlSession.selectOne("admin.menteeGetRenderingTotal", pagefilter);
	}

	// 멘티 게시판 전체 목록 확인
	public List<AdMenteeBoardListDTO> selectAll(Map<String, Integer> pageRow) {
		System.out.println("모든 게시글 조회하기");
		List<AdMenteeBoardListDTO> list = sqlSession.selectList("admin.menteeSelectAll", pageRow);
		System.out.println("조회 결과 " + list);
		return list;
	}

	// 멘티 게시판 필터링 목록 확인
	public List<AdMenteeBoardListDTO> selectFilter(Map<String, Integer> pagefilter) {
		System.out.println("랜더링 게시글 조회하기 - selectFilter 메소드 실행 : " + pagefilter);
		List<AdMenteeBoardListDTO> list = sqlSession.selectList("admin.menteeSelectFilter", pagefilter);
		System.out.println("조회 결과 : " + list);
		return list;
	}

	// 멘티 게시판 상세 보기
	public AdMenteeBoardDTO selectPage(int boardNumber) {
		System.out.println("게시글 조회");
		AdMenteeBoardDTO mentee = sqlSession.selectOne("admin.menteeSelectOne", boardNumber);
		return mentee;
	}

	// 멘티 게시판 작성
	public int insertBoard(AdMenteeBoardDTO boardDTO) {
		System.out.println("게시글 작성 - insertBoard 메소드 실행");
		int insert = sqlSession.insert("admin.menteeInsert", boardDTO);
		System.out.println(boardDTO + "===출력");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 boardNumber : " + boardDTO.getMenteeboardNumber());
		return boardDTO.getMenteeboardNumber();
	}

	// 멘티 게시판 수정
	public void updateBoard(AdMenteeBoardDTO boardDTO) {
		System.out.println("게시글 수정 - updateBoard 메소드 실행");
		sqlSession.update("admin.menteeUpdate", boardDTO);
		System.out.println("게시글 수정 쿼리 실행 완료");
	}

	// 멘티 게시판 삭제
	public void deleteBoard(int boardNumber) {
		System.out.println("게시글 삭제 - deleteBoard 메소드 실행");
		sqlSession.delete("admin.menteeDeleteAllComment", boardNumber);
		sqlSession.delete("admin.menteeDelete", boardNumber);
		System.out.println("게시글 삭제 쿼리 실행 완료");
	}

	// 멘티 게시판 댓글 조회
	public List<AdMenteeBoardCommentDTO> selectComments(int boardNumber) {
		System.out.println("멘티 게시판 댓글 조회 시작 : " + boardNumber);
		return sqlSession.selectList("admin.menteeSelectCommentList", boardNumber);
	}

}
