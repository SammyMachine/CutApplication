import java.io.File
import java.util.*

fun cut(indentation: String, input: String, output: String, range: String) {
    if (input != "None")
        inputCut(output, range, indentation, input)
    else noInputCut(output, range, indentation)
}

var beginRange = 0
var endRange = 0

fun inputCut(output: String, range: String, indentation: String, input: String) {
    val inputFile = File(input).bufferedReader()
    var string = ""
    for (line in inputFile.readLines()) {
        string += if (findingLastLine(input) != line) {
            cutLine(line, range, indentation).trimEnd() + "\n"
        } else cutLine(line, range, indentation).trimEnd()
    }
    string = string.trimEnd('\n')
    output(string, output)
}

fun noInputCut(output: String, range: String, indentation: String) {
    var string = ""
    val inputScanner = Scanner(System.`in`)
    var line: String
    while (inputScanner.hasNext()) {
        line = inputScanner.nextLine()
        if (line == "^D") break
        string += cutLine(line, range, indentation).trimEnd() + "\n"
    }
    inputScanner.close()
    string = string.trimEnd('\n')
    output(string, output)
}

fun output(string: String, output: String) {
    if (output != "None") {
        val outputFile = File(output).bufferedWriter()
        outputFile.use {
            it.write(string)
        }
    } else println(string)
}

fun findingLastLine(input: String): String {
    val file = File(input)
    return file.readLines().last()
}

fun rangeTransformation(range: String) {
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

fun cutLine(line: String, range: String, indentation: String): String {
    rangeTransformation(range)
    var string = ""
    if (endRange == Int.MAX_VALUE) {
        endRange = if (indentation == "Char")
            line.lastIndex
        else line.split(" ").lastIndex
    }
    for (i in beginRange..endRange) {
        if (indentation == "Char") {
            if (line[i] == line.last() && line.lastIndex < endRange) {
                string += line[i].toString()
                break
            }
            else string += line[i].toString()
        }
        else {
            val splitLine = line.split(" ")
            if (splitLine[i] == splitLine.last() && splitLine.lastIndex < endRange) {
                string += splitLine[i] + " "
                break
            }
            else string += splitLine[i] + " "
        }
    }
    return string
}