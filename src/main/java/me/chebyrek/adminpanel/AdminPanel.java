package me.chebyrek.adminpanel;

import me.chebyrek.adminpanel.Commands.openMenu;
import me.chebyrek.adminpanel.Events.PlayerControl;
import me.chebyrek.adminpanel.Events.invClick;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminPanel extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("menu").setExecutor(new openMenu());
        getServer().getPluginManager().registerEvents(new invClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerControl(), this);

        System.out.println("Плагин \"Админ панель\" запущен!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
