# LogSentinel – SIEM

LogSentinel is a Mini SIEM (Security Information & Event Management System) project that simulates how real-world security platforms like Splunk work.

It collects logs, parses them, stores them, detects threats, and displays security alerts in a live dashboard.

---

# Project Goal

.
├── backend
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java/com/logsentinel/backend
│       │   │   ├── BackendApplication.java
│       │   │   ├── config
│       │   │   │   └── WebSocketConfig.java
│       │   │   ├── controller
│       │   │   │   ├── AlertController.java
│       │   │   │   ├── DashboardController.java
│       │   │   │   ├── LogEntryController.java
│       │   │   │   └── TestController.java
│       │   │   ├── dto
│       │   │   ├── entity
│       │   │   │   ├── Alert.java
│       │   │   │   └── LogEntry.java
│       │   │   ├── repository
│       │   │   │   ├── AlertRepository.java
│       │   │   │   └── LogEntryRepository.java
│       │   │   └── service
│       │   │       ├── AlertService.java
│       │   │       └── LogEntryService.java
│       │   └── resources
│       │       └── application.properties
│       └── test
│
├── frontend
│   ├── package.json
│   ├── src
│   │   ├── App.jsx
│   │   ├── components
│   │   │   ├── AlertsPanel.jsx
│   │   │   ├── DashboardMetrics.jsx
│   │   │   └── LogsTable.jsx
│   │   ├── services
│   │   │   └── api.js
│   │   ├── index.css
│   │   └── main.jsx
│   ├── tailwind.config.js
│   └── vite.config.js
│
├── python
│   ├── attack-simulator
│   │   ├── attacks
│   │   │   ├── brute_force.py
│   │   │   ├── port_scan.py
│   │   │   ├── sql_injection.py
│   │   │   ├── suspicious_traffic.py
│   │   │   └── xss.py
│   │   ├── utils
│   │   │   ├── faker_utils.py
│   │   │   └── sender.py
│   │   ├── main.py
│   │   └── requirements.txt
│   │
│   └── parser-service
│       ├── collectors
│       │   ├── file_collector.py
│       │   ├── syslog_collector.py
│       │   └── windows_event_collector.py
│       ├── parsers
│       │   ├── apache_parser.py
│       │   ├── auth_parser.py
│       │   ├── firewall_parser.py
│       │   └── generic_parser.py
│       ├── forwarders
│       │   └── api_forwarder.py
│       ├── utils
│       │   ├── log_reader.py
│       │   └── regex_patterns.py
│       ├── main.py
│       └── requirements.txt
│
├── sample-logs
│   ├── apache.log
│   └── auth.log
│
└── readme.md

---

# Tech Stack

Backend:
- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL

Frontend:
- React (Vite)
- Axios
- Tailwind CSS

Parser Service:
- Python 3
- Requests library

---


# Features

## Log Ingestion
- Accepts logs via REST API
- Structured log format support

## Python Log Parser
- Reads logs
- Sends logs to backend API
- Simulates real log agents

## Alert Engine
Detects:
- Brute Force (FAILED_LOGIN ≥ 5)
- Suspicious IP activity

## Database Storage
- Stores logs in PostgreSQL
- Stores alerts separately

## Frontend Dashboard
- Logs table view
- Alerts panel
- Severity color indicators

---

# Python Parser

Run:

cd parser-service  
source venv/bin/activate  
python parser.py

Output:

Sent log: 200  
Sent log: 200

---

# Frontend Setup

cd frontend  
npm install  
npm run dev

Open:
http://localhost:5174

---

# Backend Setup

cd backend  
./mvnw spring-boot:run

Runs at:
http://localhost:8080

---

# Testing Flow

1. Start backend
2. Run Python parser
3. Send logs
4. Trigger FAILED_LOGIN events
5. Check alerts API
6. Open frontend dashboard

---

# System Flow

Python parser → Backend → Database → Alert Engine → Frontend UI

---

# Security Features

- Brute force detection
- IP tracking
- Event classification
- Severity tagging

---

# Future Improvements

- Real-time WebSockets SIEM
- Authentication system
- Docker deployment
- Attack simulator
- ML-based anomaly detection

---

# Author

Built as a learning project to simulate a mini SIEM system inspired by Splunk.

- Ashar Arif
- https://www.linkedin.com/in/ashararif