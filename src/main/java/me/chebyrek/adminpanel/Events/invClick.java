package me.chebyrek.adminpanel.Events;


import me.chebyrek.adminpanel.AdminPanel;
import me.chebyrek.adminpanel.Colors.Colors;
import me.chebyrek.adminpanel.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class invClick implements Listener {

    FileConfiguration config = AdminPanel.plugin.getCustomConfig();

    @EventHandler
    public void MainClickEvent(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase("Админ панель")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getType()) {
                    case PLAYER_HEAD: {
                        Player pl = (Player) e.getWhoClicked();
                        utils.openPlayerList(pl, 0);
                        break;
                    }
                    case COMPASS: {
                        Player pl = (Player) e.getWhoClicked();

                        String world = config.getString("spawnLoc.world");
                        double x = config.getInt("spawnLoc.x");
                        double y = config.getInt("spawnLoc.y");
                        double z = config.getInt("spawnLoc.z");

                        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
                        pl.teleport(loc);
                        pl.sendMessage(Colors.CGreen() + "Вы были телепортированы на спавн.");
                        break;
                    }
                }
            }
        }
    }

}
