package info.ahaha.wbedwarsextension;

import info.ahaha.bedwars.API.BedwarsAPI;
import info.ahaha.wbedwarsextension.Items.armor.ArmorType;
import info.ahaha.wbedwarsextension.Listeners.*;
import info.ahaha.wbedwarsextension.cmd.wbe_main;
import info.ahaha.wbedwarsextension.gui.ItemShop;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class WBedwarsExtension extends JavaPlugin {
    static BedwarsAPI api;
    static Map<Integer, PerIdData> data = new HashMap<>();

    public static BedwarsAPI getApi() {
        return api;
    }

    @Override
    public void onEnable() {
        api = (BedwarsAPI) getServer().getPluginManager().getPlugin("Bedwars");
        getCommand("wbwe").setExecutor(new wbe_main());

        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new HitProjectileListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorClickListener(), this);
        getServer().getPluginManager().registerEvents(new DragArmorListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new StartListener(), this);
        getServer().getPluginManager().registerEvents(new ArchListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
    }

    public static Map<Integer, PerIdData> getData() {
        return data;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static class PerIdData {
        public static class PerPlayerData {
            ArmorType armor;
            int Axe = 0;
            int Pickaxe = 0;
            boolean shears = false;

            public int getAxe() {
                return Axe;
            }

            public int getPickaxe() {
                return Pickaxe;
            }

            public ArmorType getArmor() {
                return armor;
            }

            public boolean hasShears() {
                return shears;
            }

            public void setAxe(int axe) {
                Axe = axe;
            }

            public void setPickaxe(int pickaxe) {
                Pickaxe = pickaxe;
            }

            public void setShears(boolean shears) {
                this.shears = shears;
            }

            public void setArmor(ArmorType armor) {
                this.armor = armor;
            }
        }

        ItemShop itemShop;
        Map<UUID, PerPlayerData> playerDataMap = new HashMap<>();

        public ItemShop getItemShop() {
            return itemShop;
        }

        public void setItemShop(ItemShop itemShop) {
            this.itemShop = itemShop;
        }

        public Map<UUID, PerPlayerData> getPlayerDataMap() {
            return playerDataMap;
        }

        public void setPlayerDataMap(Map<UUID, PerPlayerData> playerDataMap) {
            this.playerDataMap = playerDataMap;
        }

        public PerPlayerData getPlayerData(Player player) {
            return playerDataMap.get(player.getUniqueId());
        }
    }
}
