Cut
Implementation of Cut (run-length encoding) compression in kotlin and java.

Usage
Command line: cut [-c|-w] [-o ofile ] [ file ] [-r range]

-c — sets indentation in chars
-w — sets indentation in words 
-o outputname.txt — set output file name 
file - set input file name
-r range - the parameter sets the output range and has one of the following types
           (here N and K are integers):
           ○ -K range from beginning of line to K
           ○ N- range from N to the end of the line
           ○ N-K range from N to K 
If the arguments are incorrect, a corresponding error message is generated

Automated Tests
src/test/kotlin/CutterTest.kt