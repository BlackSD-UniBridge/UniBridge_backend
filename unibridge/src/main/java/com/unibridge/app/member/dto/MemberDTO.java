package com.unibridge.app.member.dto;

//CREATE TABLE UB_MEMBER (
//   member_number   NUMBER        NOT NULL,
//   member_id       VARCHAR2(50)  NOT NULL UNIQUE,
//   member_pw       VARCHAR2(255) NOT NULL,
//   member_name     VARCHAR2(50)  NOT NULL,
//   member_nickname VARCHAR2(50)  NOT NULL UNIQUE,
//   member_phone    VARCHAR2(20)  NOT NULL,
//   member_gender   CHAR(1)       NULL CHECK(member_gender IN('M','W','N')),
//   survey_number   NUMBER,
//   member_type     VARCHAR2(20)  DEFAULT 'NODECIDED' NOT NULL CHECK(member_type IN('MENTOR','MENTEE','NODECIDED')),
//   member_state    VARCHAR2(20)  DEFAULT '계정 활성화' NOT NULL CHECK (member_state IN('계정 활성화', '계정 삭제')),
//   survey_write    CHAR(1)       DEFAULT 'N' CHECK(survey_write IN('Y','N')),
//   file_number     NUMBER, 
//   CONSTRAINT pk_ub_mm PRIMARY KEY (member_number),
//   CONSTRAINT fk_ub_mm_profile FOREIGN KEY (file_number) REFERENCES UB_FILE (file_number)
//);

public class MemberDTO {
	private int memberNumber;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNickname;
	private String memberPhone;
	private String memberGender;
	private int surveyNumber;
	private String memberType;
	private String memberState;
	private char surveyWrite;
	private int fileNumber;
	private String memberImg;
	
	
	public String getMemberImg() {
		return memberImg;
	}

	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}

	public MemberDTO() {
		super();
	}

	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public int getSurveyNumber() {
		return surveyNumber;
	}
	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberState() {
		return memberState;
	}
	public void setMemberState(String memberState) {
		this.memberState = memberState;
	}
	public char getSurveyWrite() {
		return surveyWrite;
	}
	public void setSurveyWrite(char surveyWrite) {
		this.surveyWrite = surveyWrite;
	}
	public int getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}

	@Override
	public String toString() {
		return "MemberDTO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberPw=" + memberPw
				+ ", memberName=" + memberName + ", memberNickname=" + memberNickname + ", memberPhone=" + memberPhone
				+ ", memberGender=" + memberGender + ", surveyNumber=" + surveyNumber + ", memberType=" + memberType
				+ ", memberState=" + memberState + ", surveyWrite=" + surveyWrite + ", fileNumber=" + fileNumber
				+ ", memberImg=" + memberImg + "]";
	}
	
}
