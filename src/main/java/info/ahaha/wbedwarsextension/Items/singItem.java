package info.ahaha.wbedwarsextension.Items;

import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.GUI_Item;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import info.ahaha.guiapi.v2.gui_parts.GUI_Core;
import org.bukkit.Material;

public class singItem extends GUI_Item {
    public singItem(GUI_Core gui, int _Slot) {
        super(IconFactory.Make(Material.SIGN, gui.getName()), _Slot);
        setDefault((AccepterAttributes.changeGUI) () -> gui);
    }
}
