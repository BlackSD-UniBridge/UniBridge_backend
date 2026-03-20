package com.unibridge.app.pay.dto;

public class PaymentDTO {
    private long payId;         // PAY_ID (PK)
    private String payAmount;   // PAY_AMOUNT
    private String payMethod;   // PAY_METHOD
    private String payDate;     // PAY_DATE (조회 시 String 또는 LocalDateTime 사용)
    private String payStatus;   // PAY_STATUS

    // 기본 생성자
    public PaymentDTO() {}

    // Getter & Setter
    public long getPayId() {
        return payId;
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
        return "PaymentDTO [payId=" + payId + ", payAmount=" + payAmount 
                + ", payMethod=" + payMethod + ", payDate=" + payDate 
                + ", payStatus=" + payStatus + "]";
    }
}