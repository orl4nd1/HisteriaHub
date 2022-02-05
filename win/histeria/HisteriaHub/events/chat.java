package win.histeria.HisteriaHub.events;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class chat implements Listener
{
    FileConfiguration config;
    private Main plugin;
    
    public chat() {
        this.config = ((Main)Main.getPlugin((Class)Main.class)).getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
    }
    
    @EventHandler
    public void onMessage(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String playertata = (this.plugin.chat.getPlayerPrefix(player) + event.getPlayer().getName()).replaceAll("&", "§");
        if (player.hasPermission("chat.color")) {
            event.setFormat(playertata + " §8» §f" + event.getMessage().replaceAll("&", "§").replaceAll("%", ""));
        }
        else {
            event.setFormat(playertata + " §8» §f" + event.getMessage());
        }
    }
}
