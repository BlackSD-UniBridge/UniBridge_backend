package com.unibridge.app.mypage.delete.dao;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.mypage.delete.dto.MemberDeleteDTO;
import com.unibridge.config.MyBatisConfig;

public class MemberDeleteDAO {
	public SqlSession sqlSession;
	
    public MemberDeleteDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // 자동 커밋 설정
    }
    
	public int deleteUser(MemberDeleteDTO member) {
		Integer result = sqlSession.delete("mypage.delete", member);
		return  result != null ? result : -1;
	}
}