package me.chebyrek.adminpanel.Events;

import me.chebyrek.adminpanel.Colors.Colors;
import me.chebyrek.adminpanel.utils.utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class PlayerControl implements Listener {

    @EventHandler
    public void onClickMenu(InventoryClickEvent e){
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Список игроков")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                Player pl = (Player) e.getWhoClicked();

                switch (e.getCurrentItem().getType()){
                    case PLAYER_HEAD:{
                        Player selectedPl = pl.getServer().getPlayerExact(ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())));
                        utils.selectedPlayerPanel(pl, selectedPl);
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void actionPl(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(Colors.CGreen() + "Управление игроком")){
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {

                Player pl = (Player) e.getWhoClicked();
                String plName = ChatColor.stripColor(pl.getDisplayName());
                String selectedName = ChatColor.stripColor(e.getClickedInventory().getItem(0).getItemMeta().getDisplayName());
                Player selected = pl.getServer().getPlayer(selectedName);

                switch (e.getCurrentItem().getType()) {
                    case ENDER_EYE:{
                        pl.performCommand("tp " + plName + " " + selectedName);
                        break;
                    }
                    case ENDER_PEARL:{
                        pl.performCommand("tp " + selectedName + " " + plName);
                        break;
                    }
                    case SKELETON_SKULL: {
                        selected.setHealth(0);
                        selected.sendMessage(Colors.CRed() + "Вы были убиты " + plName + "!");
                        break;
                    }
                    case COMPASS: {
                        pl.performCommand("spawn " + selectedName);
                        break;
                    }
                    case BARRIER:{
                        utils.openPlayerList(pl);
                    }
                }
            }
        }
    }


}
