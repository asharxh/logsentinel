import re

from datetime import datetime

from utils.regex_patterns import (
    APACHE_IP_PATTERN,
    HTTP_METHOD_PATTERN,
    URL_PATTERN,
    STATUS_CODE_PATTERN,
    SQL_INJECTION_PATTERN,
    XSS_PATTERN,
    DIRECTORY_TRAVERSAL_PATTERN,
    SCANNER_PATTERN
)


def parse_apache_log(line):

    ip_match = re.search(
        APACHE_IP_PATTERN,
        line
    )

    ip_address = (
        ip_match.group(1)
        if ip_match
        else "0.0.0.0"
    )

    method_match = re.search(
        HTTP_METHOD_PATTERN,
        line
    )

    method = (
        method_match.group(1)
        if method_match
        else "UNKNOWN"
    )

    url_match = re.search(
        URL_PATTERN,
        line
    )

    url = (
        url_match.group(1)
        if url_match
        else "/"
    )

    status_match = re.search(
        STATUS_CODE_PATTERN,
        line
    )

    status_code = (
        status_match.group(1)
        if status_match
        else "000"
    )

    if re.search(
            SQL_INJECTION_PATTERN,
            line,
            re.IGNORECASE
    ):

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "CRITICAL",
            "eventType": "SQL_INJECTION",
            "ipAddress": ip_address,
            "message": f"{method} {url}"
        }

    if re.search(
            XSS_PATTERN,
            line,
            re.IGNORECASE
    ):

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "HIGH",
            "eventType": "XSS_ATTACK",
            "ipAddress": ip_address,
            "message": f"{method} {url}"
        }

    if re.search(
            DIRECTORY_TRAVERSAL_PATTERN,
            line,
            re.IGNORECASE
    ):

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "HIGH",
            "eventType": "DIRECTORY_TRAVERSAL",
            "ipAddress": ip_address,
            "message": f"{method} {url}"
        }

    if re.search(
            SCANNER_PATTERN,
            line,
            re.IGNORECASE
    ):

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "HIGH",
            "eventType": "SCANNER_ACTIVITY",
            "ipAddress": ip_address,
            "message": f"{method} {url}"
        }

    if "/admin" in url:

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "MEDIUM",
            "eventType": "ADMIN_ACCESS",
            "ipAddress": ip_address,
            "message": f"{method} {url}"
        }

    if status_code.startswith("5"):

        return {
            "timestamp": datetime.now().isoformat(),
            "severity": "WARNING",
            "eventType": "SERVER_ERROR",
            "ipAddress": ip_address,
            "message": f"{method} {url}"
        }

    return {
        "timestamp": datetime.now().isoformat(),
        "severity": "INFO",
        "eventType": "WEB_REQUEST",
        "ipAddress": ip_address,
        "message": f"{method} {url}"
    }