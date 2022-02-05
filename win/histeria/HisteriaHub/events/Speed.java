package win.histeria.HisteriaHub.events;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.event.player.*;
import org.bukkit.potion.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Speed implements Listener
{
    FileConfiguration config;
    private Main plugin;
    private static Main instance;
    String prefix;
    
    public Speed() {
        this.config = Main.getInstance().getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.prefix = this.config.getString("PREFIX").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", "");
    }
    
    @EventHandler
    public void onJoinSpeed(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (this.plugin.getConfig().getBoolean("SPEED_EFFECT")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, this.plugin.getConfig().getInt("SPEED_LEVEL")));
        }
    }
    
    static {
        Speed.instance = Main.getInstance();
    }
}
