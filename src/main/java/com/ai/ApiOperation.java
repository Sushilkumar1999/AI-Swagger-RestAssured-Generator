package com.ai;

import java.util.List;
import java.util.Set;

public class ApiOperation {

	private String endpoint;
	private String method;
	private String operationId;
	private String summary;
	private String payload;
	private Set<String> responseCodes;

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Set<String> getResponseCodes() {
		return responseCodes;
	}

	public void setResponseCodes(Set<String> responseCodes) {
		this.responseCodes = responseCodes;
	}
}