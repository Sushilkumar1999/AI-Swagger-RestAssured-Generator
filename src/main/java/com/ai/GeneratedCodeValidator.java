package com.ai;

public class GeneratedCodeValidator {

	public String validateAndClean(String code) {

		if (code == null || code.trim().isEmpty()) {
			return "";
		}
		code = code.replace("```java", "");
		code = code.replace("```", "");
		code = code.replaceAll("package\\s+.*?;", "");
		code = code.trim();
		return code;
	}

	public String extractClassName(String code) {
		if (code == null || code.isEmpty()) {
			return null;
		}
		int classIndex = code.indexOf("class ");
		if (classIndex == -1) {
			return null;
		}
		String remaining = code.substring(classIndex + 6);
		String[] parts = remaining.split("\\s+");
		if (parts.length == 0) {
			return null;
		}
		return parts[0].trim();
	}

	public boolean containsClass(String code) {
		if (code == null) {
			return false;
		}
		return code.contains("class ");
	}

	public boolean containsTestAnnotation(String code) {
		if (code == null) {
			return false;
		}
		return code.contains("@Test");
	}

	public boolean isValidCode(String code) {
		return containsClass(code) && containsTestAnnotation(code);
	}
}