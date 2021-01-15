package info.ahaha.wbedwarsextension.Items.tools;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.extended.items.AbstractPerPlayerItem;
import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PickaxeSeller extends AbstractPerPlayerItem {
    public PickaxeSeller(Game game, int slot) {
        super(slot);
        this.game = game;

    }

    Game game;

    class seller extends AbstractSeller {
        public seller(Icon _icon, int _Slot, ItemStack... _require) {
            super(_icon, _Slot, _require);
        }

        @Override
        public void run(Player player) {
            WBedwarsExtension.PerIdData data = WBedwarsExtension.getData().get(game.getID());
            data.setAxe(data.getAxe() + 1);
        }
    }

    @Override
    public abstract_GUI_Item run(Player player) {

        return null;
    }

    @Override
    public abstract_GUI_Item getDefault() {
        return null;
    }

    public static ItemStack get(int num) {
        switch (num) {
            break;
            case 1:
                return IconFactory.Make(Material.WOOD_AXE).addEnchant(new Icon.EnchantMeta(E))
        }
    }
}
