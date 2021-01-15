package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.GUI;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.wbedwarsextension.Items.singItem;
import info.ahaha.wbedwarsextension.Items.tools.ShearsSeller;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

public class ToolShop extends GUI implements DragParts.dragRowOK {
    public ToolShop(Game _game) {
        super(54, "WBedwars-ItemShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //Tools
        addContent(new singItem("Tools", 0));
        addContent(new ShearsSeller(game, IconFactory.Make(Material.SHEARS), 8,
                new ItemStack(Material.IRON_INGOT, 20),
                new ItemStack(Material.GOLD_INGOT, 1)));
    }

    Game game;
}
