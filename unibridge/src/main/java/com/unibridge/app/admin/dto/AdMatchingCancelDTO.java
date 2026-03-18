package com.unibridge.app.admin.dto;

public class AdMatchingCancelDTO {
//	CREATE TABLE UB_MATCHING (
//		    matching_number  NUMBER NOT NULL,
//		    matching_start   DATE DEFAULT SYSDATE NOT NULL,
//		    matching_state   VARCHAR2(20) NOT NULL CHECK (matching_state IN ('매칭됨','매칭 진행중', '매칭 취소')),
//		    mentor_number    NUMBER NOT NULL,
//		    mentee_number    NUMBER NOT NULL,
//		    subject_number   NUMBER NOT NULL,
//		    matching_cancel  DATE,
//			matching_can_reason  VARCHAR(1024),
//		    CONSTRAINT pk_ub_mt PRIMARY KEY (matching_number),
//		    CONSTRAINT fk_ub_mt_subject FOREIGN KEY (subject_number) REFERENCES UB_SUBJECT(subject_number),
//		    CONSTRAINT fk_ub_mt_mentor FOREIGN KEY (mentor_number) REFERENCES UB_MEMBER(member_number),
//		    CONSTRAINT fk_ub_mt_mentee FOREIGN KEY (mentee_number) REFERENCES UB_MEMBER(member_number)
//		);
	
	
	private int matchingNumber;
	private String matchingStart;
	private String matchingState;
	private int mentorNumber;
	private String mentorName;
	private int menteeNumber;
	private String menteeName;
	private int subjectNumber;
	private String subjectName;
	private String matchingCancel;
	private String matching_can_reason;
	
	
	public int getMatchingNumber() {
		return matchingNumber;
	}
	public String getMatchingStart() {
		return matchingStart;
	}
	public String getMatchingState() {
		return matchingState;
	}
	public int getMentorNumber() {
		return mentorNumber;
	}
	public String getMentorName() {
		return mentorName;
	}
	public int getMenteeNumber() {
		return menteeNumber;
	}
	public String getMenteeName() {
		return menteeName;
	}
	public int getSubjectNumber() {
		return subjectNumber;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public String getMatchingCancel() {
		return matchingCancel;
	}
	public String getMatching_can_reason() {
		return matching_can_reason;
	}
	
	
	
	
	
}
