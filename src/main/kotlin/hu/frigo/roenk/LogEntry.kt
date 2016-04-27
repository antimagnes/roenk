package hu.frigo.roenk

enum class LogValues {
    TIMESTAMP,
    LEVEL,
    LOGSTRING
}

data class LogEntry(val line: Long, val timestamp: Long, val level: String, val logString: String,
                    val nonLogLines: MutableList<String> = mutableListOf())