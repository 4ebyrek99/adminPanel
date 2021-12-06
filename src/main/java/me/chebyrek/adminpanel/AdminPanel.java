package me.chebyrek.adminpanel;

import me.chebyrek.adminpanel.Colors.Colors;
import me.chebyrek.adminpanel.Commands.openMenu;
import me.chebyrek.adminpanel.Events.PlayerControl;
import me.chebyrek.adminpanel.Events.invClick;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminPanel extends JavaPlugin implements Listener {

    private static Economy econ = null;

    FileConfiguration config = cfg();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        if (!setupEconomy() ) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("menu").setExecutor(new openMenu());
        getServer().getPluginManager().registerEvents(this, this);

        getServer().getPluginManager().registerEvents(new invClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerControl(), this);

        Bukkit.getLogger().info("Плагин \"Админ панель\" запущен!");
        Bukkit.getLogger().info("Файл настроек загружен : " + getConfig().getString("config-ver"));
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

    public FileConfiguration cfg(){
        return config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String args[]){

        if(sender instanceof Player) {
            Player pl = (Player) sender;
            if (pl.hasPermission("adminPanel.reload")) {
                if (args.length > 0) {
                    if(args[0].equalsIgnoreCase("reload")){
                        this.reloadConfig();
                        pl.sendMessage(Colors.CGreen() + "Файл найстроек adminPanel успешно перезагружен.");
                    }
                }
                else
                {
                    pl.sendMessage(Colors.CRed() + "Используйте: " + Colors.CGreen() + "/adminpanel reload");
                }
            }
            else
            {
                pl.sendMessage(Colors.CRed() + "У вас недостаточно прав для выполнения данной команды.");
            }
        }
        else
        {
            if (args.length > 0) {
                if(args[0].equalsIgnoreCase("reload")){
                    this.reloadConfig();
                    getLogger().info(Colors.CGreen() + "Файл найстроек adminPanel успешно перезагружен.");
                }
            }
            else
            {
                getLogger().info(Colors.CRed() + "Используйте: " + Colors.CGreen() + "/adminpanel reload");
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
