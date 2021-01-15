package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.GUI;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.GUI_Item;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.Icon.PotionIcon;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.wbedwarsextension.Items.*;
import info.ahaha.wbedwarsextension.Items.armor.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BlockShop extends GUI implements DragParts.dragRowOK {
    public BlockShop(Game _game) {
        super(54, "WBedwars-ItemShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //blocks
        addContent(new singItem("Blocks", 1));
        addContent(new WoolSeller(10, game));
        addContent(new SellItem(IconFactory.Make(Material.WOOD, 8), 10 + 9,
                new ItemStack(Material.WOOD, 8),
                new ItemStack(Material.GOLD_INGOT, 2),
                new ItemStack(Material.IRON_INGOT, 2)));
        addContent(new SellItem(IconFactory.Make(Material.SAND, 4), 10 + 9 * 2,
                new ItemStack(Material.SAND, 4),
                new ItemStack(Material.GOLD_INGOT, 4)));
        addContent(new SellItem(IconFactory.Make(Material.QUARTZ_BLOCK, 16), 10 + 9 * 3,
                new ItemStack(Material.QUARTZ_BLOCK, 16),
                new ItemStack(Material.IRON_INGOT, 32)));
        addContent(new SellObs(IconFactory.Make(Material.OBSIDIAN, 1), 10 + 9 * 4,
                new ItemStack(Material.OBSIDIAN, 1),
                new ItemStack(Material.GOLD_INGOT, 1),
                new ItemStack(Material.EMERALD, 1)));

    }

    Game game;
}
