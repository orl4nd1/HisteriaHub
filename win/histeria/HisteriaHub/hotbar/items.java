package win.histeria.HisteriaHub.hotbar;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class items implements Listener
{
    @EventHandler
    public void onClickInventory(final InventoryClickEvent event) {
        final Player player = (Player)event.getWhoClicked();
        if (event.getCurrentItem().getType() != null || event.getCurrentItem().getType() == Material.AIR) {
            event.setCancelled(true);
            player.updateInventory();
        }
    }
}
