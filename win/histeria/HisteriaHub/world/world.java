package win.histeria.HisteriaHub.world;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class world implements Listener
{
    public void pvp(final Player player) {
        final World world = player.getWorld();
        world.setPVP(false);
        world.setStorm(false);
        world.setThundering(false);
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setTime(0L);
    }
}
