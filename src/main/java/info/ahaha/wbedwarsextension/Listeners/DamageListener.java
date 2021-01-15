package info.ahaha.wbedwarsextension.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import particleeffect.ParticleEffect;

import java.util.*;

import static java.lang.Math.abs;
import static org.bukkit.Bukkit.*;

public class DamageListener implements Listener {
    static Random r = new Random();

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        switch (e.getCause()) {
            case ENTITY_ATTACK: {
                if (!(e.getDamager() instanceof Player))
                    switch (e.getDamager().getType()) {
                        case WOLF: {
                            e.getDamager().getWorld().playSound(e.getDamager().getLocation(), Sound.PORTAL, 3, 1);
                            ParticleEffect.SMOKE_LARGE.display(0.3f, 0.8f, 0.3f, 0.2f, 100, e.getDamager().getLocation(), 300);
                            e.getDamager().remove();
                            return;
                        }
                    }

                ItemStack item = ((Player) e.getDamager()).getInventory().getItemInHand();
                if (item.hasItemMeta())
                    if (item.getItemMeta().hasDisplayName()) {
                        switch (ChatColor.stripColor(item.getItemMeta().getDisplayName())) {
                            case "Knockback大好き": {
                                item.setAmount(item.getAmount() - 1);
                                ((Player) e.getDamager()).getInventory().setItemInHand(item);

                                Vector vec = new Vector();
                                double x = (e.getEntity().getLocation().getX() - e.getDamager().getLocation().getX()),
                                        z = (e.getEntity().getLocation().getZ() - e.getDamager().getLocation().getZ()),
                                        distance = Math.pow(x * x + z * z, 0.5),
                                        rad = Math.atan2(z, x);
                                vec.setX(abs((4.8 - distance) * Math.cos(rad)) * 2.5 * Math.signum(x));
                                vec.setZ(abs((4.8 - distance) * Math.sin(rad)) * 2.5 * Math.signum(z));

                                double y = (e.getEntity().getLocation().getY() - e.getDamager().getLocation().getY());
                                vec.setY(y < 0 ? y * 0.5 : (y + 1) * 0.5);

                                e.getEntity().setVelocity(e.getEntity().getVelocity().add(vec));
                                return;
                            }
                            case "Knock back1000": {
                                if (r.nextInt(10) == 1) {
                                    item.setAmount(item.getAmount() - 1);
                                    ((Player) e.getDamager()).getInventory().setItemInHand(item);
                                    ((Player) e.getDamager()).playSound(e.getDamager().getLocation(), Sound.ITEM_BREAK, 1, 1);
                                }
                                return;
                            }
                        }
                    }
            }
            break;
            case FALL: {
                if (e.getEntity() instanceof Player)
                    if (((Player) e.getEntity()).getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
                        e.setCancelled(true);
                        return;
                    }
            }
            case PROJECTILE: {
                switch (e.getDamager().getType()) {
                    case ARROW: {
                        if (e.getDamager().getCustomName() != null)
                            switch (ChatColor.stripColor(e.getDamager().getCustomName())) {
                                case "FrozenBow": {
                                    List<Block> list = new ArrayList<>();
                                    Block PPos = e.getEntity().getLocation().getBlock();
                                    list.add(PPos.getRelative(0, -1, 0));
                                    list.add(PPos.getRelative(0, 2, 0));
                                    list.add(PPos.getRelative(1, 0, 0));
                                    list.add(PPos.getRelative(-1, 0, 0));
                                    list.add(PPos.getRelative(0, 0, 1));
                                    list.add(PPos.getRelative(0, 0, -1));
                                    list.add(PPos.getRelative(1, 1, 0));
                                    list.add(PPos.getRelative(-1, 1, 0));
                                    list.add(PPos.getRelative(0, 1, 1));
                                    list.add(PPos.getRelative(0, 1, -1));
                                    Location loc = e.getEntity().getLocation().clone(), setter = PPos.getLocation().add(0.5, 0.2, 0.5);
                                    loc.setX(setter.getX());
                                    loc.setY(setter.getY());
                                    loc.setZ(setter.getZ());
                                    e.getEntity().teleport(e.getEntity().getLocation(loc));
                                    e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.GLASS, 3, 5);
                                    e.getEntity().setVelocity(new Vector());
                                    Map<Block, Material> map = new HashMap<>();
                                    for (Block block : list) {
                                        switch (block.getType()) {
                                            case CHEST:
                                            case BED_BLOCK:
                                            case DIAMOND_ORE:
                                            case EMERALD_ORE:
                                                continue;
                                            default:
                                                map.put(block, block.getType());
                                                block.setType(Material.ICE);
                                        }
                                    }
                                    getScheduler().runTaskLater(getPluginManager().getPlugin("WBedwarsExtension"), () -> {
                                        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.GLASS, 3, 5);
                                        for (Block block : list) {
                                            if (block.getType() == Material.ICE) {
                                                block.setType(map.get(block));
                                            }
                                        }
                                    }, 20 * 5);
                                    break;
                                }
                            }
                    }
                }
                break;
            }
        }
    }
}
