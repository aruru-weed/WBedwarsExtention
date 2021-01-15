package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {
    @EventHandler
    public void on(PlayerDropItemEvent e) {
        if (WBedwarsExtension.getApi().findGame(e.getPlayer().getLocation().getWorld().getName()) == null)
            return;

        switch (e.getItemDrop().getItemStack().getType()) {
            case SKULL_ITEM:
            case IRON_AXE:
            case IRON_PICKAXE:
            case WOOD_AXE:
            case WOOD_PICKAXE:
            case GOLD_AXE:
            case GOLD_PICKAXE:
            case STONE_AXE:
            case STONE_PICKAXE:
            case DIAMOND_AXE:
            case DIAMOND_PICKAXE:
                e.setCancelled(true);
        }
    }
}
