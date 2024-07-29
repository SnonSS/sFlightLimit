package pl.snon_ss.sFlightLimit.Files;

import org.bukkit.World;
import pl.snon_ss.sFlightLimit.Main;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LoadMessages {
    public static Main plugin;
    public static Map<String, List<String>> maxHeightFlyLimitMessages = new HashMap<>();
    public static Map<String, List<String>> maxHeightFlyElytraLimitMessages = new HashMap<>();
    public static Map<String, List<String>> minHeightFlyLimitMessages = new HashMap<>();
    public static Map<String, List<String>> minHeightFlyElytraLimitMessages = new HashMap<>();
    public static Map<String, List<String>> maxHeightFlyLimitAdminMessages = new HashMap<>();
    public static Map<String, List<String>> maxHeightFlyElytraLimitAdminMessages = new HashMap<>();
    public static Map<String, List<String>> minHeightFlyLimitAdminMessages = new HashMap<>();
    public static Map<String, List<String>> minHeightFlyElytraLimitAdminMessages = new HashMap<>();
    public static List<String> noPermissionReload;
    public static List<String> filesReloaded;
    public LoadMessages(Main plugin) {
        this.plugin = plugin;
    }
    public static void loadMessages() {
        for (World world : plugin.getServer().getWorlds()) {
            String worldName = world.getName();
            List<String> maxHeightFlyLimitMessage = plugin.getConfig().getStringList("Worlds." + worldName + ".Max-Height-Fly-Limit-Message");
            List<String> maxHeightFlyElytraLimitMessage = plugin.getConfig().getStringList("Worlds." + worldName + ".Max-Height-Fly-Elytra-Limit-Message");
            maxHeightFlyLimitMessages.put(worldName, maxHeightFlyLimitMessage);
            maxHeightFlyElytraLimitMessages.put(worldName, maxHeightFlyElytraLimitMessage);
            //
            List<String> minHeightFlyLimitMessage = plugin.getConfig().getStringList("Worlds." + worldName + ".Min-Height-Fly-Limit-Message");
            List<String> minHeightFlyElytraLimitMessage = plugin.getConfig().getStringList("Worlds." + worldName + ".Min-Height-Fly-Elytra-Limit-Message");
            minHeightFlyLimitMessages.put(worldName, minHeightFlyLimitMessage);
            minHeightFlyElytraLimitMessages.put(worldName, minHeightFlyElytraLimitMessage);
            //
            List<String> maxHeightFlyLimitAdminMessage = plugin.getConfig().getStringList("Worlds." + worldName + ".Max-Height-Fly-Limit-Message-Admin");
            List<String> maxHeightFlyElytraLimitAdminMessage = plugin.getConfig().getStringList("Worlds." + worldName + ".Max-Height-Fly-Elytra-Limit-Message-Admin");
            maxHeightFlyLimitAdminMessages.put(worldName, maxHeightFlyLimitAdminMessage);
            maxHeightFlyElytraLimitAdminMessages.put(worldName, maxHeightFlyElytraLimitAdminMessage);
            //
            List<String> minHeightFlyLimitAdminMessage = plugin.getConfig().getStringList("Worlds." + worldName + ".Min-Height-Fly-Limit-Message-Admin");
            List<String> minHeightFlyElytraLimitAdminMessage = plugin.getConfig().getStringList("Worlds." + worldName + ".Min-Height-Fly-Elytra-Limit-Message-Admin");
            minHeightFlyLimitAdminMessages.put(worldName, minHeightFlyLimitAdminMessage);
            minHeightFlyElytraLimitAdminMessages.put(worldName, minHeightFlyElytraLimitAdminMessage);
            //
            noPermissionReload = plugin.getConfig().getStringList("No-Permission-Reload");
            filesReloaded = plugin.getConfig().getStringList("Files-Reloaded");
        }
    }
}