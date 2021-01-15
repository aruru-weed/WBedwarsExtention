package info.ahaha.wbedwarsextension.Items.trap;

import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import org.bukkit.entity.Player;

public interface TrapRunner {
    int getRange();

    void run(Player player) throws AbstractSeller.CanNotDo;
}
