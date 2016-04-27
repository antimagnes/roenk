package hu.frigo.roenk

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty

import java.util.ArrayList

class LogEntry(line: Long?, t: Long?, l: String, ls: String) {

    private val line: SimpleLongProperty
    private val timestamp: SimpleLongProperty
    private val level: SimpleStringProperty
    private val logString: SimpleStringProperty
    private val nonLogLines: MutableList<String>

    init {
        this.line = SimpleLongProperty(line!!)
        this.timestamp = SimpleLongProperty(t!!)
        this.level = SimpleStringProperty(l)
        this.logString = SimpleStringProperty(ls)
        nonLogLines = ArrayList<String>()
    }

    fun getLine(): Long {
        return line.get()
    }

    fun setLine(line: Long) {
        this.line.set(line)
    }

    fun getTimestamp(): Long {
        return timestamp.get()
    }

    fun setTimestamp(timestamp: Long) {
        this.timestamp.set(timestamp)
    }

    fun getLevel(): String {
        return level.get()
    }

    fun setLevel(level: String) {
        this.level.set(level)
    }

    fun getLogString(): String {
        return logString.get()
    }

    fun setLogString(logString: String) {
        this.logString.set(logString)
    }

    fun addNonLogLine(nonLogLine: String) {
        this.nonLogLines.add(nonLogLine)
    }

    fun getNonLogLines(): List<String> {
        return ArrayList(nonLogLines)
    }

    override fun toString(): String {
        return "LogEntry{line=$line, timestamp=$timestamp, level=$level, logString=$logString, nonLogLines=$nonLogLines}"
    }
}
