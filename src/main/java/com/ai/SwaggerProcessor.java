package com.ai;

import java.util.Set;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;

public class SwaggerProcessor {

	private String generatedPayload = "";

	public ApiOperation processOperation(String endpoint, String method, Operation operation, OpenAPI openapi) {
		generatedPayload = "";
		if (operation == null) {
			return null;
		}
		ApiOperation apiOperation = new ApiOperation();
		apiOperation.setEndpoint(endpoint);
		apiOperation.setMethod(method);
		apiOperation.setOperationId(operation.getOperationId());
		apiOperation.setSummary(operation.getSummary());
		System.out.println("Method : " + method);
		System.out.println("Summary : " + operation.getSummary());
		System.out.println("Operation id : " + operation.getOperationId());
		processRequestBody(operation, openapi);
		apiOperation.setPayload(generatedPayload);
		Set<String> responseCodes = processResponses(operation);
		apiOperation.setResponseCodes(responseCodes);
		System.out.println("===============");
		return apiOperation;
	}

	public void processRequestBody(Operation operation, OpenAPI openapi) {
		if (operation.getRequestBody() == null) {
			return;
		}
		System.out.println("Request Body exists : YES");
		Set<String> contentTypes = operation.getRequestBody().getContent().keySet();
		boolean schemaPrinted = false;
		for (String contentType : contentTypes) {
			System.out.println("Content-type : " + contentType);
			Schema schema = operation.getRequestBody().getContent().get(contentType).getSchema();
			if (schema == null) {
				continue;
			}
			System.out.println("Schema type : " + schema.getType());
			if (schema.get$ref() != null && !schemaPrinted) {
				System.out.println("Schema ref : " + schema.get$ref());
				processSchemaDetails(schema, openapi);
				schemaPrinted = true;
			}
		}
	}

	public String processSchemaDetails(Schema schema, OpenAPI openapi) {
		if (schema.get$ref() == null) {
			return null;
		}
		StringBuilder payloadBuilder = new StringBuilder();
		String schemaRef = schema.get$ref();
		String schemaName = schemaRef.substring(schemaRef.lastIndexOf("/") + 1);
		System.out.println("Schema Name : " + schemaName);
		Schema actualSchema = openapi.getComponents().getSchemas().get(schemaName);
		System.out.println("Fields : ");
		Set<String> fieldNames = actualSchema.getProperties().keySet();
		System.out.println("Sample Payload : ");
		payloadBuilder.append("{\n");
		int fieldCount = 0;
		int totalFields = fieldNames.size();
		for (String field : fieldNames) {
			Schema fieldSchema = (Schema) actualSchema.getProperties().get(field);
			String fieldType = fieldSchema.getType();
			String sampleValue = "";
			if ("string".equals(fieldType)) {
				sampleValue = "\"sampleText\"";
			}
			else if ("integer".equals(fieldType)) {
				sampleValue = "1";
			}
			else if ("boolean".equals(fieldType)) {
				sampleValue = "true";
			}
			else if ("array".equals(fieldType)) {
				sampleValue = "[]";
			}
			else {
				sampleValue = "\"sample\"";
			}
			fieldCount++;
			if (fieldCount < totalFields) {
				payloadBuilder.append("\"" + field + "\" : " + sampleValue + ",\n");
			}
			else {
				payloadBuilder.append("\"" + field + "\" : " + sampleValue + "\n");
			}
		}
		payloadBuilder.append("}");
		generatedPayload = payloadBuilder.toString();
		System.out.println(generatedPayload);
		return generatedPayload;
	}

	public Set<String> processResponses(Operation operation) {
		Set<String> responseCodes = operation.getResponses().keySet();
		System.out.println("responseCode : ");
		for (String code : responseCodes) {
			System.out.println(code);
		}
		return responseCodes;
	}
}