package com.unibridge.api.admin.dto;

import java.util.Date;

public class AdminReportDTO {
	private int matchingNumber;
	private Date matchingStart;
	private String mentorName;
	private String menteeName;
	
	public AdminReportDTO() {
		super();
	}
	

	public AdminReportDTO(int matchingNumber, Date matchingStart, String mentorName, String menteeName) {
		super();
		this.matchingNumber = matchingNumber;
		this.matchingStart = matchingStart;
		this.mentorName = mentorName;
		this.menteeName = menteeName;
	}
	
	@Override
	public String toString() {
		return "AdminReportDTO [matchingNumber=" + matchingNumber + ", matchingStart=" + matchingStart + ", mentorName="
				+ mentorName + ", menteeName=" + menteeName + ", toString()=" + super.toString() + "]";
	}
	
	public int getMatchingNumber() {
		return matchingNumber;
	}
	public void setMatchingNumber(int matchingNumber) {
		this.matchingNumber = matchingNumber;
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
