package com.unibridge.app.main.dto;

import java.util.Date;

public class MainDTO {

	// =============================================
	// 1. 진행중인 대회 정보 (UB_CONTEST)
	// =============================================
	// CREATE TABLE UB_CONTEST (
	// contest_number NUMBER NOT NULL,
	// title VARCHAR2(255) NOT NULL,
	// detail VARCHAR2(1024) NOT NULL,
	// start_date DATE,
	// end_date DATE NOT NULL,
	// host_company VARCHAR2(100) NOT NULL,
	// tech_stack VARCHAR2(500),
	// status VARCHAR2(20) NOT NULL,
	// CONSTRAINT pk_ub_contest PRIMARY KEY (contest_number)
	// );
	public static class ContestDTO {
		private int contestNumber;
		private String title;
		private String detail;
		private Date startDate;
		private Date endDate;
		private String hostCompany;
		private String techStack;
		private String status;

		public ContestDTO() {
		}

		public int getContestNumber() {
			return contestNumber;
		}

		public void setContestNumber(int v) {
			this.contestNumber = v;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String v) {
			this.title = v;
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String v) {
			this.detail = v;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date v) {
			this.startDate = v;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date v) {
			this.endDate = v;
		}

		public String getHostCompany() {
			return hostCompany;
		}

		public void setHostCompany(String v) {
			this.hostCompany = v;
		}

		public String getTechStack() {
			return techStack;
		}

		public void setTechStack(String v) {
			this.techStack = v;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String v) {
			this.status = v;
		}

		@Override
		public String toString() {
			return "ContestDTO [contestNumber=" + contestNumber + ", title=" + title + ", status=" + status
					+ ", endDate=" + endDate + "]";
		}
	}

	// =============================================
	// 2. 추천 멘토 정보 (UB_MENTORING + UB_MEMBER + UB_SUBJECT 조인)
	// =============================================
	// UB_MENTORING: INTERNAL_ID, MENTOR_NUMBER, SUBJECT_NUMBER,
	// MENTORING_TITLE, MENTORING_GOAL, MENTORING_DETAIL,
	// MENTORING_FILE, CREATED_AT, UPDATED_AT
	// UB_MEMBER: member_number, member_nickname, member_profile(파일번호)
	// UB_SUBJECT: subject_name
	// 추천 멘토 정보 (수정됨)
	public static class MentorCardDTO {
		private String mentoringNumber;
	    private long mentorNumber;
	    private String mentoringTitle;
	    private String subjectName;
	    private String memberNickname;
	    private String fileName;     // 프로필 이미지 파일명
	    private String gradSchool;   // 학교 명
	    private String gradDepart;   // 학과 명

		public MentorCardDTO() {
		}
		// [수정] Getter/Setter 명칭 변경

		
		public String getMentoringNumber() {
			return mentoringNumber;
		}

		public void setMentoringNumber(String mentoringNumber) {
			this.mentoringNumber = mentoringNumber;
		}



		public long getMentorNumber() {
			return mentorNumber;
		}


		public void setMentorNumber(long mentorNumber) {
			this.mentorNumber = mentorNumber;
		}


		public String getMentoringTitle() {
			return mentoringTitle;
		}


		public void setMentoringTitle(String mentoringTitle) {
			this.mentoringTitle = mentoringTitle;
		}


		public String getSubjectName() {
			return subjectName;
		}


		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}


		public String getMemberNickname() {
			return memberNickname;
		}


		public void setMemberNickname(String memberNickname) {
			this.memberNickname = memberNickname;
		}


		public String getFileName() {
			return fileName;
		}


		public void setFileName(String fileName) {
			this.fileName = fileName;
		}


	public String getGradSchool() {
			return gradSchool;
		}


		public void setGradSchool(String gradSchool) {
			this.gradSchool = gradSchool;
		}


		public String getGradDepart() {
			return gradDepart;
		}


		public void setGradDepart(String gradDepart) {
			this.gradDepart = gradDepart;
		}

		@Override
		public String toString() {
			return "MentorCardDTO [mentoringNumber=" + mentoringNumber + ", mentorNumber=" + mentorNumber
					+ ", mentoringTitle=" + mentoringTitle + ", subjectName=" + subjectName + ", memberNickname="
					+ memberNickname + ", fileName=" + fileName + ", gradSchool=" + gradSchool + ", gradDepart="
					+ gradDepart + "]";
		}
		
	}
	
    // 3. 취업 회사 정보 (UB_COMPANY)
    public static class CompanyDTO {
        private int    employmentId;
        private String companyName;
        private String employmentTitle;
        private String employmentLocation;
        private String employmentCareer;
        private String employmentEducation;
        private String employmentLog;
        private String employmentUrl;

        public CompanyDTO() {}

        public int getEmploymentId() { return employmentId; }
        public void setEmploymentId(int employmentId) { this.employmentId = employmentId; }
        public String getCompanyName() { return companyName; }
        public void setCompanyName(String companyName) { this.companyName = companyName; }
        public String getEmploymentTitle() { return employmentTitle; }
        public void setEmploymentTitle(String employmentTitle) { this.employmentTitle = employmentTitle; }
        public String getEmploymentLocation() { return employmentLocation; }
        public void setEmploymentLocation(String employmentLocation) { this.employmentLocation = employmentLocation; }
        public String getEmploymentCareer() { return employmentCareer; }
        public void setEmploymentCareer(String employmentCareer) { this.employmentCareer = employmentCareer; }
        public String getEmploymentEducation() { return employmentEducation; }
        public void setEmploymentEducation(String employmentEducation) { this.employmentEducation = employmentEducation; }
        public String getEmploymentLog() { return employmentLog; }
        public void setEmploymentLog(String employmentLog) { this.employmentLog = employmentLog; }
        public String getEmploymentUrl() { return employmentUrl; }
        public void setEmploymentUrl(String employmentUrl) { this.employmentUrl = employmentUrl; }

        @Override
        public String toString() {
            return "CompanyDTO [employmentId=" + employmentId + ", companyName=" + companyName
                    + ", employmentTitle=" + employmentTitle + ", employmentLocation=" + employmentLocation + "]";
        }
    }
}
