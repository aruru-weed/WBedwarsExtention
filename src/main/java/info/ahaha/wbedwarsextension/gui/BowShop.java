package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.GUI;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.wbedwarsextension.Items.SellItem;
import info.ahaha.wbedwarsextension.Items.SellItemRequireHead;
import info.ahaha.wbedwarsextension.Items.singItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BowShop extends GUI implements DragParts.dragRowOK {
    public BowShop(Game _game, List<abstract_GUI_Item> signs) {
        super(54, "WBedwars-BowShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //Bows
        addContents(signs);
        addContent(new SellItem(IconFactory.Make(Material.ARROW), 10,
                new ItemStack(Material.ARROW, 16),
                new ItemStack(Material.GOLD_INGOT, 2)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW), 11,
                new ItemStack(Material.BOW),
                2,
                new ItemStack(Material.GOLD_INGOT, 8)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW).addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_KNOCKBACK, 2)), 12,
                IconFactory.Make(Material.BOW, "Punch II").addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_KNOCKBACK, 2)).toItem(),
                4,
                new ItemStack(Material.BOW),
                new ItemStack(Material.GOLD_INGOT, 12)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW).addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_DAMAGE, 2)), 13,
                IconFactory.Make(Material.BOW, "Power II").addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_DAMAGE, 2)).toItem(),
                2,
                new ItemStack(Material.BOW),
                new ItemStack(Material.GOLD_INGOT, 14)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW,
                ChatColor.GOLD + "キッズ大好き").setDamage((short) 324).addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_DAMAGE, 32767)), 15,
                IconFactory.Make(Material.BOW, ChatColor.GOLD + "キッズ大好き").setDamage((short) 324).addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_KNOCKBACK, 32767)).toItem(),
                15,
                IconFactory.Make(Material.BOW, "Punch II").addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_KNOCKBACK, 2)).toItem(),
                IconFactory.Make(Material.BOW, "Power II").addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_DAMAGE, 2)).toItem()));

    }

    Game game;
}
