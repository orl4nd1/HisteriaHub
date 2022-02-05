package win.histeria.HisteriaHub.inv;

import org.bukkit.plugin.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class ClickDeny implements Listener
{
    private Plugin plugin;
    
    public ClickDeny() {
        this.plugin = (Plugin)Main.getPlugin((Class)Main.class);
    }
    
    @EventHandler
    public void invClick(final InventoryClickEvent event) {
        final Player player = (Player)event.getWhoClicked();
        final org.bukkit.inventory.Inventory open = event.getClickedInventory();
        final ItemStack item = event.getCurrentItem();
        if (open == null) {
            return;
        }
        if (open.getName().equals(this.plugin.getConfig().getString("SERVER_SELECTOR_INVENTORY_NAME").replaceAll("&", "§").replaceAll("\u00c2", ""))) {
            event.setCancelled(true);
            if (item == null || !item.hasItemMeta()) {
                return;
            }
            if (item.getItemMeta().getDisplayName().equals(this.plugin.getConfig().getString("NAME_SERVER1").replaceAll("&", "§").replaceAll("\u00c2", ""))) {
                player.closeInventory();
                final Inventory InvClass = new Inventory();
                Bukkit.dispatchCommand((CommandSender)player, this.plugin.getConfig().getString("COMMAND_SERVER1"));
            }
            if (item.getItemMeta().getDisplayName().equals(this.plugin.getConfig().getString("NAME_SERVER2").replaceAll("&", "§").replaceAll("\u00c2", ""))) {
                player.closeInventory();
                final Inventory InvClass = new Inventory();
                Bukkit.dispatchCommand((CommandSender)player, this.plugin.getConfig().getString("COMMAND_SERVER2"));
            }
            if (item.getItemMeta().getDisplayName().equals(this.plugin.getConfig().getString("NAME_SERVER3").replaceAll("&", "§").replaceAll("\u00c2", ""))) {
                player.closeInventory();
                final Inventory InvClass = new Inventory();
                Bukkit.dispatchCommand((CommandSender)player, this.plugin.getConfig().getString("COMMAND_SERVER3"));
            }
            if (item.getItemMeta().getDisplayName().equals(this.plugin.getConfig().getString("NAME_SERVER4").replaceAll("&", "§").replaceAll("\u00c2", ""))) {
                player.closeInventory();
                final Inventory InvClass = new Inventory();
                Bukkit.dispatchCommand((CommandSender)player, this.plugin.getConfig().getString("COMMAND_SERVER4"));
            }
        }
    }
}
