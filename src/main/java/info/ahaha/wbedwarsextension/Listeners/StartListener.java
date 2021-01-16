package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.Team;
import info.ahaha.bedwars.API.event.WBWGameStartEvent;
import info.ahaha.wbedwarsextension.Items.armor.ArmorType;
import info.ahaha.wbedwarsextension.Items.armor.LeatherArmorSeller;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import info.ahaha.wbedwarsextension.gui.ItemShop;
import info.ahaha.wbedwarsextension.gui.TrapShop;
import info.ahaha.wbedwarsextension.trap.SearchPlayerTask;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static info.ahaha.wbedwarsextension.Items.armor.LeatherArmorSeller.GetTeamColor;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getScheduler;

public class StartListener implements Listener {
    @EventHandler
    public void on(WBWGameStartEvent e) {
        WBedwarsExtension.PerIdData data = new WBedwarsExtension.PerIdData();
        WBedwarsExtension.getData().put(e.getGame().getID(), data);
        data.setItemShop(new ItemShop(e.getGame()));
        data.setTrapShop(new TrapShop(e.getGame()));
        data.setSearchID(getScheduler().scheduleSyncRepeatingTask(getPluginManager().getPlugin("WBedwarsExtension"), new SearchPlayerTask(e.getGame()), 2, 2));
        for (Team team : e.getGame().getTeams()) {
            for (OfflinePlayer player : team.getPlayers()) {
                if (player.isOnline()) {
                    player.getPlayer().getInventory().clear();
                    LeatherArmorSeller.setAll(player.getPlayer(), GetTeamColor(team));
                }
                WBedwarsExtension.PerIdData.PerPlayerData PData = new WBedwarsExtension.PerIdData.PerPlayerData();
                PData.setArmor(ArmorType.Leather);
                data.getPlayerDataMap().put(player.getUniqueId(), PData);
            }
        }
    }
}
