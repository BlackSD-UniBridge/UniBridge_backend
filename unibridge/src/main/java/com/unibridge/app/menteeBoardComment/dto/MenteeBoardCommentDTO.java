package com.unibridge.app.menteeBoardComment.dto;

public class MenteeBoardCommentDTO {
    private int    menteeComId;
    private String menteeComContent;
    private String menteeComDate;
    private int    memberNumber;
    private String memberId;
    private String memberNickname;
    private int    menteeBoardId;   // FK → UB_MENTEE_BOARD.menteeboard_number

    public int getMenteeComId() { return menteeComId; }
    public void setMenteeComId(int menteeComId) { this.menteeComId = menteeComId; }

    public String getMenteeComContent() { return menteeComContent; }
    public void setMenteeComContent(String menteeComContent) { this.menteeComContent = menteeComContent; }

    public String getMenteeComDate() { return menteeComDate; }
    public void setMenteeComDate(String menteeComDate) { this.menteeComDate = menteeComDate; }

    public int getMemberNumber() { return memberNumber; }
    public void setMemberNumber(int memberNumber) { this.memberNumber = memberNumber; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getMemberNickname() { return memberNickname; }
    public void setMemberNickname(String memberNickname) { this.memberNickname = memberNickname; }

    public int getMenteeBoardId() { return menteeBoardId; }
    public void setMenteeBoardId(int menteeBoardId) { this.menteeBoardId = menteeBoardId; }

    @Override
    public String toString() {
        return "MenteeBoardCommentDTO [menteeComId=" + menteeComId
                + ", menteeComContent=" + menteeComContent
                + ", menteeComDate=" + menteeComDate
                + ", memberNumber=" + memberNumber
                + ", menteeBoardId=" + menteeBoardId + "]";
    }
}
