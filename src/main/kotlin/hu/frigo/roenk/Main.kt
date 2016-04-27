package hu.frigo.roenk

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage

class Main : Application() {
    lateinit var filename: TextField
    lateinit var selectFile: Button
    lateinit var readFile: Button

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = BorderPane()
        primaryStage.title = "Hello World"
        primaryStage.scene = Scene(root)
        val rootV = VBox()
        filename = TextField("/home/frigo/tooldev/roenk/src/main/resources/bef-set-with-op-fop-view-close-reopen-shutdown.WP1.txt")
        rootV.children.add(filename)
        val buttonBox = HBox()
        selectFile = Button("Select it")
        selectFile.setOnAction { e ->
            val selectedFile = FileChooser().showOpenDialog(primaryStage)
            if (selectedFile != null) {
                filename.text = selectedFile.absolutePath
            }
        }
        buttonBox.children.add(selectFile)
        readFile = Button("Read it")
        readFile.setOnAction { e -> LogWindow(filename.text, LINE_REGEX, GROUP_TO_KEY_MAP) }
        buttonBox.children.add(readFile)
        rootV.children.add(buttonBox)

        root.top = rootV
        primaryStage.show()
    }

    companion object {

        private val LINE_REGEX = "^(\\d{1,2}:\\d{2}:\\d{2},\\d{3}) *([A-Z]*) *(.*)"
        val GROUP_TO_KEY_MAP = mapOf(Pair(1, "TIMESTAMP"), Pair(2, "LEVEL"), Pair(3, "LOGSTRING"))

        @JvmStatic fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
