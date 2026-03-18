package com.unibridge.app.mypage.surveyMentee.dto;

import com.unibridge.app.mypage.survey.dto.SurveyDTO;


//survey_number    NUMBER         NOT NULL,
//mentee_school    VARCHAR2(150)  NOT NULL,
//mentee_grade     NUMBER         NOT NULL,
//mentee_hopeuni   VARCHAR2(150)  NOT NULL,
//mentee_hopemajor VARCHAR2(150)  NOT NULL,
//subject_number   NUMBER,  

public class SurveyMenteeDTO extends SurveyDTO{
	
	private String menteeSchool;
	private int menteeGrade;
	private String menteeHopeuni;
	private String menteeHopemajor;
	private int subjectNumber;
	
	public String getMenteeSchool() {
		return menteeSchool;
	}

	public void setMenteeSchool(String menteeSchool) {
		this.menteeSchool = menteeSchool;
	}

	public int getMenteeGrade() {
		return menteeGrade;
	}

	public void setMenteeGrade(int menteeGrade) {
		this.menteeGrade = menteeGrade;
	}

	public String getMenteeHopeuni() {
		return menteeHopeuni;
	}

	public void setMenteeHopeuni(String menteeHopeuni) {
		this.menteeHopeuni = menteeHopeuni;
	}

	public String getMenteeHopemajor() {
		return menteeHopemajor;
	}

	public void setMenteeHopemajor(String menteeHopemajor) {
		this.menteeHopemajor = menteeHopemajor;
	}

	public int getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(int subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	@Override
	public String toString() {
		return "SurveyMenteeDTO [" + super.toString() +"menteeSchool=" + menteeSchool + ", menteeGrade=" + menteeGrade + ", menteeHopeuni="
				+ menteeHopeuni + ", menteeHopemajor=" + menteeHopemajor + ", subjectNumber=" + subjectNumber + "]";
	}
	
}   
