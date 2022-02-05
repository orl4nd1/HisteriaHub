package win.histeria.HisteriaHub.events;

import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class hunger implements Listener
{
    @EventHandler
    public void onLoseHunger(final FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }
}
