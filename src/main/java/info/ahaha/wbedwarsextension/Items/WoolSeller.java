package info.ahaha.wbedwarsextension.Items;

import info.ahaha.bedwars.API.Game;
import info.ahaha.bedwars.API.Team;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WoolSeller extends Per_Team_Item {
    public WoolSeller(int slot, Game game) {
        super(slot, game);
    }

    @Override
    public abstract_GUI_Item run(Team team) {
        short damage = 0;
        if (team != null)
            switch (team.getColor()) {
                case GOLD:
                    damage = 1;
                    break;
                case RED:
                    damage = 14;
                    break;
                case AQUA:
                    damage = 3;
                    break;
                case BLUE:
                    damage = 11;
                    break;
                case GRAY:
                    damage = 8;
                    break;
                case BLACK:
                    damage = 15;
                    break;
                case GREEN:
                    damage = 5;
                    break;
                case WHITE:
                    damage = 0;
                    break;
                case YELLOW:
                    damage = 4;
                    break;
                case DARK_RED:
                    damage = 14;
                    break;
                case DARK_AQUA:
                    damage = 9;
                    break;
                case DARK_BLUE:
                    damage = 11;
                    break;
                case DARK_GRAY:
                    damage = 7;
                    break;
                case DARK_GREEN:
                    damage = 13;
                    break;
                case DARK_PURPLE:
                    damage = 10;
                    break;
                case LIGHT_PURPLE:
                    damage = 6;
            }
        return new SellItem(IconFactory.Make(Material.WOOL, 16).setDamage(damage), this.getSlot(),
                new ItemStack(Material.WOOL, 16, damage),
                new ItemStack(Material.IRON_INGOT, 4)
        );
    }
}
