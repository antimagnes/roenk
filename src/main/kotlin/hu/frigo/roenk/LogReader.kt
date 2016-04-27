//package hu.frigo.roenk
//
//import java.nio.file.Paths
//import java.util.AbstractMap
//import java.util.ArrayList
//import java.util.Arrays
//import java.util.Collections
//import java.util.HashMap
//import java.util.regex.Pattern
//
//object LogReader {
//
//    fun processLogFile(filename: String, lineRegexp: String,
//                       groupToKeyMap: Map<Int, String>): Map<String, List<Map<String, String>>> {
//        val l = HashMap<Long, String>()
//        var encodingSuccess: String? = null
//        for (encoding in Arrays.asList("UTF-8", "ISO-8859-1", "ISO-8859-2")) {
//            println("reading using encoding = " + encoding)
//            encodingSuccess = encoding
//
//            Paths.get(filename).toFile().readLines().mapIndexed { i, s -> Pair(i, s) }
//        }
//    }
//
//    private fun processDaList(l: MutableMap<Long, String>, lineRegexp: String,
//                              groupToKeyMap: Map<Int, String>): Map<Long, Map<String, String>> {
//        val pat = Pattern.compile(lineRegexp)
//
//        val mpn = LongStream.range(0, java.lang.Long.valueOf(l.keys.size.toLong())!!).sorted().boxed().collect<List<Long>, Any>(Collectors.toList<Long>())
//
//        var cle: LogEntry? = null
//        val iteratorList = ArrayList(l)
//        for (le in iteratorList) {
//            val matcher = pat.matcher(le.getLogString())
//            if (matcher.find()) {
//                groupToKeyMap.entries.stream().collect(Collectors.toMap<Entry<Int, String>, String, String>(Function<Entry<Int, String>, String> { it.value }) { e -> matcher.group(e.key) })
//                cle = le
//            } else {
//                if (cle == null) {
//                    cle = LogEntry(-1L, -1L, "PLACEHOLDER", "File begin")
//                    l.add(0, cle)
//                }
//                cle.addNonLogLine(le.getLogString())
//                l.remove(le)
//            }
//        }
//        return l
//    }
//}
