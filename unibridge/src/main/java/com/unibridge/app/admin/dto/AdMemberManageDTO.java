package com.unibridge.app.admin.dto;

public class AdMemberManageDTO {
	
//	CREATE TABLE UB_MEMBER (
//			   member_number   NUMBER        NOT NULL,
//			   member_id       VARCHAR2(50)  NOT NULL UNIQUE,
//			   member_pw       VARCHAR2(255) NOT NULL,
//			   member_name     VARCHAR2(50)  NOT NULL,
//			   member_nickname VARCHAR2(50)  NOT NULL UNIQUE,
//			   member_phone    VARCHAR2(20)  NOT NULL,
//			   member_gender   CHAR(1)       NULL CHECK(member_gender IN('M','W','N')),
//			   survey_number   NUMBER,
//			   member_type     VARCHAR2(20)  DEFAULT 'NODECIDED' NOT NULL CHECK(member_type IN('MENTOR','MENTEE','NODECIDED')),
//			   member_state    VARCHAR2(20)  DEFAULT '계정 활성화' NOT NULL CHECK (member_state IN('계정 활성화', '계정 삭제')),
//			   survey_write    CHAR(1)       DEFAULT 'N' CHECK(survey_write IN('Y','N')),
//			   member_date     DATE          DEFAULT SYSDATE NOT NULL;
//			   file_number     NUMBER, 
//			   CONSTRAINT pk_ub_mm PRIMARY KEY (member_number),
//			   CONSTRAINT fk_ub_mm_profile FOREIGN KEY (file_number) REFERENCES UB_FILE (file_number)
//			);
	
	
	//UB_MEMBER
	private int member_number;
	private String memberName;
	private String memberType;
	private String memberNickname;
	private String memberId;
	private String memberGender;
	private String memberPhone;
	private int surveyNumber;
	private String surveyType;

	//UB_MATCHING
	private int mentor_number;
	private int mentee_number;
	private String matchingState;
	
	
	
	public int getMember_number() {
		return member_number;
	}
	public String getMemberName() {
		return memberName;
	}
	public String getMemberType() {
		return memberType;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public String getMemberId() {
		return memberId;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public int getSurveyNumber() {
		return surveyNumber;
	}
	public int getMentor_number() {
		return mentor_number;
	}
	public int getMentee_number() {
		return mentee_number;
	}
	public String getMatchingState() {
		return matchingState;
	}
	
	public String getSurveyType() {
		return surveyType;
	}	
	
	
	
	
	
}
