package com.ai;

public class PromptBuilder {

	public String buildPrompt(ApiOperation apiOperation) {

		StringBuilder prompt = new StringBuilder();

		prompt.append("Act as a Senior QA Automation Engineer.\n");
		prompt.append("Generate only Java Rest Assured TestNG code.\n");
		prompt.append("Do not provide explanations.\n");
		prompt.append("Do not use markdown.\n");
		prompt.append("Do not use code fences.\n");
		prompt.append("Generate complete compilable Java code.\n");
		prompt.append("Generate only one positive test scenario.\n");
		prompt.append("Validate only status code.\n");
		prompt.append("Do not assume response body fields.\n");
		prompt.append("Use ContentType.JSON.\n");
		prompt.append("Create a test class name based on operation id.\n\n");
		prompt.append("API Details\n");
		prompt.append("Use BASE_URI = \"https://petstore3.swagger.io/api/v3\".\n");
		prompt.append("Use the provided payload exactly as request body.\n");
		prompt.append("Generate Rest Assured given-when-then syntax.\n");
		prompt.append("Do not create additional POJO classes.\n");
		prompt.append("Use String payload instead of POJO.\n");
		prompt.append("Endpoint : ").append(apiOperation.getEndpoint()).append("\n");
		prompt.append("Method : ").append(apiOperation.getMethod()).append("\n");
		prompt.append("Operation Id : ").append(apiOperation.getOperationId()).append("\n\n");
		if (apiOperation.getPayload() != null && !apiOperation.getPayload().isEmpty()) {
			prompt.append("Request Payload :\n");
			prompt.append(apiOperation.getPayload());
			prompt.append("\n\n");
		}
		prompt.append("Expected Response Codes :\n");
		for (String code : apiOperation.getResponseCodes()) {
			prompt.append(code).append("\n");
		}
		return prompt.toString();
	}
}