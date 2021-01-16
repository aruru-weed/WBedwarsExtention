package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.event.WBWGameEndEvent;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EndListener implements Listener {
    @EventHandler
    public void on(WBWGameEndEvent e) {
        WBedwarsExtension.getData().get(e.getID()).remove();
        WBedwarsExtension.getData().remove(e.getID());
    }
}
