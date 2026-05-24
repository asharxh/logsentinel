from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
import time


class LogFileHandler(FileSystemEventHandler):

    def __init__(self, callback):
        self.callback = callback

    def on_modified(self, event):

        if event.is_directory:
            return

        self.callback(event.src_path)


def watch_log_file(path, callback):

    event_handler = LogFileHandler(callback)

    observer = Observer()

    observer.schedule(
        event_handler,
        path=path,
        recursive=False
    )

    observer.start()

    print(f"[WATCHING] {path}")

    try:
        while True:
            time.sleep(1)

    except KeyboardInterrupt:
        observer.stop()

    observer.join()