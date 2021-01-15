package info.ahaha.wbedwarsextension.Items;

import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.GUI_Item;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.util.Item_Utils;
import info.ahaha.wbedwarsextension.Items.trap.TrapRunner;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static info.ahaha.wbedwarsextension.Items.SellItem.getMaterialColor;
import static org.bukkit.Bukkit.getLogger;

public class AbstractTrapItem extends GUI_Item {
    public static Map<String, TrapRunner> runnerMap = new HashMap<>();

    public AbstractTrapItem(Icon _icon, int _Slot, TrapRunner run, ItemStack... _require) {
        this(_icon, _Slot, new ArrayList<>(Arrays.asList(_require)), run);
    }

    public AbstractTrapItem(Icon _icon, int _Slot, List<ItemStack> _require, TrapRunner run) {
        super(_icon, _Slot);
        getIcon().getLore().add("Range : " + run.getRange() + " blocks");
        this.require = _require;
        runnerMap.put(getIcon().getName(), run);
        List<String> list = new ArrayList<>(Arrays.asList(ChatColor.WHITE + "--- requires ---"));
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
                    if (!player.getInventory().containsAtLeast(item, item.getAmount()))
                        throw new AbstractSeller.NotRequire();
                }
                {
                    ItemStack item = getIcon().toItem();
                    ItemMeta meta = item.getItemMeta();
                    meta.getLore().add(ChatColor.DARK_GRAY + "(Trap)" + getIcon().getName());
                    item.setItemMeta(meta);

                    player.getInventory().addItem(item);
                }
                for (ItemStack item : require)
                    if (!Item_Utils.removeItems(player.getInventory(), item, item.getAmount()))
                        getLogger().info("Item remove error");
                player.playSound(player.getLocation(), Sound.NOTE_PIANO, 0.5f, 5);
                if (getIcon().getName() != null)
                    player.sendMessage(ChatColor.GREEN + "Buy " + getIcon().getName());
            } catch (AbstractSeller.NotRequire e) {
                player.sendMessage(ChatColor.RED + "You don`t have required item");
            }
        });
    }

    List<ItemStack> require;
}
