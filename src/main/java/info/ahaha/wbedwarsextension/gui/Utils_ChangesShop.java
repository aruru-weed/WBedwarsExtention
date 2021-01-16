package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.GUI;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.Icon.PotionIcon;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.wbedwarsextension.Items.SellItem;
import info.ahaha.wbedwarsextension.Items.SellItemRequireHead;
import info.ahaha.wbedwarsextension.Items.singItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Utils_ChangesShop extends GUI implements DragParts.dragRowOK {
    public Utils_ChangesShop(Game _game, List<abstract_GUI_Item> signs) {
        super(InventoryType.CHEST, "WBedwars-Util&ChangeShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //Utils
        addContents(signs);

        addContent(new SellItem(IconFactory.Make(Material.GOLDEN_APPLE), 10,
                new ItemStack(Material.GOLDEN_APPLE),
                new ItemStack(Material.GOLD_INGOT, 2)));
        addContent(new SellItem(IconFactory.Make(Material.SNOW_BALL, "Slowness"), 11,
                IconFactory.Make(Material.SNOW_BALL, "slowness").toItem(),
                new ItemStack(Material.IRON_INGOT, 40)));
        addContent(new SellItem(IconFactory.Make(Material.INK_SACK, "andamited`s poop").setDamage((short) 3), 12,
                IconFactory.Make(Material.INK_SACK, "andamited`s poop").setDamage((short) 3).toItem(),
                new ItemStack(Material.IRON_INGOT, 50),
                new ItemStack(Material.GOLDEN_APPLE, 2)));
        addContent(new SellItem(IconFactory.Make(Material.BONE, "wolf summoner"), 13,
                IconFactory.Make(Material.BONE, "WolfRod").toItem(),
                new ItemStack(Material.GOLD_INGOT, 5)));
        addContent(new SellItem(IconFactory.Make(Material.FIREBALL, ChatColor.RED + "Fireball^^"), 14,
                IconFactory.Make(Material.FIREBALL, ChatColor.RED + "Fireball^^").toItem(),
                new ItemStack(Material.IRON_INGOT, 40)));

        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW, ChatColor.AQUA + "FrozenBow").setDamage((short) 354), 15,
                IconFactory.Make(Material.BOW, ChatColor.AQUA + "FrozenBow").setDamage((short) 254).toItem(),
                4,
                new ItemStack(Material.EMERALD, 8)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW, ChatColor.YELLOW + "LightningBow").setDamage((short) 354), 16,
                IconFactory.Make(Material.BOW, ChatColor.YELLOW + "LightningBow").setDamage((short) 354).toItem(),
                4,
                new ItemStack(Material.EMERALD, 8)));
        Icon invPo = ((PotionIcon) IconFactory.Make(Material.POTION, ChatColor.GRAY + "InvisiblePotion 40s")).addPotionEffect(
                new PotionEffect(PotionEffectType.INVISIBILITY, 40 * 20, 0)).setDamage((short) 4);
        addContent(new SellItemRequireHead(invPo, 17,
                invPo.toItem(),
                4,
                new ItemStack(Material.EMERALD, 4)));
        Icon spePo = ((PotionIcon) IconFactory.Make(Material.POTION, ChatColor.BLUE + "SpeedPotion 30s")).addPotionEffect(
                new PotionEffect(PotionEffectType.SPEED, 30 * 20, 0));
        addContent(new SellItemRequireHead(spePo, 19,
                spePo.toItem(),
                1,
                new ItemStack(Material.EMERALD, 1)));
        PotionIcon jumPo = (PotionIcon) ((PotionIcon) IconFactory.Make(Material.POTION, ChatColor.GREEN + "JumpBoostPotion 30s")).addPotionEffect(
                new PotionEffect(PotionEffectType.JUMP, 30 * 20, 0)).setDamage((short) 8203);
        addContent(new SellItemRequireHead(jumPo, 20,
                jumPo.toItem(),
                4,
                new ItemStack(Material.EMERALD, 1)));
    }

    Game game;
}
