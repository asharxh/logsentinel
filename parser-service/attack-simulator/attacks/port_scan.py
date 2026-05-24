from utils.sender import send_log
from utils.faker_utils import random_ip

from datetime import datetime
import time

def run():

    ip = random_ip()

    ports = [21, 22, 23, 80, 443, 3306, 8080]

    for port in ports:

        log = {
            "timestamp": datetime.now().isoformat(),
            "severity": "WARNING",
            "ipAddress": ip,
            "eventType": "PORT_SCAN",
            "message": f"Connection attempt on port {port}"
        }

        send_log(log)

        time.sleep(0.3)