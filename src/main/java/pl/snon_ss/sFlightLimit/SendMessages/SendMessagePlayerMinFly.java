package pl.snon_ss.sFlightLimit.SendMessages;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.snon_ss.sFlightLimit.Files.LoadMessages;
import pl.snon_ss.sFlightLimit.Main;
import pl.snon_ss.sFlightLimit.TranslateRGBColors;
import pl.snon_ss.sFlightLimit.Variables;
import java.util.List;
public class SendMessagePlayerMinFly {
    public Main plugin;
    public SendMessagePlayerMinFly(Main plugin) {
        this.plugin = plugin;
        Variables.config = plugin.getConfig();
    }
    public void sendMessagePlayerMinFly(Player player, World world, int minFlyLimit) {
        long currentTime = System.currentTimeMillis() / 1000;
        if (currentTime - Variables.getLastMessageTime(player) >= Variables.config.getInt("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Message-Limit")) {
            Variables.setLastMessageTime(player, currentTime);
            List<String> formattedMessage = LoadMessages.minHeightFlyLimitMessages.get(world.getName());
            for (String line : formattedMessage) {
                line = line.replace("%limit%", String.valueOf(minFlyLimit));
                line = line.replace("%world%", world.getName());
                String formattedLine = TranslateRGBColors.translateRGBColors(ChatColor.translateAlternateColorCodes('&', line));
                if (Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Message-Enabled")) {
                    player.sendMessage(formattedLine);
                }
            }
        }
    }
}