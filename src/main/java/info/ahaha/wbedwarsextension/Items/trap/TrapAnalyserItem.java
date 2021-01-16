package info.ahaha.wbedwarsextension.Items.trap;

import info.ahaha.bedwars.API.Game;
import info.ahaha.bedwars.API.Team;
import info.ahaha.guiapi.Runs;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.util.GUI_Utils;
import info.ahaha.wbedwarsextension.Items.AbstractSeller;
import info.ahaha.wbedwarsextension.Items.AbstractTrapItem;
import info.ahaha.wbedwarsextension.gui.TrapShop;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class TrapAnalyserItem extends Runs implements abstract_GUI_Item {
    public TrapAnalyserItem(int _Slot, Game _game) {
        for (Team team : _game.getTeams())
            items.put(team, new ItemStack(Material.AIR));
        this.slot = _Slot;
        this.game = _game;

        setDefault(false, (e) -> {
            ItemStack item = e.getClickedInventory().getContents()[e.getSlot()];
            Team team = game.getTeam((Player) e.getWhoClicked());
            items.get(team).setType(Material.AIR);
            traps.remove(team);
            e.setCancelled(true);
            if (item == null)
                return;
            if (!item.hasItemMeta())
                return;
            if (!item.getItemMeta().hasDisplayName())
                return;
            for (String i : AbstractTrapItem.runnerMap.keySet())
                if (item.getItemMeta().getDisplayName().contains(i)) {
                    items.put(game.getTeam((Player) e.getWhoClicked()), item);
                    traps.put(game.getTeam((Player) e.getWhoClicked()), AbstractTrapItem.runnerMap.get(i));
                    e.setCancelled(false);
                    return;
                }
        });
        addAccepter(InventoryAction.COLLECT_TO_CURSOR, true, (e) -> {
        });
    }

    int slot;
    Map<Team, ItemStack> items = new HashMap<>();
    Map<Team, TrapRunner> traps = new HashMap<>();
    Game game;

    @Override
    public ItemStack toItemStack(Player player) {
        return items.get(game.getTeam(player));
    }

    @Override
    public ItemStack toDefaultItemStack() {
        return new ItemStack(Material.AIR);
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public Runs getRuns(Player player) {
        return this;
    }

    public boolean isEnable(Team team) {
        return traps.containsKey(team);
    }

    public TrapRunner getTrap(Team team) {
        return traps.get(team);
    }

    public void use(Team team, Player player) {
        try {
            items.get(team).setType(Material.AIR);
            for (OfflinePlayer op : team.getPlayers())
                if (op.isOnline()) {
                    InventoryView view = op.getPlayer().getOpenInventory();
                    if (view.getTopInventory().getHolder() instanceof TrapShop.TrapReco) {
                        GUI_Utils.injectGUI(op.getPlayer(), op.getPlayer().getOpenInventory().getTopInventory(), ((TrapShop.TrapReco) view.getTopInventory().getHolder()).getGUI());
                    }
                }

            traps.get(team).run(player);
            traps.remove(team);
        } catch (AbstractSeller.CanNotDo canNotDo) {
        }
    }
}
