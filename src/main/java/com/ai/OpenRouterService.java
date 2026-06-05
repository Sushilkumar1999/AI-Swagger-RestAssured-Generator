package com.ai;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OpenRouterService {

	public String generateCode(String prompt) {

		String generatedCode = "";
		try {
			String apiKey = "YOUR_API_KEY";
			URL url = new URL("https://openrouter.ai/api/v1/chat/completions");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + apiKey);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			String requestBody = "{" + "\"model\": \"nvidia/nemotron-3-super-120b-a12b:free\"," + "\"messages\": ["
					+ "{" + "\"role\": \"user\"," + "\"content\": \"" + prompt.replace("\"", "\\\"") + "\"" + "}" + "]"
					+ "}";
			OutputStream os = connection.getOutputStream();
			byte[] input = requestBody.getBytes("utf-8");
			os.write(input, 0, input.length);
			os.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuilder response = new StringBuilder();
			String responseLine;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			br.close();
			System.out.println("\nAI RESPONSE:\n");
			JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
			JsonArray choices = jsonObject.getAsJsonArray("choices");
			JsonObject firstChoice = choices.get(0).getAsJsonObject();
			JsonObject message = firstChoice.getAsJsonObject("message");
			generatedCode = message.get("content").getAsString();
			generatedCode = generatedCode.replace("```java", "");
			generatedCode = generatedCode.replace("```", "");
			generatedCode = generatedCode.trim();
			System.out.println("\nGENERATED CODE:\n");
			System.out.println(generatedCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedCode;
	}
}