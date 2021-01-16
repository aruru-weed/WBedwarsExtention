package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.wbedwarsextension.SaveAndLoad;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class QuitListener implements Listener {
    @EventHandler
    public void on(PlayerQuitEvent e) {
        try {
            SaveAndLoad.Save(e.getPlayer());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
