import edu.nju.Executor;
import edu.nju.Tape;
import edu.nju.TuringMachine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @Author: pkun & yihui wang
 * @CreateTime: 2021-05-25 23:56
 */
public class ExecutorTest {

    String[] expects = new String[]{
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5 6 7 8 9\n" +
                    "Track0: a a a b b b\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 1\n" +
                    "Track0: _\n" +
                    "Head1 : 1\n" +
                    "State : 1\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 5 6 7 8 9\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 5\n" +
                    "Tape1 :\n" +
                    "Index1: 1 2\n" +
                    "Track0: 1 _\n" +
                    "Head1 : 2\n" +
                    "State : 1\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 6 7 8 9\n" +
                    "Track0: a b b b\n" +
                    "Head0 : 6\n" +
                    "Tape1 :\n" +
                    "Index1: 1 2 3\n" +
                    "Track0: 1 1 _\n" +
                    "Head1 : 3\n" +
                    "State : 1\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 7 8 9\n" +
                    "Track0: b b b\n" +
                    "Head0 : 7\n" +
                    "Tape1 :\n" +
                    "Index1: 1 2 3 4\n" +
                    "Track0: 1 1 1 _\n" +
                    "Head1 : 4\n" +
                    "State : 1\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 8 9\n" +
                    "Track0: b b\n" +
                    "Head0 : 8\n" +
                    "Tape1 :\n" +
                    "Index1: 1 2 3\n" +
                    "Track0: 1 1 1\n" +
                    "Head1 : 3\n" +
                    "State : 2\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 8 9\n" +
                    "Track0: b b\n" +
                    "Head0 : 8\n" +
                    "Tape1 :\n" +
                    "Index1: 1 2\n" +
                    "Track0: 1 1\n" +
                    "Head1 : 2\n" +
                    "State : 3\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 9\n" +
                    "Track0: b\n" +
                    "Head0 : 9\n" +
                    "Tape1 :\n" +
                    "Index1: 1\n" +
                    "Track0: 1\n" +
                    "Head1 : 1\n" +
                    "State : 3\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 10\n" +
                    "Track0: _\n" +
                    "Head0 : 10\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : 3\n" +
                    "Step  : 8\n" +
                    "Tape0 :\n" +
                    "Index0: 10\n" +
                    "Track0: _\n" +
                    "Head0 : 10\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : 4",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5 6 7 8 9\n" +
                    "Track0: a a a b b b\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : 1\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 5 6 7 8 9\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 5\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1\n" +
                    "Track0: 1 _\n" +
                    "Head1 : 1\n" +
                    "State : 1\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 6 7 8 9\n" +
                    "Track0: a b b b\n" +
                    "Head0 : 6\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2\n" +
                    "Track0: 1 1 _\n" +
                    "Head1 : 2\n" +
                    "State : 1\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 7 8 9\n" +
                    "Track0: b b b\n" +
                    "Head0 : 7\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3\n" +
                    "Track0: 1 1 1 _\n" +
                    "Head1 : 3\n" +
                    "State : 1\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 8 9\n" +
                    "Track0: b b\n" +
                    "Head0 : 8\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2\n" +
                    "Track0: 1 1 1\n" +
                    "Head1 : 2\n" +
                    "State : 2\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 8 9\n" +
                    "Track0: b b\n" +
                    "Head0 : 8\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1\n" +
                    "Track0: 1 1\n" +
                    "Head1 : 1\n" +
                    "State : 3\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 9\n" +
                    "Track0: b\n" +
                    "Head0 : 9\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: 1\n" +
                    "Head1 : 0\n" +
                    "State : 3\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 10\n" +
                    "Track0: _\n" +
                    "Head0 : 10\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : 3\n" +
                    "Step  : 8\n" +
                    "Tape0 :\n" +
                    "Index0: 10\n" +
                    "Track0: _\n" +
                    "Head0 : 10\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : 4",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a a a a a b b b\n" +
                    "Head0 : 0\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : 1\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a a a a b b b\n" +
                    "Head0 : 1\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1\n" +
                    "Track0: 1 _\n" +
                    "Head1 : 1\n" +
                    "State : 1\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4 5 6 7 8\n" +
                    "Track0: a a a a b b b\n" +
                    "Head0 : 2\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2\n" +
                    "Track0: 1 1 _\n" +
                    "Head1 : 2\n" +
                    "State : 1\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8\n" +
                    "Track0: a a a b b b\n" +
                    "Head0 : 3\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3\n" +
                    "Track0: 1 1 1 _\n" +
                    "Head1 : 3\n" +
                    "State : 1\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5 6 7 8\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3 4\n" +
                    "Track0: 1 1 1 1 _\n" +
                    "Head1 : 4\n" +
                    "State : 1\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 5 6 7 8\n" +
                    "Track0: a b b b\n" +
                    "Head0 : 5\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3 4 5\n" +
                    "Track0: 1 1 1 1 1 _\n" +
                    "Head1 : 5\n" +
                    "State : 1\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 6 7 8\n" +
                    "Track0: b b b\n" +
                    "Head0 : 6\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3 4 5 6\n" +
                    "Track0: 1 1 1 1 1 1 _\n" +
                    "Head1 : 6\n" +
                    "State : 1\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 7 8\n" +
                    "Track0: b b\n" +
                    "Head0 : 7\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3 4 5\n" +
                    "Track0: 1 1 1 1 1 1\n" +
                    "Head1 : 5\n" +
                    "State : 2\n" +
                    "Step  : 8\n" +
                    "Tape0 :\n" +
                    "Index0: 7 8\n" +
                    "Track0: b b\n" +
                    "Head0 : 7\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3 4\n" +
                    "Track0: 1 1 1 1 1\n" +
                    "Head1 : 4\n" +
                    "State : 3\n" +
                    "Step  : 9\n" +
                    "Tape0 :\n" +
                    "Index0: 8\n" +
                    "Track0: b\n" +
                    "Head0 : 8\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3\n" +
                    "Track0: 1 1 1 1\n" +
                    "Head1 : 3\n" +
                    "State : 3\n" +
                    "Step  : 10\n" +
                    "Tape0 :\n" +
                    "Index0: 9\n" +
                    "Track0: _\n" +
                    "Head0 : 9\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2\n" +
                    "Track0: 1 1 1\n" +
                    "Head1 : 2\n" +
                    "State : 3",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 3 + 6\n" +
                    "Head0 : 2\n" +
                    "State : init\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 2 + 6\n" +
                    "Head0 : 3\n" +
                    "State : add\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 2 + 6\n" +
                    "Head0 : 4\n" +
                    "State : add\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 2 + 7\n" +
                    "Head0 : 3\n" +
                    "State : sub\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 2 + 7\n" +
                    "Head0 : 2\n" +
                    "State : sub\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 1 + 7\n" +
                    "Head0 : 3\n" +
                    "State : add\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 1 + 7\n" +
                    "Head0 : 4\n" +
                    "State : add\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 1 + 8\n" +
                    "Head0 : 3\n" +
                    "State : sub\n" +
                    "Step  : 8\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 1 + 8\n" +
                    "Head0 : 2\n" +
                    "State : sub\n" +
                    "Step  : 9\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 0 + 8\n" +
                    "Head0 : 3\n" +
                    "State : add\n" +
                    "Step  : 10\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 0 + 8\n" +
                    "Head0 : 4\n" +
                    "State : add\n" +
                    "Step  : 11\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 0 + 9\n" +
                    "Head0 : 3\n" +
                    "State : sub\n" +
                    "Step  : 12\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 0 + 9\n" +
                    "Head0 : 2\n" +
                    "State : sub\n" +
                    "Step  : 13\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: _ + 9\n" +
                    "Head0 : 2\n" +
                    "State : final_state",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 3 + 6\n" +
                    "Head0 : 2\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : init\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 2 + 6\n" +
                    "Head0 : 2\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1\n" +
                    "Track0: 1 _\n" +
                    "Head1 : 1\n" +
                    "State : get_num\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 1 + 6\n" +
                    "Head0 : 2\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2\n" +
                    "Track0: 1 1 _\n" +
                    "Head1 : 2\n" +
                    "State : get_num\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 0 + 6\n" +
                    "Head0 : 2\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3\n" +
                    "Track0: 1 1 1 _\n" +
                    "Head1 : 3\n" +
                    "State : get_num\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: + 6\n" +
                    "Head0 : 3\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2 3\n" +
                    "Track0: 1 1 1 _\n" +
                    "Head1 : 3\n" +
                    "State : get_num\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 6\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2\n" +
                    "Track0: 1 1 1\n" +
                    "Head1 : 2\n" +
                    "State : add\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 7\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1\n" +
                    "Track0: 1 1\n" +
                    "Head1 : 1\n" +
                    "State : add\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 8\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: 1\n" +
                    "Head1 : 0\n" +
                    "State : add\n" +
                    "Step  : 8\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 9\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : add\n" +
                    "Step  : 9\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 9\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : final_state",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4 5\n" +
                    "Track0: a b b a\n" +
                    "Head0 : 2\n" +
                    "State : init\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5\n" +
                    "Track0: b b a\n" +
                    "Head0 : 3\n" +
                    "State : A\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5\n" +
                    "Track0: a b a\n" +
                    "Head0 : 4\n" +
                    "State : B\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5\n" +
                    "Track0: a b a\n" +
                    "Head0 : 5\n" +
                    "State : B\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6\n" +
                    "Track0: a b b _\n" +
                    "Head0 : 6\n" +
                    "State : A\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6\n" +
                    "Track0: a b b a\n" +
                    "Head0 : 6\n" +
                    "State : f",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 2\n" +
                    "State : init\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 3\n" +
                    "State : A\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 4\n" +
                    "State : A\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 5\n" +
                    "State : A\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 6\n" +
                    "State : A\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 7\n" +
                    "State : A\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 8\n" +
                    "State : A\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 9\n" +
                    "State : A\n" +
                    "Step  : 8\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 10\n" +
                    "State : A\n" +
                    "Step  : 9\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 11\n" +
                    "State : A\n" +
                    "Step  : 10\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 12\n" +
                    "State : A\n" +
                    "Step  : 11\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 13\n" +
                    "State : A\n" +
                    "Step  : 12\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 14\n" +
                    "State : A\n" +
                    "Step  : 13\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 15\n" +
                    "State : A\n" +
                    "Step  : 14\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 16\n" +
                    "State : B\n" +
                    "Step  : 15\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 17\n" +
                    "State : B\n" +
                    "Step  : 16\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 18\n" +
                    "State : B\n" +
                    "Step  : 17\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 19\n" +
                    "State : B\n" +
                    "Step  : 18\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 20\n" +
                    "State : B\n" +
                    "Step  : 19\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 21\n" +
                    "State : B\n" +
                    "Step  : 20\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 22\n" +
                    "State : B\n" +
                    "Step  : 21\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 23\n" +
                    "State : B\n" +
                    "Step  : 22\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 24\n" +
                    "State : B\n" +
                    "Step  : 23\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 25\n" +
                    "State : B\n" +
                    "Step  : 24\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 26\n" +
                    "State : B\n" +
                    "Step  : 25\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 27\n" +
                    "State : B\n" +
                    "Step  : 26\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 28\n" +
                    "State : A\n" +
                    "Step  : 27\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 29\n" +
                    "State : A\n" +
                    "Step  : 28\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 30\n" +
                    "State : A\n" +
                    "Step  : 29\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 31\n" +
                    "State : A\n" +
                    "Step  : 30\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 32\n" +
                    "State : A\n" +
                    "Step  : 31\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 33\n" +
                    "State : A\n" +
                    "Step  : 32\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 34\n" +
                    "State : A\n" +
                    "Step  : 33\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 35\n" +
                    "State : A\n" +
                    "Step  : 34\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 36\n" +
                    "State : A\n" +
                    "Step  : 35\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 37\n" +
                    "State : A\n" +
                    "Step  : 36\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 38\n" +
                    "State : A\n" +
                    "Step  : 37\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 39\n" +
                    "State : A\n" +
                    "Step  : 38\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 40\n" +
                    "State : A\n" +
                    "Step  : 39\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 41\n" +
                    "State : A\n" +
                    "Step  : 40\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 42\n" +
                    "State : A\n" +
                    "Step  : 41\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 43\n" +
                    "State : A\n" +
                    "Step  : 42\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 44\n" +
                    "State : A\n" +
                    "Step  : 43\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 45\n" +
                    "State : A\n" +
                    "Step  : 44\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  _\n" +
                    "Head0 : 46\n" +
                    "State : A\n" +
                    "Step  : 45\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46\n" +
                    "Track0: a a a a a a a a  a  a  a  a  a  b  b  b  b  b  b  b  b  b  b  b  b  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a  a\n" +
                    "Head0 : 46\n" +
                    "State : f",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 2 + 4\n" +
                    "Head0 : 2\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : init\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 1 + 4\n" +
                    "Head0 : 2\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1\n" +
                    "Track0: 1 _\n" +
                    "Head1 : 1\n" +
                    "State : get_num\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4\n" +
                    "Track0: 0 + 4\n" +
                    "Head0 : 2\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2\n" +
                    "Track0: 1 1 _\n" +
                    "Head1 : 2\n" +
                    "State : get_num\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: + 4\n" +
                    "Head0 : 3\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1 2\n" +
                    "Track0: 1 1 _\n" +
                    "Head1 : 2\n" +
                    "State : get_num\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 4\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0 1\n" +
                    "Track0: 1 1\n" +
                    "Head1 : 1\n" +
                    "State : add\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 5\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: 1\n" +
                    "Head1 : 0\n" +
                    "State : add\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 6\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : add\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4\n" +
                    "Track0: = 6\n" +
                    "Head0 : 4\n" +
                    "Tape1 :\n" +
                    "Index1: 0\n" +
                    "Track0: _\n" +
                    "Head1 : 0\n" +
                    "State : final_state",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4 5 6 7\n" +
                    "Track0: a a a b b b\n" +
                    "Head0 : 2\n" +
                    "State : 0\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 3\n" +
                    "State : 1\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 4\n" +
                    "State : 1\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 5\n" +
                    "State : 1\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 6\n" +
                    "State : 1\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 7\n" +
                    "State : 1\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7 8\n" +
                    "Track0: a a b b b _\n" +
                    "Head0 : 8\n" +
                    "State : 1\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6 7\n" +
                    "Track0: a a b b b\n" +
                    "Head0 : 7\n" +
                    "State : 2\n" +
                    "Step  : 8\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6\n" +
                    "Track0: a a b b\n" +
                    "Head0 : 6\n" +
                    "State : 3\n" +
                    "Step  : 9\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6\n" +
                    "Track0: a a b b\n" +
                    "Head0 : 5\n" +
                    "State : 3\n" +
                    "Step  : 10\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6\n" +
                    "Track0: a a b b\n" +
                    "Head0 : 4\n" +
                    "State : 3\n" +
                    "Step  : 11\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6\n" +
                    "Track0: a a b b\n" +
                    "Head0 : 3\n" +
                    "State : 3\n" +
                    "Step  : 12\n" +
                    "Tape0 :\n" +
                    "Index0: 2 3 4 5 6\n" +
                    "Track0: _ a a b b\n" +
                    "Head0 : 2\n" +
                    "State : 3\n" +
                    "Step  : 13\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5 6\n" +
                    "Track0: a a b b\n" +
                    "Head0 : 3\n" +
                    "State : 0\n" +
                    "Step  : 14\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5 6\n" +
                    "Track0: a b b\n" +
                    "Head0 : 4\n" +
                    "State : 1\n" +
                    "Step  : 15\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5 6\n" +
                    "Track0: a b b\n" +
                    "Head0 : 5\n" +
                    "State : 1\n" +
                    "Step  : 16\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5 6\n" +
                    "Track0: a b b\n" +
                    "Head0 : 6\n" +
                    "State : 1\n" +
                    "Step  : 17\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5 6 7\n" +
                    "Track0: a b b _\n" +
                    "Head0 : 7\n" +
                    "State : 1\n" +
                    "Step  : 18\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5 6\n" +
                    "Track0: a b b\n" +
                    "Head0 : 6\n" +
                    "State : 2\n" +
                    "Step  : 19\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5\n" +
                    "Track0: a b\n" +
                    "Head0 : 5\n" +
                    "State : 3\n" +
                    "Step  : 20\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5\n" +
                    "Track0: a b\n" +
                    "Head0 : 4\n" +
                    "State : 3\n" +
                    "Step  : 21\n" +
                    "Tape0 :\n" +
                    "Index0: 3 4 5\n" +
                    "Track0: _ a b\n" +
                    "Head0 : 3\n" +
                    "State : 3\n" +
                    "Step  : 22\n" +
                    "Tape0 :\n" +
                    "Index0: 4 5\n" +
                    "Track0: a b\n" +
                    "Head0 : 4\n" +
                    "State : 0\n" +
                    "Step  : 23\n" +
                    "Tape0 :\n" +
                    "Index0: 5\n" +
                    "Track0: b\n" +
                    "Head0 : 5\n" +
                    "State : 1\n" +
                    "Step  : 24\n" +
                    "Tape0 :\n" +
                    "Index0: 5 6\n" +
                    "Track0: b _\n" +
                    "Head0 : 6\n" +
                    "State : 1\n" +
                    "Step  : 25\n" +
                    "Tape0 :\n" +
                    "Index0: 5\n" +
                    "Track0: b\n" +
                    "Head0 : 5\n" +
                    "State : 2\n" +
                    "Step  : 26\n" +
                    "Tape0 :\n" +
                    "Index0: 4\n" +
                    "Track0: _\n" +
                    "Head0 : 4\n" +
                    "State : 3\n" +
                    "Step  : 27\n" +
                    "Tape0 :\n" +
                    "Index0: 5\n" +
                    "Track0: _\n" +
                    "Head0 : 5\n" +
                    "State : 0\n" +
                    "Step  : 28\n" +
                    "Tape0 :\n" +
                    "Index0: 6\n" +
                    "Track0: _\n" +
                    "Head0 : 6\n" +
                    "State : 4",
            "Step  : 0\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a a a a a b b b\n" +
                    "Head0 : 2\n" +
                    "State : 0\n" +
                    "Step  : 1\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a _ a a a b b b\n" +
                    "Head0 : 3\n" +
                    "State : 1\n" +
                    "Step  : 2\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a _ a a a b b b\n" +
                    "Head0 : 4\n" +
                    "State : 1\n" +
                    "Step  : 3\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a _ a a a b b b\n" +
                    "Head0 : 5\n" +
                    "State : 1\n" +
                    "Step  : 4\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a _ a a a b b b\n" +
                    "Head0 : 6\n" +
                    "State : 1\n" +
                    "Step  : 5\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a _ a a a b b b\n" +
                    "Head0 : 7\n" +
                    "State : 1\n" +
                    "Step  : 6\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a _ a a a b b b\n" +
                    "Head0 : 8\n" +
                    "State : 1\n" +
                    "Step  : 7\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8 9\n" +
                    "Track0: a a _ a a a b b b _\n" +
                    "Head0 : 9\n" +
                    "State : 1\n" +
                    "Step  : 8\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a _ a a a b b b\n" +
                    "Head0 : 8\n" +
                    "State : 2\n" +
                    "Step  : 9\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ a a a b b\n" +
                    "Head0 : 7\n" +
                    "State : 3\n" +
                    "Step  : 10\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ a a a b b\n" +
                    "Head0 : 6\n" +
                    "State : 3\n" +
                    "Step  : 11\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ a a a b b\n" +
                    "Head0 : 5\n" +
                    "State : 3\n" +
                    "Step  : 12\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ a a a b b\n" +
                    "Head0 : 4\n" +
                    "State : 3\n" +
                    "Step  : 13\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ a a a b b\n" +
                    "Head0 : 3\n" +
                    "State : 3\n" +
                    "Step  : 14\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ a a a b b\n" +
                    "Head0 : 2\n" +
                    "State : 3\n" +
                    "Step  : 15\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ a a a b b\n" +
                    "Head0 : 3\n" +
                    "State : 0\n" +
                    "Step  : 16\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ _ a a b b\n" +
                    "Head0 : 4\n" +
                    "State : 1\n" +
                    "Step  : 17\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ _ a a b b\n" +
                    "Head0 : 5\n" +
                    "State : 1\n" +
                    "Step  : 18\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ _ a a b b\n" +
                    "Head0 : 6\n" +
                    "State : 1\n" +
                    "Step  : 19\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ _ a a b b\n" +
                    "Head0 : 7\n" +
                    "State : 1\n" +
                    "Step  : 20\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7 8\n" +
                    "Track0: a a _ _ a a b b _\n" +
                    "Head0 : 8\n" +
                    "State : 1\n" +
                    "Step  : 21\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ _ a a b b\n" +
                    "Head0 : 7\n" +
                    "State : 2\n" +
                    "Step  : 22\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ a a b\n" +
                    "Head0 : 6\n" +
                    "State : 3\n" +
                    "Step  : 23\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ a a b\n" +
                    "Head0 : 5\n" +
                    "State : 3\n" +
                    "Step  : 24\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ a a b\n" +
                    "Head0 : 4\n" +
                    "State : 3\n" +
                    "Step  : 25\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ a a b\n" +
                    "Head0 : 3\n" +
                    "State : 3\n" +
                    "Step  : 26\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ a a b\n" +
                    "Head0 : 4\n" +
                    "State : 0\n" +
                    "Step  : 27\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ _ a b\n" +
                    "Head0 : 5\n" +
                    "State : 1\n" +
                    "Step  : 28\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ _ a b\n" +
                    "Head0 : 6\n" +
                    "State : 1\n" +
                    "Step  : 29\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6 7\n" +
                    "Track0: a a _ _ _ a b _\n" +
                    "Head0 : 7\n" +
                    "State : 1\n" +
                    "Step  : 30\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ _ a b\n" +
                    "Head0 : 6\n" +
                    "State : 2\n" +
                    "Step  : 31\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5\n" +
                    "Track0: a a _ _ _ a\n" +
                    "Head0 : 5\n" +
                    "State : 3\n" +
                    "Step  : 32\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5\n" +
                    "Track0: a a _ _ _ a\n" +
                    "Head0 : 4\n" +
                    "State : 3\n" +
                    "Step  : 33\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5\n" +
                    "Track0: a a _ _ _ a\n" +
                    "Head0 : 5\n" +
                    "State : 0\n" +
                    "Step  : 34\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5 6\n" +
                    "Track0: a a _ _ _ _ _\n" +
                    "Head0 : 6\n" +
                    "State : 1\n" +
                    "Step  : 35\n" +
                    "Tape0 :\n" +
                    "Index0: 0 1 2 3 4 5\n" +
                    "Track0: a a _ _ _ _\n" +
                    "Head0 : 5\n" +
                    "State : 2"
    };

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }


    @Test
    public void testCorrectE() throws IOException {
        Path path = Paths.get("TuringMachine/anbnTwoTape.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("____aaabbb___"));
        tapes.add(new Tape(tracks, 4, '_'));
        tracks = new ArrayList<>();
        tracks.add(new StringBuilder("____________"));
        tapes.add(new Tape(tracks, 1, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[0].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectF() throws IOException {
        Path path = Paths.get("TuringMachine/anbnTwoTape.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("____aaabbb___"));
        tapes.add(new Tape(tracks, 4, '_'));
        tracks = new ArrayList<>();
        tracks.add(new StringBuilder("_"));
        tapes.add(new Tape(tracks, 0, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[1].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectG() throws IOException {
        Path path = Paths.get("TuringMachine/anbnTwoTape.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("aaaaaabbb"));
        tapes.add(new Tape(tracks, 0, '_'));
        tracks = new ArrayList<>();
        tracks.add(new StringBuilder("_"));
        tapes.add(new Tape(tracks, 0, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[2].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectH() throws IOException {
        Path path = Paths.get("TuringMachine/3+6.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("__3+6__"));
        tapes.add(new Tape(tracks, 2, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[3].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectI() throws IOException {
        Path path = Paths.get("TuringMachine/add.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("__3+6__"));
        tapes.add(new Tape(tracks, 2, '_'));
        tracks = new ArrayList<>();
        tracks.add(new StringBuilder("_"));
        tapes.add(new Tape(tracks, 0, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[4].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectJ() throws IOException {
        Path path = Paths.get("TuringMachine/add.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("__2+4__"));
        tapes.add(new Tape(tracks, 2, '_'));
        tracks = new ArrayList<>();
        tracks.add(new StringBuilder("_"));
        tapes.add(new Tape(tracks, 0, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[7].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectK() throws IOException {
        Path path = Paths.get("TuringMachine/right_shift.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("__abba__"));
        tapes.add(new Tape(tracks, 2, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[5].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectL() throws IOException {
        Path path = Paths.get("TuringMachine/right_shift.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("__aaaaaaaaaaaaabbbbbbbbbbbbaaaaaaaaaaaaaaaaaaa__"));
        tapes.add(new Tape(tracks, 2, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[6].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectM() throws IOException {
        Path path = Paths.get("TuringMachine/anbn.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("__aaabbb__"));
        tapes.add(new Tape(tracks, 2, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[8].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    @Test
    public void testCorrectN() throws IOException {
        Path path = Paths.get("TuringMachine/anbn.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("aaaaaabbb"));
        tapes.add(new Tape(tracks, 2, '_'));
        Executor executor = new Executor(tm, tapes);
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        boolean ret = true;
        do {
            ret = executor.execute();
            actual.addAll(Arrays.asList(executor.snapShot().split(System.lineSeparator())));
        } while (ret);
        if (errContent.size() != 0) fail();
        String[] expect = expects[9].split("\n");
        if (expect.length != actual.size()) fail();
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual.get(i));
        }
    }

    /**
     * error 1,2
     *
     * @throws IOException
     */
    @Test
    public void testErrorO() throws IOException {
        Path path = Paths.get("TuringMachine/error1.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("zn"));
        tapes.add(new Tape(tracks, 0, '_'));
        Executor executor = new Executor(tm, tapes);
        String[] error = errContent.toString().split(System.lineSeparator());
        ArrayList<String> errorIndex = new ArrayList<>();
        errorIndex.add("Error: 1");
        errorIndex.add("Error: 2");
        for (String s : error) {
            if (s.length() == 0) continue;
            if (errorIndex.contains(s)) errorIndex.remove(s);
            else {
                fail();
            }
        }
        if (errorIndex.size() != 0) fail("有错误还未被检测出");
        errContent.reset();
    }

    /**
     * error 3, 4, 5, 6, 7, 8
     *
     * @throws IOException
     */
    @Test
    public void testErrorP() throws IOException {
        Path path = Paths.get("TuringMachine/error2.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("aabb"));
        tapes.add(new Tape(tracks, 0, '_'));
        Executor executor = new Executor(tm, tapes);
        String[] error = errContent.toString().split(System.lineSeparator());
        ArrayList<String> errorIndex = new ArrayList<>();
        errorIndex.add("Error: 3");
        errorIndex.add("Error: 4");
        errorIndex.add("Error: 5");
        errorIndex.add("Error: 6");
        String temp = "Error: 8\n" +
                "Error: 8\n" +
                "Error: 7\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 7\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 7\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8\n" +
                "Error: 8";
        errorIndex.addAll(Arrays.asList(temp.split("\n")));
        for (String s : error) {
            if (s.length() == 0) continue;
            if (errorIndex.contains(s)) errorIndex.remove(s);
            else {
                fail();
            }
        }
        if (errorIndex.size() != 0) fail("有错误还未被检测出");
        errContent.reset();
    }

    @Test
    public void testErrorQ() throws IOException {
        Path path = Paths.get("TuringMachine/error3.tm");
        TuringMachine tm = new TuringMachine(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
        ArrayList<Tape> tapes = new ArrayList<>();
        ArrayList<StringBuilder> tracks = new ArrayList<>();
        tracks.add(new StringBuilder("3+4"));
        tapes.add(new Tape(tracks, 0, '_'));
        Executor executor = new Executor(tm, tapes);
        String[] error = errContent.toString().split(System.lineSeparator());
        ArrayList<String> errorIndex = new ArrayList<>();
        errorIndex.add("Error: 9");
        errorIndex.add("Error: 9");
        errorIndex.add("Error: 9");
        errorIndex.add("Error: 2");
        for (String s : error) {
            if (s.length() == 0) continue;
            if (errorIndex.contains(s)) errorIndex.remove(s);
            else {
                fail();
            }
        }
        if (errorIndex.size() != 0) fail("有错误还未被检测出");
        errContent.reset();
    }


}
