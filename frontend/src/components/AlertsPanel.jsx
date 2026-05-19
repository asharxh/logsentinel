import { useEffect, useState } from "react";
import API from "../services/api";

function AlertsPanel() {

    const [alerts, setAlerts] = useState([]);

    useEffect(() => {
        fetchAlerts();
    }, []);

    const fetchAlerts = async () => {
        try {
            const response = await API.get("/alerts");
            setAlerts(response.data);
        } catch (error) {
            console.error("Error fetching alerts", error);
        }
    };

    const groupedAlerts = alerts.reduce((acc, alert) => {

        const key = `${alert.alertType}_${alert.ipAddress}`;

        if (!acc[key]) {
            acc[key] = {
                ...alert,
                count: 1,
            };
        } else {
            acc[key].count += 1;

            if (new Date(alert.createdAt) > new Date(acc[key].createdAt)) {
                acc[key].createdAt = alert.createdAt;
            }
        }

        return acc;

    }, {});

    const alertList = Object.values(groupedAlerts);

    return (
        <div className="bg-gray-900 p-5 rounded-xl mt-6 shadow-lg">

            <h2 className="text-2xl font-bold mb-4 text-red-400">
                Security Alerts
            </h2>

            {alertList.length === 0 ? (
                <p className="text-gray-400">No alerts</p>
            ) : (

                <div className="space-y-3">

                    {alertList.map((alert) => (

                        <div
                            key={`${alert.alertType}_${alert.ipAddress}`}
                            className="bg-red-950 border border-red-700 p-4 rounded-lg hover:bg-red-900 transition"
                        >

                            {/* HEADER */}
                            <div className="flex justify-between items-center">
                                <div className="font-bold text-red-300">
                                    {alert.alertType}
                                </div>

                                <span className="text-xs bg-red-700 px-2 py-1 rounded">
                                {alert.severity}
                            </span>
                            </div>

                            {/* BODY */}
                            <div className="mt-2 text-sm text-gray-200">
                                <div>IP: {alert.ipAddress}</div>

                                <div>Occurrences: {alert.count}</div>

                                <div>
                                    Last Seen:{" "}
                                    {new Date(alert.createdAt).toLocaleString()}
                                </div>
                            </div>

                        </div>
                    ))}

                </div>
            )}
        </div>
    );
}

export default AlertsPanel;