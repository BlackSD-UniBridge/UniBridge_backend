package com.unibridge.app.admin.dto;

public class AdSurveyDTO {
	
	
//미정
//	CREATE TABLE UB_SURVEY (
//			   survey_number      NUMBER NOT NULL,
//			   survey_type         VARCHAR2(20) NOT NULL,
//			   survey_approval      CHAR DEFAULT 'F' NOT NULL CHECK(survey_approval IN('T','F')),
//			   survey_rej_reason   VARCHAR2(1000)   NULL,
//			   survey_app_date      DATE   NULL,
//			   admin_number      NUMBER   NOT NULL,
//			   file_number         NUMBER,
//			   member_number         number   NOT NULL,
//			   CONSTRAINT pk_ub_survey PRIMARY KEY (survey_number),
//			   CONSTRAINT fk_ub_file_number FOREIGN KEY (file_number) REFERENCES UB_FILE(file_number),
//			   CONSTRAINT fk_ub_member_number FOREIGN KEY (member_number) REFERENCES UB_MEMBER(member_number)
//			);
//멘티
//			CREATE TABLE UB_SURVEY_MENTEE (
//			    survey_number    NUMBER         NOT NULL,
//			    mentee_school    VARCHAR2(150)  NOT NULL,
//			    mentee_grade     NUMBER         NOT NULL,
//			    mentee_hopeuni   VARCHAR2(150)  NOT NULL,
//			    mentee_hopemajor VARCHAR2(150)  NOT NULL,
//			    subject_number   NUMBER,              
//			    CONSTRAINT pk_ub_survey_mentee PRIMARY KEY (survey_number),
//			    CONSTRAINT fk_ub_survey_mentee FOREIGN KEY (survey_number) REFERENCES UB_SURVEY (survey_number) ON DELETE CASCADE,  
//			    CONSTRAINT fk_ub_subject_mentee FOREIGN KEY (subject_number) REFERENCES UB_SUBJECT (subject_number) ON DELETE SET NULL
//			);
//멘토
//			CREATE TABLE UB_SURVEY_MENTOR (
//			   survey_number   NUMBER   NOT NULL,
//			   grad_school   VARCHAR2(150) NOT NULL,
//			   grad_depart   VARCHAR2(150) NOT NULL,
//			   grad_score   NUMBER   NOT NULL,
//			   subject_number NUMBER   NOT NULL,
//			   CONSTRAINT pk_ub_mentor PRIMARY KEY (survey_number),
//			   CONSTRAINT fk_ub_survey_mentor FOREIGN KEY (survey_number) REFERENCES UB_SURVEY(survey_number) ON DELETE CASCADE,
//			   CONSTRAINT fk_ub_subject_mentor FOREIGN KEY (subject_number) REFERENCES UB_SUBJECT(subject_number) ON DELETE SET NULL
//			);

	
	private int surveyNumber;
	private String surveyType;
	private char surveyApproval;
	private String surveyRejReason;
	private String surveyAppDate;
	private int fileNumber;
	private int memberNumber;

	private String menteeSchool;
	private int menteeGrade;
	private String menteeHopeuni;
	private String menteeHopemajor;

	private String gradSchool;
	private String gradDepart;
	private int gradScore;
    
	private int subjectNumber;
	private String subjectName;
	
	
	public int getSurveyNumber() {
		return surveyNumber;
	}
	public String getSurveyType() {
		return surveyType;
	}
	public char getSurveyApproval() {
		return surveyApproval;
	}
	public String getSurveyRejReason() {
		return surveyRejReason;
	}
	public String getSurveyAppDate() {
		return surveyAppDate;
	}
	public int getFileNumber() {
		return fileNumber;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public String getMenteeSchool() {
		return menteeSchool;
	}
	public int getMenteeGrade() {
		return menteeGrade;
	}
	public String getMenteeHopeuni() {
		return menteeHopeuni;
	}
	public String getMenteeHopemajor() {
		return menteeHopemajor;
	}
	public String getGradSchool() {
		return gradSchool;
	}
	public String getGradDepart() {
		return gradDepart;
	}
	public int getGradScore() {
		return gradScore;
	}
	public int getSubjectNumber() {
		return subjectNumber;
	}
	public String getSubjectName() {
		return subjectName;
	}
	
	
	
	


}
