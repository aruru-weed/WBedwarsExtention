package info.ahaha.wbedwarsextension.Items;

import info.ahaha.bedwars.API.Game;
import info.ahaha.bedwars.API.Team;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.extended.items.AbstractPerPlayerItem;
import org.bukkit.entity.Player;

public abstract class Per_Team_Item extends AbstractPerPlayerItem {
    public Per_Team_Item(int slot, Game game) {
        super(slot);
        this.game = game;
    }

    Game game;

    public abstract abstract_GUI_Item run(Team team);

    @Override
    public abstract_GUI_Item run(Player player) {
        return run(game.getTeam(player));
    }

    @Override
    public abstract_GUI_Item getDefault() {
        return null;
    }
}
