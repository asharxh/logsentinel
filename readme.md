# LogSentinel

A SIEM (Security Information and Event Management) project inspired by tools like Splunk.

LogSentinel collects logs, parses them, detects suspicious activities, generates alerts, and displays everything in a real-time dashboard.

Built using:

* Java Spring Boot (Backend)
* React + TailwindCSS (Frontend)
* Python (Log Parser + Attack Simulator)

---

# Backend Architecture

```text
backend/
│
├── controller/
│   ├── AlertController.java
│   ├── DashboardController.java
│   ├── LogEntryController.java
│
├── service/
│   ├── AlertService.java
│   └── LogEntryService.java
│
├── repository/
│   ├── AlertRepository.java
│   └── LogEntryRepository.java
│
├── entity/
│   ├── Alert.java
│   └── LogEntry.java
│
└── config/
    └── WebSocketConfig.java
```

---

# Python Parser Service

Responsible for:

* Reading logs
* Parsing logs
* Sending parsed events to backend

```text
parser-service/
│
├── collectors/
│   ├── file_collector.py
│   ├── syslog_collector.py
│   └── windows_event_collector.py
│
├── parsers/
│   ├── auth_parser.py
│   ├── apache_parser.py
│   ├── firewall_parser.py
│   └── generic_parser.py
│
├── forwarders/
│   └── api_forwarder.py
│
├── utils/
│   ├── log_reader.py
│   └── regex_patterns.py
│
└── main.py
```

---

# Python Attack Simulator

Generates fake attack logs for testing the SIEM.

```text
attack-simulator/
│
├── attacks/
│   ├── brute_force.py
│   ├── sql_injection.py
│   ├── xss.py
│   ├── port_scan.py
│   └── suspicious_traffic.py
│
├── utils/
│   ├── sender.py
│   └── faker_utils.py
│
└── main.py
```

# Setup Guide

# 1. Clone Repository

```bash
git clone <https://github.com/asharxh/logsentinel>
cd LogSentinel
```

---

# 2. Run Backend

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

# 3. Run Frontend

```bash
cd frontend

npm install
npm run dev
```

Frontend runs on:

```text
http://localhost:5173
```

---

# 4. Run Parser Service

```bash
cd python/parser-service

python -m venv venv

source venv/bin/activate
```

Install dependencies:

```bash
pip install -r requirements.txt
```

Run parser:

```bash
python main.py
```

---

# 5. Run Attack Simulator

```bash
cd python/attack-simulator

python -m venv venv

source venv/bin/activate
```

Install dependencies:

```bash
pip install -r requirements.txt
```

Run simulator:

```bash
python main.py
```

# Author

* Ashar Arif
* https://www.linkedin.com/in/ashararif