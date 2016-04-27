import java.io.File

val lines = mutableMapOf<Int, String>()
File("./test.kts").readLines().forEachIndexed { i, s -> lines.put(i, s) }
println("$lines")
//print(File(ClassLoader.getSystemResource("/test.log").toURI()).readLines())