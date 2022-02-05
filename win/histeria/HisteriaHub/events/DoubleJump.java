package win.histeria.HisteriaHub.events;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.util.*;
import org.bukkit.event.*;

public class DoubleJump implements Listener
{
    FileConfiguration config;
    
    public DoubleJump() {
        this.config = ((Main)Main.getPlugin((Class)Main.class)).getConfig();
    }
    
    @EventHandler
    public void onPlayerDoubleJump(final PlayerToggleFlightEvent e) {
        final Player p = e.getPlayer();
        if (this.config.getBoolean("DOUBLE_JUMP") && p.getGameMode() != GameMode.CREATIVE && !p.isFlying()) {
            e.setCancelled(true);
            final Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0.0, 2.0, 0.0));
            if (!b.getType().equals((Object)Material.AIR)) {
                final Vector v = p.getLocation().getDirection().multiply(1).setY(1);
                p.setVelocity(v);
            }
        }
    }
}
