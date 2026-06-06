FAILED_LOGIN_PATTERN = r'from (\d+\.\d+\.\d+\.\d+)'
APACHE_IP_PATTERN = r'^(\d+\.\d+\.\d+\.\d+)'
HTTP_METHOD_PATTERN = r'"(GET|POST|PUT|DELETE|HEAD|OPTIONS)'
URL_PATTERN = r'"(?:GET|POST|PUT|DELETE)\s+([^ ]+)'
STATUS_CODE_PATTERN = r'"\s+(\d{3})'
SQL_INJECTION_PATTERN = r"('|--|;|UNION|SELECT|OR\s+1=1)"
XSS_PATTERN = r"(<script>|</script>|javascript:)"
DIRECTORY_TRAVERSAL_PATTERN = r"(\.\./|\.\.\\)"
SCANNER_PATTERN = r"(nmap|nikto|sqlmap)"