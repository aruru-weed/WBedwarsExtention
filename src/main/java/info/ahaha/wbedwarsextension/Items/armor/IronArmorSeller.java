package info.ahaha.wbedwarsextension.Items.armor;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static info.ahaha.wbedwarsextension.WBedwarsExtension.getData;

public class IronArmorSeller extends AbstractSeller {
    public IronArmorSeller(Game game, Icon _icon, int _Slot, ItemStack... _require) {
        super(_icon, _Slot, _require);
        this.game = game;
    }

    Game game;

    @Override
    public void run(Player player) {
        set(player);
        getData().get(game.getID()).getPlayerData(player).setArmor(ArmorType.Iron);
    }

    public static void set(Player player){
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
    }
}
