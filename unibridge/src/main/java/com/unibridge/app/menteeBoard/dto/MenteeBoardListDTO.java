package com.unibridge.app.menteeBoard.dto;

import java.util.List;

import com.unibridge.app.file.dto.FileDTO;

public class MenteeBoardListDTO {
	private int memberNumber;
	private String memberId;
	private int menteeBoardNumber;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private int boardClick;
	//파일 추가
	private List<FileDTO> files;
	
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getMenteeBoardNumber() {
		return menteeBoardNumber;
	}
	public void setMenteeBoardNumber(int menteeBoardNumber) {
		this.menteeBoardNumber = menteeBoardNumber;
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
	public List<FileDTO> getFiles() {
		return files;
	}
	public void setFiles(List<FileDTO> files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "BoardListDTO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", menteeBoardNumber=" + menteeBoardNumber
				+ ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", boardDate=" + boardDate
				+ ", boardClick=" + boardClick + "]";
	}
	
	
}
