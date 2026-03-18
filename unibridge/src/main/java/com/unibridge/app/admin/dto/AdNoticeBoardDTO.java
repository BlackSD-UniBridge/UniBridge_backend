package com.unibridge.app.admin.dto;

public class AdNoticeBoardDTO {

//	CREATE TABLE UB_NOTICEBOARD (
//		    noticeboard_number  NUMBER          NOT NULL,
//		    board_type         VARCHAR2(50)    NULL,
//		    board_title        VARCHAR2(255)   NOT NULL,
//		    board_content      VARCHAR2(4000)  NOT NULL,
//		    board_click        NUMBER          DEFAULT 0 NOT NULL,
//		    board_date         DATE            NOT NULL,
//		    admin_number       NUMBER          NOT NULL,
//		    contest_number     NUMBER          NULL,
//		    file_number        NUMBER          NULL,
//		    CONSTRAINT PK_UB_NOTICEBOARD  PRIMARY KEY (noticeboard_number),
//		    CONSTRAINT FK_UB_ADMIN_NB     FOREIGN KEY (admin_number)   REFERENCES UB_ADMIN(admin_number),
//		    CONSTRAINT FK_UB_CONTEST_NB   FOREIGN KEY (contest_number) REFERENCES UB_CONTEST(contest_number),
//		    CONSTRAINT FK_UB_FILENUM_NB   FOREIGN KEY (file_number)    REFERENCES UB_FILE (file_number)
//		);
//	
	
	int noticeboardNumber;
	String boardType;
	String boardTitle;
	String boardContent;
	int boardClick;
	String boardDate;
	int adminNumber;
	int contestNumber;
	int fileNumber;
	
}
