//import java.io.File
//
val LINE_REGEX = Regex("""^(\d{1,2}:\d{2}:\d{2},\d{3}) *([A-Z]*) *(.*)""")

val theLine = "1:10:56,513 INFO  alcatel.elektra.ahoi.Starter start - Starter.run(): after instantiation"

val matcher = LINE_REGEX.toPattern().matcher(theLine)

println(LINE_REGEX.matches(theLine))
matcher.matches()
println("m " + matcher.groupCount())
(1..matcher.groupCount()).forEach { println("$it - ${matcher.group(it)}") }

//
//
//
//
//
//val lines = mutableMapOf<Int, String>()
///
//for (i in 1..10) {
//    lines.put(i, "Str$i")
//}
////
//val (keyv, valuev) = lines.entries.find { it.value == "Str5" }
////
////print("${keyv}")
////File("./test.kts").readLines().forEachIndexed { i, s -> lines.put(i, s) }
////println("$lines")
////print(File(ClassLoader.getSystemResource("/test.log").toURI()).readLines())