package info.ahaha.wbedwarsextension;

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

public class ItemShop extends GUI implements DragParts.dragRowOK {
    public ItemShop(Game _game) {
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
        //Armors
        addContent(new singItem("Armors", 3));
        addContent(new LeatherArmorSeller(game, IconFactory.Make(Material.LEATHER_LEGGINGS, ChatColor.MAGIC + "" + ChatColor.UNDERLINE + "Random Color Lether Armors"), 12,
                new ItemStack(Material.IRON_INGOT, 10)));
        addContent(new IronArmorSeller(game, IconFactory.Make(Material.IRON_LEGGINGS, ChatColor.GRAY + "Iron Armors"), 12 + 9 * 1,
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
        //Tools
        addContent(new singItem("Tools", 5));

        //Utils
        addContent(new singItem("Utils & Changes", 6));
        addContent(new SellItem(IconFactory.Make(Material.GOLDEN_APPLE), 15,
                new ItemStack(Material.GOLDEN_APPLE),
                new ItemStack(Material.GOLD_INGOT, 2)));
        addContent(new SellItem(IconFactory.Make(Material.SNOW_BALL, "Slowness"), 15 + 9,
                IconFactory.Make(Material.SNOW_BALL, "slowness").toItem(),
                new ItemStack(Material.IRON_INGOT, 40)));
        addContent(new SellItem(IconFactory.Make(Material.INK_SACK, "andamited`s poop").setDamage((short) 3), 15 + 9 * 2,
                IconFactory.Make(Material.INK_SACK, "andamited`s poop").setDamage((short) 3).toItem(),
                new ItemStack(Material.IRON_INGOT, 50),
                new ItemStack(Material.GOLDEN_APPLE, 2)));
        addContent(new SellItem(IconFactory.Make(Material.BONE, "wolf summoner"), 15 + 9 * 3,
                IconFactory.Make(Material.BONE, "WolfRod").toItem(),
                new ItemStack(Material.GOLD_INGOT, 5)));
        addContent(new SellItem(IconFactory.Make(Material.FIREBALL, ChatColor.RED + "Fireball^^"), 15 + 9 * 4,
                IconFactory.Make(Material.FIREBALL, ChatColor.RED + "Fireball^^").toItem(),
                new ItemStack(Material.IRON_INGOT, 40)));
        //         space x1

        //Change
        addContent(new GUI_Item(IconFactory.Make(Material.STAINED_GLASS_PANE, " ").setDamage((short) 3), 7));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW, ChatColor.AQUA + "FrozenBow").setDamage((short) 354), 16,
                IconFactory.Make(Material.BOW, ChatColor.AQUA + "FrozenBow").setDamage((short) 254).toItem(),
                4,
                new ItemStack(Material.EMERALD, 8)));
        addContent(new SellItemRequireHead(IconFactory.Make(Material.BOW, ChatColor.YELLOW + "LightningBow").setDamage((short) 354), 16 + 9,
                IconFactory.Make(Material.BOW, ChatColor.YELLOW + "LightningBow").setDamage((short) 354).toItem(),
                4,
                new ItemStack(Material.EMERALD, 8)));
        Icon invPo = ((PotionIcon) IconFactory.Make(Material.POTION, ChatColor.GRAY + "InvisiblePotion 40s")).addPotionEffect(
                new PotionEffect(PotionEffectType.INVISIBILITY, 40 * 20, 0)).setDamage((short) 4);
        addContent(new SellItemRequireHead(invPo, 16 + 9 * 2,
                invPo.toItem(),
                4,
                new ItemStack(Material.EMERALD, 4)));
        Icon spePo = ((PotionIcon) IconFactory.Make(Material.POTION, ChatColor.BLUE + "SpeedPotion 30s")).addPotionEffect(
                new PotionEffect(PotionEffectType.SPEED, 30 * 20, 0));
        addContent(new SellItemRequireHead(spePo, 16 + 9 * 3,
                spePo.toItem(),
                1,
                new ItemStack(Material.EMERALD, 1)));
        PotionIcon jumPo = (PotionIcon) ((PotionIcon) IconFactory.Make(Material.POTION, ChatColor.GREEN + "JumpBoostPotion 30s")).addPotionEffect(
                new PotionEffect(PotionEffectType.JUMP, 30 * 20, 0)).setDamage((short) 8203);
        addContent(new SellItemRequireHead(jumPo, 16 + 9 * 4,
                jumPo.toItem(),
                4,
                new ItemStack(Material.EMERALD, 1)));
    }

    Game game;
}
