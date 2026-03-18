package com.unibridge.app.member.dao;

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
}
