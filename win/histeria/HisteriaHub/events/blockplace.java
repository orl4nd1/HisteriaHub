package win.histeria.HisteriaHub.events;

import win.histeria.HisteriaHub.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class blockplace implements Listener
{
    Main plugin;
    String prefix;
    
    public blockplace() {
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.prefix = this.plugin.getConfig().getString("PREFIX").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", "");
    }
    
    @EventHandler
    public void onPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        event.setCancelled(true);
        player.sendMessage(this.prefix + this.plugin.getConfig().getString("ON_PLACE").replaceAll("&", "§").replaceAll("\u00c2", ""));
    }
}
