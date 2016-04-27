package hu.frigo.roenk

import javafx.collections.FXCollections
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import java.util.Arrays

class LogWindow(file: String, lineRegExp: String, groupToKeyMap: Map<Int, String>) {

    internal var dtf = DateTimeFormatter.ofPattern("H:mm:ss,SSS")

    internal var stage: Stage
    internal var file: String

    internal var logTable: TableView<LogEntry>
    internal val logTableData = FXCollections.observableArrayList<LogEntry>()

    init {
        val encodingToResultMap = LogReader.processLogFile(file, lineRegExp, groupToKeyMap)
        if (!encodingToResultMap.isEmpty()) {
            this.file = file
            this.stage = Stage()
            val root = BorderPane()
            stage.scene = Scene(root, 1024.0, 768.0)

            root.setCenter(logTable = TableView<LogEntry>())
            val lineCol = TableColumn<LogEntry, Long>("Line")
            lineCol.setCellValueFactory(PropertyValueFactory<LogEntry, Long>("line"))
            lineCol.setPrefWidth(100.0)
            val timeStampCol = TableColumn<LogEntry, Long>("TimeStamp")
            timeStampCol.setCellValueFactory(PropertyValueFactory<LogEntry, Long>("timestamp"))
            timeStampCol.setPrefWidth(100.0)
            val levelCol = TableColumn<LogEntry, String>("Level")
            levelCol.setCellValueFactory(PropertyValueFactory<LogEntry, String>("level"))
            levelCol.setPrefWidth(100.0)
            val logStringCol = TableColumn<LogEntry, String>("LogString")
            logStringCol.setCellValueFactory(PropertyValueFactory<LogEntry, String>("logString"))
            logStringCol.setPrefWidth(1000.0)

            Arrays.asList(lineCol, timeStampCol, levelCol, logStringCol).forEach { v -> logTable.columns.add(v) }
            logTable.setItems(logTableData)
            val encoding = encodingToResultMap.keys.stream().findFirst().get()
            stage.title = "$file <$encoding>"
            logTableData.addAll(encodingToResultMap[encoding])
            stage.show()
        } else {
            Alert(Alert.AlertType.ERROR, "Cannot load " + file, ButtonType.OK).show()
        }
    }
}
