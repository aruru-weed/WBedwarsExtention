package info.ahaha.wbedwarsextension.Items.armor;

import info.ahaha.bedwars.API.Game;
import info.ahaha.bedwars.API.Team;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Random;

public class LeatherArmorSeller extends AbstractSeller {
    public LeatherArmorSeller(Game game, Icon _icon, int _Slot, ItemStack... _require) {
        super(_icon, _Slot, _require);
        this.game = game;
    }

    Game game;

    @Override
    public void run(Player player) {
        Random r = new Random();
        Color color = Color.fromRGB(r.nextInt(16 * 16 + 1), r.nextInt(16 * 16 + 1), r.nextInt(16 * 16 + 1));
        setAll(player, color);
    }

    public static Color GetTeamColor(Team team) {
        Color color = Color.BLACK;
        switch (team.getColor()) {
            case GOLD:
                color = Color.ORANGE;
                break;
            case RED:
                color = Color.fromRGB(0xA12722);
                break;
            case AQUA:
                color = Color.AQUA;
                break;
            case BLUE:
                color = Color.BLUE;
                break;
            case GRAY:
                color = Color.GRAY;
                break;
            case BLACK:
                color = Color.BLACK;
                break;
            case GREEN:
                color = Color.GREEN;
                break;
            case WHITE:
                color = Color.WHITE;
                break;
            case YELLOW:
                color = Color.YELLOW;
                break;
            case DARK_RED:
                color = Color.fromRGB(0x963430);
                break;
            case DARK_AQUA:
                color = Color.fromRGB(0x2E6E89);
                break;
            case DARK_BLUE:
                color = Color.fromRGB(0x2E388D);
                break;
            case DARK_GRAY:
                color = Color.fromRGB(0x404040);
                break;
            case DARK_GREEN:
                color = Color.fromRGB(0x35461B);
                break;
            case DARK_PURPLE:
                color = Color.fromRGB(0x7E3DB5);
                break;
            case LIGHT_PURPLE:
                color = Color.fromRGB(0xD08499);
        }
        return color;
    }

    public static void setAll(Player player, Color color) {
        LeatherArmorMeta meta;
        ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
        meta = (LeatherArmorMeta) leg.getItemMeta();
        meta.setColor(color);
        leg.setItemMeta(meta);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        meta = (LeatherArmorMeta) boots.getItemMeta();
        meta.setColor(color);
        boots.setItemMeta(meta);
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET), chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setColor(color);
        helmet.setItemMeta(meta);
        meta = (LeatherArmorMeta) chestplate.getItemMeta();
        meta.setColor(color);
        chestplate.setItemMeta(meta);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setLeggings(leg);
        player.getInventory().setBoots(boots);
    }
}
