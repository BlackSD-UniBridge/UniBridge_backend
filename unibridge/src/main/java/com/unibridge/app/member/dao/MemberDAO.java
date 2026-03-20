package com.unibridge.app.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.config.MyBatisConfig;

public class MemberDAO {
    public SqlSession sqlSession;

    public MemberDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // 자동 커밋 설정
    }

    /**
     * 회원 로그인 정보와 일치하는 ID 존재 여부를 확인합니다.
     *
     * <p>전달받은 회원 정보를 기준으로 MyBatis의 {@code member.login} 쿼리를 실행하여
     * 조회 결과를 반환합니다.</p>
     *
     * @param member 회원 로그인 확인에 필요한 회원 정보
     * @return 조회 결과 값  
     *         <ul>
     *             <li>0: 일치하는 회원 정보 없음</li>
     *             <li>...: 일치하는 회원 정보</li>
     *         </ul>
     */
    public MemberDTO memberLogin(MemberDTO member) {
    	MemberDTO result = sqlSession.selectOne("member.login", member);
    	return result;
    }
    
    public int memberSignup(MemberDTO member) {
    	Integer result = sqlSession.insert("member.join", member);
    	return result != null ? result : -1;
    }
    
    public boolean checkMember(MemberDTO member) {
        Integer result = sqlSession.selectOne("member.checkMember", member);
        return result != null && result > 0;
    }
    
    public Map<String, Object> selectMember(int memberNumber) {
        SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession();
        // DB에서 한 행을 Map으로 가져오기
        Map<String, Object> member = sqlSession.selectOne("member.selectMap", memberNumber);
        sqlSession.close();
        return member;
    }
    
    public String getMemberTypeByNum(int memberNumber) {
        // MyBatis 실행 결과인 "MENTOR", "MENTEE", "NODECIDED" 문자열을 바로 반환
        return sqlSession.selectOne("member.getMemberTypeByNum", memberNumber);
    }
    
    // 1. 매칭 체크 메서드 추가
    public boolean isMatchingActive(int memberNumber) {
        Integer count = sqlSession.selectOne("member.isMatchingActive", memberNumber);
        return count != null && count > 0;
    }

    // 2. 기존 deleteMember 메서드 수정 (새로 만든 update 쿼리를 호출하도록)
    public void deleteMember(int memberNumber) {
        // 기존 DELETE 쿼리 대신 새로 추가한 updateMemberState를 호출하여 안전하게 처리
        sqlSession.update("member.updateMemberState", memberNumber);
    }
}
