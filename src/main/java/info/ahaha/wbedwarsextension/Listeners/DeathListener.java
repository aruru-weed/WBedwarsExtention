package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.Game;
import info.ahaha.bedwars.API.Team;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class DeathListener implements Listener {
    @EventHandler
    public void on(PlayerDeathEvent e) {
        Game game = WBedwarsExtension.getApi().findGame(e.getEntity().getLocation().getWorld().getName());
        if (game == null)
            return;
        Team team = game.getTeam(e.getEntity());

        ArrayList<ItemStack> list = new ArrayList<>();
        for (ItemStack drop : e.getDrops())
            switch (drop.getType()) {
                case GOLD_INGOT:
                case IRON_INGOT:
                case EMERALD:
                case DIAMOND:
                case NETHER_STAR:
                case OBSIDIAN:
                    list.add(drop);
                case SKULL_ITEM:
                case GOLDEN_APPLE:
                    team.getResourceGeneratorPos().getWorld()
                            .dropItem(team.getResourceGeneratorPos(),drop);
            }
        e.getDrops().clear();
        e.getDrops().addAll(list);
    }
}
