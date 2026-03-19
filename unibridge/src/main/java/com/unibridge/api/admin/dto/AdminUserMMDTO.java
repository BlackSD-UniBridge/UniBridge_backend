package com.unibridge.api.admin.dto;

public class AdminUserMMDTO {
	private int memberNumber;
	private String memberName;
	private String memberType;
	private String memberState;
	
	public AdminUserMMDTO() {
		super();
	}
	
	public AdminUserMMDTO(int memberNumber, String memberName, String memberType, String memberState) {
		super();
		this.memberNumber = memberNumber;
		this.memberName = memberName;
		this.memberType = memberType;
		this.memberState = memberState;
	}
	
	@Override
	public String toString() {
		return "AdminUserMMDTO [memberNumber=" + memberNumber + ", memberName=" + memberName + ", memberType="
				+ memberType + ", memberState=" + memberState + ", toString()=" + super.toString() + "]";
	}
	
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
}