from collectors.file_collector import collect_logs
from parsers.auth_parser import parse_auth_log
from parsers.generic_parser import parse_generic_log
from forwarders.api_forwarder import forward_log
from utils.log_reader import clean_log_line


LOG_FILE = "../sample-logs/auth.log"


def main():

    logs = collect_logs(LOG_FILE)

    for line in logs:
        clean_line = clean_log_line(line)
        parsed_log = parse_auth_log(clean_line)

        if not parsed_log:
            parsed_log = parse_generic_log(clean_line)

        forward_log(parsed_log)


if __name__ == "__main__":
    main()