import { useEffect, useState } from "react";
import API from "../services/api";

function DashboardMetrics() {

    const [metrics, setMetrics] = useState({});

    useEffect(() => {
        fetchMetrics();
    }, []);

    const fetchMetrics = async () => {

        try {

            const response = await API.get(
                "/dashboard/metrics"
            );

            setMetrics(response.data);

        } catch (error) {

            console.error(
                "Error fetching metrics",
                error
            );
        }
    };

    return (

        <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-6">

            <div className="bg-gray-800 p-5 rounded-xl">
                <div className="text-gray-400 text-sm">
                    Total Logs
                </div>

                <div className="text-3xl font-bold mt-2">
                    {metrics.totalLogs}
                </div>
            </div>

            <div className="bg-red-900 p-5 rounded-xl">
                <div className="text-red-200 text-sm">
                    Critical Alerts
                </div>

                <div className="text-3xl font-bold mt-2">
                    {metrics.criticalAlerts}
                </div>
            </div>

            <div className="bg-yellow-700 p-5 rounded-xl">
                <div className="text-yellow-100 text-sm">
                    Total Alerts
                </div>

                <div className="text-3xl font-bold mt-2">
                    {metrics.totalAlerts}
                </div>
            </div>

            <div className="bg-blue-800 p-5 rounded-xl">
                <div className="text-blue-100 text-sm">
                    Top Attacker
                </div>

                <div className="text-lg font-bold mt-2 break-all">
                    {metrics.topAttackerIp}
                </div>
            </div>

        </div>
    );
}

export default DashboardMetrics;