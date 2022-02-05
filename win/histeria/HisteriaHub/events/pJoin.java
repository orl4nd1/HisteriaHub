package win.histeria.HisteriaHub.events;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.event.block.*;
import win.histeria.HisteriaHub.inv.*;

public class pJoin implements Listener
{
    FileConfiguration config;
    private Main plugin;
    private static Main instance;
    String prefix;
    
    public pJoin() {
        this.config = Main.getInstance().getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.prefix = this.config.getString("PREFIX").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", "");
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.getInventory().clear();
        final Location spawn = new Location(player.getWorld(), (double)this.config.getInt("X"), (double)this.config.getInt("Y"), (double)this.config.getInt("Z"));
        player.teleport(spawn);
        for (int a = 0, i = 41; a < i; ++a) {
            player.sendMessage("");
        }
        player.sendMessage(this.config.getString("MOTD_LINE").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        player.sendMessage(this.config.getString("MOTD_WELCOME").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        player.sendMessage(this.config.getString("MOTD_WEBSITE").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        player.sendMessage(this.config.getString("MOTD_STORE").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        player.sendMessage(this.config.getString("MOTD_TEAMSPEAK").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        player.sendMessage(this.config.getString("MOTD_CONNECTED").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        player.sendMessage(this.config.getString("MOTD_LINE").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        player.setGameMode(GameMode.SURVIVAL);
        player.setFoodLevel(20);
        if (!player.isFlying()) {
            player.setAllowFlight(true);
        }
        event.setJoinMessage(this.config.getString("ON_JOIN").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("%player%", player.getName()));
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Action action = event.getAction();
        final Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals((Object)Material.getMaterial(this.config.getString("SERVER_SELECTOR_ITEM"))) && (action.equals((Object)Action.RIGHT_CLICK_AIR) || action.equals((Object)Action.RIGHT_CLICK_BLOCK))) {
            final Inventory InvClass = new Inventory();
            player.openInventory(InvClass.newInventory());
        }
    }
    
    static {
        pJoin.instance = Main.getInstance();
    }
}
