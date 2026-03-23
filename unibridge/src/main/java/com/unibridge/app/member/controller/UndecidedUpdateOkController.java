package com.unibridge.app.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.file.dto.FileDTO; // FileDTO 임포트 확인
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;

public class UndecidedUpdateOkController implements Execute {
    private Result outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String method = request.getMethod().toUpperCase();

        switch (method) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
        }

        return outResult;
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) {
    	
        HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        MemberDAO dao = new MemberDAO();

        if (loginUser != null) {
        	Map<String, Object> member = dao.selectMemberDetail(loginUser.getMemberNumber());
            request.setAttribute("member", member);
        }

        outResult.setPath("/app/user/undetermined/myPage/userManage/userModify.jsp");
        outResult.setRedirect(false);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        MemberDAO dao = new MemberDAO();
        int memberNumber = loginUser.getMemberNumber();

        // 운영체제에 상관없이 동작하도록 설정하거나, 기존 경로 유지
        String UPLOAD_PATH = "C:/upload/profile/"; 
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        try {
            boolean isMultipart = request.getContentType() != null && 
                                  request.getContentType().toLowerCase().contains("multipart/form-data");

            if (isMultipart) {
                // [A] 프로필 사진 업로드 처리
                MultipartRequest multi = new MultipartRequest(
                    request, UPLOAD_PATH, 5 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy()
                );

                String updateType = multi.getParameter("updateType");

                if ("profileImg".equals(updateType)) {
                    String originalName = multi.getOriginalFileName("profileFile");
                    if (originalName != null) {
                        File uploadedFile = multi.getFile("profileFile");
                        String extension = originalName.substring(originalName.lastIndexOf("."));
                        String newFileName = "profile_" + memberNumber + "_" + System.currentTimeMillis() + extension;
                        File newFile = new File(UPLOAD_PATH, newFileName);
                        
                        if (uploadedFile.renameTo(newFile)) {
                            // 1. [중요] 기존 물리 파일 삭제 (서버 용량 관리)
                            String oldFileName = loginUser.getMemberImg();
                            if (oldFileName != null && !oldFileName.isEmpty()) {
                                File oldFile = new File(UPLOAD_PATH, oldFileName);
                                if (oldFile.exists()) oldFile.delete();
                            }

                            // 2. 파일 정보 DTO 생성
                            FileDTO fileDTO = new FileDTO();
                            fileDTO.setFileName(newFileName);
                            fileDTO.setFileOriginalName(originalName);
                            fileDTO.setFileExtension(extension.replace(".", ""));
                            fileDTO.setFileSize(newFile.length());
                            fileDTO.setFilePath("/upload/profile/" + newFileName);

                            // 3. DB 분기 처리
                            if (loginUser.getFileNumber() == 0) {
                                int newFileNumber = dao.insertProfileFile(fileDTO);
                                dao.updateMemberFileNumber(memberNumber, newFileNumber);
                                loginUser.setFileNumber(newFileNumber);
                            } else {
                                fileDTO.setFileNumber(loginUser.getFileNumber());
                                dao.updateProfileFile(fileDTO);
                            }

                            // 4. 세션 및 결과 세팅
                            loginUser.setMemberImg(newFileName); 
                            request.setAttribute("updateStatus", "profileImg_ok");
                        }
                    }
                }
            } else {
                // --- [B] 일반 텍스트 데이터 처리 (기존 로직 유지) ---
            	String updateType = request.getParameter("updateType");
                System.out.println("[일반수정] 요청 updateType: " + updateType);

                if ("nickname".equals(updateType)) {
                    String nickname = request.getParameter("memberNickname");
                    System.out.println("[닉네임] 중복 체크 진행: " + nickname);
                    if (dao.checkNickname(nickname, memberNumber) == 0) { 
                        dao.updateNickname(memberNumber, nickname);
                        request.setAttribute("updateStatus", "nickname_ok");
                        System.out.println("[닉네임] 수정 성공");
                    } else {
                        request.setAttribute("nickError", "중복된 닉네임입니다.");
                    }
                }
                else if ("password".equals(updateType)) {
                    String pw = request.getParameter("newPw");
                    String pwConfirm = request.getParameter("newPwConfirm");
                    if (pw != null && pw.equals(pwConfirm) && !pw.isEmpty()) {
                        dao.updatePassword(memberNumber, pw);
                        request.setAttribute("updateStatus", "password_ok");
                    }
                }
                else if ("phone".equals(updateType)) {
                    Boolean isVerified = (Boolean) session.getAttribute("isPhoneVerified");
                    String phone = request.getParameter("memberPhone");
                    if (isVerified != null && isVerified) {
                        dao.updatePhone(memberNumber, phone);
                        session.removeAttribute("isPhoneVerified");
                        request.setAttribute("updateStatus", "phone_ok");
                    }
                }
                else if ("gender".equals(updateType)) {
                    String gender = request.getParameter("memberGender");
                    dao.updateGender(memberNumber, gender);
                    request.setAttribute("updateStatus", "gender_ok");
                }
            }
        } catch (Exception e) {
        	System.err.println("[오류발발] 처리 중 예외 발생!");
            e.printStackTrace();
            request.setAttribute("updateError", "처리 중 오류가 발생했습니다.");
        }

        // 결과 세팅
        System.out.println("[결과세팅] 최종 회원 상세 데이터 조회(selectMemberDetail)");
        request.setAttribute("member", dao.selectMemberDetail(memberNumber));
        System.out.println("[컨트롤러] <<< doPost 실행 종료: JSP로 포워딩\n");
        
        outResult.setPath("/app/user/undetermined/myPage/userManage/userModify.jsp");
        outResult.setRedirect(false);
    }
}