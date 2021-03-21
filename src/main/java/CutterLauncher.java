
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
            if (!w && !c)  {
                System.err.println("cut [-c|-w] [-o File] [file] [-r range]");
                parser.printUsage(System.err);
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("cut [-c|-w] [-o File] [file] [-r range]");
            parser.printUsage(System.err);
            return;
        }
        String indentation = "";
        if (inputFileName == null) inputFileName = "None";
        if (outputFileName == null) outputFileName = "None";
        if (c) {
            indentation = "Char";
        } else
            indentation = "Word";
        CutterKt.cut(indentation, inputFileName, outputFileName, range);
    }

}
