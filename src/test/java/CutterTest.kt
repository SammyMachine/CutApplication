import CutterLauncher.main
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException
import kotlin.test.assertTrue
import org.junit.jupiter.api.Assertions.assertThrows
import org.kohsuke.args4j.CmdLineException
import java.lang.IllegalArgumentException

class CutterTest {
    private fun assertFileContent(expectedFile: String, actualFile: String): Boolean {
        val expected = File(expectedFile).readLines()
        val actual = File(actualFile).readLines()
        for (i in actual.indices) {
            if (expected[i] != actual[i]) return false
        }
        return expected.size == actual.size
    }

    @Test

    fun cut() {
        main("-c -o output/hello1.txt input/hello.txt -r 3-7".split(" ").toTypedArray())
        assertTrue {
            assertFileContent("output/hello2.txt", "output/hello1.txt")
        }
        main("-c -o output/hello1.txt input/hello.txt -r -7".split(" ").toTypedArray())
        assertTrue {
            assertFileContent("output/hello3.txt", "output/hello1.txt")
        }
        main("-c -o output/hello1.txt input/hello.txt -r 5-".split(" ").toTypedArray())
        assertTrue {
            assertFileContent("output/hello4.txt", "output/hello1.txt")
        }
        main("-w -o output/hello1.txt input/hello134.txt -r 3-7".split(" ").toTypedArray())
        assertTrue {
            assertFileContent("output/hello5.txt", "output/hello1.txt")
        }
        main("-w -o output/hello1.txt input/hello134.txt -r -7".split(" ").toTypedArray())
        assertTrue {
            assertFileContent("output/hello6.txt", "output/hello1.txt")
        }
        main("-w -o output/hello1.txt input/hello134.txt -r 5-".split(" ").toTypedArray())
        assertTrue {
            assertFileContent("output/hello7.txt", "output/hello1.txt")
        }
        assertThrows(IllegalArgumentException::class.java) { main("-c -o output/hello1.txt input/hello81460146.txt -r 3-7".split(" ").toTypedArray()) }
        assertThrows(IllegalArgumentException::class.java) { main("-c -o output/hello1.txt input/hello81460146.txt -r -7".split(" ").toTypedArray()) }
        assertThrows(IllegalArgumentException::class.java) { main("-c -o output/hello1.txt input/hello81460146.txt -r 5-".split(" ").toTypedArray()) }
        assertThrows(IllegalArgumentException::class.java) { main("-w -o output/hello1.txt input/hello81460146.txt -r 3-7".split(" ").toTypedArray()) }
        assertThrows(IllegalArgumentException::class.java) { main("-w -o output/hello1.txt input/hello81460146.txt -r -7".split(" ").toTypedArray()) }
        assertThrows(IllegalArgumentException::class.java) { main("-w -o output/hello1.txt input/hello81460146.txt -r 5-".split(" ").toTypedArray()) }






    }

}