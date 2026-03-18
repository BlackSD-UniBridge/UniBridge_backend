package com.unibridge.app.admin.dto;

public class AdMentoBoardDTO {

//	CREATE TABLE UB_MENTOR_BOARD (
//		    mentorboard_number  NUMBER NOT NULL,
//		    board_title     VARCHAR2(255) NOT NULL,
//		    board_content   VARCHAR2(2000) NOT NULL,
//		    board_click     NUMBER DEFAULT 0 NOT NULL,
//		    board_date      DATE DEFAULT SYSDATE NOT NULL,
//		    file_number     NUMBER NULL,
//		    member_number   NUMBER NULL,
//		    admin_number    NUMBER NULL,
//		    CONSTRAINT PK_UB_MENTOR_BOARD PRIMARY KEY (mentorboard_id),
//		    CONSTRAINT FK_UB_MENTOR_B_MEM FOREIGN KEY (member_number) REFERENCES UB_MEMBER(member_number),
//		    CONSTRAINT FK_UB_MENTOR_B_ADM FOREIGN KEY (admin_number) REFERENCES UB_ADMIN(admin_number),
//		    CONSTRAINT FK_UB_MENTOR_B_FIL FOREIGN KEY (file_number) REFERENCES UB_FILE(file_number)
//		);
	
	
	private int mentorboardNumber;
	private String boardTitle;
	private String boardContent;
	private int boardClick;
	private String boardDate;
	private int fileNumber;
	private int memberNumber;
	private String memberNickname;
	private int adminNumber;
	private String adminNickname;
	
	
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public int getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}
	public int getMentorboardNumber() {
		return mentorboardNumber;
	}
	public int getBoardClick() {
		return boardClick;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public int getAdminNumber() {
		return adminNumber;
	}
	public String getAdminNickname() {
		return adminNickname;
	}
	
	
	
	
	
}
