package pl.snon_ss.sFlightLimit.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.snon_ss.sFlightLimit.Files.LoadMessages;
import pl.snon_ss.sFlightLimit.Main;
import pl.snon_ss.sFlightLimit.TranslateRGBColors;
import pl.snon_ss.sFlightLimit.Variables;
import java.util.List;
public class CommandReload implements CommandExecutor {
    public Main plugin;
    public CommandReload(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("sflightlimit.reload")) {
            List<String> formattedMessage = LoadMessages.noPermissionReload;
            for (String line : formattedMessage) {
                String formattedLine = TranslateRGBColors.translateRGBColors(ChatColor.translateAlternateColorCodes('&', line));
                sender.sendMessage(formattedLine);
            }
            return true;
        }
        plugin.reloadConfig();
        Variables.updateConfig(plugin.getConfig());
        //
        LoadMessages loadMessages = new LoadMessages(plugin);
        loadMessages.loadMessages();
        //
        List<String> formattedMessage = LoadMessages.filesReloaded;
        for (String line : formattedMessage) {
            String formattedLine = TranslateRGBColors.translateRGBColors(ChatColor.translateAlternateColorCodes('&', line));
            sender.sendMessage(formattedLine);
        }
        return false;
    }
}