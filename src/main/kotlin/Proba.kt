
fun main(args: Array<String>) {
    val (k,v) = mapOf(Pair(1, "egy")).entries.find { it.value == "egy" }!!
    print("$k, $v")
}

