package me.chebyrek.adminpanel.Events;

import me.chebyrek.adminpanel.AdminPanel;
import me.chebyrek.adminpanel.Colors.Colors;
import me.chebyrek.adminpanel.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class PlayerControl implements Listener {

    FileConfiguration config = AdminPanel.plugin.getCustomConfig();

    @EventHandler
    public void onClickMenu(InventoryClickEvent e){
        if (e.getView().getTitle().equalsIgnoreCase("Список игроков")) {
            if(e.getCurrentItem() != null) {
                Player pl = (Player) e.getWhoClicked();
                int pageIndex = Integer.parseInt(ChatColor.stripColor(e.getClickedInventory().getItem(45).getItemMeta().getDisplayName()));
                switch (e.getCurrentItem().getType()){
                    case PLAYER_HEAD:{
                        Player selectedPl = pl.getServer().getPlayerExact(ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())));
                        utils.selectedPlayerPanel(pl, selectedPl);
                        break;
                    }
                    case BARRIER: {
                        pl.closeInventory();
                        utils.openAdminPanel(pl);
                        break;
                    }
                    case STONE_BUTTON:{
                        if((pageIndex-1) < 1){
                            pl.sendMessage(Colors.CRed() + "Вы уже на первой странице!");
                            break;
                        }
                        else{
                            utils.openPlayerList(pl, pageIndex-1);
                        }
                        break;
                    }
                    case OAK_BUTTON:{
                        utils.openPlayerList(pl, pageIndex+1);
                        break;
                    }
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void actionPl(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase("Управление игроком")){
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {

                Player pl = (Player) e.getWhoClicked();
                String selectedName = ChatColor.stripColor(e.getClickedInventory().getItem(0).getItemMeta().getDisplayName());

                if(pl.getServer().getPlayer(selectedName) != null){
                    String plName = ChatColor.stripColor(pl.getDisplayName());
                    Player selected = pl.getServer().getPlayer(selectedName);

                    switch (e.getCurrentItem().getType()) {
                        case ENDER_EYE:{
                            Location loc = selected.getLocation();
                            pl.teleport(loc);
                            pl.sendMessage(Colors.CGreen() + "Телепорт к игроку: " + Colors.CBlue() + selectedName);
                            break;
                        }
                        case ENDER_PEARL:{
                            Location loc = pl.getLocation();
                            selected.teleport(loc);
                            pl.sendMessage(Colors.CGreen() + "Вы были телепортированы к: " + Colors.CBlue() + plName);
                            break;
                        }
                        case SKELETON_SKULL: {
                            selected.setHealth(0);
                            pl.sendMessage(Colors.CRed() + selectedName + Colors.CGreen() + "был убит.");
                            selected.sendMessage(Colors.CRed() + "Вы были убиты Администратором " + plName + "\"!");
                            break;
                        }
                        case COMPASS: {
                            String world = config.getString("spawnLoc.world");
                            double x = config.getInt("spawnLoc.x");
                            double y = config.getInt("spawnLoc.y");
                            double z = config.getInt("spawnLoc.z");

                            Location loc = new Location(Bukkit.getWorld(world), x, y, z);
                            selected.teleport(loc);
                            selected.sendMessage(Colors.CGreen() + "Вы были телепортированы на спавн.");
                            break;
                        }
                        case CHEST:{
                            pl.performCommand("invsee " + selectedName);
                            break;
                        }
                        case BARRIER:{
                            utils.openPlayerList(pl, 0);
                        }
                    }
                }

            }
        }
    }


}
