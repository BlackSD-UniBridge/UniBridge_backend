package com.unibridge.app.menteeBoard.dto;

public class MenteeBoardDTO {
//	board_number NUMBER,
//	board_title varchar2(200),
//	board_content varchar2(2000),
//	board_date DATE DEFAULT SYSDATE,
//	board_update DATE DEFAULT SYSDATE,
//	board_read_count NUMBER DEFAULT 0,
//	member_number NUMBER,
	private int MenteeBoardNumber;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private int boardClick;
	private int memberNumber;
	
	public int getMenteeBoardNumber() {
		return MenteeBoardNumber;
	}
	public void setMenteeBoardNumber(int MenteeBoardNumber) {
		this.MenteeBoardNumber = MenteeBoardNumber;
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
		return "MenteeBoardDTO [MenteeBoardNumber=" + MenteeBoardNumber + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDate=" + boardDate + ", boardClick=" + boardClick + ", memberNumber=" + memberNumber
				+ "]";
	}

	

}
