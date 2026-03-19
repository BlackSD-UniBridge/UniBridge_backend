package com.unibridge.app.learningReport.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.unibridge.app.learningReport.dto.LearningReportDTO;
import com.unibridge.config.MyBatisConfig;

public class LearningReportDAO {
    
    // 세션을 메서드 실행 시마다 가져오도록 변경 (자원 누수 방지)
    private SqlSession getSession() {
        return MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    public List<LearningReportDTO> selectReportList(Map<String, Object> filters) {
        // try-with-resources 구문을 사용하여 작업 후 세션이 자동 종료되게 함
        try (SqlSession sqlSession = getSession()) {
            return sqlSession.selectList("learningReportMapper.selectReportList", filters);
        }
    }

    public Map<String, Object> getReportStatus(int matchingNumber) {
        try (SqlSession sqlSession = getSession()) {
            return sqlSession.selectOne("learningReportMapper.getReportStatus", matchingNumber);
        }
    }
    
    // 상세보기도 미리 추가해두면 좋습니다.
    public LearningReportDTO selectReportDetail(int learningReportNumber) {
        try (SqlSession sqlSession = getSession()) {
            return sqlSession.selectOne("learningReportMapper.selectReportDetail", learningReportNumber);
        }
    }
}