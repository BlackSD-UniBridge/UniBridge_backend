package com.unibridge.app.menteeBoardComment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.menteeBoardComment.dto.MenteeBoardCommentDTO;
import com.unibridge.config.MyBatisConfig;

public class MenteeBoardCommentDAO {

    private SqlSession sqlSession;

    public MenteeBoardCommentDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    /** 댓글 목록 조회 (게시글 번호 기준) */
    public List<MenteeBoardCommentDTO> selectCommentList(int menteeBoardId) {
        System.out.println("댓글 목록 조회 - menteeBoardId : " + menteeBoardId);
        return sqlSession.selectList("MenteeBoardComment.selectCommentList", menteeBoardId);
    }

    /** 댓글 총 개수 */
    public int getCommentTotal(int menteeBoardId) {
        return sqlSession.selectOne("MenteeBoardComment.getCommentTotal", menteeBoardId);
    }

    /** 댓글 등록 */
    public void insertComment(MenteeBoardCommentDTO dto) {
        System.out.println("댓글 등록 - " + dto);
        sqlSession.insert("MenteeBoardComment.insertComment", dto);
    }

    /** 댓글 수정 */
    public void updateComment(MenteeBoardCommentDTO dto) {
        System.out.println("댓글 수정 - menteeComId : " + dto.getMenteeComId());
        sqlSession.update("MenteeBoardComment.updateComment", dto);
    }

    /** 댓글 삭제 */
    public void deleteComment(int menteeComId) {
        System.out.println("댓글 삭제 - menteeComId : " + menteeComId);
        sqlSession.delete("MenteeBoardComment.deleteComment", menteeComId);
    }
}
