package info.ahaha.wbedwarsextension.gui;

import info.ahaha.bedwars.API.Game;
import info.ahaha.guiapi.GUI;
import info.ahaha.guiapi.Recognizer;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.gui_parts.DragParts;
import info.ahaha.guiapi.v2.gui_parts.GUI_Core;
import info.ahaha.wbedwarsextension.Items.AbstractTrapItem;
import info.ahaha.wbedwarsextension.Items.ShiftClickAccepter;
import info.ahaha.wbedwarsextension.Items.trap.SlownessTrap;
import info.ahaha.wbedwarsextension.Items.trap.TrapAnalyserItem;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class TrapShop extends GUI implements DragParts.dragRowOK {
    public TrapShop(Game _game) {
        super(54, "WBedwars-TrapShop-" + _game.getName());
        this.game = _game;
        getUsersInventoryRuns().setDefault(false, (AccepterAttributes.noAug) () -> {
        });
        getUsersInventoryRuns().addAccepter(ClickType.SHIFT_LEFT, new ShiftClickAccepter());
        getUsersInventoryRuns().addAccepter(InventoryAction.COLLECT_TO_CURSOR, (e) -> {
        });

        addContent(new AbstractTrapItem(IconFactory.Make(Material.SLIME_BLOCK, "SlownessTrap", "Slowness lvl.II 3sec"), 1, new SlownessTrap()));
        addContent(new AbstractTrapItem(IconFactory.Make(Material.SLIME_BLOCK, "BlindnessTrap", "Blindness 10sec"), 2, new SlownessTrap()));

        Bed = new TrapAnalyserItem(51, game);
        Spawn = new TrapAnalyserItem(53, game);
        BreakBed = new TrapAnalyserItem(55, game);
        addContents(Bed, Spawn, BreakBed);
    }

    TrapAnalyserItem Bed, Spawn, BreakBed;
    Game game;

    public static class TrapReco extends Recognizer {
        public TrapReco(int Inventory_Size, GUI_Core gui) {
            super(Inventory_Size, gui);
        }
    }

    @Override
    public Recognizer getRecognizer() {
        return new TrapReco(getInventorySize(), this);
    }

    public TrapAnalyserItem getBed() {
        return Bed;
    }

    public TrapAnalyserItem getBreakBed() {
        return BreakBed;
    }

    public TrapAnalyserItem getSpawn() {
        return Spawn;
    }
}
