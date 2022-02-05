package win.histeria.HisteriaHub.events;

import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class bedjoin implements Listener
{
    @EventHandler
    public void bedjoin(final PlayerBedEnterEvent event) {
        final Location bedloc = event.getBed().getLocation();
        final World world = event.getBed().getWorld();
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
        world.strikeLightningEffect(bedloc);
    }
}
