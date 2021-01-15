package info.ahaha.wbedwarsextension.Items.armor;

import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.wbedwarsextension.Items.AbstractSellerRequireHead;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

import static java.lang.Math.*;
import static org.bukkit.Bukkit.*;

public class IppouTuukouArmorSeller extends AbstractSellerRequireHead {
    public IppouTuukouArmorSeller(Icon _icon, int _Slot, int Amount, ItemStack... _require) {
        super(_icon, _Slot, Amount, _require);
    }

    @Override
    public void run(Player player) {
        player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
        getScheduler().runTaskLater(getPluginManager().getPlugin("WBedwarsExtension"), new task(player), 5);
    }

    static class task implements Runnable {
        task(Player player) {
            this.player = player;
        }

        Player player;
        static Random r = new Random();

        @Override
        public void run() {
            for (Entity entity : player.getNearbyEntities(5, 5, 5))
                if (entity instanceof Projectile)
                    if (pow(pow(entity.getLocation().getX() - player.getLocation().getX(), 2) + pow(entity.getLocation().getZ() - player.getLocation().getZ(), 2), 0.5) <= 5) {
                        if (!(((Projectile) entity).getShooter() instanceof Entity))
                            continue;
                        if (((Projectile) entity).getShooter() == player)
                            continue;
                        if (entity.isOnGround())
                            continue;
                        if (entity.getCustomName() != null)
                            if (entity.getCustomName().equals("accelerator" + player.getName()))
                                continue;

                        entity.setCustomName("accelerator" + player.getName());
                        Entity shooter = (Entity) ((Projectile) entity).getShooter();
                        double
                                deg = atan2(
                                entity.getLocation().getZ() - shooter.getLocation().getZ(),
                                entity.getLocation().getX() - shooter.getLocation().getX()
                        ),
                                power = pow(pow(entity.getVelocity().getX(), 2) + pow(entity.getVelocity().getZ(), 2), 0.5),
                                distance = pow(pow(entity.getLocation().getZ() - shooter.getLocation().getZ(), 2) +
                                        pow(entity.getLocation().getX() - shooter.getLocation().getX(), 2), 0.5);

                        double time = distance / power;

                        double a = 0.05;

                        double xVec = (entity.getLocation().getY() - shooter.getLocation().getY()) / time
                                + 0.5 * a * time;

                        getLogger().info(xVec / 20 + " : " + time + " : " + power);

                        entity.setVelocity(new Vector(cos(deg) * -power
                                , xVec / 20
                                , sin(deg) * -power));
                    }
            getScheduler().runTaskLater(getPluginManager().getPlugin("WBedwarsExtension"), this, 2/*r.nextInt(5) + 5*/);
        }
    }
}
