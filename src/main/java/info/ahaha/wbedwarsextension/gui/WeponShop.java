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

public class WeponShop extends GUI implements DragParts.dragRowOK {
    public WeponShop(Game _game) {
        super(54, "WBedwars-ItemShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //weapons
        addContent(new singItem("Weapons", 2));
        addContent(new SellItem(IconFactory.Make(Material.STONE_SWORD), 11,
                new ItemStack(Material.STONE_SWORD, 1),
                new ItemStack(Material.GOLD_INGOT, 10)));
        addContent(new SellItem(IconFactory.Make(Material.IRON_SWORD), 11 + 9,
                new ItemStack(Material.IRON_SWORD, 1),
                new ItemStack(Material.GOLD_INGOT, 9)));
        addContent(new SellItem(IconFactory.Make(Material.DIAMOND_SWORD), 11 + 9 * 2,
                new ItemStack(Material.DIAMOND_SWORD, 1),
                new ItemStack(Material.EMERALD, 2),
                new ItemStack(Material.IRON_SWORD, 1)));
        addContent(new SellItem(IconFactory.Make(Material.STICK, "Knockback大好き"), 11 + 9 * 3,
                IconFactory.Make(Material.STICK, ChatColor.RED + "Knockback大好き",
                        ChatColor.WHITE + "一回しか使えない", ChatColor.WHITE + "相手との距離が近いほどよく飛ぶ").toItem(),
                new ItemStack(Material.EMERALD, 2)));
        addContent(new SellItem(IconFactory.Make(Material.WOOD, ChatColor.DARK_RED + "Knock back1000"), 11 + 9 * 4,
                IconFactory.Make(Material.WOOD, "Knock back1000", ChatColor.GOLD + "10%の確率で壊れる", "Knockback大好きで作った木材").addEnchant(new Icon.EnchantMeta(Enchantment.KNOCKBACK, 1000)).toItem(),
                IconFactory.Make(Material.STICK, "Knockback大好き",
                        ChatColor.WHITE + "一回しか使えない", ChatColor.WHITE + "相手との距離が近いほどよく飛ぶ").toItem()));

    }

    Game game;
}
