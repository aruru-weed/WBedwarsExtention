package info.ahaha.wbedwarsextension.Items.armor;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.wbedwarsextension.Items.AbstractSellerRequireHead;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static info.ahaha.wbedwarsextension.WBedwarsExtension.getData;

public class DiaArmorSeller extends AbstractSellerRequireHead {
    public DiaArmorSeller(Game game, Icon _icon, int _Slot, int Amount, ItemStack... _require) {
        super(_icon, _Slot, Amount, _require);
        this.game = game;
    }

    Game game;

    @Override
    public void run(Player player) {
        set(player);
        getData().get(game.getID()).getPlayerData(player).setArmor(ArmorType.Dia);
    }

    public static void set(Player player) {
        player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
    }
}
