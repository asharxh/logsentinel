from utils.sender import send_log
from utils.faker_utils import random_ip

from datetime import datetime

def run():

    log = {
        "timestamp": datetime.now().isoformat(),
        "severity": "CRITICAL",
        "ipAddress": random_ip(),
        "eventType": "SQL_INJECTION",
        "message": "' OR 1=1 --"
    }

    send_log(log)