package com.sprtms16;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    Logger logger = getLogger();

    @Override
    public void onEnable() {
        super.onEnable();
        PluginDescriptionFile pdfFile = this.getDescription();
        logger.log(Level.FINE, "PVP Plugin Enable");
        logger.log(Level.FINE, pdfFile.getName() + " Ver " + pdfFile.getVersion() + " loding complete");
        getServer().getPluginManager().registerEvents(new Matching(this), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        logger.log(Level.FINE, "PVP Plugin Disable");
    }
}
