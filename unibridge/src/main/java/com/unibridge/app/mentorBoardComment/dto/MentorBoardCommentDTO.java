package com.unibridge.app.mentorBoardComment.dto;

public class MentorBoardCommentDTO {
    private int    mentorComId;
    private String mentorComContent;
    private String mentorComDate;
    private int    memberNumber;
    private String memberId;
    private String memberNickname;
    private int    mentorBoardId;   // FK → UB_MENTOR_BOARD.mentorboard_number

    public int getMentorComId() { return mentorComId; }
    public void setMentorComId(int mentorComId) { this.mentorComId = mentorComId; }

    public String getMentorComContent() { return mentorComContent; }
    public void setMentorComContent(String mentorComContent) { this.mentorComContent = mentorComContent; }

    public String getMentorComDate() { return mentorComDate; }
    public void setMentorComDate(String mentorComDate) { this.mentorComDate = mentorComDate; }

    public int getMemberNumber() { return memberNumber; }
    public void setMemberNumber(int memberNumber) { this.memberNumber = memberNumber; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getMemberNickname() { return memberNickname; }
    public void setMemberNickname(String memberNickname) { this.memberNickname = memberNickname; }

    public int getMentorBoardId() { return mentorBoardId; }
    public void setMentorBoardId(int mentorBoardId) { this.mentorBoardId = mentorBoardId; }

    @Override
    public String toString() {
        return "MentorBoardCommentDTO [mentorComId=" + mentorComId
                + ", mentorComContent=" + mentorComContent
                + ", mentorComDate=" + mentorComDate
                + ", memberNumber=" + memberNumber
                + ", mentorBoardId=" + mentorBoardId + "]";
    }
}
