package pl.snon_ss.sFlightLimit.SendMessages;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.snon_ss.sFlightLimit.Files.LoadMessages;
import pl.snon_ss.sFlightLimit.Main;
import pl.snon_ss.sFlightLimit.TranslateRGBColors;
import pl.snon_ss.sFlightLimit.Variables;
import java.util.List;
public class SendMessagePlayerMaxFlyElytra {
    public Main plugin;
    public SendMessagePlayerMaxFlyElytra(Main plugin) {
        this.plugin = plugin;
        Variables.config = plugin.getConfig();
    }
    public void sendMessagePlayerMaxFlyElytra(Player player, World world, int maxFlyElytraLimit) {
        long currentTime = System.currentTimeMillis() / 1000;
        if (currentTime - Variables.getLastMessageTime(player) >= Variables.config.getInt("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Message-Limit")) {
            Variables.setLastMessageTime(player, currentTime);
            List<String> formattedMessage = LoadMessages.maxHeightFlyElytraLimitMessages.get(world.getName());
            for (String line : formattedMessage) {
                line = line.replace("%limit%", String.valueOf(maxFlyElytraLimit));
                line = line.replace("%world%", world.getName());
                String formattedLine = TranslateRGBColors.translateRGBColors(ChatColor.translateAlternateColorCodes('&', line));
                if (Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Message-Enabled")) {
                    player.sendMessage(formattedLine);
                }
            }
        }
    }
}