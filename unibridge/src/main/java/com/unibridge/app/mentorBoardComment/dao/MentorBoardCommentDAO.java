package com.unibridge.app.mentorBoardComment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.mentorBoardComment.dto.MentorBoardCommentDTO;
import com.unibridge.config.MyBatisConfig;

public class MentorBoardCommentDAO {

    private SqlSession sqlSession;

    public MentorBoardCommentDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    /** 댓글 목록 조회 (게시글 번호 기준) */
    public List<MentorBoardCommentDTO> selectCommentList(int mentorBoardId) {
        System.out.println("댓글 목록 조회 - mentorBoardId : " + mentorBoardId);
        return sqlSession.selectList("MentorBoardComment.selectCommentList", mentorBoardId);
    }

    /** 댓글 총 개수 */
    public int getCommentTotal(int mentorBoardId) {
        return sqlSession.selectOne("MentorBoardComment.getCommentTotal", mentorBoardId);
    }

    /** 댓글 등록 */
    public void insertComment(MentorBoardCommentDTO dto) {
        System.out.println("댓글 등록 - " + dto);
        sqlSession.insert("MentorBoardComment.insertComment", dto);
    }

    /** 댓글 수정 */
    public void updateComment(MentorBoardCommentDTO dto) {
        System.out.println("댓글 수정 - mentorComId : " + dto.getMentorComId());
        sqlSession.update("MentorBoardComment.updateComment", dto);
    }

    /** 댓글 삭제 */
    public void deleteComment(int mentorComId) {
        System.out.println("댓글 삭제 - mentorComId : " + mentorComId);
        sqlSession.delete("MentorBoardComment.deleteComment", mentorComId);
    }
}
