package com.unibridge.app.pay.dto;

public class PaymentDTO {
    private long payId;         // PAY_ID (PK)
    private String payAmount;   // PAY_AMOUNT
    private String payMethod;   // PAY_METHOD
    private String payDate;     // PAY_DATE (조회 시 String 또는 LocalDateTime 사용)
    private String payStatus;   // PAY_STATUS
    private int memberNumber;   // JSP의 memberNumber와 일치시킴 (기존 menteeNumber 역할)
    private long matchingNumber; // JSP의 matchingNumber를 위해 추가
    private String fileName;
    
    private String memberId;   // 회원 아이디 (UB_MEMBER.MEMBER_ID)
    private String memberName; // 회원 이름 (UB_MEMBER.MEMBER_NAME)

    
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

    // 기본 생성자
    public PaymentDTO() {}

    // Getter & Setter
    public long getPayId() {
        return payId;
    }

    public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public long getMatchingNumber() {
		return matchingNumber;
	}

	public void setMatchingNumber(long matchingNumber) {
		this.matchingNumber = matchingNumber;
	}

	public void setPayId(long payId) {
        this.payId = payId;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

	@Override
	public String toString() {
		return "PaymentDTO [payId=" + payId + ", payAmount=" + payAmount + ", payMethod=" + payMethod + ", payDate="
				+ payDate + ", payStatus=" + payStatus + ", memberNumber=" + memberNumber + ", matchingNumber="
				+ matchingNumber + ", memberId=" + memberId + ", memberName=" + memberName + "]";
	}

}