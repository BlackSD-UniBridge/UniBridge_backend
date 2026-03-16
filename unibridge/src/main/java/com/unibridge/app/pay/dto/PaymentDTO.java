package com.unibridge.app.pay.dto;

public class PaymentDTO {
    private long payId;             // PAY_ID
    private long matchingNumber;    // MATCHING_NUMBER
    private String payAmount;       // PAY_AMOUNT (테이블 타입에 맞춰 String으로 설정)
    private String payMethod;       // PAY_METHOD
    private String payDate;         // PAY_DATE (보통 DB에서 SYSDATE로 넣지만 조회용으로 추가)
    private String payStatus;       // PAY_STATUS
    private long memberNumber;      // MEMBER_NUMBER (결제한 회원 번호)

    // 기본 생성자
    public PaymentDTO() {}

    // Getter & Setter
    public long getPayId() {
        return payId;
    }

    public void setPayId(long payId) {
        this.payId = payId;
    }

    public long getMatchingNumber() {
        return matchingNumber;
    }

    public void setMatchingNumber(long matchingNumber) {
        this.matchingNumber = matchingNumber;
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

    public long getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(long memberNumber) {
        this.memberNumber = memberNumber;
    }

    @Override
    public String toString() {
        return "PaymentDTO [payId=" + payId + ", matchingNumber=" + matchingNumber + ", payAmount=" + payAmount
                + ", payMethod=" + payMethod + ", payDate=" + payDate + ", payStatus=" + payStatus + ", memberNumber="
                + memberNumber + "]";
    }
}