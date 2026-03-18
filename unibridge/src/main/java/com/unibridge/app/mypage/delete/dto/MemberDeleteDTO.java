package com.unibridge.app.mypage.delete.dto;

public class MemberDeleteDTO {
	private String memberId;
	private String memberPw;
	
	public MemberDeleteDTO() {
		super();
	}
	
	public MemberDeleteDTO(String memberId, String memberPw) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
	}
	
	@Override
	public String toString() {
		return "MemberDeleteDTO [memberId=" + memberId + ", memberPw=" + memberPw + ", toString()=" + super.toString()
				+ "]";
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
}
