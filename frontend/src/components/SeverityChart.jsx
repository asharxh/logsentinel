import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer,
    CartesianGrid
} from "recharts";

function SeverityChart({ logs }) {

    const counts = {};

    logs.forEach((log) => {

        counts[log.severity] =
            (counts[log.severity] || 0) + 1;
    });

    const data = Object.entries(counts).map(
        ([severity, count]) => ({
            severity,
            count
        })
    );

    return (

        <div className="bg-gray-800 p-4 rounded-xl">

            <h2 className="text-xl font-bold mb-4">
                Severity Distribution
            </h2>

            <ResponsiveContainer
                width="100%"
                height={300}
            >

                <BarChart data={data}>

                    <CartesianGrid strokeDasharray="3 3" />

                    <XAxis dataKey="severity" />

                    <YAxis />

                    <Tooltip />

                    <Bar dataKey="count" />

                </BarChart>

            </ResponsiveContainer>

        </div>
    );
}

export default SeverityChart;