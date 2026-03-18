package com.unibridge.app.admin.dto;

public class AdMenteeBoardCommentDTO {
	

//CREATE TABLE UB_MENTEE_BOARD_COMMENT (
//    mentee_com_number      NUMBER         NOT NULL,
//    mentee_com_date    DATE           NULL,
//    mentee_com_content VARCHAR2(2000) NULL,
//    member_number      NUMBER         NOT NULL,
//    menteeboard_number     NUMBER         NOT NULL,
//    CONSTRAINT PK_UB_MENTEE_BRD_COM PRIMARY KEY (mentee_com_number),
//    CONSTRAINT FK_UB_MBR_MENTEE_COM FOREIGN KEY (member_number) REFERENCES UB_MEMBER (MEMBER_NUMBER),
//    CONSTRAINT FK_UB_BRD_MENTEE_COM FOREIGN KEY (menteeBoard_Number) REFERENCES UB_MENTEE_BOARD (menteeboard_id)
//);
	
	private int menteeComNumber;
	private String menteeComDate;
	private String menteeComContent;
	private int memberNumber;
	private String memberNickname;
	private int menteeboardNumber;
	
	public int getMenteeComNumber() {
		return menteeComNumber;
	}
	public String getMenteeComDate() {
		return menteeComDate;
	}
	public String getMenteeComContent() {
		return menteeComContent;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public int getMenteeboardNumber() {
		return menteeboardNumber;
	}
	
	
	
}
