package com.unibridge.app.learningReport.dto;

import java.time.LocalDateTime;

public class LearningReportDTO {
	private int learningReportNumber; // 학습 보고서 번호 (PK)
	private int matchingNumber; // 매칭 번호 (FK)
	private int subjectNumber; // 과목 번호 (FK)
	private String subjectTitle; // 과목 제목
	private String subjectSummary; // 과목 요약
	private String subjectContent; // 과목 내용
	private int reportWeek; // 보고서 주차
	private int reportSession; // 보고서 회차
	private LocalDateTime reportDate; // 보고서 날짜 (DB의 DATE)
	private LocalDateTime createdAt; // 생성일시
	private LocalDateTime updatedAt; // 수정일시

	// 기본 생성자
	public LearningReportDTO() {
	}

	// Getter 및 Setter
	public int getLearningReportNumber() {
		return learningReportNumber;
	}

	public void setLearningReportNumber(int learningReportNumber) {
		this.learningReportNumber = learningReportNumber;
	}

	public int getMatchingNumber() {
		return matchingNumber;
	}

	public void setMatchingNumber(int matchingNumber) {
		this.matchingNumber = matchingNumber;
	}

	public int getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(int subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getSubjectSummary() {
		return subjectSummary;
	}

	public void setSubjectSummary(String subjectSummary) {
		this.subjectSummary = subjectSummary;
	}

	public String getSubjectContent() {
		return subjectContent;
	}

	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
	}

	public int getReportWeek() {
		return reportWeek;
	}

	public void setReportWeek(int reportWeek) {
		this.reportWeek = reportWeek;
	}

	public int getReportSession() {
		return reportSession;
	}

	public void setReportSession(int reportSession) {
		this.reportSession = reportSession;
	}

	public LocalDateTime getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDateTime reportDate) {
		this.reportDate = reportDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	// 데이터 확인용 toString()
	@Override
	public String toString() {
		return "LearningReportDTO [learningReportNumber=" + learningReportNumber + ", matchingNumber=" + matchingNumber
				+ ", subjectNumber=" + subjectNumber + ", subjectTitle=" + subjectTitle + ", subjectSummary="
				+ subjectSummary + ", subjectContent=" + subjectContent + ", reportWeek=" + reportWeek
				+ ", reportSession=" + reportSession + ", reportDate=" + reportDate + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
}