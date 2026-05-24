from attacks import (
    brute_force,
    sql_injection,
    xss,
    port_scan,
    suspicious_traffic
)

def menu():

    while True:

        print("\n=== LogSentinel Attack Simulator ===")

        print("1. Brute Force")
        print("2. SQL Injection")
        print("3. XSS Attack")
        print("4. Port Scan")
        print("5. Suspicious Traffic")
        print("0. Exit")

        choice = input("\nSelect Attack: ")

        if choice == "1":
            brute_force.run()

        elif choice == "2":
            sql_injection.run()

        elif choice == "3":
            xss.run()

        elif choice == "4":
            port_scan.run()

        elif choice == "5":
            suspicious_traffic.run()

        elif choice == "0":
            break

        else:
            print("Invalid choice")


if __name__ == "__main__":
    menu()