package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.GUI;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.GUI_Item;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.Icon.PotionIcon;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.wbedwarsextension.Items.SellItem;
import info.ahaha.wbedwarsextension.Items.SellItemRequireHead;
import info.ahaha.wbedwarsextension.Items.armor.*;
import info.ahaha.wbedwarsextension.Items.singItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArmorShop extends GUI implements DragParts.dragRowOK {
    public ArmorShop(Game _game) {
        super(54, "WBedwars-ItemShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //Armors
        addContent(new singItem("Armors", 3));
        addContent(new LeatherArmorSeller(game, IconFactory.Make(Material.LEATHER_LEGGINGS, ChatColor.MAGIC + "" + ChatColor.UNDERLINE + "Random Color Lether Armors"), 12,
                new ItemStack(Material.IRON_INGOT, 10)));
        addContent(new IronArmorSeller(game, IconFactory.Make(Material.IRON_LEGGINGS, ChatColor.GRAY + "Iron Armors"), 12 + 9,
                new ItemStack(Material.GOLD_INGOT, 8)));
        addContent(new DiaArmorSeller(game, IconFactory.Make(Material.DIAMOND_LEGGINGS, ChatColor.AQUA + "Diamond Armors"), 12 + 9 * 2,
                4,
                new ItemStack(Material.IRON_INGOT, 4),
                new ItemStack(Material.GOLD_INGOT, 4),
                new ItemStack(Material.DIAMOND, 4),
                new ItemStack(Material.EMERALD, 4)));
        addContent(new FallingCancelArmorSeller(IconFactory.Make(Material.CHAINMAIL_BOOTS, ChatColor.DARK_GRAY + "FallingDamageCancelArmor"), 12 + 9 * 3,
                new ItemStack(Material.EMERALD, 4),
                new ItemStack(Material.IRON_INGOT, 32)));
        addContent(new IppouTuukouArmorSeller(IconFactory.Make(Material.GOLD_HELMET, ChatColor.GOLD + "反射", "Level6 shift"), 12 + 9 * 4,
                10,
                new ItemStack(Material.EMERALD, 4),
                new ItemStack(Material.IRON_INGOT, 32)));


    }

    Game game;
}
