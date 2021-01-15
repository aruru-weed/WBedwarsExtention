package info.ahaha.wbedwarsextension.Items;

import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.GUI_Item;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.util.Item_Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static info.ahaha.wbedwarsextension.Items.SellItem.getMaterialColor;
import static org.bukkit.Bukkit.getLogger;

public class SellItemRequireHead extends GUI_Item {
    public SellItemRequireHead(Icon _icon, int _Slot, ItemStack _sellItem, int HeadAmount, ItemStack... _require) {
        this(_icon, _Slot, new ArrayList<>(Arrays.asList(_require)), _sellItem, HeadAmount);
    }

    public SellItemRequireHead(Icon _icon, int _Slot, List<ItemStack> _require, ItemStack _sellItem, int _HeadAmount) {
        super(_icon, _Slot);
        this.require = _require;
        this.sellItem = _sellItem;
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
                HashMap<Integer, ItemStack> a = player.getInventory().addItem(sellItem);

                player.playSound(player.getLocation(), Sound.NOTE_PIANO, 0.5f, 5);
                if (getIcon().getName() != null)
                    player.sendMessage(ChatColor.GREEN + "Buy " + getIcon().getName());
                else
                    player.sendMessage(ChatColor.GREEN + "Buy " + sellItem.getType().name());

                if (!a.isEmpty()) {
                    player.sendMessage(ChatColor.RED + "It wont fit in");
                    for (ItemStack item : a.values()) {
                        if (item.hasItemMeta())
                            if (item.getItemMeta().hasDisplayName()) {
                                player.sendMessage(ChatColor.RED + item.getItemMeta().getDisplayName() + " x" + item.getAmount());
                                continue;
                            }
                        player.sendMessage(ChatColor.RED + item.getType().toString() + " x" + item.getAmount());
                    }
                }
            } catch (AbstractSeller.NotRequire e) {
                player.sendMessage(ChatColor.RED + "You don`t have required item");
            }
        });
    }

    List<ItemStack> require;
    ItemStack sellItem;
    int HeadAmount;
}
