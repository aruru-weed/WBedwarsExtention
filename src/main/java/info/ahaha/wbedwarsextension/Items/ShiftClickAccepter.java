package info.ahaha.wbedwarsextension.Items;

import info.ahaha.guiapi.Runs;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ShiftClickAccepter implements Runs.Accepter {
    @Override
    public void accept(InventoryClickEvent e) {
        e.setCancelled(true);

        ItemStack item = e.getInventory().getContents()[e.getSlot()];
        if (item != null) {
            for (int slot : Arrays.asList(51, 53, 55))
                if (e.getInventory().getItem(slot) == null) {
                    e.getInventory().setItem(slot, e.getCurrentItem());
                    e.getWhoClicked().getInventory().clear(e.getSlot());
                    return;
                }
        }
    }
}
