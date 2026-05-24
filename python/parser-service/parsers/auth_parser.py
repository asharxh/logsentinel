import re
from datetime import datetime
from utils.regex_patterns import FAILED_LOGIN_PATTERN


def parse_auth_log(line):

    if "Failed password" in line:

        ip_match = re.search(FAILED_LOGIN_PATTERN, line)

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "ERROR",
            "eventType": "FAILED_LOGIN",
            "ipAddress": ip_match.group(1),
            "message": line.strip()
        }

    elif "Accepted password" in line:

        ip_match = re.search(FAILED_LOGIN_PATTERN, line)

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "INFO",
            "eventType": "SUCCESS_LOGIN",
            "ipAddress": ip_match.group(1),
            "message": line.strip()
        }

    return None