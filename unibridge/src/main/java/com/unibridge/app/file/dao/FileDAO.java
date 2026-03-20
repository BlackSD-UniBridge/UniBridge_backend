package com.unibridge.app.file.dao;

import org.apache.ibatis.session.SqlSession;

import com.unibridge.app.file.dto.FileDTO;
import com.unibridge.config.MyBatisConfig;

public class FileDAO {
	
	public Integer insertFileIfExists(FileDTO fileDTO) {

        // 파일 없으면 null
        if (fileDTO == null 
            || fileDTO.getFileName() == null 
            || fileDTO.getFileName().isEmpty()
            || fileDTO.getFileSize() == 0) {
            return null;
        }

        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {

            System.out.println("파일 insert 실행: " + fileDTO);

            session.insert("file.insertFile", fileDTO);

            return fileDTO.getFileNumber();
        }
    }

    public FileDTO selectFile(int fileNumber) {
        System.out.println("파일 DAO - 파일 조회 시작 (fileNumber=" + fileNumber + ")");

        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
            return session.selectOne("file.selectFile", fileNumber);
        }
    }
   
}
