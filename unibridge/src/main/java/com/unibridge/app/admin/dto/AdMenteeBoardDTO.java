package com.unibridge.app.admin.dto;

public class AdMenteeBoardDTO {
	
	private int menteeboardNumber;
	private String boardTitle;
	private String boardContent;
	private int boardClick;
	private String boardDate;
	private int fileNumber;
	private int memberNumber;
	private int adminNumber;
	private String memberNickname;
	private String adminNickname;	
	
	public int getMemberNumber() {
		return memberNumber;
	}
	public int getAdminNumber() {
		return adminNumber;
	}
	
	public int getMenteeboardNumber() {
		return menteeboardNumber;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public int getBoardClick() {
		return boardClick;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public int getFileNumber() {
		return fileNumber;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public String getAdminNickname() {
		return adminNickname;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}
	
	
	
}
