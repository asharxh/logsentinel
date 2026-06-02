import {
    PieChart,
    Pie,
    Cell,
    Tooltip,
    Legend,
    ResponsiveContainer
} from "recharts";

function ThreatDistributionChart({ logs }) {

    const counts = {};

    logs.forEach((log) => {

        counts[log.eventType] =
            (counts[log.eventType] || 0) + 1;
    });

    const data = Object.entries(counts).map(
        ([name, value]) => ({
            name,
            value
        })
    );

    const COLORS = [
        "#ef4444",
        "#f97316",
        "#eab308",
        "#22c55e",
        "#3b82f6",
        "#8b5cf6"
    ];

    return (

        <div className="bg-gray-800 p-4 rounded-xl">

            <h2 className="text-xl font-bold mb-4">
                Threat Distribution
            </h2>

            <ResponsiveContainer
                width="100%"
                height={300}
            >

                <PieChart>

                    <Pie
                        data={data}
                        dataKey="value"
                        nameKey="name"
                        outerRadius={100}
                        label
                    >

                        {data.map((entry, index) => (

                            <Cell
                                key={index}
                                fill={
                                    COLORS[
                                    index % COLORS.length
                                        ]
                                }
                            />

                        ))}

                    </Pie>

                    <Tooltip />

                    <Legend />

                </PieChart>

            </ResponsiveContainer>

        </div>
    );
}

export default ThreatDistributionChart;