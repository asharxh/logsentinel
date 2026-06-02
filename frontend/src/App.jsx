import { useEffect, useState } from "react";

import API from "./services/api";

import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client/dist/sockjs";

import LogsTable from "./components/LogsTable";
import AlertsPanel from "./components/AlertsPanel";
import DashboardMetrics from "./components/DashboardMetrics";
import LogFilters from "./components/LogFilters";
import SeverityChart from "./components/SeverityChart";
import ThreatDistributionChart from "./components/ThreatDistributionChart";

function App() {

    const [allLogs, setAllLogs] = useState([]);
    const [logs, setLogs] = useState([]);

    const [searchIp, setSearchIp] = useState("");
    const [severity, setSeverity] = useState("");
    const [eventType, setEventType] = useState("");

    useEffect(() => {

        fetchLogs();

        const socket =
            new SockJS(
                "http://localhost:8080/ws"
            );

        const stompClient =
            new Client({

                webSocketFactory: () => socket,

                reconnectDelay: 5000,

                onConnect: () => {

                    console.log(
                        "WebSocket Connected"
                    );

                    stompClient.subscribe(
                        "/topic/logs",
                        (message) => {

                            const newLog =
                                JSON.parse(
                                    message.body
                                );

                            setAllLogs(
                                (prev) =>
                                    [
                                        newLog,
                                        ...prev
                                    ]
                            );

                            setLogs(
                                (prev) =>
                                    [
                                        newLog,
                                        ...prev
                                    ]
                            );
                        }
                    );
                },
            });

        stompClient.activate();

        return () => {
            stompClient.deactivate();
        };

    }, []);

    const fetchLogs = async () => {

        try {

            const response =
                await API.get("/logs");

            setAllLogs(
                response.data
            );

            setLogs(
                response.data
            );

        } catch (error) {

            console.error(
                error
            );
        }
    };

    const applyFilters = () => {

        const filtered =
            allLogs.filter((log) => {

                const ipMatch =
                    !searchIp ||
                    log.ipAddress
                        ?.toLowerCase()
                        .includes(
                            searchIp
                                .toLowerCase()
                        );

                const severityMatch =
                    !severity ||
                    log.severity === severity;

                const eventMatch =
                    !eventType ||
                    log.eventType === eventType;

                return (
                    ipMatch &&
                    severityMatch &&
                    eventMatch
                );
            });

        setLogs(filtered);
    };

    const resetFilters = () => {

        setSearchIp("");
        setSeverity("");
        setEventType("");

        setLogs(allLogs);
    };

    return (

        <div className="min-h-screen bg-gray-900 text-white p-6">

            <h1 className="text-4xl font-bold mb-6">
                LogSentinel SIEM Dashboard
            </h1>

            <DashboardMetrics />

            <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-6">

                <SeverityChart logs={logs} />

                <ThreatDistributionChart logs={logs} />

            </div>

            <AlertsPanel />

            <LogFilters
                searchIp={searchIp}
                setSearchIp={setSearchIp}
                severity={severity}
                setSeverity={setSeverity}
                eventType={eventType}
                setEventType={setEventType}
                onApply={applyFilters}
                onReset={resetFilters}
            />

            <LogsTable logs={logs} />

        </div>
    );
}

export default App;