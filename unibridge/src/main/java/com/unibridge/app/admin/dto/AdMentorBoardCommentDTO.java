package com.unibridge.app.admin.dto;

public class AdMentorBoardCommentDTO {
	
//	CREATE TABLE UB_MENTOR_BOARD_COMMENT (
//		    mentor_com_number      NUMBER         NOT NULL,
//		    mentor_com_date    DATE           NULL,
//		    mentor_com_content VARCHAR2(2000) NULL,
//		    member_number      NUMBER         NOT NULL,
//		    mentorBoard_number     NUMBER         NOT NULL,
//		    CONSTRAINT PK_UB_MENTOR_BRD_COM PRIMARY KEY (mentor_com_number),
//		    CONSTRAINT FK_UB_MBR_MENTOR_COM FOREIGN KEY (member_number) REFERENCES UB_MEMBER (member_number),
//		    CONSTRAINT FK_UB_BRD_MENTOR_COM FOREIGN KEY (mentorBoard_number) REFERENCES UB_MENTOR_BOARD (mentorBoard_number)
//		);
	
	private int mentorComNumber;
	private String mentorComDate;
	private String mentorComContent;
	private int memberNumber;
	private String memberNickname;
	private int memtorBoardNumber;
	
	public int getMentorComNumber() {
		return mentorComNumber;
	}
	public String getMentorComDate() {
		return mentorComDate;
	}
	public String getMentorComContent() {
		return mentorComContent;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public int getMemtorBoardNumber() {
		return memtorBoardNumber;
	}
	
	
	
}
