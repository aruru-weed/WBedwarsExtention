package info.ahaha.wbedwarsextension.trap;

import info.ahaha.bedwars.API.Game;
import info.ahaha.bedwars.API.Team;
import info.ahaha.wbedwarsextension.Items.trap.TrapRunner;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.OfflinePlayer;

public class SearchPlayerTask implements Runnable {
    public SearchPlayerTask(Game game) {
        this.game = game;
        data = WBedwarsExtension.getData().get(game.getID());
    }

    Game game;
    WBedwarsExtension.PerIdData data;

    @Override
    public void run() {
        for (Team team : game.getTeams()) {
            if (data.getTrapShop().getBed().isEnable(team)) {
                TrapRunner runner = data.getTrapShop().getBed().getTrap(team);
                for (Team enemyTeam : game.getTeams())
                    if (enemyTeam != team)
                        for (OfflinePlayer player : enemyTeam.getPlayers())
                            if (player.isOnline())
                                if (team.getBedPos()[0].getLocation().distance(player.getPlayer().getLocation()) < runner.getRange()) {
                                    data.getTrapShop().getBed().use(team, player.getPlayer());
                                }
            }
            if (data.getTrapShop().getSpawn().isEnable(team)) {
                TrapRunner runner = data.getTrapShop().getSpawn().getTrap(team);
                for (Team enemyTeam : game.getTeams())
                    if (enemyTeam != team)
                        for (OfflinePlayer player : enemyTeam.getPlayers())
                            if (player.isOnline())
                                if (team.getRespawnPos().distance(player.getPlayer().getLocation()) < runner.getRange()) {
                                    data.getTrapShop().getSpawn().use(team, player.getPlayer());
                                }
            }
        }
    }
}
