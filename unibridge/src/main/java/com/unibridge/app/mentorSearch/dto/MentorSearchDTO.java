package com.unibridge.app.mentorSearch.dto;

public class MentorSearchDTO {

	private int memberNumber;
	private String memberId;
	private String memberNickname;
	private String gradSchool; // 학교
	private String gradDepart; // 학과
	private int surveyNumber;
	private String file_number;
	private String createdAt;

	// getter, setter
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getFile() {
		return file_number;
	}

	public void setFile(String file) {
		this.file_number = file_number;
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

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getGradSchool() {
		return gradSchool;
	}

	public void setGradSchool(String gradSchool) {
		this.gradSchool = gradSchool;
	}

	public String getGradDepart() {
		return gradDepart;
	}

	public void setGradDepart(String gradDepart) {
		this.gradDepart = gradDepart;
	}

	public int getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

}
