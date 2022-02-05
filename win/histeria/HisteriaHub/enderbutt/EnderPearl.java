package win.histeria.HisteriaHub.enderbutt;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.scheduler.*;
import org.bukkit.plugin.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class EnderPearl implements Listener
{
    FileConfiguration config;
    private Main plugin;
    private static Main hub;
    String prefix;
    ArrayList<String> pearllore;
    
    public EnderPearl() {
        this.config = Main.getInstance().getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.prefix = this.config.getString("PREFIX").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", "");
        this.pearllore = new ArrayList<String>();
    }
    
    public ItemStack getEnderButt() {
        final ItemStack enderPearl = new ItemStack(Material.ENDER_PEARL, 1);
        final ItemMeta enderPearlMeta = enderPearl.getItemMeta();
        enderPearlMeta.setDisplayName(this.config.getString("ENDER_BUTT_NAME").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        this.pearllore.add(this.config.getString("ENDER_BUTT_LORE").replaceAll("&", "§").replaceAll("\u00c2", "").replaceAll("\u00c2", ""));
        enderPearlMeta.setLore((List)this.pearllore);
        enderPearl.setItemMeta(enderPearlMeta);
        return enderPearl;
    }
    
    public void setupEnderpearlRunnable(final Item item) {
        new BukkitRunnable() {
            public void run() {
                if (item.isDead()) {
                    this.cancel();
                }
                if (item.getVelocity().getX() == 0.0 || item.getVelocity().getY() == 0.0 || item.getVelocity().getZ() == 0.0) {
                    final Player player = (Player)item.getPassenger();
                    item.remove();
                    if (player != null) {
                        player.teleport(player.getLocation().add(0.0, 0.5, 0.0));
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)EnderPearl.hub, 2L, 1L);
    }
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            final Player player = event.getPlayer();
            final ItemStack itemStack = player.getItemInHand();
            if (itemStack.getType() == Material.ENDER_PEARL) {
                event.setCancelled(true);
                event.setUseItemInHand(Event.Result.DENY);
                event.setUseInteractedBlock(Event.Result.DENY);
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    final Item item = player.getWorld().dropItem(player.getLocation().add(0.0, 0.5, 0.0), new ItemStack(Material.ENDER_PEARL, 1));
                    item.setPickupDelay(10000);
                    item.setVelocity(player.getLocation().getDirection().normalize().multiply(1.5f));
                    item.setPassenger((Entity)player);
                    player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                    this.setupEnderpearlRunnable(item);
                    player.updateInventory();
                }
            }
        }
    }
    
    static {
        EnderPearl.hub = Main.getInstance();
    }
}
