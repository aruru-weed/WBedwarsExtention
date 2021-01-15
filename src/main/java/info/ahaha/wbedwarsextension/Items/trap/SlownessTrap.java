package info.ahaha.wbedwarsextension.Items.trap;

import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlownessTrap implements TrapRunner {
    @Override
    public int getRange() {
        return 10;
    }

    @Override
    public void run(Player player) throws AbstractSeller.CanNotDo {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 2, false, false));
    }
}
