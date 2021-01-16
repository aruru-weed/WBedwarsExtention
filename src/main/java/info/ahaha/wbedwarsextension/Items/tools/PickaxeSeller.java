package info.ahaha.wbedwarsextension.Items.tools;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.GUI_Item;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.extended.items.AbstractPerPlayerItem;
import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PickaxeSeller extends AbstractPerPlayerItem {
    public PickaxeSeller(Game game, int slot) {
        super(slot);
        this.game = game;
        perIdData = WBedwarsExtension.getData().get(game.getID());
    }

    Game game;
    WBedwarsExtension.PerIdData perIdData;
    Map<Integer, List<ItemStack>> requires = new HashMap<>();

    class seller extends AbstractSeller {
        public seller(Icon _icon, int _Slot, List<ItemStack> _require) {
            super(_icon, _Slot, _require);
            requires.put(0, new ArrayList<>(Arrays.asList(
                    new ItemStack(Material.IRON_INGOT, 10)
            )));
            requires.put(1, new ArrayList<>(Arrays.asList(
                    new ItemStack(Material.IRON_INGOT, 10),
                    new ItemStack(Material.GOLD_INGOT, 5)
            )));
            requires.put(2, new ArrayList<>(Arrays.asList(
                    new ItemStack(Material.IRON_INGOT, 30),
                    new ItemStack(Material.GOLD_INGOT, 3)
            )));
            requires.put(3, new ArrayList<>(Arrays.asList(
                    new ItemStack(Material.IRON_INGOT, 40),
                    new ItemStack(Material.GOLD_INGOT, 4)
            )));
        }

        @Override
        public void run(Player player) {
            WBedwarsExtension.PerIdData.PerPlayerData data = perIdData.getPlayerData(player);
            player.getInventory().remove(get(data.getPickaxe()));
            data.setPickaxe(data.getPickaxe() + 1);
            player.getInventory().addItem(get(data.getPickaxe()));
        }
    }

    @Override
    public abstract_GUI_Item run(Player player) {
        WBedwarsExtension.PerIdData.PerPlayerData data = perIdData.getPlayerData(player);
        if (data.getPickaxe() >= 4)
            return new GUI_Item(getIcon(4).setName(ChatColor.GRAY + "Nothing"), getSlot());

        return new seller(getIcon(data.getPickaxe() + 1), getSlot(), requires.get(data.getPickaxe()));
    }

    @Override
    public abstract_GUI_Item getDefault() {
        return null;
    }

    public static ItemStack get(int num) {
        return getIcon(num).toItem();
    }

    public static Icon getIcon(int num) {
        switch (num) {
            case 1:
                return IconFactory.Make(Material.WOOD_PICKAXE).addEnchant(new Icon.EnchantMeta(Enchantment.DIG_SPEED, 3));
            case 2:
                return IconFactory.Make(Material.IRON_PICKAXE).addEnchant(new Icon.EnchantMeta(Enchantment.DIG_SPEED, 2));
            case 3:
                return IconFactory.Make(Material.GOLD_PICKAXE).addEnchant(new Icon.EnchantMeta(Enchantment.DIG_SPEED, 1));
            case 4:
                return IconFactory.Make(Material.DIAMOND_PICKAXE).addEnchant(new Icon.EnchantMeta(Enchantment.DIG_SPEED, 1));
        }
        return null;
    }
}
