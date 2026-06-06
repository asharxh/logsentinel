import re
from datetime import datetime


def parse_firewall_log(line):

    parts = line.split()

    if len(parts) < 7:
        return None

    action = parts[2]
    source_ip = parts[3]
    destination_ip = parts[5]

    protocol = parts[6]
    port = parts[7]

    if action == "BLOCK":

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "WARNING",
            "eventType": "BLOCKED_CONNECTION",
            "ipAddress": source_ip,
            "message": f"{protocol}:{port} -> {destination_ip}"
        }

    return {
        "timestamp": datetime.now().isoformat(),
        "severity": "INFO",
        "eventType": "NETWORK_CONNECTION",
        "ipAddress": source_ip,
        "message": f"{protocol}:{port} -> {destination_ip}"
    }