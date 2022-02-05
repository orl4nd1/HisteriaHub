package win.histeria.HisteriaHub.inv;

import org.bukkit.event.*;
import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import java.util.*;

public class Inventory implements Listener
{
    FileConfiguration config;
    private Main plugin;
    private static Main instance;
    ArrayList<String> lore1new;
    List<String> lore1;
    ArrayList<String> lore2new;
    List<String> lore2;
    ArrayList<String> lore3new;
    List<String> lore3;
    ArrayList<String> lore4new;
    List<String> lore4;
    
    public Inventory() {
        this.config = Main.getInstance().getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.lore1new = new ArrayList<String>();
        this.lore1 = (List<String>)this.config.getStringList("LORE_SERVER1");
        this.lore2new = new ArrayList<String>();
        this.lore2 = (List<String>)this.config.getStringList("LORE_SERVER2");
        this.lore3new = new ArrayList<String>();
        this.lore3 = (List<String>)this.config.getStringList("LORE_SERVER3");
        this.lore4new = new ArrayList<String>();
        this.lore4 = (List<String>)this.config.getStringList("LORE_SERVER4");
    }
    
    public org.bukkit.inventory.Inventory newInventory() {
        final org.bukkit.inventory.Inventory inv = this.plugin.getServer().createInventory((InventoryHolder)null, 27, this.plugin.getConfig().getString("SERVER_SELECTOR_INVENTORY_NAME").replaceAll("&", "§").replaceAll("\u00c2", ""));
        final ItemStack item1 = new ItemStack(Material.getMaterial(this.plugin.getConfig().getString("ITEM_SERVER1")), 1);
        final ItemMeta item1meta = item1.getItemMeta();
        item1meta.setDisplayName(this.plugin.getConfig().getString("NAME_SERVER1").replaceAll("&", "§").replaceAll("\u00c2", ""));
        this.changeList(this.lore1, this.lore1new);
        item1meta.setLore((List)this.lore1new);
        item1.setItemMeta(item1meta);
        final ItemStack item2 = new ItemStack(Material.getMaterial(this.plugin.getConfig().getString("ITEM_SERVER2")), 1);
        final ItemMeta item2meta = item2.getItemMeta();
        item2meta.setDisplayName(this.plugin.getConfig().getString("NAME_SERVER2").replaceAll("&", "§").replaceAll("\u00c2", ""));
        this.changeList(this.lore2, this.lore2new);
        item2meta.setLore((List)this.lore2new);
        item2.setItemMeta(item2meta);
        final ItemStack item3 = new ItemStack(Material.getMaterial(this.plugin.getConfig().getString("ITEM_SERVER3")), 1);
        final ItemMeta item3meta = item3.getItemMeta();
        item3meta.setDisplayName(this.plugin.getConfig().getString("NAME_SERVER3").replaceAll("&", "§").replaceAll("\u00c2", ""));
        this.changeList(this.lore3, this.lore3new);
        item3meta.setLore((List)this.lore3new);
        item3.setItemMeta(item3meta);
        final ItemStack item4 = new ItemStack(Material.getMaterial(this.plugin.getConfig().getString("ITEM_SERVER4")), 1);
        final ItemMeta item4meta = item4.getItemMeta();
        item4meta.setDisplayName(this.plugin.getConfig().getString("NAME_SERVER4").replaceAll("&", "§").replaceAll("\u00c2", ""));
        this.changeList(this.lore4, this.lore4new);
        item4meta.setLore((List)this.lore4new);
        item4.setItemMeta(item4meta);
        if (this.config.getBoolean("ITEM1_ENABLED")) {
            inv.setItem(10, item1);
        }
        if (this.config.getBoolean("ITEM2_ENABLED")) {
            inv.setItem(13, item2);
        }
        if (this.config.getBoolean("ITEM3_ENABLED")) {
            inv.setItem(14, item3);
        }
        if (this.config.getBoolean("ITEM4_ENABLED")) {
            inv.setItem(16, item4);
        }
        return inv;
    }
    
    void changeList(final List<String> list1, final ArrayList<String> newlist) {
        for (final String b : list1) {
            newlist.add(b.replace("&", "§"));
        }
    }
    
    static {
        Inventory.instance = Main.getInstance();
    }
}
