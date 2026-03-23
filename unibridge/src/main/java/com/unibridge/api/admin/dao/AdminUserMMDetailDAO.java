package com.unibridge.api.admin.dao;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import com.unibridge.api.admin.dto.AdminUserMMDTO;
import com.unibridge.config.MyBatisConfig;

public class AdminUserMMDetailDAO {
    public SqlSession sqlSession;

    public AdminUserMMDetailDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // 자동 커밋 설정
    }
    
    public int updateMemberStatePendingKill(int memberNumber) {
    	return this.sqlSession.update("api.userMMDetail.setPendingKill", memberNumber); 
    }
    
    public AdminUserMMDTO selectOneUser(int memberNumber) {
    	return this.sqlSession.selectOne("api.userMMDetail.selectUserDetail", memberNumber);
    }
    
    public int acceptUserStateBySurvey(int memberNumber) {
    	// UB_SURVEY 테이블에서 설문조사 승인 상태로 변경
    	int udpateAcceptResult = this.sqlSession.delete("api.userMMDetail.updateAcceptTrue", memberNumber);
    	if (udpateAcceptResult == -1 || udpateAcceptResult == 0) {
    		System.err.println("AdminUserMMDAO::acceptUserStateBySurvey(int memberNumber := " + memberNumber + ") failed.");
    		return udpateAcceptResult;
    	}
    	
    	// UB_MEMBER 테이블에서 member_type을 변경
    	int updateMemberState = this.sqlSession.update("api.userMMDetail.udpateMemberStateTrue", memberNumber);
    	if (updateMemberState == -1 || updateMemberState == 0) {
    		System.err.println("AdminUserMMDAO::acceptUserStateBySurvey(int memberNumber := " + memberNumber + ") failed.");
    		return updateMemberState;
    	}
    	
    	return updateMemberState;
    }
    
    public int rejectUserStateBySurvey(int memberNumber, String rejectReason) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("memberNumber", memberNumber);
        paramMap.put("rejectReason", rejectReason);
        
    	// UB_SURVEY 테이블에서 설문조사 거부 상태로 변경
    	return this.sqlSession.update("api.userMMDetail.updateAcceptFalse", paramMap);
    }
}
