import java.io.File
import java.lang.StringBuilder
import java.util.*

fun cut(indentationFlag: Boolean, input: File?, output: File?, range: String) {
    val scanner: Scanner = if (input != null) Scanner(input.inputStream())
    else Scanner(System.`in`)
    rangeParser(range)
    var line: String
    val newLine = StringBuilder()
    while (scanner.hasNext()) {
        line = scanner.nextLine()
        newLine.append(cutLine(line, indentationFlag).trimEnd() + "\n")
    }
    scanner.close()
    newLine.deleteCharAt(newLine.length - 1)
    output(newLine.toString(), output)
}

var beginRange = 0
var endRange = 0

fun rangeParser(range: String) {
    when ('-') {
        range[0] -> {
            beginRange = 0
            endRange = range.replace("-", "").toInt()
        }
        range.last() -> {
            beginRange = range.replace("-", "").toInt()
            endRange = Int.MAX_VALUE
        }
        else -> {
            beginRange = range.split("-")[0].toInt()
            endRange = range.split("-")[1].toInt()
        }
    }
}

fun output(string: String, output: File?) {
    if (output != null) {
        val outputFileWriter = output.bufferedWriter()
        outputFileWriter.use {
            it.write(string)
        }
    } else println(string)
}

fun cutLine(line: String, indentationFlag: Boolean): String {
    val string = StringBuilder()
    if (endRange == Int.MAX_VALUE) {
        endRange = if (indentationFlag)
            line.lastIndex
        else line.split(" ").lastIndex
    }
    val splitLine = line.split(" ")
    if (line.lastIndex < beginRange)
        string.append("\n")
    else for (i in beginRange..endRange) {
        if (indentationFlag) {
            if (line[i] == line.last() && line.lastIndex < endRange) {
                string.append(line[i])
                break
            } else string.append(line[i])
        } else {
            if (splitLine.lastIndex < beginRange) {
                string.append("\n")
                break
            }
            if (splitLine[i] == splitLine.last() && splitLine.lastIndex < endRange) {
                string.append(splitLine[i] + " ")
                break
            } else string.append(splitLine[i] + " ")
        }

    }
    return string.toString()
}