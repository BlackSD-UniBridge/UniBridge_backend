package com.unibridge.app.admin.dto;

public class AdminUserMMDetailDTO {
	private int memberNumber;
	private String memberId;
	private String memberName;
	private String memberNickname;
	private String memberGender;
	private String memberPhone;
	private String memberType;
	private String memberState;
	
	private String mtrGradSchool;
	private String mtrGradDepart;
	private float mtrGradScore;
	
	private String mteSchool;
	private int mteGrade;
	private String mteHopeUni;
	private String mteHopeMajor;
	
	private String subjectName;

	public AdminUserMMDetailDTO() {
		super();
	}

	public AdminUserMMDetailDTO(int memberNumber, String memberId, String memberName, String memberNickname,
			String memberGender, String memberPhone, String memberType, String memberState, String mtrGradSchool,
			String mtrGradDepart, float mtrGradScore, String mteSchool, int mteGrade, String mteHopeUni,
			String mteHopeMajor, String subjectName) {
		super();
		this.memberNumber = memberNumber;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberGender = memberGender;
		this.memberPhone = memberPhone;
		this.memberType = memberType;
		this.memberState = memberState;
		this.mtrGradSchool = mtrGradSchool;
		this.mtrGradDepart = mtrGradDepart;
		this.mtrGradScore = mtrGradScore;
		this.mteSchool = mteSchool;
		this.mteGrade = mteGrade;
		this.mteHopeUni = mteHopeUni;
		this.mteHopeMajor = mteHopeMajor;
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "AdminUserMMDetailDTO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberName="
				+ memberName + ", memberNickname=" + memberNickname + ", memberGender=" + memberGender
				+ ", memberPhone=" + memberPhone + ", memberType=" + memberType + ", memberState=" + memberState
				+ ", mtrGradSchool=" + mtrGradSchool + ", mtrGradDepart=" + mtrGradDepart + ", mtrGradScore="
				+ mtrGradScore + ", mteSchool=" + mteSchool + ", mteGrade=" + mteGrade + ", mteHopeUni=" + mteHopeUni
				+ ", mteHopeMajor=" + mteHopeMajor + ", subjectName=" + subjectName + ", toString()=" + super.toString()
				+ "]";
	}

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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberState() {
		return memberState;
	}
	public void setMemberState(String memberState) {
		this.memberState = memberState;
	}
	public String getMtrGradSchool() {
		return mtrGradSchool;
	}
	public void setMtrGradSchool(String mtrGradSchool) {
		this.mtrGradSchool = mtrGradSchool;
	}
	public String getMtrGradDepart() {
		return mtrGradDepart;
	}
	public void setMtrGradDepart(String mtrGradDepart) {
		this.mtrGradDepart = mtrGradDepart;
	}
	public float getMtrGradScore() {
		return mtrGradScore;
	}
	public void setMtrGradScore(float mtrGradScore) {
		this.mtrGradScore = mtrGradScore;
	}
	public String getMteSchool() {
		return mteSchool;
	}
	public void setMteSchool(String mteSchool) {
		this.mteSchool = mteSchool;
	}
	public int getMteGrade() {
		return mteGrade;
	}
	public void setMteGrade(int mteGrade) {
		this.mteGrade = mteGrade;
	}
	public String getMteHopeUni() {
		return mteHopeUni;
	}
	public void setMteHopeUni(String mteHopeUni) {
		this.mteHopeUni = mteHopeUni;
	}
	public String getMteHopeMajor() {
		return mteHopeMajor;
	}
	public void setMteHopeMajor(String mteHopeMajor) {
		this.mteHopeMajor = mteHopeMajor;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
