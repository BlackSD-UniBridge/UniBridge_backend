package com.unibridge.app.mypage.mentoring.dto;

public class MentoringDTO {
	private int internalId;
	private int mentorNumber;
	private int subjectNumber;
	private String mentoringTitle;
	private String mentoringGoal;
	private String mentoringDetail;
	private int fileNumber;
	private String createAt;
	private String updateAt;

	// Getter & Setter (철자 주의: InternalId)
	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public int getMentorNumber() {
		return mentorNumber;
	}

	public void setMentorNumber(int mentorNumber) {
		this.mentorNumber = mentorNumber;
	}

	public int getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(int subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public String getMentoringTitle() {
		return mentoringTitle;
	}

	public void setMentoringTitle(String mentoringTitle) {
		this.mentoringTitle = mentoringTitle;
	}

	public String getMentoringGoal() {
		return mentoringGoal;
	}

	public void setMentoringGoal(String mentoringGoal) {
		this.mentoringGoal = mentoringGoal;
	}

	public String getMentoringDetail() {
		return mentoringDetail;
	}

	public void setMentoringDetail(String mentoringDetail) {
		this.mentoringDetail = mentoringDetail;
	}

	public int getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}
}