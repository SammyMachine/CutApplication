
import org.junit.Test
import org.kohsuke.args4j.CmdLineException
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
        main("-c -o output/hello1.txt -r 3-7".split(" ").toTypedArray())
        assertTrue {
            assertFileContent("output/hello8.txt", "output/hello1.txt")
        }
    }

}