from utils.sender import send_log
from utils.faker_utils import random_ip

from datetime import datetime
import time

def run():

    ip = random_ip()

    for i in range(10):

        log = {
            "timestamp": datetime.now().isoformat(),
            "severity": "ERROR",
            "ipAddress": ip,
            "eventType": "FAILED_LOGIN",
            "message": "Multiple failed login attempts"
        }

        send_log(log)

        time.sleep(0.5)