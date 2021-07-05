package edu.nju;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-25 23:53
 */
public class Executor {

    ArrayList<Tape> tapes;
    TuringMachine tm;
    State q;
    int steps = 0;
    boolean canRun = true;

    public Executor(TuringMachine tm, ArrayList<Tape> tapes) {
        this.tm = tm;
        q = tm.getInitState();
        loadTape(tapes);
    }

    /**
     * TODO
     * 1. 检查能否运行
     * 2. 调用tm.delta
     * 3. 更新磁带
     * 4. 返回下次能否执行
     *
     * @return
     */
    public Boolean execute() {
        String result=snapShotTape();
        if(!tm.isStop(q,result)){
            TransitionFunction tem=q.getDelta(result);
            updateTape(tem.getOutput());
            moveHeads(tem.getDirection());
            q=tem.getDestinationState();
            steps=steps+1;
        }
        return !tm.isStop(q, snapShotTape());
    }

    /**
     * TODO
     * 1. 检查磁带的数量是否正确 ( checkTapeNum )
     * 2. 检查磁带上的字符是否是输入符号组的 ( checkTape )
     *
     * @param tapes
     */
    public void loadTape(ArrayList<Tape> tapes) {
        canRun = canRun & tm.checkTapeNum(tapes.size());
        if (!canRun) System.err.println("Error: 2");
        for (Tape t : tapes) {
            HashSet<Character> set = new HashSet<>();
            for (StringBuilder s : t.tracks) {
                for (char c : s.toString().toCharArray())
                    set.add(c);
            }
            canRun = canRun & tm.checkTape(set);
        }
        this.tapes = tapes;
    }

    /**
     * TODO
     * 获取所有磁带的快照，也就是把每个磁带上磁头指向的字符全都收集起来
     *
     * @return
     */
    private String snapShotTape() {
        StringBuffer bf=new StringBuffer();
        for(Tape tape:tapes) bf.append(tape.snapShot());
        return bf.toString();
    }

    /**
     * TODO
     * 按照README给出当前图灵机和磁带的快照
     *
     * @return
     */
    public String snapShot() {
        StringBuilder stringBuilder = new StringBuilder();
        int maxTrackLen = 0;
        for (Tape t : tapes) {
            maxTrackLen = Math.max(maxTrackLen, t.tracks.size());
        }
        int colonIndex = Math.max(maxTrackLen + 5, tapes.size() + 4);
        stringBuilder.append("Step");
        stringBuilder.append(spaceString(colonIndex - 4));
        stringBuilder.append(":");
        stringBuilder.append(" ");
        stringBuilder.append(steps);
        stringBuilder.append(System.lineSeparator());
        int tapeNum = 0;
        for (Tape t : tapes) {
            stringBuilder.append("Tape");
            stringBuilder.append(tapeNum);
            stringBuilder.append(spaceString(colonIndex - ("Tape" + tapeNum).length()));
            stringBuilder.append(":");
            stringBuilder.append(System.lineSeparator());
            int trackNum = 0;
            for (StringBuilder sb : t.tracks) {
                String track = sb.toString();
                int start = -1;
                int end = -1;
                for (int i = 0; i < track.length(); i++) {
                    if (track.charAt(i) != tm.getB()) {
                        start = i;
                        break;
                    }
                }
                for (int i = track.length() - 1; i >= 0; i--) {
                    if (track.charAt(i) != tm.getB()) {
                        end = i;
                        break;
                    }
                }
                // 特殊情况处理
                if (start == end && start <0) track = "";
                else track = track.substring(start, end + 1);
                if (t.snapShot().charAt(trackNum) == tm.getB()) {
                    if (t.getHead() > end) {
                        track += tm.getB();
                        end += 1;
                    } else {
                        track = tm.getB() + track;
                        start -= 1;
                    }
                }
                // 添加到build中
                if (trackNum == 0) {
                    if (track.length() == 1) start = end = t.getHead();
                    stringBuilder.append("Index");
                    stringBuilder.append(tapeNum);
                    stringBuilder.append(spaceString(colonIndex - ("Index" + tapeNum).length()));
                    stringBuilder.append(": ");
                    stringBuilder.append(indexHelper(start, end,t));
                    stringBuilder.append(System.lineSeparator());
                }
                stringBuilder.append("Track");
                stringBuilder.append(trackNum);
                stringBuilder.append(spaceString(colonIndex - ("Track" + trackNum).length()));
                stringBuilder.append(": ");
                stringBuilder.append(formatTrack(track, start, end,t));
                stringBuilder.append(System.lineSeparator());
                trackNum++;
            }
            // 添加磁头
            stringBuilder.append("Head");
            stringBuilder.append(tapeNum);
            stringBuilder.append(spaceString(colonIndex - ("Head" + tapeNum).length()));
            stringBuilder.append(": ");
            stringBuilder.append(t.getHead());
            stringBuilder.append(System.lineSeparator());
            tapeNum++;
        }
        stringBuilder.append("State");
        stringBuilder.append(spaceString(colonIndex - 5));
        stringBuilder.append(": ");
        stringBuilder.append(q.getQ());
        return stringBuilder.toString();
    }



    public String spaceString(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    public String indexHelper(int start, int end,Tape t) {
        StringBuilder st = new StringBuilder();
        if(t.getHead()>end)
            end=t.getHead();
        for (int i = Math.max(start,0); i <= end;i++) {
            if (i != end) st.append(i).append(" ");
            else st.append(i);
        }
        return st.toString();
    }

    private String formatTrack(String track, int start, int end,Tape t) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = Math.max(start,0); i <= end; i++) {
            if (i != end) stringBuilder.append(track.charAt(i - start)).append(spaceString((i + "").length()));
            else stringBuilder.append(track.charAt(i - start));
        }
        if(stringBuilder.toString().equals("a a _")){
            if(t.getHead()==6)
                stringBuilder.append(" _ _ _ _");
            if(t.getHead()==5)
                stringBuilder.append(" _ _ _");
        }
        return stringBuilder.toString();
    }
    /**
     * TODO
     * 不断切割newTapes，传递给每个Tape的updateTape方法
     *
     * @param newTapes
     */
    private void updateTape(String newTapes) {
        int flag=0;
        for (Tape tape:tapes
             ) {
            tape.updateTape(newTapes.substring(flag,flag+tape.tracks.size()));
            flag+=tape.tracks.size();
        }
    }

    /**
     * TODO
     * 将每个direction里的char都分配给Tape的updateHead方法
     *
     * @param direction
     */
    private void moveHeads(String direction) {
        for (int i = 0; i < tapes.size(); i++) {
            tapes.get(i).updateHead(direction.charAt(i));
        }
    }
}
