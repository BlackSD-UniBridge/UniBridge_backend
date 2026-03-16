package com.unibridge.app.pay.dao;

import org.apache.ibatis.session.SqlSession;
import com.unibridge.app.pay.dto.PaymentDTO; // DTO 패키지 위치 확인
import com.unibridge.config.MyBatisConfig;

public class PaymentDAO {
    public SqlSession sqlSession;

    public PaymentDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // 자동 커밋 설정
    }

    public void insertPayment(PaymentDTO paymentDTO) {
        sqlSession.insert("pay.insertPayment", paymentDTO);
    }
}