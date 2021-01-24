package com.sprtms16;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    Logger logger = getLogger();

    @Override
    public void onEnable() {
        super.onEnable();
        logger.log(Level.FINE,"PVP Plugin Enable");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        logger.log(Level.FINE,"PVP Plugin Disable");
    }
}
