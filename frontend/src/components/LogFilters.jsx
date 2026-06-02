function LogFilters({
                        searchIp,
                        setSearchIp,
                        severity,
                        setSeverity,
                        eventType,
                        setEventType,
                        onApply,
                        onReset
                    }) {
    return (
        <div className="bg-gray-800 p-4 rounded-xl mb-6">

            <h2 className="text-xl font-bold mb-4">
                Filters
            </h2>

            <div className="flex gap-4 flex-wrap">

                <input
                    type="text"
                    placeholder="Search IP..."
                    value={searchIp}
                    onChange={(e) =>
                        setSearchIp(e.target.value)
                    }
                    className="px-3 py-2 rounded bg-gray-700"
                />

                <select
                    value={severity}
                    onChange={(e) =>
                        setSeverity(e.target.value)
                    }
                    className="px-3 py-2 rounded bg-gray-700"
                >
                    <option value="">
                        All Severities
                    </option>

                    <option value="INFO">
                        INFO
                    </option>

                    <option value="WARNING">
                        WARNING
                    </option>

                    <option value="ERROR">
                        ERROR
                    </option>

                    <option value="CRITICAL">
                        CRITICAL
                    </option>
                </select>

                <select
                    value={eventType}
                    onChange={(e) =>
                        setEventType(e.target.value)
                    }
                    className="px-3 py-2 rounded bg-gray-700"
                >
                    <option value="">
                        All Events
                    </option>

                    <option value="FAILED_LOGIN">
                        FAILED_LOGIN
                    </option>

                    <option value="SQL_INJECTION">
                        SQL_INJECTION
                    </option>

                    <option value="XSS_ATTACK">
                        XSS_ATTACK
                    </option>

                    <option value="PORT_SCAN">
                        PORT_SCAN
                    </option>
                </select>

                <button
                    onClick={onApply}
                    className="bg-blue-600 px-4 py-2 rounded"
                >
                    Apply
                </button>

                <button
                    onClick={onReset}
                    className="bg-gray-600 px-4 py-2 rounded"
                >
                    Reset
                </button>

            </div>

        </div>
    );
}

export default LogFilters;