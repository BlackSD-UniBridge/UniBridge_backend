package com.unibridge.app.mentorBoard.dto;

import java.util.List;
import com.unibridge.app.file.dto.FileDTO;

public class MentorBoardListDTO {
	private int memberNumber;
	private String memberId;
	private int mentorBoardNumber;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private int boardClick;
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
	public List<FileDTO> getFiles() {
		return files;
	}
	public void setFiles(List<FileDTO> files) {
		this.files = files;
	}
	
	@Override
	public String toString() {
		return "MentorBoardListDTO [memberNumber=" + memberNumber + ", memberId=" + memberId
				+ ", mentorBoardNumber=" + mentorBoardNumber + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardDate=" + boardDate
				+ ", boardClick=" + boardClick + "]";
	}
}
