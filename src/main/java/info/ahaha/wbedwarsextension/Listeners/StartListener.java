package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.Team;
import info.ahaha.bedwars.API.event.WBWGameStartEvent;
import info.ahaha.wbedwarsextension.Items.armor.LeatherArmorSeller;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static info.ahaha.wbedwarsextension.Items.armor.LeatherArmorSeller.GetTeamColor;

public class StartListener implements Listener {
    @EventHandler
    public void on(WBWGameStartEvent e) {
        for (Team team : e.getGame().getTeams())
            for (OfflinePlayer player : team.getPlayers())
                if (player.isOnline()) {
                    player.getPlayer().getInventory().clear();
                    LeatherArmorSeller.setAll(player.getPlayer(), GetTeamColor(team));
                }
    }
}
