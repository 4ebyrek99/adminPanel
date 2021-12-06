package me.chebyrek.adminpanel.Commands;

import me.chebyrek.adminpanel.Colors.Colors;
import me.chebyrek.adminpanel.utils.utils;
import org.bukkit.Bukkit;
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

            if(pl.hasPermission("adminPanel.admin")){
                if(args.length > 0){
                    Player selected = Bukkit.getServer().getPlayer(args[0]);
                    utils.selectedPlayerPanel(pl, selected);
                }
                else{
                    utils.openAdminPanel(pl);
                }
            }
            else{
                pl.sendMessage(Colors.CRed() + "У вас недостаточно прав для выполнения данной команды.");
            }
        }
        else{
            Bukkit.getLogger().info(Colors.CRed() + "Эта команда должна выполнятся игроком!");
        }

        return true;
    }
}
