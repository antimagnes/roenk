package hu.frigo.roenk

import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import javafx.util.Callback

class LogWindow(val title: String, val logEntries: List<LogEntry>) {

    internal lateinit var stage: Stage

    internal lateinit var logTable: TableView<LogEntry>
    internal val logTableData = FXCollections.observableArrayList<LogEntry>()

    init {
        this.stage = Stage()
        val root = BorderPane()
        stage.scene = Scene(root, 1024.0, 768.0)

        logTable = TableView<LogEntry>()
        root.center = logTable
        val lineCol = TableColumn<LogEntry, Long>("Line")
        lineCol.cellValueFactory = Callback { p -> ReadOnlyObjectWrapper(p.value.line) }
        lineCol.prefWidth = 100.0
        val timeStampCol = TableColumn<LogEntry, Long>("TimeStamp")
        timeStampCol.cellValueFactory = Callback { p -> ReadOnlyObjectWrapper(p.value.timestamp) }
        timeStampCol.prefWidth = 100.0
        val levelCol = TableColumn<LogEntry, String>("Level")
        levelCol.cellValueFactory = Callback { p -> ReadOnlyObjectWrapper(p.value.level) }
        levelCol.prefWidth = 100.0
        val logStringCol = TableColumn<LogEntry, String>("LogString")
        logStringCol.cellValueFactory = Callback { p -> ReadOnlyObjectWrapper(p.value.logString) }
        logStringCol.prefWidth = 1000.0

        listOf(lineCol, timeStampCol, levelCol, logStringCol).forEach { v -> logTable.columns.add(v) }
        logTable.items = logTableData
        stage.title = title
        logTableData.addAll(logEntries)
        stage.show()
    }
}
