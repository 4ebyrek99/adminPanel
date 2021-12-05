package me.chebyrek.adminpanel.Commands;

import me.chebyrek.adminpanel.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class openMenu implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String args[]){

        if(sender instanceof Player){
            Player pl = (Player) sender;

            if(args.length > 0){
                Player selected = Bukkit.getServer().getPlayer(args[0]);
                utils.selectedPlayerPanel(pl, selected);
            }
            else{

                Inventory inv = Bukkit.createInventory(pl, 9, ChatColor.RED + "Админ панель");

                ItemStack PlayerList = new ItemStack(Material.PLAYER_HEAD);
                ItemMeta PlayerListMeta = PlayerList.getItemMeta();
                PlayerListMeta.setDisplayName("Список игроков");
                PlayerList.setItemMeta(PlayerListMeta);

                ItemStack TpToSpawn = new ItemStack(Material.COMPASS);
                ItemMeta TpToSpawnMeta = TpToSpawn.getItemMeta();
                TpToSpawnMeta.setDisplayName("Телепорт на спавн");
                TpToSpawn.setItemMeta(TpToSpawnMeta);

                ItemStack[] menu_items = {PlayerList, TpToSpawn};
                inv.setContents(menu_items);

                pl.openInventory(inv);
            }
        }

        return true;
    }
}
