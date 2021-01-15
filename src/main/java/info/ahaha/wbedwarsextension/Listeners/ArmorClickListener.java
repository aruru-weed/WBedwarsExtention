package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.Game;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static org.bukkit.Bukkit.getLogger;

public class ArmorClickListener implements Listener {
    @EventHandler
    public void on(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Game game = WBedwarsExtension.getApi().findGame(player.getLocation().getWorld().getName());
        if (game == null)
            return;
        switch (e.getSlotType()) {
            case CONTAINER:
            case QUICKBAR:
                return;
            default:
                e.setCancelled(true);
        }
    }
}
