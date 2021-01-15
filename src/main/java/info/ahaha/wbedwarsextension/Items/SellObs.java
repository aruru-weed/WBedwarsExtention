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

import static org.bukkit.Bukkit.getLogger;

public class SellObs extends GUI_Item {
    public static ChatColor getMaterialColor(Material m) {
        switch (m) {
            case GOLD_INGOT:
                return ChatColor.GOLD;
            case IRON_INGOT:
                return ChatColor.GRAY;
            case EMERALD:
                return ChatColor.DARK_GREEN;
            case DIAMOND:
                return ChatColor.AQUA;
        }
        return ChatColor.WHITE;
    }

    public SellObs(Icon _icon, int _Slot, ItemStack _sellItem, ItemStack... _require) {
        this(_icon, _Slot, new ArrayList<>(Arrays.asList(_require)), _sellItem);
    }

    public SellObs(Icon _icon, int _Slot, List<ItemStack> _require, ItemStack _sellItem) {
        super(_icon, _Slot);
        this.require = _require;
        this.sellItem = _sellItem;
        List<String> list = new ArrayList<>(Arrays.asList(ChatColor.WHITE + "--- requires? ---"));
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
                if (player.getInventory().contains(Material.OBSIDIAN, 16)) {
                    Item_Utils.removeItems(player.getInventory(), Material.OBSIDIAN, 16);
                    player.getInventory().addItem(new ItemStack(Material.OBSIDIAN));
                    player.sendMessage(ChatColor.GREEN + "Buy " + ChatColor.STRIKETHROUGH + "" + ChatColor.MAGIC + "Bedrock");
                    return;
                }

                for (ItemStack item : require) {
                    if (player.getInventory().containsAtLeast(item, item.getAmount()))
                        continue;
                    else
                        throw new AbstractSeller.NotRequire();
                }
                for (ItemStack item : require)
                    if (!Item_Utils.removeItems(player.getInventory(), item, item.getAmount()))
                        getLogger().info("Item remove error");
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
}
