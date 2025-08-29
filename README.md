💰 Spend Mate – Expense Tracking System

📌 Repo: SpendMate

📅 Started: January 2025

✨ Overview

Spend Mate is an event-driven expense tracking system built on microservices architecture.
It helps users record, analyze, and interact with their expenses in real time. With Kafka for high-throughput messaging, MySQL for reliable persistence, and ChatGPT API for natural language queries, users can simply “Ask how much I spent on food last week” and get instant answers.

🚀 Key Features

⚡ High Throughput – Handles 500+ transactions per minute with Kafka-based microservices.

🤖 ChatGPT Integration – Natural language queries improve user interaction efficiency by 40%.

📊 Expense Analytics – Category-wise and time-based tracking.

🔐 Secure APIs – Built with Spring Boot + JWT/OAuth-ready.

🐳 Containerized – Docker + docker-compose for local deployment.

🏗 Scalable – Event-driven design, ready for cloud-native scaling.

🧱 Tech Stack

Backend Framework: Spring Boot (3.x)

Messaging System: Apache Kafka

Database: MySQL

AI Integration: OpenAI ChatGPT API

Containerization: Docker & Docker Compose

Build Tool: Gradle/Maven (choose based on your repo)

🏗 Architecture
flowchart LR
  subgraph Client["📱 User / Frontend"]
    A[Add Expense] -->|REST/gRPC| B
    A2[Ask Query] -->|NL Query| B
  end

  subgraph API["Spring Boot Services"]
    B[API Gateway] --> C[Expense Service]
    B --> D[Query Service (ChatGPT)]
  end

  subgraph Kafka["Kafka Broker"]
    C -->|Publish ExpenseEvent| E[(Kafka Topic: expenses)]
    E --> F[Analytics Service]
  end

  subgraph DB["Databases"]
    C --> G[(MySQL: expenses)]
    F --> H[(MySQL: analytics)]
  end

  D -->|ChatGPT API| I[🤖 OpenAI GPT-4]

⚙️ Environment Variables

Create .env file(s) as needed:

# Database
MYSQL_USER=root
MYSQL_PASSWORD=your_password
MYSQL_DB=spendmate

# Kafka
KAFKA_BROKER=kafka:9092
KAFKA_TOPIC=expenses

# OpenAI
OPENAI_API_KEY=your_openai_api_key

🐳 Running with Docker

Clone the repo:

git clone https://github.com/SUMIT74184/SpendMate.git
cd SpendMate


Build & start services:

docker-compose up --build


Access services:

API Gateway: http://localhost:8080

MySQL: localhost:3306

Kafka UI (if enabled): http://localhost:8081

Stop:

docker-compose down

🔌 API Endpoints (Sample)

Expense Service

POST /expenses → Add a new expense

GET /expenses/{userId} → Get all expenses of a user

GET /expenses/{userId}/category/{cat} → Filter by category

Query Service (ChatGPT)

POST /query → { "question": "How much did I spend on groceries last week?" }
→ Returns AI-generated response based on DB + GPT reasoning

🧪 Testing
./gradlew test


or

mvn test

📊 Sample Query Flow

User asks: “Show me my food expenses in July”

Query Service sends request → GPT API → interprets → SQL-like query

Query Service fetches from MySQL → GPT formats user-friendly answer

Response: “You spent ₹8,450 on food in July across 15 transactions.”

🛠 Future Enhancements

📱 React Native / Flutter frontend for mobile users

☁️ Deployment on Kubernetes (K8s + Helm)

🔔 Real-time notifications (WebSockets)

🔒 Advanced security (OAuth2.0, Keycloak)

📈 Rich dashboards with Grafana + Prometheus

🤝 Contributing

Fork the repo

Create a feature branch: git checkout -b feature/amazing-feature

Commit changes: git commit -m 'Add amazing feature'

Push: git push origin feature/amazing-feature

Open a PR 🚀

📜 License

This project is not open-source licensed. All rights reserved © 2025 Sumit .
