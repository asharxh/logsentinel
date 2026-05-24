from datetime import datetime

def parse_generic_log(line):

    return {
        "timestamp": datetime.now().isoformat(),
        "severity": "INFO",
        "eventType": "GENERIC_LOG",
        "ipAddress": "127.0.0.1",
        "message": line.strip()
    }