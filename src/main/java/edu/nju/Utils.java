package edu.nju;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-23 19:56
 */
public class Utils {
    public static String SetToString(String name, Set<?> set) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#").append(name).append(" = {");
        set.forEach(s -> stringBuilder.append(s).append(","));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("}").append(System.lineSeparator());
        return stringBuilder.toString();
    }

    //split with { }
    public static String[] SplitString(String s) {
        int i;
        for (i = 0; i < s.length(); i++) if (s.charAt(i) == '}') break;
        if (i == s.length()) return null;
        for (i = 0; i < s.length(); i++) if (s.charAt(i) == '{') break;
        if (i == s.length()) return null;
        s = s.substring(i);
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

    public static boolean isSubSet(Set<?> small, Set<?> big) {
        boolean ret = false;
        for(Object o : small) {
            ret |= !big.contains(o);
        }
        return !ret;
    }

    public static Set<Character> stringToCharSet(String s) {
        Set<Character> set = new HashSet<>();
        for(char c : s.toCharArray()) set.add(c);
        return set;
    }

}