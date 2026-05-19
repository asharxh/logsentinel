import requests
import random
import time
from faker import Faker
from datetime import datetime

fake = Faker()

API_URL = "http://localhost:8080/api/logs"

def send_log(log):

    try:
        response = requests.post(API_URL, json=log)

        print(f"[{response.status_code}] {log['eventType']} -> {log['ipAddress']}")

    except Exception as e:
        print("Error:", e)

def brute_force_attack():

    attacker_ip = fake.ipv4()

    for _ in range(10):

        log = {
            "timestamp": datetime.now().isoformat(),
            "severity": "ERROR",
            "source": "SSH",
            "ipAddress": attacker_ip,
            "eventType": "FAILED_LOGIN",
            "message": "Multiple failed login attempts"
        }

        send_log(log)

        time.sleep(0.5)

def sql_injection_attack():

    payloads = [
        "' OR 1=1 --",
        "' UNION SELECT * FROM users --",
        "' DROP TABLE users --"
    ]

    log = {
        "timestamp": datetime.now().isoformat(),
        "severity": "CRITICAL",
        "source": "WEB",
        "ipAddress": fake.ipv4(),
        "eventType": "SQL_INJECTION",
        "message": random.choice(payloads)
    }

    send_log(log)

def xss_attack():

    payloads = [
        "<script>alert(1)</script>",
        "<img src=x onerror=alert(1)>"
    ]

    log = {
        "timestamp": datetime.now().isoformat(),
        "severity": "HIGH",
        "source": "WEB",
        "ipAddress": fake.ipv4(),
        "eventType": "XSS_ATTACK",
        "message": random.choice(payloads)
    }

    send_log(log)

def port_scan_attack():

    attacker_ip = fake.ipv4()

    common_ports = [21, 22, 23, 80, 443, 3306, 8080]

    for port in common_ports:

        log = {
            "timestamp": datetime.now().isoformat(),
            "severity": "WARNING",
            "source": "FIREWALL",
            "ipAddress": attacker_ip,
            "eventType": "PORT_SCAN",
            "message": f"Connection attempt on port {port}"
        }

        send_log(log)

        time.sleep(0.3)

def suspicious_traffic():

    log = {
        "timestamp": datetime.now().isoformat(),
        "severity": "MEDIUM",
        "source": "NGINX",
        "ipAddress": fake.ipv4(),
        "eventType": "SUSPICIOUS_TRAFFIC",
        "message": "High frequency requests detected"
    }

    send_log(log)

def main():

    while True:

        print("\n=== LogSentinel Attack Simulator ===")
        print("1. Brute Force")
        print("2. SQL Injection")
        print("3. Port Scan")
        print("4. XSS Attack")
        print("5. Suspicious Traffic")
        print("6. Exit")

        choice = input("Select attack: ")

        if choice == "1":
            brute_force_attack()

        elif choice == "2":
            sql_injection_attack()

        elif choice == "3":
            port_scan_attack()

        elif choice == "4":
            xss_attack()

        elif choice == "5":
            suspicious_traffic()

        elif choice == "6":
            break

        else:
            print("Invalid option")

if __name__ == "__main__":
    main()