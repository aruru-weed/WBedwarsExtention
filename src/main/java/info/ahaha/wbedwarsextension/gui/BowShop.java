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
import info.ahaha.wbedwarsextension.Items.singItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BowShop extends GUI implements DragParts.dragRowOK {
    public BowShop(Game _game) {
        super(54, "WBedwars-ItemShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //Bows
        addContent(new singItem("Bows", 4));
        addContent(new SellItem(IconFactory.Make(Material.ARROW), 13,
                new ItemStack(Material.ARROW, 16),
                new ItemStack(Material.GOLD_INGOT, 2)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW), 13 + 9,
                new ItemStack(Material.BOW),
                2,
                new ItemStack(Material.GOLD_INGOT, 8)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW).addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_KNOCKBACK, 2)), 13 + 9 * 2,
                IconFactory.Make(Material.BOW, "Punch II").addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_KNOCKBACK, 2)).toItem(),
                4,
                new ItemStack(Material.BOW),
                new ItemStack(Material.GOLD_INGOT, 12)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW).addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_DAMAGE, 2)), 13 + 9 * 3,
                IconFactory.Make(Material.BOW, "Power II").addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_DAMAGE, 2)).toItem(),
                2,
                new ItemStack(Material.BOW),
                new ItemStack(Material.GOLD_INGOT, 12)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW, ChatColor.GOLD + "キッズ大好き").setDamage((short) 324).addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_DAMAGE, 32767)), 13 + 9 * 4,
                IconFactory.Make(Material.BOW, ChatColor.GOLD + "キッズ大好き").setDamage((short) 324).addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_KNOCKBACK, 32767)).toItem(),
                15,
                IconFactory.Make(Material.BOW, "Punch II").addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_KNOCKBACK, 2)).toItem(),
                IconFactory.Make(Material.BOW, "Power II").addEnchant(new Icon.EnchantMeta(Enchantment.ARROW_DAMAGE, 2)).toItem()));

    }

    Game game;
}
