package info.ahaha.wbedwarsextension.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class ArchListener implements Listener {
    @EventHandler
    public void on(EntityShootBowEvent e) {
        if (e.getBow().hasItemMeta())
            if (e.getBow().getItemMeta().hasDisplayName())
                e.getProjectile().setCustomName(e.getBow().getItemMeta().getDisplayName());
    }
}
