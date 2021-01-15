package info.ahaha.wbedwarsextension.Items;

import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.GUI_Item;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.util.Item_Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static info.ahaha.wbedwarsextension.Items.SellItem.getMaterialColor;
import static org.bukkit.Bukkit.getLogger;

public abstract class AbstractSellerRequireHead extends GUI_Item {
    public AbstractSellerRequireHead(Icon _icon, int _Slot, int HeadAmount, ItemStack... _require) {
        this(_icon, _Slot, HeadAmount, new ArrayList<>(Arrays.asList(_require)));
    }

    public AbstractSellerRequireHead(Icon _icon, int _Slot, int _HeadAmount, List<ItemStack> _require) {
        super(_icon, _Slot);
        this.require = _require;
        this.HeadAmount = _HeadAmount;
        List<String> list = new ArrayList<>(Arrays.asList(ChatColor.WHITE + "--- requires ---", ChatColor.DARK_RED + "PlayerHead x" + HeadAmount));
        for (ItemStack item : _require) {
            if (item.hasItemMeta())
                if (item.getItemMeta().hasDisplayName()) {
                    list.add(getMaterialColor(item.getType()) + item.getItemMeta().getDisplayName() + " x" + item.getAmount());
                    continue;
                }
            list.add(getMaterialColor(item.getType()) + item.getType().name() + " x" + item.getAmount());
        }
        getIcon().setLore(list);
        setDefault((AccepterAttributes.augPlayer) (player) -> {
            try {
                for (ItemStack item : require) {
                    if (player.getInventory().containsAtLeast(item, item.getAmount()))
                        continue;
                    else
                        throw new AbstractSeller.NotRequire();
                }
                if (!player.getInventory().contains(Material.SKULL_ITEM, HeadAmount))
                    throw new AbstractSeller.NotRequire();
                for (ItemStack item : require)
                    if (!Item_Utils.removeItems(player.getInventory(), item, item.getAmount()))
                        getLogger().info("Item remove error");
                Item_Utils.removeItems(player.getInventory(), Material.SKULL_ITEM, HeadAmount);
                run(player);

                player.playSound(player.getLocation(), Sound.NOTE_PIANO, 0.5f, 5);
                if (getIcon().getName() != null)
                    player.sendMessage(ChatColor.GREEN + "Buy " + getIcon().getName());
            } catch (AbstractSeller.NotRequire e) {
                player.sendMessage(ChatColor.RED + "You don`t have required item");
            }
        });
    }

    public abstract void run(Player player);

    List<ItemStack> require;
    int HeadAmount;
}
