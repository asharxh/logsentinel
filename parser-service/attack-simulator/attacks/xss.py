from utils.sender import send_log
from utils.faker_utils import random_ip

from datetime import datetime

def run():

    log = {
        "timestamp": datetime.now().isoformat(),
        "severity": "HIGH",
        "ipAddress": random_ip(),
        "eventType": "XSS_ATTACK",
        "message": "<script>alert(1)</script>"
    }

    send_log(log)