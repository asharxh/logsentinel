function LogsTable({ logs }) {

    return (
        <div className="bg-gray-800 p-4 rounded-xl mt-6">

            <h2 className="text-2xl font-bold mb-4">
                Logs
            </h2>

            <div className="overflow-x-auto">

                <table className="w-full text-sm">

                    <thead>
                    <tr className="border-b border-gray-700">
                        <th className="text-left p-2">Time</th>
                        <th className="text-left p-2">Severity</th>
                        <th className="text-left p-2">IP</th>
                        <th className="text-left p-2">Event</th>
                        <th className="text-left p-2">Message</th>
                    </tr>
                    </thead>

                    <tbody>

                    {logs.map((log) => (

                        <tr
                            key={log.id}
                            className="border-b border-gray-700"
                        >
                            <td className="p-2">
                                {
                                    log.timestamp
                                        ? new Date(
                                            log.timestamp
                                        ).toLocaleString()
                                        : "N/A"
                                }
                            </td>

                            <td className="p-2">
                                <span
                                    className={
                                        log.severity === "CRITICAL"
                                            ? "text-red-500 font-bold"
                                            : log.severity === "ERROR"
                                                ? "text-red-400"
                                                : log.severity === "WARNING"
                                                    ? "text-yellow-400"
                                                    : "text-green-400"
                                    }
                                >
                                    {log.severity}
                                </span>
                            </td>

                            <td className="p-2">
                                {log.ipAddress}
                            </td>

                            <td className="p-2">
                                {log.eventType}
                            </td>

                            <td className="p-2">
                                {log.message}
                            </td>

                        </tr>

                    ))}

                    </tbody>

                </table>

            </div>

        </div>
    );
}

export default LogsTable;