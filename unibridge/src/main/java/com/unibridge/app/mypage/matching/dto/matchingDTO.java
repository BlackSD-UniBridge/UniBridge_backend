package com.unibridge.app.mypage.matching.dto;

public class matchingDTO {
//    matching_number  NUMBER,
//    matching_start   DATE DEFAULT SYSDATE NOT NULL,
//    matching_state   VARCHAR2(20) NOT NULL CHECK (matching_state IN ('매칭됨','매칭 진행중', '매칭 취소')),
//    mentor_number    NUMBER NOT NULL,
//    mentee_number    NUMBER NOT NULL,
//    subject_number   NUMBER NOT NULL,
//    matching_cancel  DATE,
//	matching_can_reason  VARCHAR2(1024)
	
	private int matchinNumber;
	private String matchingStart;
	private String matchingState;
	private int mentorNumber;
	private int menteeNumber;
	private int subjectNumber;
	private String matchinCancel;
	private String mentorName;
    private String menteeName;
    private String subjectName;
    private String matchingCanReason;
    private long payId;
	
    private int payAmount;      // 추가
    private String payMethod;   // 추가
    private String payDate;     // 추가
    private String payStatus;
    
    
	public int getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public long getPayId() {
		return payId;
	}
	public void setPayId(long payId) {
		this.payId = payId;
	}
	public String getMatchingCanReason() {
		return matchingCanReason;
	}
	public void setMatchingCanReason(String matchingCanReason) {
		this.matchingCanReason = matchingCanReason;
	}
	public String getMentorName() {
		return mentorName;
	}
	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}
	public String getMenteeName() {
		return menteeName;
	}
	public void setMenteeName(String menteeName) {
		this.menteeName = menteeName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getMatchinNumber() {
		return matchinNumber;
	}
	public void setMatchinNumber(int matchinNumber) {
		this.matchinNumber = matchinNumber;
	}
	public String getMatchingStart() {
		return matchingStart;
	}
	public void setMatchingStart(String matchingStart) {
		this.matchingStart = matchingStart;
	}
	public String getMatchingState() {
		return matchingState;
	}
	public void setMatchingState(String matchingState) {
		this.matchingState = matchingState;
	}
	public int getMentorNumber() {
		return mentorNumber;
	}
	public void setMentorNumber(int mentorNumber) {
		this.mentorNumber = mentorNumber;
	}
	public int getMenteeNumber() {
		return menteeNumber;
	}
	public void setMenteeNumber(int menteeNumber) {
		this.menteeNumber = menteeNumber;
	}
	public int getSubjectNumber() {
		return subjectNumber;
	}
	public void setSubjectNumber(int subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	public String getMatchinCancel() {
		return matchinCancel;
	}
	public void setMatchinCancel(String matchinCancel) {
		this.matchinCancel = matchinCancel;
	}
	
	@Override
	public String toString() {
		return "matchingDTO [matchinNumber=" + matchinNumber + ", matchingStart=" + matchingStart + ", matchingState="
				+ matchingState + ", mentorNumber=" + mentorNumber + ", menteeNumber=" + menteeNumber
				+ ", subjectNumber=" + subjectNumber + ", matchinCancel=" + matchinCancel + ", mentorName=" + mentorName
				+ ", menteeName=" + menteeName + ", subjectName=" + subjectName + ", matchingCanReason="
				+ matchingCanReason + ", payId=" + payId + ", payAmount=" + payAmount + ", payMethod=" + payMethod
				+ ", payDate=" + payDate + ", payStatus=" + payStatus + "]";

	}

}
