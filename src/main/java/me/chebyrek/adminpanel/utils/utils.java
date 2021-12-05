package me.chebyrek.adminpanel.utils;

import jdk.tools.jlink.plugin.Plugin;
import me.chebyrek.adminpanel.AdminPanel;
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
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;


public class utils {
    public static Economy economy = AdminPanel.getEconomy();

    public static void openPlayerList(Player pl){
        pl.closeInventory();

        String name = pl.getName();
        UUID uuid = pl.getUniqueId();
        OfflinePlayer offlinePlayer = pl.getServer().getOfflinePlayer(uuid);
        System.out.println(uuid);
        Inventory PlayerListInv = Bukkit.createInventory(pl, 54, ChatColor.GREEN + "Список игроков");



        ArrayList<Player> playerList = new ArrayList<>(pl.getServer().getOnlinePlayers());

        for(int i = 0; i < playerList.size(); i++) {

            ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta skull = (SkullMeta) head.getItemMeta();

            skull.setOwningPlayer(offlinePlayer);
            skull.setDisplayName(offlinePlayer.getName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add("Здоровье: " + ChatColor.GREEN + Math.round(playerList.get(i).getHealth()));
            lore.add("Уровень: " + ChatColor.GREEN + Math.round(playerList.get(i).getLevel()));
            lore.add("Баланс: " + ChatColor.GREEN + economy.getBalance(playerList.get(i)));
            lore.add("Пинг: " + ChatColor.GREEN + playerList.get(i).getPing());
            skull.setLore(lore);
            head.setItemMeta(skull);

            PlayerListInv.addItem(head);
        }
        pl.openInventory(PlayerListInv);
    }


    public static void selectedPlayerPanel(Player pl, Player selectedPl){
        Inventory controlPlayerMenu = Bukkit.createInventory(pl, 18, ChatColor.GREEN + "Управление игроком");

        ItemStack playerName = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta playerNameMeta = playerName.getItemMeta();
        playerNameMeta.setDisplayName(selectedPl.getDisplayName());
        playerName.setItemMeta(playerNameMeta);
        controlPlayerMenu.setItem(0, playerName);

        ItemStack tpToPlayer = new ItemStack(Material.ENDER_EYE);
        ItemMeta tpToPlayerMeta = tpToPlayer.getItemMeta();
        tpToPlayerMeta.setDisplayName("Телепорт к игроку");
        tpToPlayer.setItemMeta(tpToPlayerMeta);
        controlPlayerMenu.setItem(2, tpToPlayer);

        ItemStack tpToAdmin = new ItemStack(Material.ENDER_PEARL);
        ItemMeta tpToAdminMeta = tpToAdmin.getItemMeta();
        tpToAdminMeta.setDisplayName("Телепорт к себе");
        tpToAdmin.setItemMeta(tpToAdminMeta);
        controlPlayerMenu.setItem(3, tpToAdmin);

        ItemStack tpToSpawn = new ItemStack(Material.COMPASS);
        ItemMeta tpToSpawnMeta = tpToSpawn.getItemMeta();
        tpToSpawnMeta.setDisplayName("Телепорт на спавн");
        tpToSpawn.setItemMeta(tpToSpawnMeta);
        controlPlayerMenu.setItem(4, tpToSpawn);

        ItemStack backBtn = new ItemStack(Material.BARRIER);
        ItemMeta backBtnMeta = backBtn.getItemMeta();
        backBtnMeta.setDisplayName("Назад");
        backBtn.setItemMeta(backBtnMeta);
        controlPlayerMenu.setItem(8, backBtn);

        pl.closeInventory();

        pl.openInventory(controlPlayerMenu);
    }

}
