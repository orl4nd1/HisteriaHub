package win.histeria.HisteriaHub.scoreboard;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class SidebarManager implements Listener
{
    FileConfiguration config;
    private Main plugin;
    private static Main instance;
    
    public SidebarManager() {
        this.config = Main.getInstance().getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
    }
    
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final ScoreHelper helper = ScoreHelper.createScore(player);
        new BukkitRunnable() {
            public void run() {
                helper.setTitle(SidebarManager.this.config.getString("SCOREBOARD_TITLE").replaceAll("&", "§").replaceAll("\u00c2", ""));
                helper.setSlot(9, SidebarManager.this.config.getString("SCOREBOARD_LINE").replaceAll("&", "§").replaceAll("\u00c2", ""));
                helper.setSlot(8, SidebarManager.this.config.getString("SCOREBOARD_WELCOME").replaceAll("%player%", player.getName()).replaceAll("&", "§").replaceAll("\u00c2", ""));
                helper.setSlot(7, SidebarManager.this.config.getString("SCOREBOARD_RANK").replaceAll("%rank%", SidebarManager.this.plugin.chat.getPrimaryGroup(player)).replaceAll("&", "§").replaceAll("\u00c2", ""));
                helper.setSlot(6, SidebarManager.this.config.getString("SCOREBOARD_CONNECTED").replaceAll("&", "§").replaceAll("\u00c2", ""));
                helper.setSlot(5, "");
                helper.setSlot(4, SidebarManager.this.config.getString("SCOREBOARD_TOTAL").replaceAll("&", "§").replaceAll("\u00c2", ""));
                helper.setSlot(3, SidebarManager.this.config.getString("SCOREBOARD_HUB").replaceAll("&", "§").replaceAll("\u00c2", ""));
                helper.setSlot(2, SidebarManager.this.config.getString("SCOREBOARD_HCF").replaceAll("&", "§").replaceAll("\u00c2", ""));
                helper.setSlot(1, SidebarManager.this.config.getString("SCOREBOARD_LINE").replaceAll("&", "§").replaceAll("\u00c2", ""));
            }
        }.runTaskTimer((Plugin)this.plugin, 0L, 5L);
        final ItemStack svselec = new ItemStack(Material.getMaterial(this.config.getString("SERVER_SELECTOR_ITEM")), this.config.getInt("SERVER_SELECTOR_AMOUNT"));
        final ItemMeta svselecmeta = svselec.getItemMeta();
        final ItemStack pearlitem = new ItemStack(Material.ENDER_PEARL, this.config.getInt("ENDER_BUTT_AMOUNT"));
        final ItemMeta pearlitemmeta = pearlitem.getItemMeta();
        svselecmeta.setDisplayName(this.config.getString("SERVER_SELECTOR_NAME").replaceAll("&", "§").replaceAll("\u00c2", ""));
        svselec.setItemMeta(svselecmeta);
        pearlitemmeta.setDisplayName(this.config.getString("ENDER_BUTT_NAME").replaceAll("&", "§").replaceAll("\u00c2", ""));
        pearlitem.setItemMeta(pearlitemmeta);
        player.getInventory().setItem(this.config.getInt("SERVER_SELECTOR_SLOT"), svselec);
        player.getInventory().setItem(this.config.getInt("ENDER_BUTT_SLOT"), pearlitem);
    }
    
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        if (ScoreHelper.hasScore(player)) {
            ScoreHelper.removeScore(player);
        }
        player.getInventory().clear();
        event.setQuitMessage(this.config.getString("ON_QUIT").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("%player%", player.getName()));
    }
    
    static {
        SidebarManager.instance = Main.getInstance();
    }
}
