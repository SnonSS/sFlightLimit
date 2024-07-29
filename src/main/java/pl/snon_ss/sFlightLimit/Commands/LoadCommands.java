package pl.snon_ss.sFlightLimit.Commands;

import pl.snon_ss.sFlightLimit.Main;
import java.util.Objects;
public class LoadCommands {
    public Main plugin;
    public LoadCommands(Main plugin) {
        this.plugin = plugin;
    }
    public void loadCommands() {
        Objects.requireNonNull(plugin.getCommand("sflightlimitreload")).setExecutor(new CommandReload(plugin));
    }
}