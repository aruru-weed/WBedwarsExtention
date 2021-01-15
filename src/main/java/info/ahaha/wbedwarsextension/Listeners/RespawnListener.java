package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.event.PlayerRespawnByBedwarsEvent;
import info.ahaha.wbedwarsextension.Items.armor.DiaArmorSeller;
import info.ahaha.wbedwarsextension.Items.armor.IronArmorSeller;
import info.ahaha.wbedwarsextension.Items.armor.LeatherArmorSeller;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static info.ahaha.wbedwarsextension.Items.armor.LeatherArmorSeller.GetTeamColor;
import static info.ahaha.wbedwarsextension.WBedwarsExtension.getData;

public class RespawnListener implements Listener {
    @EventHandler
    public void on(PlayerRespawnByBedwarsEvent e) {
        LeatherArmorSeller.setAll(e.getPlayer(), GetTeamColor(e.getTeam()));
        switch (getData().get(e.getGame().getID()).getArmorTypes().get(e.getPlayer().getUniqueId())) {
            case Iron:
                IronArmorSeller.set(e.getPlayer());
                break;
            case Dia:
                DiaArmorSeller.set(e.getPlayer());
                break;
            case Leather:
                break;
        }
    }
}
