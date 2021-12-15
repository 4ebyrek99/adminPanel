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

    public static final ItemStack leftBtn(){
        ItemStack leftBtn = new ItemStack(Material.STONE_BUTTON);
        ItemMeta leftBtnMeta = leftBtn.getItemMeta();
        leftBtnMeta.setDisplayName(Colors.CGreen() +"<--");
        leftBtn.setItemMeta(leftBtnMeta);
        return leftBtn;
    }

    public static final ItemStack rightBtn(){
        ItemStack rightBtn = new ItemStack(Material.OAK_BUTTON);
        ItemMeta rightBtnMeta = rightBtn.getItemMeta();
        rightBtnMeta.setDisplayName(Colors.CGreen() +"-->");
        rightBtn.setItemMeta(rightBtnMeta);
        return rightBtn;
    }

    public static final ItemStack page(int pageIndex){
        ItemStack page = new ItemStack(Material.PAPER);
        ItemMeta pageMeta = page.getItemMeta();
        pageMeta.setDisplayName(Colors.CGreen() + "" + pageIndex);
        page.setItemMeta(pageMeta);
        return page;
    }


}
