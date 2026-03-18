package com.unibridge.app.admin.dto;

public class AdReportDTO {


//CREATE TABLE UB_LEARNING_REPORT (
//    INTERNAL_ID        VARCHAR2(64),
//    REPORT_SEQ         NUMBER NOT NULL UNIQUE,
//    MATCHING_NUMBER    NUMBER NOT NULL,
//    SUBJECT_NUMBER     NUMBER NOT NULL,
//    SUBJECT_TITLE      VARCHAR2(32) NOT NULL,
//    SUBJECT_SUMMARY    VARCHAR2(64) NOT NULL,
//    SUBJECT_CONTENT    VARCHAR2(1024) NOT NULL,
//    REPORT_WEEK        NUMBER NOT NULL,
//    REPORT_SESSION     NUMBER NOT NULL,
//    REPORT_DATE        DATE NOT NULL,		//학습 진행일
//    CREATED_AT         TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
//    UPDATED_AT         TIMESTAMP,
//    CONSTRAINT pk_ub_lr PRIMARY KEY (INTERNAL_ID),
//    CONSTRAINT fk_ub_lr_matching FOREIGN KEY (MATCHING_NUMBER) REFERENCES UB_MATCHING (MATCHING_NUMBER),
//    CONSTRAINT fk_ub_lr_subject FOREIGN KEY (SUBJECT_NUMBER) REFERENCES UB_SUBJECT (SUBJECT_NUMBER)
//);

	private int reportSeq;
	private int matchingNumber;
	private int subjectNumber;
	private String subjectName;		//학습 과목
	private String subjectTitle;	//학습 주제
	private String subjectSummary;	//학습 일지 요약
	private String subjectContent;	//학습 내용
	private int reportSession;	//학습 일차
	private String createdAt;
	
	public int getReportSeq() {
		return reportSeq;
	}
	public int getMatchingNumber() {
		return matchingNumber;
	}
	public int getSubjectNumber() {
		return subjectNumber;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public String getSubjectSummary() {
		return subjectSummary;
	}
	public String getSubjectContent() {
		return subjectContent;
	}
	public int getReportSession() {
		return reportSession;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	
	
	
}
