package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.Game;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class DragArmorListener implements Listener {
    @EventHandler
    public void on(InventoryDragEvent e) {
        Player player = (Player) e.getWhoClicked();
        Game game = WBedwarsExtension.getApi().findGame(player.getLocation().getWorld().getName());
        if (game == null)
            return;
        for (int s : e.getNewItems().keySet()) {
            if (4 < s && s < 9) {
                e.setCancelled(true);
            }
        }
    }
}
