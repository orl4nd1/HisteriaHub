package win.histeria.HisteriaHub.events;

import win.histeria.HisteriaHub.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class blockbreak implements Listener
{
    private Main plugin;
    String prefix;
    
    public blockbreak() {
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.prefix = this.plugin.getConfig().getString("PREFIX").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", "");
    }
    
    @EventHandler
    public void onBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        event.setCancelled(true);
        player.sendMessage(this.prefix + this.plugin.getConfig().getString("ON_BREAK").replaceAll("&", "§").replaceAll("\u00c2", ""));
    }
}
