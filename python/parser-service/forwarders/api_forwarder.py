import requests

API_URL = "http://localhost:8080/api/logs"

def forward_log(log):

    try:

        response = requests.post(API_URL, json=log)

        print(
            f"[{response.status_code}] Forwarded: {log['eventType']}"
        )

    except Exception as e:

        print("Error forwarding log:", e)