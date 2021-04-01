
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class CutterLauncher {
    @Option(name = "-c", metaVar = "CharsI", usage = "Indentation in characters", forbids = {"-w"})
    private Boolean c = false;

    @Option(name = "-w", metaVar = "WordsI", usage = "Indentation in words", forbids = {"-c"})
    private Boolean w = false;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String outputFileName;

    @Option(required = true, name = "-r", metaVar = "Range", usage = "Output range")
    private String range;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    public static void main(String[] args) {
        new CutterLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (!w && !c || range.isEmpty() || range.length() == 1)  {
                System.err.println("You must use -w or -c\nRange can be used like this\n        -K range from beginning of line to K\n        N- range from N to the end of the line\n        N-K range from N to K\n                where N and K - Integers");
                parser.printUsage(System.err);
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("cut [-c|-w] [-o File] [file] [-r range]");
            parser.printUsage(System.err);
            return;
        }
        Boolean indentationFlag;
        if (c) {
            indentationFlag = true;
        } else
            indentationFlag = false;
        File input = new File(inputFileName);
        File output = new File(outputFileName);
        CutterKt.cut(indentationFlag, input, output, range);
    }

}
