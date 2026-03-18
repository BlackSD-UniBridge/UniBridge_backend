package com.unibridge.app.mypage.survey.dto;

//survey_number      NUMBER NOT NULL,
//survey_type         VARCHAR2(20) NOT NULL,
//survey_approval      CHAR DEFAULT 'F' NOT NULL CHECK(survey_approval IN('T','F')),
//survey_rej_reason   VARCHAR2(1000)   NULL,
//survey_app_date      DATE   NULL,
//admin_number      NUMBER   NOT NULL,
//file_number         NUMBER,
//member_number         number   NOT NULL,

public class SurveyDTO {
	private int surveyNumber;
	private String surveyType;
	private String surveyApproval;
	private String surveyRejReason;
	private String surveyAppDate;
	private int adminNumber;
	//Integer NULL 입력가능;
	private Integer fileNumber;
	private int memberNumber;
	public int getSurveyNumber() {
		return surveyNumber;
	}
	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}
	public String getSurveyType() {
		return surveyType;
	}
	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}
	public String getSurveyApproval() {
		return surveyApproval;
	}
	public void setSurveyApproval(String surveyApproval) {
		this.surveyApproval = surveyApproval;
	}
	public String getSurveyRejReason() {
		return surveyRejReason;
	}
	public void setSurveyRejReason(String surveyRejReason) {
		this.surveyRejReason = surveyRejReason;
	}
	public String getSurveyAppDate() {
		return surveyAppDate;
	}
	public void setSurveyAppDate(String surveyAppDate) {
		this.surveyAppDate = surveyAppDate;
	}
	public int getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}
	public Integer getFileNumber() {
	    return fileNumber;
	}
	public void setFileNumber(Integer fileNumber) {
	    this.fileNumber = fileNumber;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	@Override
	public String toString() {
		return "SurveyDTO [surveyNumber=" + surveyNumber + ", surveyType=" + surveyType + ", surveyApproval="
				+ surveyApproval + ", surveyRejReason=" + surveyRejReason + ", surveyAppDate=" + surveyAppDate
				+ ", adminNumber=" + adminNumber + ", fileNumber=" + fileNumber + ", memberNumber=" + memberNumber
				+ "]";
	}

}
