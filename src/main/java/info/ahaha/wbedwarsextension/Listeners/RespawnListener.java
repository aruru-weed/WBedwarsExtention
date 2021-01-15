package info.ahaha.wbedwarsextension.Listeners;

import info.ahaha.bedwars.API.event.PlayerRespawnByBedwarsEvent;
import info.ahaha.wbedwarsextension.Items.armor.DiaArmorSeller;
import info.ahaha.wbedwarsextension.Items.armor.IronArmorSeller;
import info.ahaha.wbedwarsextension.Items.armor.LeatherArmorSeller;
import info.ahaha.wbedwarsextension.Items.tools.AxeSeller;
import info.ahaha.wbedwarsextension.Items.tools.PickaxeSeller;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import static info.ahaha.wbedwarsextension.Items.armor.LeatherArmorSeller.GetTeamColor;

public class RespawnListener implements Listener {
    @EventHandler
    public void on(PlayerRespawnByBedwarsEvent e) {
        WBedwarsExtension.PerIdData.PerPlayerData data = WBedwarsExtension.getData().get(e.getGame().getID()).getPlayerData(e.getPlayer());

        LeatherArmorSeller.setAll(e.getPlayer(), GetTeamColor(e.getTeam()));
        switch (data.getArmor()) {
            case Iron:
                IronArmorSeller.set(e.getPlayer());
                break;
            case Dia:
                DiaArmorSeller.set(e.getPlayer());
                break;
        }
        if (data.hasShears())
            e.getPlayer().getInventory().addItem(new ItemStack(Material.SHEARS));
        if (data.getAxe() != 0)
            e.getPlayer().getInventory().addItem(AxeSeller.get(data.getAxe()));
        if (data.getPickaxe() != 0)
            e.getPlayer().getInventory().addItem(PickaxeSeller.get(data.getPickaxe()));
    }
}
