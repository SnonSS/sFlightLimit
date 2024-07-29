package pl.snon_ss.sFlightLimit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class Variables {
    public static void updateConfig(FileConfiguration newConfig) {
        config = newConfig;
    }
    public static void setLastMessageTime(Player player, long time) {
        lastMessageTimes.put(player.getUniqueId(), time);
    }
    public static long getLastMessageTime(Player player) {
        return lastMessageTimes.getOrDefault(player.getUniqueId(), 0L);
    }
    public static Map<UUID, Long> lastMessageTimes = new HashMap<>();
    public static void setLastMessageTimeAdmin(Player onlinePlayers, long time) {
        lastMessageTimesAdmin.put(onlinePlayers.getUniqueId(), time);
    }
    public static long getLastMessageTimeAdmin(Player onlinePlayers) {
        return lastMessageTimesAdmin.getOrDefault(onlinePlayers.getUniqueId(), 0L);
    }
    public static Map<UUID, Long> lastMessageTimesAdmin = new HashMap<>();
    public static FileConfiguration config;
}