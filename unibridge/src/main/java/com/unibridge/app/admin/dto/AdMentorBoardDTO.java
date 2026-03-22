package com.unibridge.app.admin.dto;

import com.unibridge.app.file.dto.FileDTO;

public class AdMentorBoardDTO {

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
	private FileDTO fileName;
	private int writeNumber;
	private String writeNickname;

	
	public void setMentorboardNumber(int mentorboardNumber) {
		this.mentorboardNumber = mentorboardNumber;
	}
	public void setBoardClick(int boardClick) {
		this.boardClick = boardClick;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public void setWriteNumber(int writeNumber) {
		this.writeNumber = writeNumber;
	}
	public void setWriteNickname(String writeNickname) {
		this.writeNickname = writeNickname;
	}
	
	public String getBoardTitle() {
		return boardTitle;
	}
	public int getWriteNumber() {
		return writeNumber;
	}
	public String getWriteNickname() {
		return writeNickname;
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
		return writeNumber;
	}
	public String getMemberNickname() {
		return writeNickname;
	}
	public FileDTO getFileName() {
		return fileName;
	}
	public void setFileName(FileDTO fileName) {
		this.fileName = fileName;
	}


	
	
	
	
}
