package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.event.BedBreakEvent;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static info.ahaha.wbedwarsextension.WBedwarsExtension.getData;

public class BedBreakListener implements Listener {
    @EventHandler
    public void on(BedBreakEvent e) {
        WBedwarsExtension.PerIdData data = getData().get(e.getGame().getID());
        data.getTrapShop().getSpawn().use(e.getTeam(), e.getPlayer());
    }
}
