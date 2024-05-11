package lightwolf.lwspit;
import org.bukkit.ChatColor;

public class Coloriser {
    public static String coloriser(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String transform(String input) {
        return input.replaceAll("#([0-9a-f])([0-9a-f])([0-9a-f])([0-9a-f])([0-9a-f])([0-9a-f])", "&x&$1&$2&$3&$4&$5&$6");
    }
}