package info.ahaha.wbedwarsextension.Items.trap;

import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BlindnessTrap implements TrapRunner {
    @Override
    public int getRange() {
        return 20;
    }

    @Override
    public void run(Player player) throws AbstractSeller.CanNotDo {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 0, false, false));
    }
}
