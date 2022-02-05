package win.histeria.HisteriaHub.events;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class playerpickup implements Listener
{
    FileConfiguration config;
    private Main plugin;
    private static Main instance;
    String prefix;
    
    public playerpickup() {
        this.config = Main.getInstance().getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.prefix = this.config.getString("PREFIX").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", "");
    }
    
    @EventHandler
    public void onPickup(final PlayerPickupItemEvent event) {
        final Player player = event.getPlayer();
        event.setCancelled(true);
        player.sendMessage(this.prefix + this.config.getString("ON_PICKUP").replaceAll("&", "§").replaceAll("\u00c2", ""));
    }
    
    static {
        playerpickup.instance = Main.getInstance();
    }
}
