package com.rabbitcompany.epicanvils;

import com.rabbitcompany.epicanvils.listeners.AnvilListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EpicAnvils extends JavaPlugin {

    private static EpicAnvils instance;

    @Override
    public void onEnable() {
        instance = this;

        new AnvilListener(this);
    }

    @Override
    public void onDisable() {}

    public static EpicAnvils getInstance(){
        return instance;
    }
}
