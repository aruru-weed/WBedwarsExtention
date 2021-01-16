package info.ahaha.wbedwarsextension.Items.tools;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.extended.items.AbstractPerPlayerItem;
import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
            WBedwarsExtension.PerIdData.PerPlayerData data = WBedwarsExtension.getData().get(game.getID()).getPlayerData(player);
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
            case 1:
                return IconFactory.Make(Material.WOOD_PICKAXE).addEnchant(new Icon.EnchantMeta(Enchantment.DIG_SPEED, 3)).toItem();
            case 2:
                return IconFactory.Make(Material.IRON_PICKAXE).addEnchant(new Icon.EnchantMeta(Enchantment.DIG_SPEED, 2)).toItem();
            case 3:
                return IconFactory.Make(Material.GOLD_PICKAXE).addEnchant(new Icon.EnchantMeta(Enchantment.DIG_SPEED, 1)).toItem();
            case 4:
                return IconFactory.Make(Material.DIAMOND_PICKAXE).addEnchant(new Icon.EnchantMeta(Enchantment.DIG_SPEED, 1)).toItem();
        }
        return null;
    }
}
