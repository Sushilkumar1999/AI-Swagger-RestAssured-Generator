![Java](https://img.shields.io/badge/Java-17+-orange)
![AI](https://img.shields.io/badge/AI-OpenRouter-Yellow)
![Build](https://img.shields.io/badge/Build-Maven-brightgreen)

# 🚀 AI Swagger to Rest Assured Generator

An AI-powered framework that automatically converts Swagger/OpenAPI specifications into executable Rest Assured + TestNG automation tests using Java and Large Language Models (LLMs).

## 🎯 Overview

This project explores how Generative AI can accelerate API automation development by generating Rest Assured test scripts directly from Swagger/OpenAPI contracts.

The framework:

✅ Reads Swagger/OpenAPI specifications
✅ Extracts endpoints, methods, request schemas, and response codes
✅ Builds operation-specific prompts dynamically
✅ Uses an LLM to generate Rest Assured + TestNG code
✅ Validates generated output
✅ Saves each API operation as an individual Java test class

---

## 🏗️ Architecture

<img width="1327" height="313" alt="image" src="https://github.com/user-attachments/assets/fa1c47aa-b631-4c7f-b5d2-5cc4a5485eff" />


```text
Swagger/OpenAPI Specification
              │
              ▼
     Swagger Parser Engine
              │
              ▼
    API Metadata Extraction
              │
              ▼
         Prompt Builder
              │
              ▼
      OpenRouter / LLM
              │
              ▼
      Test Code Generator
              │
              ▼
      Generated Code Validator
              │
              ▼
      Java File Generation
```

---

## 🛠️ Tech Stack

* ☕ Java
* 📦 Maven
* 📄 Swagger / OpenAPI
* 🔥 Rest Assured
* 🧪 TestNG
* 📦 Gson
* 🤖 OpenRouter API
* 🧠 Generative AI

---

## ✨ Features

### 📄 Swagger Processing

Automatically extracts:

* API Endpoints
* HTTP Methods
* Operation IDs
* Request Payload Schemas
* Response Codes

### 🧩 Dynamic Payload Generation

Generates sample payloads directly from Swagger schemas.

Example:

```json
{
  "id": 1,
  "username": "sampleText",
  "email": "sampleText"
}
```

### 🤖 AI-Powered Test Generation

Generates:

* Rest Assured Test Scripts
* TestNG Test Classes
* Request Payloads
* Status Code Assertions
* Positive Test Scenarios

### ✅ Code Validation

Validates generated output before saving:

* Class existence
* Test annotations
* Empty responses
* Markdown cleanup

---

## 📊 Execution Results

PetStore Swagger Evaluation

```text
Total API Operations Parsed : 19
Generated Test Classes      : 19

Passed Tests               : 7
Failed Tests               : 12
```

### 📌 Reality Check

The generated tests highlighted both the strengths and limitations of current AI-assisted automation.

### ✅ What Worked Well

* Boilerplate test generation
* Rest Assured request creation
* TestNG structure generation
* Basic payload construction
* Status code validations

### ❌ Where AI Struggled

* API dependency awareness
* Test data management
* Path parameter handling
* Environment-specific context
* Complex business workflows
* Nested schema understanding

Example:

A generated DELETE test may fail because the AI does not understand that a valid resource must first be created before it can be deleted.

> AI understands the API contract.
> It does not automatically understand the execution context.

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/Sushilkumar1999/AI-Swagger-RestAssured-Generator.git
```

### Install Dependencies

```bash
mvn clean install
```

### Configure OpenRouter API Key

Set your OpenRouter API key before execution.

### Run

Execute:

```text
Main.java
```

Generated test classes will be created automatically.

---

## 💡 Key Learning

This project demonstrates that AI can significantly accelerate the creation of API automation scaffolding.

However, generating reliable, executable automation still requires engineering judgment, domain understanding, and proper test design.

🚀 AI accelerates implementation.

👨‍💻 Quality engineering still requires human expertise.

---

## 👨‍💻 Author

Built by an SDET engineer as a production-quality AI automation portfolio project.

⭐ If you find this project interesting, consider starring the repository.
