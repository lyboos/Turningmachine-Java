package edu.nju;

import java.util.ArrayList;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-23 19:37
 */
public class Tape {

    ArrayList<StringBuilder> tracks;
    private final char B;
    private int head;

    public int getHead() {
        return head;
    }

    public Tape(ArrayList<StringBuilder> tracks, int head, char B) {
        this.tracks = tracks;
        this.head = head;
        this.B = B;
    }

    public String snapShot() {
        StringBuilder builder=new StringBuilder();
        for (StringBuilder sb:tracks
             ) {
            builder.append(sb.charAt(head));
        }
        return builder.toString();
    }

    public void updateHead(char c) {
        if(c=='l')
            head--;
        if(c=='r')
            head++;
        if(head < 0) {
            for(StringBuilder s : tracks) {
                s.reverse();
                s.append(B);
                s.reverse();
            }
            head = 0;
        }
        else {
            for(StringBuilder s : tracks) {
                if(head == s.length()) s.append(B);
            }
        }
    }


    public void updateTape(String newTape) {
        for (int i = 0; i < tracks.size(); i++) {
            tracks.get(i).replace(head,head+1, newTape.charAt(i)+"");
        }
    }


}
