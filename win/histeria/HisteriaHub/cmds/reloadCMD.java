package win.histeria.HisteriaHub.cmds;

import org.bukkit.configuration.file.*;
import win.histeria.HisteriaHub.*;
import org.bukkit.command.*;
import org.bukkit.*;

public class reloadCMD implements CommandExecutor
{
    FileConfiguration config;
    private Main plugin;
    private static Main instance;
    String prefix;
    
    public reloadCMD() {
        this.config = Main.getInstance().getConfig();
        this.plugin = (Main)Main.getPlugin((Class)Main.class);
        this.prefix = this.config.getString("PREFIX").replaceAll("&", "§").replaceAll("\u00c2", "");
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {
        if (!sender.hasPermission("histeriahub.reload")) {
            sender.sendMessage(this.prefix + ChatColor.RED + "You don't have permissions to execute this command.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(this.prefix + ChatColor.RED + "Incorrect usage, type: /histeriahub reload");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            this.plugin.reloadConfig();
            sender.sendMessage(this.prefix + ChatColor.GREEN + "The configuration file has been reloaded.");
            return true;
        }
        sender.sendMessage(this.prefix + ChatColor.RED + "Incorrect usage, type: /histeriahub reload");
        return true;
    }
    
    static {
        reloadCMD.instance = Main.getInstance();
    }
}
