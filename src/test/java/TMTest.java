import edu.nju.TransitionFunction;
import edu.nju.TuringMachine;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-23 23:27
 */
public class TMTest {


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

    public Set<String> StringSetGenerator() {
        Set<String> set = new HashSet<>();
        int size = 0;
        while (size == 0) size = new Random().nextInt(50);
        for (int i = 0; i < size; i++) {
            set.add(RandomStringUtils.randomAlphanumeric(1, 12));
        }
        return set;
    }

    public Set<Character> CharacterSetGenerator() {
        Set<Character> set = new HashSet<>();
        int size = 0;
        while (size == 0) size = new Random().nextInt(50);
        for (int i = 0; i < size; i++) {
            int t = new Random().nextInt(10 + 26 + 26);
            if (t < 10)
                set.add((char) ('0' + t));
            else if (t < 10 + 26)
                set.add((char) ('a' + t - 10));
            else
                set.add((char) ('A' + t - 10 - 26));
        }
        return set;
    }

    public Set<TransitionFunction> TransitionFunctionGenerator(int tapeNum) {
        Set<TransitionFunction> set = new HashSet<>();
        for (int i = 0; i < 1 + new Random().nextInt(100); i++) {
            String fromState = RandomStringUtils.randomAlphanumeric(5);
            String toState = RandomStringUtils.randomAlphanumeric(5);
            String input = RandomStringUtils.randomAlphanumeric(tapeNum);
            String output = RandomStringUtils.randomAlphanumeric(tapeNum);
            String direction = RandomStringUtils.random(tapeNum, 'l', 'r', '*');
            TransitionFunction transitionFunction = new TransitionFunction(fromState, toState, input, output, direction);
            set.add(transitionFunction);
        }
        return set;
    }

    public TransitionFunction TransitionFunctionErrorGenerator(int tapeNum) {
        String fromState = RandomStringUtils.randomAlphanumeric(1 + new Random().nextInt(15));
        String toState = RandomStringUtils.randomAlphanumeric(1 + new Random().nextInt(15));
        String input = RandomStringUtils.randomAlphanumeric(tapeNum);
        String output = RandomStringUtils.randomAlphanumeric(tapeNum);
        String direction = RandomStringUtils.random(tapeNum, 'l', 'r', '*');
        TransitionFunction transitionFunction = new TransitionFunction(fromState, toState, input, output, direction);
        return transitionFunction;
    }

    // 测试toString
    @Test
    public void testNormalCreateA() {
        int size = 50;
        while (size-- > 0) {
            int tapeNum = 0;
            while (tapeNum == 0) tapeNum = new Random().nextInt(20);
            String q0 = RandomStringUtils.randomAlphanumeric(2, 12);
            Set<String> stateSet = StringSetGenerator();
            Set<Character> inputSymbolSet = CharacterSetGenerator();
            Set<Character> tapeSymbolSet = CharacterSetGenerator();
            Set<String> finalSet = StringSetGenerator();
            Set<TransitionFunction> transitionFunctions = TransitionFunctionGenerator(tapeNum);
            TuringMachine tm = new TuringMachine(stateSet, inputSymbolSet, tapeSymbolSet, q0, finalSet, '_', tapeNum, transitionFunctions);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(IOUtils.SetToString("Q", stateSet));
            stringBuilder.append(IOUtils.SetToString("S", inputSymbolSet));
            stringBuilder.append(IOUtils.SetToString("G", tapeSymbolSet));
            stringBuilder.append(IOUtils.SetToString("F", finalSet));
            stringBuilder.append("#q0 = ").append(q0).append(System.lineSeparator());
            stringBuilder.append("#B = ").append('_').append(System.lineSeparator());
            stringBuilder.append("#N = ").append(tapeNum).append(System.lineSeparator());
            transitionFunctions.forEach(transitionFunction -> stringBuilder.append(transitionFunction.toString()));
//			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            String var = stringBuilder.toString();

            ArrayList<String> expect = new ArrayList<>(Arrays.asList(var.split(System.lineSeparator())));
            ArrayList<String> actual = new ArrayList<>(Arrays.asList(tm.toString().split(System.lineSeparator())));

            if (expect.size() != actual.size()) fail("图灵机序列化结果长度错误");
            for (String s : expect) {
                if (!actual.contains(s)) {
                    fail("结果不包含: " + s);
                }
            }
        }
    }

    // 测试过滤注释
    @Test
    public void testNormalCreateB() {
        int size = 50;
        while (size-- > 0) {
            int tapeNum = 0;
            int t;
            while (tapeNum == 0) tapeNum = new Random().nextInt(20);

            String q0 = RandomStringUtils.randomAlphanumeric(2, 12);

            Set<String> stateSet = StringSetGenerator();
            Set<Character> inputSymbolSet = CharacterSetGenerator();
            Set<Character> tapeSymbolSet = CharacterSetGenerator();
            Set<String> finalSet = StringSetGenerator();
            Set<TransitionFunction> transitionFunctions = TransitionFunctionGenerator(tapeNum);

            StringBuilder stringBuilderA = new StringBuilder();
            StringBuilder stringBuilderB = new StringBuilder();

            for (int i = 0; i < new Random().nextInt(20); i++)
                stringBuilderA.append("; ").append(RandomStringUtils.randomAlphanumeric(16, 256)).append(System.lineSeparator());

            t = new Random().nextInt(16);
            while (t-- > 0) stringBuilderA.append(" ");
            stringBuilderA.append(IOUtils.SetToString("Q", stateSet));
            stringBuilderB.append(IOUtils.SetToString("Q", stateSet));
            for (int i = 0; i < new Random().nextInt(20); i++)
                stringBuilderA.append("; ").append(RandomStringUtils.randomAlphanumeric(16, 256)).append(System.lineSeparator());

            t = new Random().nextInt(16);
            while (t-- > 0) stringBuilderA.append(" ");
            stringBuilderA.append(IOUtils.SetToString("S", inputSymbolSet));
            stringBuilderB.append(IOUtils.SetToString("S", inputSymbolSet));
            for (int i = 0; i < new Random().nextInt(20); i++)
                stringBuilderA.append("; ").append(RandomStringUtils.randomAlphanumeric(16, 256)).append(System.lineSeparator());

            t = new Random().nextInt(16);
            while (t-- > 0) stringBuilderA.append(" ");
            stringBuilderA.append(IOUtils.SetToString("G", tapeSymbolSet));
            stringBuilderB.append(IOUtils.SetToString("G", tapeSymbolSet));
            for (int i = 0; i < new Random().nextInt(20); i++)
                stringBuilderA.append("; ").append(RandomStringUtils.randomAlphanumeric(16, 256)).append(System.lineSeparator());

            stringBuilderA.append(IOUtils.SetToString("F", finalSet));
            stringBuilderB.append(IOUtils.SetToString("F", finalSet));
            for (int i = 0; i < new Random().nextInt(20); i++)
                stringBuilderA.append("; ").append(RandomStringUtils.randomAlphanumeric(16, 256)).append(System.lineSeparator());

            t = new Random().nextInt(16);
            while (t-- > 0) stringBuilderA.append(" ");
            stringBuilderA.append("#q0 = ").append(q0).append(System.lineSeparator());
            stringBuilderB.append("#q0 = ").append(q0).append(System.lineSeparator());

            t = new Random().nextInt(16);
            while (t-- > 0) stringBuilderA.append(" ");
            stringBuilderA.append("#B = ").append('_').append(System.lineSeparator());
            stringBuilderB.append("#B = ").append('_').append(System.lineSeparator());
            for (int i = 0; i < new Random().nextInt(20); i++)
                stringBuilderA.append("; ").append(RandomStringUtils.randomAlphanumeric(16, 256)).append(System.lineSeparator());

            stringBuilderA.append("#N = ").append(tapeNum).append(System.lineSeparator());
            stringBuilderB.append("#N = ").append(tapeNum).append(System.lineSeparator());

            transitionFunctions.forEach(transitionFunction -> stringBuilderA.append(transitionFunction.toString()));
            transitionFunctions.forEach(transitionFunction -> stringBuilderB.append(transitionFunction.toString()));

            stringBuilderA.deleteCharAt(stringBuilderA.length() - 1);
            stringBuilderB.delete(stringBuilderB.length() - 2,stringBuilderB.length());
            String var = stringBuilderA.toString();
            TuringMachine tm = new TuringMachine(var);
            ArrayList<String> expect = new ArrayList<>(Arrays.asList(stringBuilderB.toString().split(System.lineSeparator())));
            ArrayList<String> actual = new ArrayList<>(Arrays.asList(tm.toString().split(System.lineSeparator())));

            if (expect.size() != actual.size()) fail("图灵机序列化结果长度错误" + expect.size() + " " + actual.size());
            for (String s : expect) {
                if (!actual.contains(s)) {
                    fail("结果不包含: " + s + tm.toString());
                } else actual.remove(s);
            }
            if (actual.size() != 0) fail();
        }
    }

    // 测试：漏写括号
    @Test
    public void testNormalCreateC() {
        int size = 50;
        while (size-- > 0) {
            int tapeNum = 0;
            int lineno;
            while (tapeNum == 0) tapeNum = new Random().nextInt(20);

            ArrayList<Integer> errorIndex = new ArrayList<>();
            String q0 = RandomStringUtils.randomAlphanumeric(2, 12);
            Set<String> stateSet = StringSetGenerator();
            Set<Character> inputSymbolSet = CharacterSetGenerator();
            Set<Character> tapeSymbolSet = CharacterSetGenerator();
            Set<String> finalSet = StringSetGenerator();
            Set<TransitionFunction> transitionFunctions = TransitionFunctionGenerator(tapeNum);
            StringBuilder stringBuilder = new StringBuilder();

            if (new Random().nextBoolean()) {
                String q = IOUtils.SetToString("Q", stateSet);
                q = q.substring(0, 5) + q.substring(6);
                stringBuilder.append(q);
                errorIndex.add(1);
            } else stringBuilder.append(IOUtils.SetToString("Q", stateSet));

            if (new Random().nextBoolean()) {
                String q = IOUtils.SetToString("S", inputSymbolSet);
                q = q.substring(0, 5) + q.substring(6);
                stringBuilder.append(q);
                errorIndex.add(2);
            } else stringBuilder.append(IOUtils.SetToString("S", inputSymbolSet));

            stringBuilder.append(IOUtils.SetToString("G", tapeSymbolSet));
//			stringBuilder.deleteCharAt(stringBuilder.length() - 2);
            stringBuilder.deleteCharAt(stringBuilder.length() - 3);
            lineno = stringBuilder.toString().split(System.lineSeparator()).length;
            errorIndex.add(lineno);

            stringBuilder.append(IOUtils.SetToString("F", finalSet));
            stringBuilder.append("#q0 = ").append(q0).append(System.lineSeparator());
            stringBuilder.append("#B = ").append('_').append(System.lineSeparator());
            stringBuilder.append("#N = ").append(tapeNum).append(System.lineSeparator());

            transitionFunctions.forEach(transitionFunction -> stringBuilder.append(transitionFunction.toString()));
//			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            String var = stringBuilder.toString();

            TuringMachine tm = new TuringMachine(var);
            String[] error = errContent.toString().split(System.lineSeparator());
            for (int i = 0; i < error.length; i++) {
                error[i] = error[i].substring(7);
                if (!errorIndex.contains(Integer.parseInt(error[i])))
                    fail("第" + error[i] + "行: " + var.split(System.lineSeparator())[Integer.parseInt(error[i]) - 1] + " 没有错误");
                else errorIndex.remove(Integer.valueOf(error[i]));
            }
            if (errorIndex.size() != 0) fail("有错误还未被检测出");
            errContent.reset();
        }
    }

    // 测试：出现了无法解析的内容，即不属于任何最后给出的格式的内容
    @Test
    public void testNormalCreateD() {
        int size = 50;
        while (size-- > 0) {
            int tapeNum = 0;
            while (tapeNum == 0) tapeNum = new Random().nextInt(20);

            String q0 = RandomStringUtils.randomAlphanumeric(2, 12);
            ArrayList<Integer> errorIndex = new ArrayList<>();

            int lineno = 1;
            Set<String> stateSet = StringSetGenerator();
            Set<Character> inputSymbolSet = CharacterSetGenerator();
            Set<Character> tapeSymbolSet = CharacterSetGenerator();
            Set<String> finalSet = StringSetGenerator();
            Set<TransitionFunction> transitionFunctions = TransitionFunctionGenerator(tapeNum);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(IOUtils.SetToString("Q", stateSet));
            lineno++;
            stringBuilder.append(IOUtils.SetToString("S", inputSymbolSet));
            lineno++;

            if (new Random().nextBoolean()) {
                stringBuilder.append("#q = bingo!" + System.lineSeparator());
                errorIndex.add(lineno++);
            }

            stringBuilder.append(IOUtils.SetToString("G", tapeSymbolSet));
            lineno++;

            if (new Random().nextBoolean()) {
                stringBuilder.append("#error = bingo!" + System.lineSeparator());
                errorIndex.add(lineno++);
            }
            stringBuilder.append(IOUtils.SetToString("F", finalSet));
            lineno++;

            if (new Random().nextBoolean()) {
                stringBuilder.append(RandomStringUtils.randomAlphanumeric(255)).append(System.lineSeparator());
                errorIndex.add(lineno++);
            }
            stringBuilder.append("#q0 = ").append(q0).append(System.lineSeparator());
            lineno++;

            stringBuilder.append("#B = ").append('_').append(System.lineSeparator());
            lineno++;

            stringBuilder.append("#what happen?" + System.lineSeparator());
            errorIndex.add(lineno++);

            stringBuilder.append("the TA in SEC1 is cute!" + System.lineSeparator());
            errorIndex.add(lineno++);

            stringBuilder.append("#N = ").append(tapeNum).append(System.lineSeparator());

            transitionFunctions.forEach(transitionFunction -> stringBuilder.append(transitionFunction.toString()));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            String var = stringBuilder.toString();

            TuringMachine tm = new TuringMachine(var);
            String[] error = errContent.toString().split(System.lineSeparator());
            int totError = errorIndex.size();
            for (int i = 0; i < error.length; i++) {
                error[i] = error[i].substring(7);
                if (!errorIndex.contains(Integer.parseInt(error[i])))
                    fail(var.split(System.lineSeparator())[0] +
                            "第" + error[i] + "行: " + var.split(System.lineSeparator())[Integer.parseInt(error[i]) - 1] + " 没有错误");
                else errorIndex.remove(Integer.valueOf(error[i]));
            }
            if (errorIndex.size() != 0)
                fail("有错误还未被检测出");
            errContent.reset();
        }
    }

    // 测试： 某个需要的七元组信息不在输入当中
    @Test
    public void testNormalCreateE() {
        int size = 100;
        while (size-- > 0) {
            int tapeNum = 0;
            while (tapeNum == 0) tapeNum = new Random().nextInt(20);
            String q0 = RandomStringUtils.randomAlphanumeric(2, 12);

            ArrayList<String> errorIndex = new ArrayList<>();
            Set<String> stateSet = StringSetGenerator();
            Set<Character> inputSymbolSet = CharacterSetGenerator();
            Set<Character> tapeSymbolSet = CharacterSetGenerator();
            Set<String> finalSet = StringSetGenerator();
            Set<TransitionFunction> transitionFunctions = TransitionFunctionGenerator(tapeNum);
            StringBuilder stringBuilder = new StringBuilder();

            if (new Random().nextBoolean()) stringBuilder.append(IOUtils.SetToString("Q", stateSet));
            else errorIndex.add("Error: lack Q");

            if (new Random().nextBoolean()) stringBuilder.append(IOUtils.SetToString("S", inputSymbolSet));
            else errorIndex.add("Error: lack S");

            if (new Random().nextBoolean()) stringBuilder.append(IOUtils.SetToString("G", tapeSymbolSet));
            else errorIndex.add("Error: lack G");

            if (new Random().nextBoolean()) stringBuilder.append(IOUtils.SetToString("F", finalSet));
            else errorIndex.add("Error: lack F");

            if (new Random().nextBoolean()) stringBuilder.append("#q0 = ").append(q0).append(System.lineSeparator());
            else errorIndex.add("Error: lack q0");

            if (new Random().nextBoolean()) stringBuilder.append("#B = ").append('_').append(System.lineSeparator());
            else errorIndex.add("Error: lack B");

            if (new Random().nextBoolean()) stringBuilder.append("#N = ").append(tapeNum).append(System.lineSeparator());
            else errorIndex.add("Error: lack N");

            if (new Random().nextBoolean())
                transitionFunctions.forEach(transitionFunction -> stringBuilder.append(transitionFunction.toString()));
            else errorIndex.add("Error: lack D");

//			if (stringBuilder.length() != 0)
//				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            String var = stringBuilder.toString();
            TuringMachine tm = new TuringMachine(var);
            String[] error = errContent.toString().split(System.lineSeparator());

            ArrayList<String> t = new ArrayList<>(errorIndex);
            for (String s : error) {
                if (s.length() == 0) continue;
                if (errorIndex.contains(s))
                    errorIndex.remove(s);
                else {
                    System.out.println(var);
                    fail();
                }
            }
            if (errorIndex.size() != 0) fail("有错误还未被检测出");
            errContent.reset();
        }
    }

    // 测试：输入的Delta函数读取的符号和写回的符号长度不同
    @Test
    public void testNormalCreateF() {
        int size = 50;
        while (size-- > 0) {
            int tapeNum = 0;
            int lineno = 1;
            while (tapeNum == 0) tapeNum = new Random().nextInt(20);
            String q0 = RandomStringUtils.randomAlphanumeric(2, 12);
            Set<String> stateSet = StringSetGenerator();
            ArrayList<Integer> errorIndex = new ArrayList<>();
            Set<Character> inputSymbolSet = CharacterSetGenerator();
            Set<Character> tapeSymbolSet = CharacterSetGenerator();
            Set<String> finalSet = StringSetGenerator();
            Set<TransitionFunction> transitionFunctions = new HashSet<>();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(IOUtils.SetToString("Q", stateSet));
            lineno++;
            stringBuilder.append(IOUtils.SetToString("S", inputSymbolSet));
            lineno++;
            stringBuilder.append(IOUtils.SetToString("G", tapeSymbolSet));
            lineno++;
            stringBuilder.append(IOUtils.SetToString("F", finalSet));
            lineno++;
            stringBuilder.append("#q0 = ").append(q0).append(System.lineSeparator());
            lineno++;
            stringBuilder.append("#B = ").append('_').append(System.lineSeparator());
            lineno++;
            stringBuilder.append("#N = ").append(tapeNum).append(System.lineSeparator());
            lineno++;
            transitionFunctions.forEach(transitionFunction -> stringBuilder.append(transitionFunction.toString()));
            for (int i = 0; i < 30; i++) {
                TransitionFunction transitionFunction = TransitionFunctionErrorGenerator(tapeNum);
                transitionFunctions.add(transitionFunction);
                if (transitionFunction.getInput().length() != transitionFunction.getOutput().length())
                    errorIndex.add(lineno);
                lineno++;
            }
            transitionFunctions.forEach(transitionFunction -> stringBuilder.append(transitionFunction.toString()));
            int flag = 0;
            if(errorIndex.size()==30) flag=1;
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            String var = stringBuilder.toString();
            TuringMachine tm = new TuringMachine(var);
            String[] error = errContent.toString().split(System.lineSeparator());
            for (int i = 0; i < error.length; i++) {
                if(error[i].length()==0) continue;
                error[i] = error[i].substring(7);
                if (!(error[i].equals("lack D") && flag == 1)) {
                    fail();
                }
                if (!errorIndex.contains(Integer.parseInt(error[i])))
                    fail("第" + error[i] + "行: " + var.split(System.lineSeparator())[Integer.parseInt(error[i]) - 1] + " 没有错误");
                else errorIndex.remove(Integer.valueOf(error[i]));
            }
            if (errorIndex.size() != 0) fail("有错误还未被检测出");
            errContent.reset();
        }
    }

}

