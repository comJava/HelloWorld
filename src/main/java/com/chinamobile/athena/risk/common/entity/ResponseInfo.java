package com.chinamobile.athena.risk.common.entity;

public class ResponseInfo {
	private RiskHeader header;
	private ResBody body;

	public RiskHeader getHeader() {
		return header;
	}

	public void setHeader(RiskHeader header) {
		this.header = header;
	}

	public ResBody getBody() {
		return body;
	}

	public void setBody(ResBody body) {
		this.body = body;
	}

}
