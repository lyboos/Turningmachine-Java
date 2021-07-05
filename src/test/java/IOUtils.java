import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-23 19:56
 */
public class IOUtils {
    public static String SetToString(String name, Set<?> set) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#").append(name).append(" = {");
        set.forEach(s -> stringBuilder.append(s).append(","));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("}").append(System.lineSeparator());
        return stringBuilder.toString();
    }
    public static String[] SplitString(String s) {
        int i = 0;
        for (i = 0; i < s.length(); i++) if (s.charAt(i) == '{') break;
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


}
