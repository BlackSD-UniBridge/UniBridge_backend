package com.unibridge.app.noticeBoard.dto;

import java.util.List;

import com.unibridge.app.file.dto.FileDTO;

public class NoticeBoardListDTO {
    private int    noticeBoardNumber;   // noticeboard_number
    private String boardType;           // board_type
    private String boardTitle;          // board_title
    private String boardContent;        // board_content
    private String boardUserId;         // board_userid
    private int    boardClick;          // board_click
    private String boardDate;           // board_date
    private int    adminNumber;         // admin_number
    private int    contestNumber;       // contest_number (FK → UB_CONTEST, nullable)
    private int    fileNumber;          // file_number
	//파일 추가
	private List<FileDTO> files;
	
	public int getNoticeBoardNumber() { return noticeBoardNumber; }
    public void setNoticeBoardNumber(int noticeBoardNumber) { this.noticeBoardNumber = noticeBoardNumber; }

    public String getBoardType() { return boardType; }
    public void setBoardType(String boardType) { this.boardType = boardType; }

    public String getBoardTitle() { return boardTitle; }
    public void setBoardTitle(String boardTitle) { this.boardTitle = boardTitle; }

    public String getBoardContent() { return boardContent; }
    public void setBoardContent(String boardContent) { this.boardContent = boardContent; }

    public String getBoardUserId() { return boardUserId; }
    public void setBoardUserId(String boardUserId) { this.boardUserId = boardUserId; }

    public int getBoardClick() { return boardClick; }
    public void setBoardClick(int boardClick) { this.boardClick = boardClick; }

    public String getBoardDate() { return boardDate; }
    public void setBoardDate(String boardDate) { this.boardDate = boardDate; }

    public int getAdminNumber() { return adminNumber; }
    public void setAdminNumber(int adminNumber) { this.adminNumber = adminNumber; }

    public int getContestNumber() { return contestNumber; }
    public void setContestNumber(int contestNumber) { this.contestNumber = contestNumber; }

    public int getFileNumber() { return fileNumber; }
    public void setFileNumber(int fileNumber) { this.fileNumber = fileNumber; }

    public List<FileDTO> getFiles() { return files; }
    public void setFiles(List<FileDTO> files) { this.files = files; }

    // 기존 코드와의 하위 호환성을 위한 별칭 메서드
    // (JSP나 다른 컨트롤러가 getMemberNumber() 를 호출하면 0 반환)
    @Deprecated
    public int getMemberNumber() { return 0; }

    // 기존 boardNumber → noticeBoardNumber 별칭
    @Deprecated
    public int getBoardNumber() { return noticeBoardNumber; }
    @Deprecated
    public void setBoardNumber(int n) { this.noticeBoardNumber = n; }

    @Override
    public String toString() {
        return "NoticeBoardListDTO [noticeBoardNumber=" + noticeBoardNumber
                + ", boardType=" + boardType
                + ", boardTitle=" + boardTitle
                + ", boardUserId=" + boardUserId
                + ", boardClick=" + boardClick
                + ", boardDate=" + boardDate
                + ", contestNumber=" + contestNumber + "]";
    }
	
}
