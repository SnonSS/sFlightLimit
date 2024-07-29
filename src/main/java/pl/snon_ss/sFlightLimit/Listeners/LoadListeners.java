package pl.snon_ss.sFlightLimit.Listeners;

import pl.snon_ss.sFlightLimit.Main;
public class LoadListeners {
    public Main plugin;
    public LoadListeners(Main plugin) {
        this.plugin = plugin;
    }
    public void loadListeners() {
        plugin.getServer().getPluginManager().registerEvents(new onPlayerMoveEventMax(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new onPlayerMoveEventMin(plugin), plugin);
    }
}