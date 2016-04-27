package hu.frigo.roenk

import hu.frigo.roenk.LogValues.LEVEL
import hu.frigo.roenk.LogValues.LOGSTRING
import hu.frigo.roenk.LogValues.TIMESTAMP
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Arrays

object LogReader {

    internal var dtf = DateTimeFormatter.ofPattern("H:mm:ss,SSS")

    fun processLogFile(filename: String, lineRegexp: Regex, groupToKeyMap: Map<Int, LogValues>):
            List<LogEntry> {
        val toReturn = mutableListOf<LogEntry>()
        val l = mutableMapOf<Long, String>()

        for (encoding in Arrays.asList("UTF-8", "ISO-8859-1", "ISO-8859-2")) {
            println("reading using encoding = " + encoding)

            Paths.get(filename).toFile().readLines().forEachIndexed { i, s -> l.put(i.toLong(), s) }
            break
        }

        var cle: LogEntry
        var remainingMap = mutableMapOf<Long, String>()
        remainingMap.putAll(l)


        if (remainingMap.entries.sortedBy { it.key }.first().value.matches(lineRegexp)) {
            val (firstLineNumber, firstVal) = remainingMap.entries.sortedBy { it.key }.first()
            cle = logEntry(firstLineNumber, firstVal, groupToKeyMap, lineRegexp)
            remainingMap.remove(firstLineNumber)
        } else {
            cle = LogEntry(-1, -1, "-", "beginning of file")
        }
        toReturn.add(cle)

        while (!remainingMap.isEmpty() && remainingMap.entries.find{ it.value.matches(lineRegexp) } != null) {
            val sortedEntries = remainingMap.entries.sortedBy { it.key }
            val (lineNumber, lineString) = sortedEntries.find { it.value.matches(lineRegexp) }!!
            if ( lineNumber != sortedEntries.first().key ) {
                cle.nonLogLines.addAll(sortedEntries.filter { it.key < lineNumber }.map { it.value })
            }
            cle = logEntry(lineNumber, lineString, groupToKeyMap, lineRegexp)
            toReturn.add(cle)
            sortedEntries.filter { it.key <= lineNumber }.forEach { remainingMap.remove(it.key) }
            println("remainingMap ${remainingMap.size}")
        }

        println(toReturn)
        return toReturn
    }

    private fun logEntry(lineNumber: Long, lineString: String, groupToKeyMap: Map<Int, LogValues>, lineRegexp: Regex):
            LogEntry {
        val matcher = lineRegexp.toPattern().matcher(lineString)
        matcher.find()
        val results = groupToKeyMap.entries.map {
            Pair<LogValues, String>(it.value,
                    try { matcher.group(it.key) } catch (e: IllegalStateException) { print("Hopp")
                        "nomatch " +
                            "$lineNumber : " +
                            "\"$lineString\" ${it.key} - ${it.value}" })
                    }.toMap()
        return LogEntry(lineNumber,
                LocalTime.parse(results[TIMESTAMP]!!, dtf).toNanoOfDay(),
                results[LEVEL]!!,
                results[LOGSTRING]!!)
    }
}
