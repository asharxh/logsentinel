import requests

API_URL = "http://localhost:8080/api/logs"

def send_log(log_data):

    try:
        response = requests.post(API_URL, json=log_data)

        print(f"[{response.status_code}] Sent: {log_data['eventType']}")

    except Exception as e:
        print("Error sending log:", e)