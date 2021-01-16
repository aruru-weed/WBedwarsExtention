package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.wbedwarsextension.SaveAndLoad;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class JoinListener implements Listener {
    @EventHandler
    public void on(PlayerJoinEvent e){
        try {
            SaveAndLoad.Load(e.getPlayer());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
