package com.unibridge.app.mypage.surveyMentor.dto;

//survey_number   NUMBER   NOT NULL,
//grad_school   VARCHAR2(150) NOT NULL,
//grad_depart   VARCHAR2(150) NOT NULL,
//grad_score   NUMBER   NOT NULL,
//subject_number NUMBER   NOT NULL,

import com.unibridge.app.mypage.survey.dto.SurveyDTO;

public class SurveyMentorDTO extends SurveyDTO{
	
	private String gradSchool;
	private String gradDepart;
	private double gradScore;
	private int subjectNumber;
	
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
	public double getGradScore() {
		return gradScore;
	}
	public void setGradScore(double gradScore) {
		this.gradScore = gradScore;
	}
	public int getSubjectNumber() {
		return subjectNumber;
	}
	public void setSubjectNumber(int subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	
	@Override
	public String toString() {
		return "SurveyMentorDTO [" + super.toString() +"gradSchool=" + gradSchool + ", gradDepart=" + gradDepart + ", gradScore=" + gradScore
				+ ", subjectNumber=" + subjectNumber + "]";
	}

}
