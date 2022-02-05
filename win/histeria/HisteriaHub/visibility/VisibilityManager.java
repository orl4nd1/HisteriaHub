package win.histeria.HisteriaHub.visibility;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.inventory.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import java.util.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class VisibilityManager implements Listener
{
    FileConfiguration config;
    private Main plugin;
    private static Main hub;
    List<String> visibilitylore;
    ArrayList<String> visibilitylorenew;
    String prefix;
    
    public VisibilityManager() {
        this.config = Main.getInstance().getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.visibilitylore = (List<String>)this.config.getStringList("VANISH_LORE");
        this.visibilitylorenew = new ArrayList<String>();
        this.prefix = this.config.getString("PREFIX").replaceAll("&", "§").replaceAll("\u00c2", "");
    }
    
    @EventHandler
    public void vanishItem(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Action action = event.getAction();
        final ItemStack vanishitem = new ItemStack(Material.INK_SACK, 1, (short)10);
        final ItemMeta vanishitemmeta = vanishitem.getItemMeta();
        vanishitemmeta.setDisplayName(this.config.getString("VANISH_ON_ITEM_NAME").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        vanishitem.setItemMeta(vanishitemmeta);
        final ItemStack vanish2item = new ItemStack(Material.INK_SACK, 1, (short)8);
        final ItemMeta vanish2itemmeta = vanish2item.getItemMeta();
        vanish2itemmeta.setDisplayName(this.config.getString("VANISH_OFF_ITEM_NAME").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        vanish2item.setItemMeta(vanish2itemmeta);
        if (player.getItemInHand().getType().equals((Object)Material.INK_SACK) && player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(this.config.getString("VANISH_OFF_ITEM_NAME").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""))) {
            if (action.equals((Object)Action.RIGHT_CLICK_AIR) || action.equals((Object)Action.RIGHT_CLICK_BLOCK)) {
                player.getInventory().setItem(8, vanishitem);
                for (final Player all : Bukkit.getServer().getOnlinePlayers()) {
                    player.showPlayer(all);
                }
                player.sendMessage(this.prefix + this.config.getString("VANISH_ACTIVATED").replaceAll("&", "§").replaceAll("\u00c2", ""));
                player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1.0f, 1.0f);
            }
        }
        else if (player.getItemInHand().getType().equals((Object)Material.INK_SACK) && player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(this.config.getString("VANISH_ON_ITEM_NAME").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", "")) && (action.equals((Object)Action.RIGHT_CLICK_AIR) || action.equals((Object)Action.RIGHT_CLICK_BLOCK))) {
            player.getInventory().setItem(8, vanish2item);
            for (final Player all : Bukkit.getServer().getOnlinePlayers()) {
                player.hidePlayer(all);
            }
            player.sendMessage(this.prefix + this.config.getString("VANISH_DESACTIVATED").replaceAll("&", "§").replaceAll("\u00c2", ""));
            player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0f, 1.0f);
        }
    }
    
    @EventHandler
    public void onJoinVisibility(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final ItemStack vanishitem = new ItemStack(Material.INK_SACK, 1, (short)10);
        final ItemMeta vanishitemmeta = vanishitem.getItemMeta();
        vanishitemmeta.setDisplayName(this.config.getString("VANISH_ON_ITEM_NAME").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        vanishitem.setItemMeta(vanishitemmeta);
        final ItemStack vanish2item = new ItemStack(Material.INK_SACK, 1, (short)8);
        final ItemMeta vanish2itemmeta = vanish2item.getItemMeta();
        vanish2itemmeta.setDisplayName(this.config.getString("VANISH_OFF_ITEM_NAME").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        vanish2item.setItemMeta(vanish2itemmeta);
        for (final Player all : Bukkit.getServer().getOnlinePlayers()) {
            player.showPlayer(all);
        }
        player.getInventory().setItem(8, vanishitem);
    }
    
    void changeList(final List<String> list1, final ArrayList<String> newlist) {
        for (final String b : list1) {
            newlist.add(b.replace("&", "§"));
        }
    }
    
    static {
        VisibilityManager.hub = Main.getInstance();
    }
}
