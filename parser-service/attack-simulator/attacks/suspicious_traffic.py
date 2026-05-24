from utils.sender import send_log
from utils.faker_utils import random_ip

from datetime import datetime

def run():

    log = {
        "timestamp": datetime.now().isoformat(),
        "severity": "MEDIUM",
        "ipAddress": random_ip(),
        "eventType": "SUSPICIOUS_TRAFFIC",
        "message": "High frequency requests detected"
    }

    send_log(log)