package info.ahaha.wbedwarsextension;

import info.ahaha.wbedwarsextension.gui.ShopType;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;

public class SaveAndLoad {
    public static void Save(Player player) throws IOException {
        Path path = Paths.get("plugins/WBWExtention/");
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);//Make WWarpSystem
            } catch (IOException e) {
                getLogger().info(e.toString());
            }
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("plugins/WBWExtention/" + player.getUniqueId() + ".data"));
        out.writeObject(WBedwarsExtension.getPerPlayerShortCutData().get(player.getUniqueId()));
        out.flush();
        out.close();
        WBedwarsExtension.getPerPlayerShortCutData().remove(player.getUniqueId());
    }

    public static void Load(Player player) throws IOException, ClassNotFoundException {
        File file = new File("plugins/WBWExtention/" + player.getUniqueId() + ".data");
        if (!file.exists()) {
            Map<Integer, WBedwarsExtension.ShortCutData> data = new HashMap<>();
            for (ShopType type : ShopType.values())
                for (int i = 10; i < 16; i++)
                    data.put(type.ordinal() + i * 9 - 1, new WBedwarsExtension.ShortCutData(type, i));
            WBedwarsExtension.getPerPlayerShortCutData().put(player.getUniqueId(), data);
            return;
        }

        ObjectInputStream inputStream;
        inputStream = new ObjectInputStream(new FileInputStream(file));
        Map<Integer, WBedwarsExtension.ShortCutData> data = (Map<Integer, WBedwarsExtension.ShortCutData>) inputStream.readObject();
        WBedwarsExtension.getPerPlayerShortCutData().put(player.getUniqueId(), data);
    }
}
