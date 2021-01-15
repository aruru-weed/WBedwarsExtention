package info.ahaha.wbedwarsextension.Items.armor;

import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FallingCancelArmorSeller extends AbstractSeller {
    public FallingCancelArmorSeller(Icon _icon, int _Slot, ItemStack... _require) {
        super(_icon, _Slot, _require);
    }

    @Override
    public void run(Player player) {
        player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
    }
}
