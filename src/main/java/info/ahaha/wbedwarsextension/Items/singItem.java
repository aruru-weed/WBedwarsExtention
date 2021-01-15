package info.ahaha.wbedwarsextension.Items;

import info.ahaha.guiapi.v2.GUI_Item;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import org.bukkit.Material;

public class singItem extends GUI_Item {
    public singItem(String name, int _Slot) {
        super(IconFactory.Make(Material.SIGN, name), _Slot);
    }
}
