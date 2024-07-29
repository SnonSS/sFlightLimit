package pl.snon_ss.sFlightLimit.SendMessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.snon_ss.sFlightLimit.Files.LoadMessages;
import pl.snon_ss.sFlightLimit.Main;
import pl.snon_ss.sFlightLimit.TranslateRGBColors;
import pl.snon_ss.sFlightLimit.Variables;
import java.util.List;
public class SendMessageAdminOnlineMaxFlyElytra {
    public Main plugin;
    public SendMessageAdminOnlineMaxFlyElytra(Main plugin) {
        this.plugin = plugin;
        Variables.config = plugin.getConfig();
    }
    public void sendMessageAdminOnlineMaxFlyElytra(World world, Player player, int maxFlyElytraLimit) {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Message-Admin-Enabled")) {
                long currentTimeAdmin = System.currentTimeMillis() / 1000;
                if (currentTimeAdmin - Variables.getLastMessageTimeAdmin(onlinePlayers) >= Variables.config.getInt("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Message-Admin-Limit")) {
                    Variables.setLastMessageTimeAdmin(onlinePlayers, currentTimeAdmin);
                    List<String> formattedMessages = LoadMessages.maxHeightFlyElytraLimitAdminMessages.get(world.getName());
                    for (String lines : formattedMessages) {
                        lines = lines.replace("%player%", player.getName());
                        lines = lines.replace("%limit%", String.valueOf(maxFlyElytraLimit));
                        lines = lines.replace("%world%", world.getName());
                        String formattedLines = TranslateRGBColors.translateRGBColors(ChatColor.translateAlternateColorCodes('&', lines));
                        if (onlinePlayers.hasPermission(Variables.config.getString("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Message-Admin-Permission"))) {
                            onlinePlayers.sendMessage(formattedLines);
                        }
                    }
                }
            }
        }
    }
}