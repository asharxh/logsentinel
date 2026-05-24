from collectors.file_collector import watch_log_file
from parsers.auth_parser import parse_auth_log
from parsers.generic_parser import parse_generic_log
from forwarders.api_forwarder import forward_log
from utils.log_reader import clean_log_line


LOG_FILE = "../../sample-logs/auth.log"


processed_lines = set()

def process_log_file(file_path):

    with open(file_path, "r") as file:

        lines = file.readlines()

        for line in lines:

            clean_line = clean_log_line(line)

            if clean_line in processed_lines:
                continue
            processed_lines.add(clean_line)
            parsed_log = parse_auth_log(clean_line)

            if not parsed_log:
                parsed_log = parse_generic_log(clean_line)

            forward_log(parsed_log)


def main():
    process_log_file(LOG_FILE)
    watch_log_file(
        "../../sample-logs",
        process_log_file
    )

if __name__ == "__main__":
    main()