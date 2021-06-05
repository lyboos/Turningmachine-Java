package edu.nju;

import java.util.Set;

public class IOU {
        public static String SetToString(String name, Set<?> set) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("#").append(name).append(" = {");
            set.forEach(s -> stringBuilder.append(s).append(","));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("}"+System.lineSeparator());
            return stringBuilder.toString();
        }
    public static String[] SplitString(String s) {
        int i = 0;
        if(s.charAt(s.length()-1)!='}')
            return null;
        for (i = 0; i < s.length(); i++) if (s.charAt(i) == '{') break;
        s = s.substring(i);
        if(s.length()<=1)
            return null;
        String[] var1 = s.split(",");
        var1[0] = var1[0].substring(1);
        var1[var1.length - 1] = var1[var1.length - 1].substring(0, var1[var1.length - 1].length() - 1);
        for (i = 0; i < var1.length; i++) {
            var1[i] = var1[i].trim();
        }
        return var1;
    }
        public static boolean IsComment(String s) {
            return s.charAt(0) == ';';
        }

        public static void main(String[] args) {
            String[] s = SplitString("#F = {12312, 12,312,3123, 21,3,12,3}");
            for (String s1 : s) System.out.println(s1);
        }

    }

