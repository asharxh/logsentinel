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

    return (
        <div className="bg-gray-800 p-4 rounded-xl mt-6">

            <h2 className="text-2xl font-bold mb-4 text-red-400">
                Security Alerts
            </h2>

            <div className="space-y-3">

                {alerts.map((alert) => (

                    <div
                        key={alert.id}
                        className="bg-red-900 p-4 rounded-lg"
                    >
                        <div className="font-bold">
                            {alert.alertType}
                        </div>

                        <div>
                            IP: {alert.ipAddress}
                        </div>

                        <div>
                            Severity: {alert.severity}
                        </div>

                        <div className="text-sm mt-1">
                            {alert.message}
                        </div>

                    </div>
                ))}

            </div>
        </div>
    );
}

export default AlertsPanel;