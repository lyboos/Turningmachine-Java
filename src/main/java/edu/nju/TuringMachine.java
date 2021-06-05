package edu.nju;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-23 16:15
 */
public class TuringMachine {

    // 状态集合
    Set<String> Q;
    // 输入符号集
    Set<Character> S;
    // 磁带符号集
    Set<Character> G;
    // 初始状态
    String q;
    // 终止状态集
    Set<String> F;
    // 空格符号
    Character B;
    // 磁带数
    Integer tapeNum;
    // 迁移函数集
    Set<TransitionFunction> Delta;

    public TuringMachine(Set<String> Q, Set<Character> S, Set<Character> G, String q, Set<String> F, char B, int tapeNum, Set<TransitionFunction> Delta) {
        this.Q = Q;
        this.S = S;
        this.G = G;
        this.q = q;
        this.F = F;
        this.B = B;
        this.tapeNum = tapeNum;
        this.Delta = Delta;
    }

    //TODO
    public TuringMachine(String tm) {
        String[] strarr=tm.split(System.lineSeparator());
        Delta=new HashSet<>();
        int i=0;
        for (String s:strarr) {
            i++;
            s = s.trim();
            String[] result;
            if(s.length()!=0 && !IOU.IsComment(s))
            switch (s.substring(0, 2)) {
                case "#Q":
                    Q = new HashSet<>();
                    result = IOU.SplitString(s);
                    if (result == null) {
                        System.err.println("Error: " + i);
                    } else
                        Q.addAll(Arrays.asList(result));
                    break;
                case "#S":
                    S = new HashSet<>();
                    result = IOU.SplitString(s);
                    if (result == null) {
                        System.err.println("Error: " + i);
                    } else
                        Arrays.asList(result).forEach(word -> S.add(word.charAt(0)));
                    break;
                case "#G":
                    G = new HashSet<>();
                    result = IOU.SplitString(s);
                    if (result == null) {
                        System.err.println("Error: " + i);
                    } else
                        Arrays.asList(result).forEach(word -> G.add(word.charAt(0)));
                    break;
                case "#F":
                    F = new HashSet<>();
                    result = IOU.SplitString(s);
                    if (result == null) {
                        System.err.println("Error: " + i);
                    } else
                        Arrays.asList(result).forEach(word -> F.add(word));
                    break;
                case "#B":
                    B = s.charAt(5);
                    break;
                case "#N":
                    tapeNum = (Integer.parseInt(s.substring(5)));
                    break;
                case "#D":
                    TransitionFunction tem = new TransitionFunction(s.substring(3));
                    System.out.println(s.substring(3));
                    Delta.add(tem);
                    break;
                case "#q":
                    if (!(s.substring(2, 6).equals("0 = "))) {
                        System.err.println("Error: "+ i);
                        break;
                    }q = s.substring(6);
                    break;
                default:
                    System.err.println("Error: " + i);
                    break;
            }
        }
            if(Q==null) System.err.println("Error: lack Q");
            if(S==null) System.err.println("Error: lack S");
            if(G==null) System.err.println("Error: lack G");
            if(q==null) System.err.println("Error: lack q0");
            if(F==null) System.err.println("Error: lack F");
            if(B==null) System.err.println("Error: lack B");
            if(tapeNum==null) System.err.println("Error: lack N");
            if(Delta==null) System.err.println("Error: lack Delta");
            if(Delta.size()==0) System.err.println("Error: lack D");
    }

    //TODO
    @Override
    public String toString() {
        StringBuilder strbuilder=new StringBuilder();
        strbuilder.append(IOU.SetToString("Q",Q));
        strbuilder.append(IOU.SetToString("S",S));
        strbuilder.append(IOU.SetToString("G",G));
        strbuilder.append(IOU.SetToString("F",F));
        strbuilder.append("#q0 = ").append(q).append(System.lineSeparator());
        strbuilder.append("#B = ").append(B).append(System.lineSeparator());
        strbuilder.append("#N = ").append(tapeNum).append(System.lineSeparator());
        Iterator<TransitionFunction> it=Delta.iterator();
        while (it.hasNext()){
            TransitionFunction tem=it.next();
            strbuilder.append("#D ").append(tem.getFromState()).append(" ").append(tem.getInput()).append(" ").append(tem.getOutput()).append(" ").append(tem.getDirection()).append(" ").append(tem.getToState());
            strbuilder.append(System.lineSeparator());
            System.out.println(strbuilder.toString());
        }
        if(System.lineSeparator().length()==2)
        strbuilder.delete(strbuilder.length()-2,strbuilder.length());
        else
            strbuilder.deleteCharAt(strbuilder.length()-1);
        return strbuilder.toString();
    }

}
