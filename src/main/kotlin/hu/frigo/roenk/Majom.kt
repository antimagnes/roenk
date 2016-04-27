//package hu.frigo.roenk
//
//import javafx.application.Application
//import javafx.scene.Group
//import javafx.scene.Scene
//import javafx.scene.input.KeyEvent
//import javafx.scene.layout.Pane
//import javafx.scene.layout.Priority
//import javafx.scene.layout.VBox
//import javafx.scene.paint.Color
//import javafx.scene.shape.Rectangle
//import javafx.stage.Stage
//
//class Majom : Application() {
//
//    override fun start(primaryStage: Stage) {
//        val pane = Pane()
//        val group = Group()
//
//        VBox.setVgrow(group, Priority.NEVER)
//        VBox.setVgrow(pane, Priority.NEVER)
//
//        val vbox = VBox(group, pane)
//
//
//        val rect1 = Rectangle(100.0, 100.0, 100.0, 100.0)
//        val rect2 = Rectangle(100.0, 100.0, 100.0, 100.0)
//        val rect3 = Rectangle(200.0, 200.0, 100.0, 100.0)
//        val rect4 = Rectangle(200.0, 200.0, 100.0, 100.0)
//        rect1.fill = Color.BLUE
//        rect2.fill = Color.BLUE
//        rect3.fill = Color.GREEN
//        rect4.fill = Color.GREEN
//
//        group.children.addAll(rect1, rect3)
//        pane.children.addAll(rect2, rect4)
//
//        val scene = Scene(vbox, 800.0, 800.0)
//        scene.addEventHandler(KeyEvent.KEY_PRESSED) { e ->
//            val deltaX: Double
//            when (e.code) {
//                KeyCode.LEFT -> deltaX = -10.0
//                KeyCode.RIGHT -> deltaX = 10.0
//                else -> deltaX = 0.0
//            }
//            rect3.x = rect3.x + deltaX
//            rect4.x = rect4.x + deltaX
//        }
//
//        primaryStage.scene = scene
//        primaryStage.show()
//    }
//
//    companion object {
//
//        @JvmStatic fun main(args: Array<String>) {
//            Application.launch(*args)
//        }
//    }
//}