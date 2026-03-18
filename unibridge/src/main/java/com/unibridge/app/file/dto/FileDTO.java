package com.unibridge.app.file.dto;
//file_number        NUMBER         NOT NULL,
//file_name          VARCHAR2(255)  NOT NULL, 
//file_original_name VARCHAR2(255)  NOT NULL, 
//file_extension     VARCHAR2(20)   NOT NULL, 
//file_size          NUMBER         NOT NULL, 
//file_path          VARCHAR2(1024) NOT NULL,
public class FileDTO {

	private int fileNumber;
	private String fileName;
	private String fileOriginalName;
	private String fileExtension;
	private long fileSize;
	private String filePath;
	
	public int getFileNumber() {
		return fileNumber;
	}


	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileOriginalName() {
		return fileOriginalName;
	}


	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}


	public String getFileExtension() {
		return fileExtension;
	}


	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}


	public long getFileSize() {
		return fileSize;
	}


	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "FileDTO [fileNumber=" + fileNumber + ", fileName=" + fileName + ", fileOriginalName=" + fileOriginalName
				+ ", fileExtension=" + fileExtension + ", fileSize=" + fileSize + ", filePath=" + filePath + "]";
	}

}
