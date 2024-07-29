package pl.snon_ss.sFlightLimit.Listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import pl.snon_ss.sFlightLimit.Main;
import pl.snon_ss.sFlightLimit.SendMessages.SendMessageAdminOnlineMaxFly;
import pl.snon_ss.sFlightLimit.SendMessages.SendMessageAdminOnlineMaxFlyElytra;
import pl.snon_ss.sFlightLimit.SendMessages.SendMessagePlayerMaxFly;
import pl.snon_ss.sFlightLimit.SendMessages.SendMessagePlayerMaxFlyElytra;
import pl.snon_ss.sFlightLimit.Variables;
public class onPlayerMoveEventMax implements Listener {
    public Main plugin;
    public onPlayerMoveEventMax(Main plugin) {
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
            if (Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Limit-Enabled")) {
                if (player.isFlying() && to != null) {
                    if (!player.hasPermission(Variables.config.getString("Worlds." + world.getName() + ".Max-Height-Fly-Limit-Permission")) || !Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Limit-Permission-Enabled")) {
                        if (Variables.config.contains("Worlds." + world.getName() + ".Max-Height-Fly-Limit")) {
                            int maxFlyLimit = Variables.config.getInt("Worlds." + world.getName() + ".Max-Height-Fly-Limit");
                            if (to.getY() > maxFlyLimit) {
                                player.teleport(to.clone().subtract(0, to.getY() - maxFlyLimit, 0));
                                if (Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Limit-Reset-Enabled")) {
                                    player.setFlying(false);
                                    if (player.getGameMode() == GameMode.SPECTATOR) {
                                        player.setAllowFlight(false);
                                    }
                                }
                                if (Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Limit-Reset-Sound-Enabled")) {
                                    Sound soundType = Sound.valueOf(Variables.config.getString("Worlds." + world.getName() + ".Max-Height-Fly-Limit-Reset-Sound"));
                                    float volume = (float) Variables.config.getDouble("Worlds." + world.getName() + ".Max-Height-Fly-Limit-Reset-Sound-Volume");
                                    float pitch = (float) Variables.config.getDouble("Worlds." + world.getName() + ".Max-Height-Fly-Limit-Reset-Sound-Pitch");
                                    player.playSound(player.getLocation(), soundType, volume, pitch);
                                }
                                //
                                SendMessageAdminOnlineMaxFly sendMessageAdminOnlineMaxFly = new SendMessageAdminOnlineMaxFly(plugin);
                                sendMessageAdminOnlineMaxFly.sendMessageAdminOnlineMaxFly(world, player, maxFlyLimit);
                                //
                                SendMessagePlayerMaxFly sendMessagePlayerMaxFly = new SendMessagePlayerMaxFly(plugin);
                                sendMessagePlayerMaxFly.sendMessagePlayerMaxFly(player, world, maxFlyLimit);
                                //
                            }
                        }
                    }
                }
            }
            if (Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Enabled")) {
                if (player.isGliding() && to != null) {
                    if (!player.hasPermission(Variables.config.getString("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Permission")) || !Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Permission-Enabled")) {
                        ItemStack chestplate = player.getInventory().getChestplate();
                        if (chestplate != null && chestplate.getType() == Material.ELYTRA) {
                            if (Variables.config.contains("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit")) {
                                int maxFlyElytraLimit = Variables.config.getInt("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit");
                                if (to.getY() > maxFlyElytraLimit) {
                                    player.teleport(to.clone().subtract(0, to.getY() - maxFlyElytraLimit, 0));
                                    if (Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Reset-Enabled")) {
                                        player.setGliding(false);
                                        if (player.getGameMode() == GameMode.SPECTATOR) {
                                            player.setAllowFlight(false);
                                        }
                                    }
                                    if (Variables.config.getBoolean("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Reset-Sound-Enabled")) {
                                        Sound soundType = Sound.valueOf(Variables.config.getString("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Reset-Sound"));
                                        float volume = (float) Variables.config.getDouble("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Reset-Sound-Volume");
                                        float pitch = (float) Variables.config.getDouble("Worlds." + world.getName() + ".Max-Height-Fly-Elytra-Limit-Reset-Sound-Pitch");
                                        player.playSound(player.getLocation(), soundType, volume, pitch);
                                    }
                                    //
                                    SendMessageAdminOnlineMaxFlyElytra sendMessageAdminOnlineMaxFlyElytra = new SendMessageAdminOnlineMaxFlyElytra(plugin);
                                    sendMessageAdminOnlineMaxFlyElytra.sendMessageAdminOnlineMaxFlyElytra(world, player, maxFlyElytraLimit);
                                    //
                                    SendMessagePlayerMaxFlyElytra sendMessagePlayerMaxFlyElytra = new SendMessagePlayerMaxFlyElytra(plugin);
                                    sendMessagePlayerMaxFlyElytra.sendMessagePlayerMaxFlyElytra(player, world, maxFlyElytraLimit);
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