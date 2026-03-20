package com.unibridge.app.pay.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.unibridge.app.pay.dto.PaymentDTO;
import com.unibridge.config.MyBatisConfig;

public class PaymentDAO {
	private SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    public SqlSession sqlSession;
    
    public PaymentDAO() {
        // 이 부분이 누락되었을 겁니다! 
        // true를 넣으면 오토 커밋(Auto-commit)이 설정됩니다.
        sqlSession = sqlSessionFactory.openSession(true); 
    }

    public void insertPayment(PaymentDTO payInfo) {
    	sqlSession.insert("pay.insertPayment", payInfo);
    }

    // 2. 특정 회원의 결제 정보를 JOIN으로 가져오기
    public PaymentDTO selectLatestPaymentByMember(long memberNumber) {
        return sqlSession.selectOne("pay.selectLatestPaymentByMemberNew", memberNumber);
    }
    
    // 3. (대안) 만약 JOIN이 번거롭다면, 방금 생성된 pay_id로 직접 조회
    public PaymentDTO selectPaymentById(long payId) {
        return sqlSession.selectOne("pay.selectPaymentById", payId);
    }
    
    public long getLatestPayId() {
        // 가장 최근에 생성된 시퀀스 번호(CURRVAL)를 가져옴
        return sqlSession.selectOne("pay.getLatestPayId");
    }
    
    // 결제 내역 조회 메서드
    public List<PaymentDTO> getPayLog(Long menteeNumber) {
        // "namespace.id" 형식으로 호출합니다.
        return sqlSession.selectList("com.unibridge.app.pay.mapper.PaymentMapper.selectPayLog", menteeNumber);
    }
}