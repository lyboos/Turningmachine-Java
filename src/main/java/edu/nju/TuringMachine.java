package edu.nju;

import java.util.*;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-23 16:15
 */
public class TuringMachine {

    // 状态集合
    private final Map<String, State> Q;
    // 输入符号集
    private Set<Character> S;
    // 磁带符号集
    private Set<Character> G;
    // 初始状态
    private String q0;
    // 终止状态集
    private Set<String> F;
    // 空格符号
    private Character B;
    // 磁带数
    private Integer tapeNum;

    public TuringMachine(Set<String> Q, Set<Character> S, Set<Character> G, String q, Set<String> F, char B, int tapeNum, Set<TransitionFunction> Delta) {
        this.S = S;
        this.G = G;
        this.F = F;
        this.B = B;
        this.q0 = q;
        this.Q = new HashMap<>();
        for (String state : Q) {
            State temp = new State(state);
            temp.setQ(state);
            this.Q.put(state, temp);
        }
        this.tapeNum = tapeNum;
        for (TransitionFunction t : Delta) {
            this.Q.get(t.getSourceState().getQ()).addTransitionFunction(t);
        }
    }

    /**
     * TODO
     * is done in Lab1 ~
     *
     * @param tm
     */
    public TuringMachine(String tm) {
        String[] var = tm.split("\n");
        Q = new HashMap<>();
        int i = 0;
        for (String s : var) {
            i++;
            s = s.trim();
            if (s.length() != 0 && !Utils.IsComment(s)) {
                switch (s.substring(0, 2)) {
                    case "#Q":
                        createQ(s, i);
                        break;
                    case "#S":
                        createS(s, i);
                        break;
                    case "#G":
                        createG(s, i);
                        break;
                    case "#F":
                        createF(s, i);
                        break;
                    case "#q":
                        if (s.charAt(2) != '0') System.err.println("Error: " + i);
                        else {
                            q0 = s.split(" ")[2];
                        }
                        break;
                    case "#B":
                        B = s.split(" ")[2].charAt(0);
                        break;
                    case "#N":
                        tapeNum = Integer.parseInt(s.split(" ")[2]);
                        break;
                    case "#D":
                        s = s.substring(3);
                        resolverTransitionFunction(s, i);
                        break;
                    default:
                        System.err.println("Error: " + i);
                        break;
                }
            }
        }
        assert F != null;
        if (!Utils.isSubSet(F, Q.keySet())) System.err.println("Error: 3");
        if (S.contains(B)) System.err.println("Error: 4");
        if (!G.contains(B)) System.err.println("Error: 5");
        if (!Utils.isSubSet(S, G)) System.err.println("Error: 6");
        if (Q.size() == 0) System.err.println("Error: lack Q");
        if (S == null) System.err.println("Error: lack S");
        if (G == null) System.err.println("Error: lack G");
        if (F == null) System.err.println("Error: lack F");
        if (q0 == null) System.err.println("Error: lack q0");
        if (B == null) System.err.println("Error: lack B");
        if (tapeNum == null) System.err.println("Error: lack N");
        int temp = 0;
        for (State s : Q.values())
        {
            temp += s.getDeltas().size();
        }
        if (temp == 0) System.err.println("Error: lack D");

    }
    private void createQ(String s, int lineno) {
        String[] res = Utils.SplitString(s);
        if (res == null) System.err.println("Error: " + lineno);
        else {
            for (String state : res) {
                State temp = new State(state);
                Q.put(state, temp);
            }
        }
    }

    private void createS(String s, int lineno) {
        S = new HashSet<>();
        String[] res = Utils.SplitString(s);
        if (res == null) System.err.println("Error: " + lineno);
        else Arrays.asList(res).forEach(s1 -> S.add(s1.charAt(0)));
    }

    private void createG(String s, int lineno) {
        G = new HashSet<>();
        String[] res = Utils.SplitString(s);
        if (res == null) System.err.println("Error: " + lineno);
        else Arrays.asList(res).forEach(s1 -> G.add(s1.charAt(0)));
    }

    private void createF(String s, int lineno) {
        F = new HashSet<>();
        String[] res = Utils.SplitString(s);
        if (res == null) System.err.println("Error: " + lineno);
        else F.addAll(Arrays.asList(res));
    }

    public State getInitState() {
        return Q.get(q0);
    }

    /**
     * TODO
     * 停止的两个条件 1. 到了终止态 2. 无路可走，halts
     *
     * @param q Z
     * @return
     */
    public boolean isStop(State q, String Z) {
        boolean judger1=F.contains(q.getQ());
        boolean judger2=q.getDelta(Z)==null;
        return judger1||judger2;
    }

    public boolean checkTape(Set<Character> tape) {
        tape.remove(B);
        if(!S.containsAll(tape)){
            System.err.println("Error: 1");
            return false;
        }
        return true;
    }

    public boolean checkTapeNum(int tapeNum) {
        return tapeNum==this.tapeNum;
    }

    public Character getB() {
        return B;
    }


    /**
     * TODO
     * 检查迁移函数是否符合要求
     * @param s
     * @param lineno
     */
    private void resolverTransitionFunction(String s, int lineno) {
        String[] arr=s.split(" ");
        if(arr[1].length()!=arr[2].length()){
            System.err.println("Error: "+lineno);
        }
        if(!Q.containsKey(arr[0]))
            System.err.println("Error: "+7);
        if(!Q.containsKey(arr[4]))
            System.err.println("Error: "+7);
        if((!G.containsAll(Utils.stringToCharSet(arr[1]))))
            System.err.println("Error: "+8);
        if((!G.containsAll(Utils.stringToCharSet(arr[2]))))
            System.err.println("Error: "+8);
        TransitionFunction function=new TransitionFunction(s,Q);
        if (Q.get(function.getSourceState().getQ()).containDelta(function)){
            TransitionFunction temp = Q.get(function.getSourceState().getQ()).getDelta(function.getInput());
            String originalOutput = temp.getOutput();
            String originalToState = temp.getDestinationState().getQ();
            String originalDirection = temp.getDirection();
            if (!originalOutput.equals(function.getOutput())
                    || !originalToState.equals(function.getDestinationState().getQ())
                    || !originalDirection.equals(function.getDirection())) {
                System.err.println("Error: 9");
            }

        }
        Q.get(function.getSourceState().getQ()).addTransitionFunction(function);
    }

    /**
     * TODO
     * is done in lab1 ~
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.SetToString("Q", Q.keySet()));
        stringBuilder.append(Utils.SetToString("S", S));
        stringBuilder.append(Utils.SetToString("G", G));
        stringBuilder.append(Utils.SetToString("F", F));
        stringBuilder.append("#q0 = ").append(q0).append(System.lineSeparator());
        stringBuilder.append("#B = ").append(B).append(System.lineSeparator());
        stringBuilder.append("#N = ").append(tapeNum).append(System.lineSeparator());
        for (State s : Q.values())
            s.getDeltas().forEach(transitionFunction -> stringBuilder.append(transitionFunction.toString()));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}
