
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CutterLauncher {
    @Option(name = "-c", metaVar = "CharsI", usage = "Indentation in characters", forbids = {"-w"})
    private Boolean c = false;

    @Option(name = "-w", metaVar = "WordsI", usage = "Indentation in words", forbids = {"-c"})
    private Boolean w = false;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private File outputFile;

    @Option(required = true, name = "-r", metaVar = "Range", usage = "Output range")
    private String range;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private File inputFile;

    public CutterLauncher() {
    }

    public static void main(String[] args) {
        new CutterLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (!w && !c || range.equals("") || !range.matches("\\d-\\d|\\d-|-\\d")) {
                System.err.println("You must use -w or -c\nRange (-r) must be used like this\n        -K range from beginning of line to K\n        N- range from N to the end of the line\n         N-K range from N to K\n           where N and K are integers");
                parser.printUsage(System.err);
                System.err.println("cut [-c|-w] [-o File] [file] [-r range]");
                throw new IllegalArgumentException("");
            }
            if (!inputFile.exists()) {
                System.err.println("Input file is not found");
                parser.printUsage(System.err);
                System.err.println("cut [-c|-w] [-o File] [file] [-r range]");
                throw new IllegalArgumentException("");
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("cut [-c|-w] [-o File] [file] [-r range]");
            parser.printUsage(System.err);
            throw new IllegalArgumentException("");
        }
        Boolean indentationFlag;
        if (c) {
            indentationFlag = true;
        } else
            indentationFlag = false;
        CutterKt.cut(indentationFlag, inputFile, outputFile, range);
    }

}
