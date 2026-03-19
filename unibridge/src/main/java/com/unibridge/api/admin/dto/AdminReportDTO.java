package com.unibridge.api.admin.dto;

import java.util.Date;

public class AdminReportDTO {
	private Date matchingStart;
	private String mentorName;
	private String menteeName;
	
	public AdminReportDTO() {
		super();
	}
	
	public AdminReportDTO(Date matchingStart, String mentorName, String menteeName) {
		super();
		this.matchingStart = matchingStart;
		this.mentorName = mentorName;
		this.menteeName = menteeName;
	}

	@Override
	public String toString() {
		return "AdminReportDTO [matchingStart=" + matchingStart + ", mentorName=" + mentorName + ", menteeName="
				+ menteeName + ", toString()=" + super.toString() + "]";
	}

	public Date getMatchingStart() {
		return matchingStart;
	}

	public void setMatchingStart(Date matchingStart) {
		this.matchingStart = matchingStart;
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
}
