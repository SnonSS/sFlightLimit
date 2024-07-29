package pl.snon_ss.sFlightLimit;

import org.bukkit.ChatColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TranslateRGBColors {
    public static String translateRGBColors(String message) {
        Pattern pattern = Pattern.compile("&#([a-fA-F0-9]{6})");
        Matcher matcher = pattern.matcher(message);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String colorString = matcher.group(1);
            String replacement = ChatColor.COLOR_CHAR + "x" +
                    ChatColor.COLOR_CHAR + colorString.charAt(0) +
                    ChatColor.COLOR_CHAR + colorString.charAt(1) +
                    ChatColor.COLOR_CHAR + colorString.charAt(2) +
                    ChatColor.COLOR_CHAR + colorString.charAt(3) +
                    ChatColor.COLOR_CHAR + colorString.charAt(4) +
                    ChatColor.COLOR_CHAR + colorString.charAt(5);
            matcher.appendReplacement(stringBuffer, replacement);
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}