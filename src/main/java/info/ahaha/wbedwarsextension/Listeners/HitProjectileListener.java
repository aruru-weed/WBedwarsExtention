package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.Team;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import particleeffect.ParticleEffect;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.pow;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getScheduler;

public class HitProjectileListener implements Listener {
    @EventHandler
    public void on(ProjectileHitEvent e) {
        switch (e.getEntity().getType()) {
            case SNOWBALL: {
                if (e.getEntity().getCustomName() != null)
                    switch (ChatColor.stripColor(e.getEntity().getCustomName())) {
                        case "Slowness":
                            e.getEntity().getWorld().spigot().playEffect(e.getEntity().getLocation(), Effect.CLOUD,
                                    0, 1, 0, 0, 0, 0.1f, 120, 200);
                            for (Entity entity : e.getEntity().getNearbyEntities(4, 4, 4))
                                if (entity instanceof Player)
                                    ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 1));
                            return;
                    }
                break;
            }
            case ARROW: {
                switch (ChatColor.stripColor(e.getEntity().getCustomName())) {
                    case "LightningBow":
                        e.getEntity().getWorld().spigot().strikeLightning(e.getEntity().getLocation(), false);
                        return;
                }

                break;
            }
        }
    }

    @EventHandler
    public void on2(PlayerInteractEvent e) {
        switch (e.getAction()) {
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK: {
                if (e.getItem() == null)
                    return;
                Class<? extends Projectile> c = null;

                if (e.getItem().hasItemMeta())
                    if (e.getItem().getItemMeta().hasDisplayName())
                        switch (e.getItem().getType()) {
                            case INK_SACK: {
                                e.setCancelled(true);
                                Projectile projectile = e.getPlayer().launchProjectile(Egg.class);
                                projectile.setCustomName("poop");

                                ItemStack poopItem = e.getItem().clone();
                                poopItem.setAmount(1);
                                getScheduler().runTaskLater(getPluginManager().getPlugin("WBedwarsExtension"), () -> {
                                    Item poop = (Item) projectile.getWorld().dropItem(projectile.getLocation(), poopItem);

                                    projectile.setPassenger(poop);

                                    poop.setPickupDelay(20 * 15);
                                    poop.getVelocity().add(projectile.getVelocity());
                                    poop.setItemStack(poopItem);

                                    AtomicInteger count = new AtomicInteger();
                                    AtomicInteger proId = new AtomicInteger();
                                    proId.set(getScheduler().scheduleSyncRepeatingTask(getPluginManager().getPlugin("WBedwarsExtension"), () -> {
                                        if (projectile.isDead()) {
                                            getScheduler().cancelTask(proId.get());
                                            AtomicInteger poopId = new AtomicInteger();

                                            poopId.set(getScheduler().scheduleSyncRepeatingTask(getPluginManager().getPlugin("WBedwarsExtension"), () -> {
                                                count.getAndIncrement();
                                                if (poop.getPickupDelay() < 10) {
                                                    getScheduler().cancelTask(poopId.get());
                                                    poop.remove();
                                                    return;
                                                }
                                                if (count.get() % 5 == 0) {
                                                    for (Entity entity : poop.getNearbyEntities(5, 1, 5))
                                                        if (entity instanceof Player) {
                                                            boolean go = true;
                                                            for (PotionEffect effect : ((Player) entity).getActivePotionEffects())
                                                                if (effect.getType() == PotionEffectType.CONFUSION)
                                                                    go = false;
                                                            if (go)
                                                                if (pow(pow(entity.getLocation().getX() - poop.getLocation().getX(), 2) + pow(entity.getLocation().getZ() - poop.getLocation().getZ(), 2), 0.5) <= 5)
                                                                    ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, poop.getPickupDelay(), 5));
                                                        }
                                                }

                                                for (int i = 0; i < 50; i++) {
                                                    double distance = Math.random() * 5,
                                                            deg = Math.random() * 360;
                                                    ParticleEffect.SPELL_MOB.display(
                                                            0.4f,
                                                            0.3f,
                                                            0.2f, 1, 0, poop.getLocation().add(
                                                                    Math.sin(Math.toRadians(deg)) * distance, (Math.random() / 2) + 0.25, Math.cos(Math.toRadians(deg)) * distance), 300);
                                                }
                                            }, 1, 1));

                                            return;
                                        }
                                        for (int i = 0; i < 4; i++) {
                                            ParticleEffect.REDSTONE.display(
                                                    0.5f,
                                                    0.3f,
                                                    0.2f, 1, 0, projectile.getLocation().add(Math.random() / 4, Math.random() / 4, Math.random() / 4), 300);
                                        }
                                    }, 1, 1));
                                }, 1);
                                e.getItem().setAmount(e.getItem().getAmount() - 1);
                                return;
                            }
                            case SNOW_BALL:
                                if (c == null)
                                    c = Snowball.class;
                            case EGG:
                                if (c == null)
                                    c = Snowball.class;
                            case ENDER_PEARL:
                                if (c == null)
                                    c = EnderPearl.class;
                            case FIREBALL:
                                if (c == null)
                                    c = Fireball.class;
                                e.setCancelled(true);
                                Projectile projectile = e.getPlayer().launchProjectile(c);
                                projectile.setCustomName(e.getItem().getItemMeta().getDisplayName());
                                e.getItem().setAmount(e.getItem().getAmount() - 1);
                            case BONE: {
                                Team team = WBedwarsExtension.getApi().findGame(e.getPlayer().getWorld().getName()).getTeam(e.getPlayer());

                                if (team != null)
                                    for (int i = 0; i < 3; i++) {
                                        Wolf wolf = (Wolf) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.WOLF);
                                        wolf.setOwner(e.getPlayer());
                                        wolf.setCustomName("Helper for "+e.getPlayer().getName());
                                        DyeColor color = DyeColor.BLACK;

                                        switch (team.getColor()) {
                                            case LIGHT_PURPLE:
                                                color = DyeColor.PURPLE;
                                                break;
                                            case DARK_PURPLE:
                                                color = DyeColor.MAGENTA;
                                                break;
                                            case BLACK:
                                                color = DyeColor.BLACK;
                                                break;
                                            case DARK_BLUE:
                                                color = DyeColor.LIGHT_BLUE;
                                                break;
                                            case DARK_GREEN:
                                                color = DyeColor.GREEN;
                                                break;
                                            case DARK_AQUA:
                                                color = DyeColor.BLUE;
                                                break;
                                            case DARK_RED:
                                                color = DyeColor.RED;
                                                break;
                                            case GOLD:
                                                color = DyeColor.ORANGE;
                                                break;
                                            case GRAY:
                                                color = DyeColor.GRAY;
                                                break;
                                            case DARK_GRAY:
                                                color = DyeColor.GRAY;
                                                break;
                                            case BLUE:
                                                color = DyeColor.BLUE;
                                                break;
                                            case GREEN:
                                                color = DyeColor.GREEN;
                                                break;
                                            case AQUA:
                                                color = DyeColor.CYAN;
                                                break;
                                            case RED:
                                                color = DyeColor.RED;
                                                break;
                                            case YELLOW:
                                                color = DyeColor.YELLOW;
                                                break;
                                            case WHITE:
                                                color = DyeColor.WHITE;
                                                break;
                                        }

                                        wolf.setCollarColor(color);
                                        e.getPlayer().getWorld().playSound(
                                                e.getPlayer().getLocation(), Sound.PORTAL, 3, 1
                                        );
                                        e.getItem().setAmount(e.getItem().getAmount() - 1);
                                        e.getPlayer().getInventory().setItemInHand(e.getItem());
                                    }
                                break;
                            }
                        }
                break;
            }
        }
    }
}
