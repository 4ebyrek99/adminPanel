package me.chebyrek.adminpanel.utils;

import me.chebyrek.adminpanel.AdminPanel;
import me.chebyrek.adminpanel.Buttons.Buttons;
import me.chebyrek.adminpanel.Colors.Colors;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
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

    public static void openPlayerList(Player pl, int pageIndex){

        Inventory PlayerListInv = Bukkit.createInventory(pl, 54, "Список игроков");

        ArrayList<Player> playerList = new ArrayList<>(pl.getServer().getOnlinePlayers());
        ArrayList<ItemStack> heads = new ArrayList<>();


        int index = 45 * pageIndex;


        PlayerListInv.setItem(45, Buttons.page(pageIndex));
        PlayerListInv.setItem(48, Buttons.leftBtn());
        PlayerListInv.setItem(49, Buttons.backBtn());
        PlayerListInv.setItem(50, Buttons.rightBtn());

        for(int i = 0; i < playerList.size(); i++) {
            heads.add(getHead(playerList.get(i), true));
        }

        if(!((index) >= heads.size())) {
            for(int i = 0; i < PlayerListInv.getSize()-9; i++){
                index += i;
                if(index >= heads.size()){
                    break;
                }
                if (heads.get(index) != null){
                    PlayerListInv.addItem(heads.get(index));
                }
            }
            pl.openInventory(PlayerListInv);
        }
        else{
            pl.sendMessage(Colors.CRed() + "Вы уже на последней странице!");
        }
    }



    public static void openAdminPanel(Player pl){
        Inventory inv = Bukkit.createInventory(pl, 9, "Админ панель");

        ItemStack PlayerList = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta PlayerListMeta = PlayerList.getItemMeta();
        PlayerListMeta.setDisplayName(Colors.CWhite() + "Список игроков");
        PlayerList.setItemMeta(PlayerListMeta);

        ItemStack TpToSpawn = new ItemStack(Material.COMPASS);
        ItemMeta TpToSpawnMeta = TpToSpawn.getItemMeta();
        TpToSpawnMeta.setDisplayName(Colors.CWhite() +"Телепорт на спавн");
        TpToSpawn.setItemMeta(TpToSpawnMeta);

        ItemStack[] menu_items = {PlayerList, TpToSpawn};
        inv.setContents(menu_items);

        pl.openInventory(inv);
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
        Inventory controlPlayerMenu = Bukkit.createInventory(pl, 18,"Управление игроком");

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

        controlPlayerMenu.setItem(8, Buttons.backBtn());

        pl.closeInventory();

        pl.openInventory(controlPlayerMenu);
    }

}
