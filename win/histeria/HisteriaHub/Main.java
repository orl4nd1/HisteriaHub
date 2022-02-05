package win.histeria.HisteriaHub;

import org.bukkit.plugin.java.*;
import org.bukkit.plugin.messaging.*;
import net.milkbowl.vault.chat.*;
import org.bukkit.*;
import org.bukkit.event.*;
import win.histeria.HisteriaHub.inv.*;
import win.histeria.HisteriaHub.scoreboard.*;
import win.histeria.HisteriaHub.hotbar.*;
import win.histeria.HisteriaHub.enderbutt.*;
import win.histeria.HisteriaHub.visibility.*;
import win.histeria.HisteriaHub.events.*;
import win.histeria.HisteriaHub.cmds.*;
import org.bukkit.command.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import com.google.common.io.*;

public class Main extends JavaPlugin implements PluginMessageListener
{
    public Chat chat;
    private static Main instance;
    public int playerCount;
    public int playerCountHCF;
    
    public static void setInstance(final Main instance) {
        Main.instance = instance;
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            (Main.instance = this).onLoad();
            System.out.println("[HisteriaHub] El plugin se ha activado en la version 1.0");
            this.registrarEventos();
            this.registrarComandos();
            this.setupChat();
            this.saveDefaultConfig();
        }
        else {
            Bukkit.getConsoleSender().sendMessage("Could not find PlaceholderAPI!! Plugin can not work without it!");
            Bukkit.shutdown();
        }
    }
    
    public void onDisable() {
        System.out.println("[HisteriaHub] El plugin se ha desactivado en la version 1.0");
    }
    
    public void registrarEventos() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)new pJoin(), (Plugin)this);
        pm.registerEvents((Listener)new chat(), (Plugin)this);
        pm.registerEvents((Listener)new Inventory(), (Plugin)this);
        pm.registerEvents((Listener)new ClickDeny(), (Plugin)this);
        pm.registerEvents((Listener)new SidebarManager(), (Plugin)this);
        pm.registerEvents((Listener)new blockbreak(), (Plugin)this);
        pm.registerEvents((Listener)new blockplace(), (Plugin)this);
        pm.registerEvents((Listener)new playerdrop(), (Plugin)this);
        pm.registerEvents((Listener)new playerpickup(), (Plugin)this);
        pm.registerEvents((Listener)new items(), (Plugin)this);
        pm.registerEvents((Listener)new EnderPearl(), (Plugin)this);
        pm.registerEvents((Listener)new onDamage(), (Plugin)this);
        pm.registerEvents((Listener)new VisibilityManager(), (Plugin)this);
        pm.registerEvents((Listener)new bedjoin(), (Plugin)this);
        pm.registerEvents((Listener)new hunger(), (Plugin)this);
        pm.registerEvents((Listener)new Speed(), (Plugin)this);
        pm.registerEvents((Listener)new DoubleJump(), (Plugin)this);
    }
    
    public void registrarComandos() {
        Bukkit.getPluginCommand("histeriahub").setExecutor((CommandExecutor)new reloadCMD());
    }
    
    public boolean setupChat() {
        final RegisteredServiceProvider<Chat> rsp = (RegisteredServiceProvider<Chat>)this.getServer().getServicesManager().getRegistration((Class)Chat.class);
        this.chat = (Chat)rsp.getProvider();
        return this.chat != null;
    }
    
    public void onPluginMessageReceived(final String channel, final Player player, final byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        final ByteArrayDataInput in = ByteStreams.newDataInput(message);
        final String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            final String server = in.readUTF();
            this.playerCount = in.readInt();
        }
    }
    
    public void getCount(final Player player, String server) {
        if (server == null) {
            server = "ALL";
        }
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        player.sendPluginMessage((Plugin)this, "BungeeCord", out.toByteArray());
    }
    
    public void onPluginMessageReceived2(final String channel, final Player player, final byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        final ByteArrayDataInput in = ByteStreams.newDataInput(message);
        final String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            final String server = in.readUTF();
            this.playerCountHCF = in.readInt();
        }
    }
    
    public void getCount2(final Player player, String server) {
        if (server == null) {
            server = "HCF";
        }
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        player.sendPluginMessage((Plugin)this, "HCF", out.toByteArray());
    }
}
