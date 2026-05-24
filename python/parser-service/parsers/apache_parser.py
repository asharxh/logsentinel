import re
from datetime import datetime
from utils.regex_patterns import (
    APACHE_IP_PATTERN,
    SQL_INJECTION_PATTERN,
    XSS_PATTERN
)


def parse_apache_log(line):
    ip_match = re.search(APACHE_IP_PATTERN, line)
    ip_address = ip_match.group(1) if ip_match else "0.0.0.0"
    if re.search(SQL_INJECTION_PATTERN, line, re.IGNORECASE):

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "CRITICAL",
            "eventType": "SQL_INJECTION",
            "ipAddress": ip_address,
            "message": line.strip()
        }

    if re.search(XSS_PATTERN, line, re.IGNORECASE):

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "HIGH",
            "eventType": "XSS_ATTACK",
            "ipAddress": ip_address,
            "message": line.strip()
        }

    if "/admin" in line:
        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "MEDIUM",
            "eventType": "ADMIN_ACCESS",
            "ipAddress": ip_address,
            "message": line.strip()
        }

    return {
        "timestamp": datetime.now().isoformat(),
        "severity": "INFO",
        "eventType": "WEB_REQUEST",
        "ipAddress": ip_address,
        "message": line.strip()
    }