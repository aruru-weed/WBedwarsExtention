package info.ahaha.wbedwarsextension.Items.tools;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShearsSeller extends AbstractSeller {
    public ShearsSeller(Game game, Icon _icon, int _Slot, ItemStack... _require) {
        super(_icon, _Slot, _require);
        this.game = game;
    }

    Game game;

    @Override
    public void run(Player player) throws CanNotDo {
        WBedwarsExtension.PerIdData data = WBedwarsExtension.getData().get(game.getID());
        if (data.hasShears())
            throw new CanNotDo();
        data.setShears(true);
        player.getInventory().addItem(new ItemStack(Material.SHEARS));
    }
}
