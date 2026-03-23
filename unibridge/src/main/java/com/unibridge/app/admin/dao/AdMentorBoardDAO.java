package com.unibridge.app.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.admin.dto.AdMentorBoardCommentDTO;
import com.unibridge.app.admin.dto.AdMentorBoardDTO;
import com.unibridge.app.admin.dto.AdMentorBoardListDTO;
import com.unibridge.config.MyBatisConfig;

public class AdMentorBoardDAO {

	public SqlSession sqlSession;

	public AdMentorBoardDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 총 개수 조회
	public int getTotal() {
		System.out.println("게시글 총 개수 조회 - getTotal");
		return sqlSession.selectOne("admin.mentorGetTotal");
	}

	// 필터링 개수 조회
	public int getRenderingTotal(Map<String, Integer> pagefilter) {
		System.out.println("게시글 필터 후 개수 조회 - getRenderingTotal");
		return sqlSession.selectOne("admin.mentorGetRenderingTotal", pagefilter);
	}

	// 멘토 게시판 전체 목록 확인
	public List<AdMentorBoardListDTO> selectAll(Map<String, Integer> pageRow) {
		System.out.println("모든 게시글 조회하기");
		List<AdMentorBoardListDTO> list = sqlSession.selectList("admin.mentorSelectAll", pageRow);
		System.out.println("조회 결과 " + list);
		return list;
	}

	// 멘토 게시판 필터링 목록 확인
	public List<AdMentorBoardListDTO> selectFilter(Map<String, Integer> pagefilter) {
		System.out.println("랜더링 게시글 조회하기 - selectFilter 메소드 실행 : " + pagefilter);
		List<AdMentorBoardListDTO> list = sqlSession.selectList("admin.mentorSelectFilter", pagefilter);
		System.out.println("조회 결과 : " + list);
		return list;
	}

	// 멘토 게시판 상세 보기
	public AdMentorBoardDTO selectPage(int boardNumber) {
		System.out.println("게시글 조회");
		AdMentorBoardDTO mentee = sqlSession.selectOne("admin.mentorSelectOne", boardNumber);
		return mentee;
	}

	// 멘토 게시판 작성
	public int insertBoard(AdMentorBoardDTO boardDTO) {
		System.out.println("게시글 작성 - insertBoard 메소드 실행");
		int insert = sqlSession.insert("admin.mentorInsert", boardDTO);
		System.out.println(boardDTO + "===출력");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 boardNumber : " + boardDTO.getMentorboardNumber());
		return boardDTO.getMentorboardNumber();
	}

	// 멘토 게시판 수정
	public void updateBoard(AdMentorBoardDTO boardDTO) {
		System.out.println("게시글 수정 - updateBoard 메소드 실행");
		sqlSession.update("admin.mentorUpdate", boardDTO);
		System.out.println("게시글 수정 쿼리 실행 완료");
	}

	// 멘티 게시판 삭제
	public void deleteBoard(int boardNumber) {
		System.out.println("게시글 삭제 - deleteBoard 메소드 실행");
		sqlSession.delete("admin.mentorDeleteAllComment", boardNumber);
		sqlSession.delete("admin.mentorDelete", boardNumber);
		System.out.println("게시글 삭제 쿼리 실행 완료");
	}

	// 멘토 게시판 뎃글 조회
	public List<AdMentorBoardCommentDTO> selectComments(int boardNumber) {
		System.out.println("멘토 게시판 댓글 조회 시작 : " + boardNumber);
		return sqlSession.selectList("admin.mentorSelectCommentList", boardNumber);
	}

}
