package com.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.parser.OpenAPIV3Parser;

public class Main {

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\main\\resources\\SwaggerSpec\\PetStore_Swagger_Spec.json";
		OpenAPI openapi = new OpenAPIV3Parser().read(filePath);
		if (openapi == null) {
			System.out.println("Could not read swagger");
			return;
		}
		System.out.println(openapi.getInfo().getTitle());
		SwaggerProcessor processor = new SwaggerProcessor();
		List<ApiOperation> allOperations = new ArrayList<>();
		Set<String> allPaths = openapi.getPaths().keySet();
		Iterator<String> it = allPaths.iterator();
		while (it.hasNext()) {
			String path = it.next();
			System.out.println("Endpoint : " + path);
			PathItem pitem = openapi.getPaths().get(path);
			ApiOperation operation;
			operation = processor.processOperation(path, "GET", pitem.getGet(), openapi);
			if (operation != null) {
				allOperations.add(operation);
			}
			operation = processor.processOperation(path, "POST", pitem.getPost(), openapi);
			if (operation != null) {
				allOperations.add(operation);
			}
			operation = processor.processOperation(path, "PUT", pitem.getPut(), openapi);
			if (operation != null) {
				allOperations.add(operation);
			}
			operation = processor.processOperation(path, "DELETE", pitem.getDelete(), openapi);
			if (operation != null) {
				allOperations.add(operation);
			}
			operation = processor.processOperation(path, "PATCH", pitem.getPatch(), openapi);
			if (operation != null) {
				allOperations.add(operation);
			}
		}
		System.out.println("\nTotal Operations : " + allOperations.size());
		PromptBuilder builder = new PromptBuilder();
		OpenRouterService service = new OpenRouterService();
		FileWriterUtil fileWriter = new FileWriterUtil();
		GeneratedCodeValidator validator = new GeneratedCodeValidator();
		for (ApiOperation operation : allOperations) {
			System.out.println("\n================================");
			System.out.println(operation.getMethod() + " " + operation.getEndpoint());
			String prompt = builder.buildPrompt(operation);
			String generatedCode = service.generateCode(prompt);
			if (generatedCode == null || generatedCode.trim().isEmpty()) {
				System.out.println("Empty AI response. Skipping...");
				continue;
			}
			generatedCode = validator.validateAndClean(generatedCode);
			if (!validator.isValidCode(generatedCode)) {
				System.out.println("Invalid generated code. Skipping...");
				continue;
			}
			String className = validator.extractClassName(generatedCode);
			if (className != null) {
				fileWriter.saveFile(className + ".java", generatedCode);
				System.out.println("Saved : " + className + ".java");
			}
		}
	}
}