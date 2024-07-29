package pl.snon_ss.sFlightLimit;

import org.bukkit.plugin.java.JavaPlugin;
import pl.snon_ss.sFlightLimit.Commands.LoadCommands;
import pl.snon_ss.sFlightLimit.Files.LoadMessages;
import pl.snon_ss.sFlightLimit.Listeners.LoadListeners;
public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        LoadCommands loadCommands = new LoadCommands(this);
        loadCommands.loadCommands();

        LoadListeners loadListeners = new LoadListeners(this);
        loadListeners.loadListeners();

        LoadMessages loadMessages = new LoadMessages(this);
        loadMessages.loadMessages();
    }
}