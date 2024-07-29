package pl.snon_ss.sFlightLimit.Listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import pl.snon_ss.sFlightLimit.Main;
import pl.snon_ss.sFlightLimit.SendMessages.SendMessageAdminOnlineMinFly;
import pl.snon_ss.sFlightLimit.SendMessages.SendMessageAdminOnlineMinFlyElytra;
import pl.snon_ss.sFlightLimit.SendMessages.SendMessagePlayerMinFly;
import pl.snon_ss.sFlightLimit.SendMessages.SendMessagePlayerMinFlyElytra;
import pl.snon_ss.sFlightLimit.Variables;
public class onPlayerMoveEventMin implements Listener {
    public Main plugin;
    public onPlayerMoveEventMin(Main plugin) {
        this.plugin = plugin;
        Variables.config = plugin.getConfig();
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        World world = to.getWorld();
        Block fromBlock = event.getFrom().getBlock();
        Block toBlock = (event.getTo()).getBlock();
        if (fromBlock.equals(toBlock)) {
            return;
        }
        if (Variables.config.getBoolean("Worlds." + world.getName() + ".Enabled")) {
            if (Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Enabled")) {
                if (player.isFlying() && to != null) {
                    if (!player.hasPermission(Variables.config.getString("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Permission")) || !Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Permission-Enabled")) {
                        if (Variables.config.contains("Worlds." + world.getName() + ".Min-Height-Fly-Limit")) {
                            int minFlyLimit = Variables.config.getInt("Worlds." + world.getName() + ".Min-Height-Fly-Limit");
                            if (to.getY() < minFlyLimit) {
                                player.teleport(to.clone().subtract(0, to.getY() - minFlyLimit, 0));
                                if (Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Reset-Enabled")) {
                                    player.setFlying(false);
                                    if (player.getGameMode() == GameMode.SPECTATOR) {
                                        player.setAllowFlight(false);
                                    }
                                }
                                if (Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Reset-Sound-Enabled")) {
                                    Sound soundType = Sound.valueOf(Variables.config.getString("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Reset-Sound"));
                                    float volume = (float) Variables.config.getDouble("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Reset-Sound-Volume");
                                    float pitch = (float) Variables.config.getDouble("Worlds." + world.getName() + ".Min-Height-Fly-Limit-Reset-Sound-Pitch");
                                    player.playSound(player.getLocation(), soundType, volume, pitch);
                                }
                                //
                                SendMessageAdminOnlineMinFly sendMessageAdminOnlineMinFly = new SendMessageAdminOnlineMinFly(plugin);
                                sendMessageAdminOnlineMinFly.sendMessageAdminOnlineMinFly(world, player, minFlyLimit);
                                //
                                SendMessagePlayerMinFly sendMessagePlayerMinFly = new SendMessagePlayerMinFly(plugin);
                                sendMessagePlayerMinFly.sendMessagePlayerMinFly(player, world, minFlyLimit);
                                //
                            }
                        }
                    }
                }
            }
            if (Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit-Enabled")) {
                if (player.isGliding() && to != null) {
                    if (!player.hasPermission(Variables.config.getString("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit-Permission")) || !Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit-Permission-Enabled")) {
                        ItemStack chestplate = player.getInventory().getChestplate();
                        if (chestplate != null && chestplate.getType() == Material.ELYTRA) {
                            if (Variables.config.contains("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit")) {
                                int minFlyElytraLimit = Variables.config.getInt("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit");
                                if (to.getY() < minFlyElytraLimit) {
                                    player.teleport(to.clone().subtract(0, to.getY() - minFlyElytraLimit, 0));
                                    if (Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit-Reset-Enabled")) {
                                        player.setGliding(false);
                                        if (player.getGameMode() == GameMode.SPECTATOR) {
                                            player.setAllowFlight(false);
                                        }
                                    }
                                    if (Variables.config.getBoolean("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit-Reset-Sound-Enabled")) {
                                        Sound soundType = Sound.valueOf(Variables.config.getString("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit-Reset-Sound"));
                                        float volume = (float) Variables.config.getDouble("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit-Reset-Sound-Volume");
                                        float pitch = (float) Variables.config.getDouble("Worlds." + world.getName() + ".Min-Height-Fly-Elytra-Limit-Reset-Sound-Pitch");
                                        player.playSound(player.getLocation(), soundType, volume, pitch);
                                    }
                                    //
                                    SendMessageAdminOnlineMinFlyElytra sendMessageAdminOnlineMinFlyElytra = new SendMessageAdminOnlineMinFlyElytra(plugin);
                                    sendMessageAdminOnlineMinFlyElytra.sendMessageAdminOnlineMinFlyElytra(world, player, minFlyElytraLimit);
                                    //
                                    SendMessagePlayerMinFlyElytra sendMessagePlayerMinFlyElytra = new SendMessagePlayerMinFlyElytra(plugin);
                                    sendMessagePlayerMinFlyElytra.sendMessagePlayerMinFlyElytra(player, world, minFlyElytraLimit);
                                    //
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}