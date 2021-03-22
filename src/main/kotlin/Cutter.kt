import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.util.*


//fun main(args: Array<String>) {
//    CutterLauncher.main(args)
//}

fun cut(indentation: String, input: String, output: String, range: String) {
    if (input != "None")
        inputCut(output, range, indentation, input)
    else noInputCut(output, range, indentation)
}

fun inputCut(output: String, range: String, indentation: String, input: String) {
    var fullString = ""
    var string = ""
    val check = '-'
    val inputFile = File(input).bufferedReader()
    if (indentation == "Char") {
        when (check) {
            range[0] -> {
                val newRange = range.replace("-", "")
                for (line in inputFile.readLines()) {
                    for (i in 0 until newRange.toInt() + 1)
                        string += line[i].toString()
                    if (findingLastLine(input) != line) {
                        fullString += string + "\n"
                        string = ""
                    } else fullString += string
                }
            }
            range.last() -> {
                val newRange = range.replace("-", "")
                for (line in inputFile.readLines()) {
                    for (i in newRange.toInt() until line.length)
                        string += line[i].toString()
                    if (findingLastLine(input) != line) {
                        fullString += string + "\n"
                        string = ""
                    } else fullString += string
                }
            }
            else -> {
                val newRange = range.split("-")[0].toInt()..range.split("-")[1].toInt()
                for (line in inputFile.readLines()) {
                    for (i in newRange)
                        string += line[i].toString()
                    if (findingLastLine(input) != line) {
                        fullString += string + "\n"
                        string = ""
                    } else fullString += string
                }
            }
        }
    } else {
        when (check) {
            range[0] -> {
                val newRange = range.replace("-", "")
                for (line in inputFile.readLines()) {
                    val splitLine = line.split(" ")
                    for (i in 0 until newRange.toInt() + 1)
                        string += splitLine[i] + " "
                    if (findingLastLine(input) != line) {
                        fullString += string.trimEnd() + "\n"
                        string = ""
                    } else fullString += string.trimEnd()
                }
            }
            range.last() -> {
                val newRange = range.replace("-", "")
                for (line in inputFile.readLines()) {
                    val splitLine = line.split(" ")
                    for (i in newRange.toInt() until line.split(" ").size)
                        string += splitLine[i] + " "
                    if (findingLastLine(input) != line) {
                        fullString += string.trimEnd() + "\n"
                        string = ""
                    } else fullString += string.trimEnd()
                }
            }
            else -> {
                val newRange = range.split("-")[0].toInt()..range.split("-")[1].toInt()
                for (line in inputFile.readLines()) {
                    val splitLine = line.split(" ")
                    for (i in newRange)
                        string += splitLine[i] + " "
                    if (findingLastLine(input) != line) {
                        fullString += string.trimEnd() + "\n"
                        string = ""
                    } else fullString += string.trimEnd()
                }
            }
        }
    }
    output(fullString, output)
}

fun noInputCut(output: String, range: String, indentation: String) {
    var fullString = ""
    var string = ""
    val check = '-'
    val inputScanner = Scanner(System.`in`)
    var next: String
    if (indentation == "Char") {
        when (check) {
            range[0] -> {
                val newRange = range.replace("-", "")
                while (inputScanner.hasNext()) {
                    next = inputScanner.nextLine()
                    for (i in 0 until newRange.toInt() + 1) {
                        string += next[i].toString()
                    }
                    fullString += string + "\n"
                    string = ""
                }
            }
            range.last() -> {
                val newRange = range.replace("-", "")
                while (inputScanner.hasNext()) {
                    next = inputScanner.nextLine()
                    for (i in newRange.toInt() until next.length) {
                        string += next[i].toString()
                    }
                    fullString += string + "\n"
                    string = ""
                }
            }
            else -> {
                val newRange = range.split("-")[0].toInt()..range.split("-")[1].toInt()
                while (inputScanner.hasNext()) {
                    next = inputScanner.nextLine()
                    for (i in newRange) {
                        string += next[i].toString()
                    }
                    fullString += string + "\n"
                    string = ""
                }
            }
        }
    } else {
        when (check) {
            range[0] -> {
                val newRange = range.replace("-", "")
                while (inputScanner.hasNext()) {
                    next = inputScanner.nextLine()
                    for (i in 0 until newRange.toInt() + 1) {
                        val splitLine = next.split(" ")
                        string += splitLine[i] + " "
                    }
                    fullString += string.trimEnd() + "\n"
                    string = ""
                }
            }
            range.last() -> {
                val newRange = range.replace("-", "")
                while (inputScanner.hasNext()) {
                    next = inputScanner.nextLine()
                    val splitLine = next.split(" ")
                    for (i in newRange.toInt() - 1 until splitLine.size - 1) {
                        string += splitLine[i] + " "
                    }
                    fullString += string.trimEnd() + "\n"
                    string = ""
                }
            }
            else -> {
                val split = range.split("-")
                while (inputScanner.hasNext()) {
                    next = inputScanner.nextLine()
                    for (i in split[0].toInt() until split[1].toInt() + 1) {
                        val splitLine = next.split(" ")
                        string += splitLine[i] + " "
                    }
                    fullString += string.trimEnd() + "\n"
                    string = ""
                }
            }
        }
    }
    inputScanner.close()
    output(fullString, output)
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
