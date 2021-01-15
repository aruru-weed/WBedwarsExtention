package info.ahaha.wbedwarsextension.cmd;

import info.ahaha.bedwars.API.Game;
import info.ahaha.wbedwarsextension.ItemShop;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static info.ahaha.wbedwarsextension.WBedwarsExtension.getApi;
import static info.ahaha.wbedwarsextension.WBedwarsExtension.getData;

public class wbe_main implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Game game = getApi().findGame(((Player) sender).getWorld().getName());
        WBedwarsExtension.PerIdData data = new WBedwarsExtension.PerIdData();
        getData().put(game.getID(),data);
        data.setItemShop(new ItemShop(game));
        return true;
    }
}
