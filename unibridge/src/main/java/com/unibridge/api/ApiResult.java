package com.unibridge.api;

public class ApiResult<T> {
	private String contentType;
	private T rawData;
	
	public ApiResult() {
		super();
	}
	
	public ApiResult(String contentType, T rawData) {
		super();
		this.contentType = contentType;
		this.rawData = rawData;
	}
	
	@Override
	public String toString() {
		return "Result [contentType=" + contentType + ", rawData=" + rawData + ", toString()=" + super.toString() + "]";
	}
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public T getRawData() {
		return rawData;
	}
	public void setRawData(T rawData) {
		this.rawData = rawData;
	}
}
