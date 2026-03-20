package com.unibridge.app.file.dao;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.file.dto.FileDTO;
import com.unibridge.config.MyBatisConfig;

public class FileDAO {
	// SqlSessionFactory 설정 (MyBatisConfig 활용)

    public void insertFile(SqlSession session, FileDTO fileDTO) {
    	System.out.println("파일 DAO - 파일 저장 시작");
    	// 이미 트랜잭션이 시작된 session을 전달받아 실행
        session.insert("file.insertFile", fileDTO);
    }
    
    public FileDTO selectFile(int fileNumber) {
        System.out.println("파일 DAO - 파일 조회 시작 (fileNumber=" + fileNumber + ")");
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            return session.selectOne("file.selectFile", fileNumber);
        }
    }
   
}
