package win.histeria.HisteriaHub.scoreboard;

import org.bukkit.entity.*;
import org.bukkit.scoreboard.*;
import org.bukkit.*;
import java.util.*;

public class ScoreHelper
{
    private static HashMap<UUID, ScoreHelper> players;
    private Scoreboard scoreboard;
    private Objective sidebar;
    
    public static boolean hasScore(final Player player) {
        return ScoreHelper.players.containsKey(player.getUniqueId());
    }
    
    public static ScoreHelper createScore(final Player player) {
        return new ScoreHelper(player);
    }
    
    public static ScoreHelper getByPlayer(final Player player) {
        return ScoreHelper.players.get(player.getUniqueId());
    }
    
    public static ScoreHelper removeScore(final Player player) {
        return ScoreHelper.players.remove(player.getUniqueId());
    }
    
    private ScoreHelper(final Player player) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        (this.sidebar = this.scoreboard.registerNewObjective("sidebar", "dummy")).setDisplaySlot(DisplaySlot.SIDEBAR);
        for (int i = 1; i <= 15; ++i) {
            final Team team = this.scoreboard.registerNewTeam("SLOT_" + i);
            team.addEntry(this.genEntry(i));
        }
        player.setScoreboard(this.scoreboard);
        ScoreHelper.players.put(player.getUniqueId(), this);
    }
    
    public void setTitle(String title) {
        title = ChatColor.translateAlternateColorCodes('&', title);
        this.sidebar.setDisplayName((title.length() > 32) ? title.substring(0, 32) : title);
    }
    
    public void setSlot(final int slot, String text) {
        final Team team = this.scoreboard.getTeam("SLOT_" + slot);
        final String entry = this.genEntry(slot);
        if (!this.scoreboard.getEntries().contains(entry)) {
            this.sidebar.getScore(entry).setScore(slot);
        }
        text = ChatColor.translateAlternateColorCodes('&', text);
        final String pre = this.getFirstSplit(text);
        final String suf = this.getFirstSplit(ChatColor.getLastColors(pre) + this.getSecondSplit(text));
        team.setPrefix(pre);
        team.setSuffix(suf);
    }
    
    public void removeSlot(final int slot) {
        final String entry = this.genEntry(slot);
        if (this.scoreboard.getEntries().contains(entry)) {
            this.scoreboard.resetScores(entry);
        }
    }
    
    public void setSlotsFromList(final List<String> list) {
        while (list.size() > 15) {
            list.remove(list.size() - 1);
        }
        int slot = list.size();
        if (slot < 15) {
            for (int i = slot + 1; i <= 15; ++i) {
                this.removeSlot(i);
            }
        }
        for (final String line : list) {
            this.setSlot(slot, line);
            --slot;
        }
    }
    
    private String genEntry(final int slot) {
        return ChatColor.values()[slot].toString();
    }
    
    private String getFirstSplit(final String s) {
        return (s.length() > 16) ? s.substring(0, 16) : s;
    }
    
    private String getSecondSplit(String s) {
        if (s.length() > 32) {
            s = s.substring(0, 32);
        }
        return (s.length() > 16) ? s.substring(16) : "";
    }
    
    static {
        ScoreHelper.players = new HashMap<UUID, ScoreHelper>();
    }
}
