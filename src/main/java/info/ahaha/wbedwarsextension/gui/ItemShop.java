package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.Runs;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.extended.gui.AbstractPerPlayerGUI;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.wbedwarsextension.Items.singItem;
import info.ahaha.wbedwarsextension.WBedwarsExtension;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemShop extends AbstractPerPlayerGUI implements DragParts.dragRowOK {
    public ItemShop(Game _game) {
        super(54, "WBedwars-ItemShop-" + _game.getName());
        this.game = _game;
        perIdData = WBedwarsExtension.getData().get(game.getID());
        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });
        List<abstract_GUI_Item> signs = new ArrayList<>();
        signs.add(new singItem(blockShop, 2));
        signs.add(new singItem(weponShop, 1));
        signs.add(new singItem(bowShop, 3));
        signs.add(new singItem(armorShop, 4));
        signs.add(new singItem(toolShop, 5));
        signs.add(new singItem(utils_changesShop, 6));
        armorShop = new ArmorShop(game,signs);
        blockShop = new BlockShop(game,signs);
        bowShop = new BowShop(game,signs);
        toolShop = new ToolShop(game,signs);
        utils_changesShop = new Utils_ChangesShop(game,signs);
        weponShop = new WeponShop(game,signs);
    }

    ArmorShop armorShop;
    BlockShop blockShop;
    BowShop bowShop;
    ToolShop toolShop;
    Utils_ChangesShop utils_changesShop;
    WeponShop weponShop;

    Game game;
    WBedwarsExtension.PerIdData perIdData;

    class WrappedItem implements abstract_GUI_Item {
        WrappedItem(int Slot, abstract_GUI_Item item) {
            this.Slot = Slot;
            this.item = item;
        }

        int Slot;
        abstract_GUI_Item item;

        @Override
        public ItemStack toItemStack(Player player) {
            return item.toItemStack(player);
        }

        @Override
        public ItemStack toDefaultItemStack() {
            return item.toDefaultItemStack();
        }

        @Override
        public int getSlot() {
            return Slot;
        }

        @Override
        public Runs getRuns(Player player) {
            return item.getRuns(player);
        }
    }

    @Override
    public List<abstract_GUI_Item> getContents(Player player) {
        List<abstract_GUI_Item> items = new ArrayList<>();
        Map<Integer, WBedwarsExtension.ShortCutData> map = WBedwarsExtension.getPerPlayerShortCutData().get(player.getUniqueId());
        for (int i : map.keySet()) {
            abstract_GUI_Item item = null;
            switch (map.get(i).type) {
                case Armor:
                    item = armorShop.getContents().get(map.get(i).slot);
                    break;
                case Block:
                    item = blockShop.getContents().get(map.get(i).slot);
                    break;
                case Bow:
                    item = bowShop.getContents().get(map.get(i).slot);
                    break;
                case Tool:
                    item = toolShop.getContents().get(map.get(i).slot);
                    break;
                case Util_change:
                    item = utils_changesShop.getContents().get(map.get(i).slot);
                    break;
                case Weapon:
                    item = weponShop.getContents().get(map.get(i).slot);
                    break;
            }
            if (item == null)
                continue;
            items.add(new WrappedItem(i, item));
        }

        return items;
    }

    @Override
    public List<abstract_GUI_Item> getDefaultContents() {
        return new ArrayList<>();
    }
}
