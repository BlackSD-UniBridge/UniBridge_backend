package com.unibridge.app;

public class Result {
	//필드
	private String path;
	private boolean isRedirect;
	
	//세터,게터
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

}
