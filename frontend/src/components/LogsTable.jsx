import { useEffect, useState } from "react";
import API from "../services/api";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client/dist/sockjs";

function LogsTable() {

    const [logs, setLogs] = useState([]);

    useEffect(() => {

        fetchLogs();

        const socket = new SockJS("http://localhost:8080/ws");

        const stompClient = new Client({

            webSocketFactory: () => socket,

            reconnectDelay: 5000,

            debug: (str) => {
                console.log(str);
            },

            onConnect: () => {

                console.log("WebSocket Connected");

                stompClient.subscribe("/topic/logs", (message) => {

                    const newLog = JSON.parse(message.body);

                    setLogs((prevLogs) => [newLog, ...prevLogs]);
                });
            },

            onStompError: (frame) => {
                console.error(frame);
            },
        });

        stompClient.activate();

        return () => {
            stompClient.deactivate();
        };

    }, []);

    const fetchLogs = async () => {
        try {
            const response = await API.get("/logs");
            setLogs(response.data);
        } catch (error) {
            console.error("Error fetching logs", error);
        }
    };

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
                                {log.timestamp}
                            </td>

                            <td className="p-2">
                  <span className={
                      log.severity === "ERROR"
                          ? "text-red-400"
                          : log.severity === "WARNING"
                              ? "text-yellow-400"
                              : "text-green-400"
                  }>
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