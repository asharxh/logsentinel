import re
import requests
from datetime import datetime
API_URL = "http://localhost:8080/api/logs"

LOG_FILE = "../sample-logs/auth.log"

pattern = re.compile(
    r'(\w+\s+\d+\s+\d+:\d+:\d+).*from\s+(\d+\.\d+\.\d+\.\d+)'
)

with open(LOG_FILE, "r") as file:
    for line in file:

        match = pattern.search(line)

        if match:

            timestamp_str = match.group(1)
            ip_address = match.group(2)

            if "Failed password" in line:
                event_type = "FAILED_LOGIN"
                severity = "ERROR"

            elif "Accepted password" in line:
                event_type = "SUCCESS_LOGIN"
                severity = "INFO"

            else:
                event_type = "UNKNOWN"
                severity = "WARNING"

            log_data = {
                "timestamp": datetime.now().isoformat(),
                "severity": severity,
                "source": "SSH",
                "ipAddress": ip_address,
                "eventType": event_type,
                "message": line.strip()
            }

            response = requests.post(API_URL, json=log_data)

            print(f"Sent log: {response.status_code}")