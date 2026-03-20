package com.unibridge.app.mentorBoard.dto;

public class MentorBoardDTO {
	private int mentorBoardNumber;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private int boardClick;
	private int memberNumber;
	
	public int getMentorBoardNumber() {
		return mentorBoardNumber;
	}
	public void setMentorBoardNumber(int mentorBoardNumber) {
		this.mentorBoardNumber = mentorBoardNumber;
	}
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
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardClick() {
		return boardClick;
	}
	public void setBoardClick(int boardClick) {
		this.boardClick = boardClick;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	
	@Override
	public String toString() {
		return "MentorBoardDTO [mentorBoardNumber=" + mentorBoardNumber + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardDate=" + boardDate
				+ ", boardClick=" + boardClick + ", memberNumber=" + memberNumber + "]";
	}
}
