import LogsTable from "./components/LogsTable";
import AlertsPanel from "./components/AlertsPanel";
import DashboardMetrics from "./components/DashboardMetrics";

function App() {

    return (
        <div className="min-h-screen bg-gray-900 text-white p-6">

            <h1 className="text-4xl font-bold mb-6">
                LogSentinel SIEM Dashboard
            </h1>
            <DashboardMetrics />

            <AlertsPanel />

            <LogsTable />

        </div>
    );
}

export default App;