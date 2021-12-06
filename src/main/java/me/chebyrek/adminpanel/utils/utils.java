package me.chebyrek.adminpanel.utils;

import me.chebyrek.adminpanel.AdminPanel;
import me.chebyrek.adminpanel.Colors.Colors;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

public class utils {
    public static Economy economy = AdminPanel.getEconomy();

    public static void openPlayerList(Player pl){
        pl.closeInventory();

        Inventory PlayerListInv = Bukkit.createInventory(pl, 54, ChatColor.GREEN + "Список игроков");

        ArrayList<Player> playerList = new ArrayList<>(pl.getServer().getOnlinePlayers());

        for(int i = 0; i < playerList.size(); i++) {

            PlayerListInv.addItem(getHead(playerList.get(i), true));
        }
        pl.openInventory(PlayerListInv);
    }

    public static ItemStack getHead(Player pl, Boolean setLore){

        UUID uuid = pl.getUniqueId();
        OfflinePlayer offlinePlayer = pl.getServer().getOfflinePlayer(uuid);
        String name = offlinePlayer.getName();

        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) head.getItemMeta();

        skull.setOwningPlayer(offlinePlayer);
        skull.setDisplayName(Colors.CWhite() + name);
        if(setLore){
            ArrayList<String> lore = new ArrayList<>();
            lore.add("Здоровье: " + Colors.CGreen() + Math.round(pl.getHealth()));
            lore.add("Уровень: " + Colors.CGreen() + Math.round(pl.getLevel()));
            lore.add("Баланс: " + Colors.CGreen() + economy.getBalance(pl));
            lore.add("Пинг: " + Colors.CGreen() + pl.getPing());
            skull.setLore(lore);
        }

        head.setItemMeta(skull);

        return head;
    }

    public static void selectedPlayerPanel(Player pl, Player selectedPl){
        Inventory controlPlayerMenu = Bukkit.createInventory(pl, 18, Colors.CGreen() + "Управление игроком");


        controlPlayerMenu.setItem(0, utils.getHead(selectedPl, true));

        ItemStack tpToPlayer = new ItemStack(Material.ENDER_EYE);
        ItemMeta tpToPlayerMeta = tpToPlayer.getItemMeta();
        tpToPlayerMeta.setDisplayName(Colors.CWhite() +"Телепорт к игроку");
        tpToPlayer.setItemMeta(tpToPlayerMeta);
        controlPlayerMenu.setItem(2, tpToPlayer);

        ItemStack tpToAdmin = new ItemStack(Material.ENDER_PEARL);
        ItemMeta tpToAdminMeta = tpToAdmin.getItemMeta();
        tpToAdminMeta.setDisplayName(Colors.CWhite() +"Телепорт к себе");
        tpToAdmin.setItemMeta(tpToAdminMeta);
        controlPlayerMenu.setItem(3, tpToAdmin);

        ItemStack tpToSpawn = new ItemStack(Material.COMPASS);
        ItemMeta tpToSpawnMeta = tpToSpawn.getItemMeta();
        tpToSpawnMeta.setDisplayName(Colors.CWhite() +"Телепорт на спавн");
        tpToSpawn.setItemMeta(tpToSpawnMeta);
        controlPlayerMenu.setItem(4, tpToSpawn);

        ItemStack Kill = new ItemStack(Material.SKELETON_SKULL);
        ItemMeta KillMeta = Kill.getItemMeta();
        KillMeta.setDisplayName(Colors.CWhite() +"Убить игрока");
        Kill.setItemMeta(KillMeta);
        controlPlayerMenu.setItem(5, Kill);

        ItemStack backBtn = new ItemStack(Material.BARRIER);
        ItemMeta backBtnMeta = backBtn.getItemMeta();
        backBtnMeta.setDisplayName(Colors.CRed() +"Назад");
        backBtn.setItemMeta(backBtnMeta);
        controlPlayerMenu.setItem(8, backBtn);

        pl.closeInventory();

        pl.openInventory(controlPlayerMenu);
    }

}
