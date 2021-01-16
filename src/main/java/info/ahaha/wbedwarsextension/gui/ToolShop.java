package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.GUI;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.wbedwarsextension.Items.singItem;
import info.ahaha.wbedwarsextension.Items.tools.AxeSeller;
import info.ahaha.wbedwarsextension.Items.tools.PickaxeSeller;
import info.ahaha.wbedwarsextension.Items.tools.ShearsSeller;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ToolShop extends GUI implements DragParts.dragRowOK {
    public ToolShop(Game _game, List<abstract_GUI_Item> signs) {
        super(54, "WBedwars-ToolShop-" + _game.getName());
        this.game = _game;

        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        //Tools
        addContents(signs);
        addContent(new ShearsSeller(game, IconFactory.Make(Material.SHEARS), 10,
                new ItemStack(Material.IRON_INGOT, 20),
                new ItemStack(Material.GOLD_INGOT, 1)));
        addContent(new AxeSeller(game, 11));
        addContent(new PickaxeSeller(game, 12));
    }

    Game game;
}
