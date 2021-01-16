package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.GUI;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.wbedwarsextension.Items.armor.*;
import info.ahaha.wbedwarsextension.Items.singItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ArmorShop extends GUI implements DragParts.dragRowOK {
    public ArmorShop(Game _game, List<abstract_GUI_Item> signs) {
        super(InventoryType.CHEST, "WBedwars-ArmorShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //Armors
        addContents(signs);

        addContent(new LeatherArmorSeller(game, IconFactory.Make(Material.LEATHER_LEGGINGS, ChatColor.MAGIC + "" + ChatColor.UNDERLINE + "Random Color Lether Armors"), 10,
                new ItemStack(Material.IRON_INGOT, 10)));
        addContent(new IronArmorSeller(game, IconFactory.Make(Material.IRON_LEGGINGS, ChatColor.GRAY + "Iron Armors"), 11,
                new ItemStack(Material.GOLD_INGOT, 8)));
        addContent(new DiaArmorSeller(game, IconFactory.Make(Material.DIAMOND_LEGGINGS, ChatColor.AQUA + "Diamond Armors"), 12,
                4,
                new ItemStack(Material.IRON_INGOT, 4),
                new ItemStack(Material.GOLD_INGOT, 4),
                new ItemStack(Material.DIAMOND, 4),
                new ItemStack(Material.EMERALD, 4)));
        addContent(new FallingCancelArmorSeller(IconFactory.Make(Material.CHAINMAIL_BOOTS, ChatColor.DARK_GRAY + "FallingDamageCancelArmor"), 13,
                new ItemStack(Material.EMERALD, 4),
                new ItemStack(Material.IRON_INGOT, 32)));
        addContent(new IppouTuukouArmorSeller(IconFactory.Make(Material.GOLD_HELMET, ChatColor.GOLD + "反射", "Level6 shift"), 14,
                10,
                new ItemStack(Material.EMERALD, 4),
                new ItemStack(Material.IRON_INGOT, 32)));
    }

    Game game;
}
