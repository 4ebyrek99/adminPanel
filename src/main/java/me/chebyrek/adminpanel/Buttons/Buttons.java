package me.chebyrek.adminpanel.Buttons;

import me.chebyrek.adminpanel.Colors.Colors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Buttons {


    public static final ItemStack backBtn(){
        ItemStack backBtn = new ItemStack(Material.BARRIER);
        ItemMeta backBtnMeta = backBtn.getItemMeta();
        backBtnMeta.setDisplayName(Colors.CRed() +"Назад");
        backBtn.setItemMeta(backBtnMeta);
        return backBtn;
    }

}
