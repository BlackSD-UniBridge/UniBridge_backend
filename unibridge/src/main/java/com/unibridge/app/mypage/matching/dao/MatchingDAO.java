package com.unibridge.app.mypage.matching.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.unibridge.app.mypage.matching.dto.matchingDTO;
import com.unibridge.config.MyBatisConfig;

public class MatchingDAO {
    private SqlSession sqlSession;

    public MatchingDAO() {
    	// MyBatis 설정 파일을 통해 SqlSession 생성
    	try {
			sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
			System.out.println("[DAO] SqlSession 생성 성공");
		} catch (Exception e) {
			System.out.println("[DAO] SqlSession 생성 실패: " + e.getMessage());
			e.printStackTrace();
		}
    }

    /**
     * 특정 사용자의 매칭 정보를 조회 (멘토/멘티 이름 및 과목명 포함)
     * @param memberNumber 로그인한 사용자의 번호
     * @return 매칭 정보 리스트
     */
    public List<matchingDTO> selectMatchingList(int memberNumber) {
        // 로그인한 회원과 관련된 매칭 정보 호출
        return sqlSession.selectList("matching.selectMatchingList", memberNumber);
    }

    /**
     * 매칭 취소 신청 (상태 업데이트)
     * @param matchingNumber 취소할 매칭 번호
     */
    public void updateMatchingCancel(matchingDTO dto) {
        // 세션에서 update 실행 (ID: matching.updateMatchingCancel)
        sqlSession.update("matching.updateMatchingCancel", dto);
    }
    
    public void updatePayId(long matchingNumber, long payId) {
        Map<String, Long> map = new HashMap<>();
        map.put("matchingNumber", matchingNumber);
        map.put("payId", payId);
        
        // namespace="matching", id="updatePayId" 호출
        sqlSession.update("matching.updatePayId", map);
    }
}