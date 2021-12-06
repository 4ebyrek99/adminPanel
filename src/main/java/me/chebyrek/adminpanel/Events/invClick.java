package me.chebyrek.adminpanel.Events;

import me.chebyrek.adminpanel.Colors.Colors;
import me.chebyrek.adminpanel.utils.utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;




public class invClick implements Listener {

    @EventHandler
    public void MainClickEvent(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase("Админ панель")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getType()) {
                    case PLAYER_HEAD: {
                        Player pl = (Player) e.getWhoClicked();
                        utils.openPlayerList(pl);
                        break;
                    }
                    case COMPASS: {
                        Player pl = (Player) e.getWhoClicked();
                        pl.performCommand("spawn");
                        break;
                    }
                }
            }
        }
    }

}
