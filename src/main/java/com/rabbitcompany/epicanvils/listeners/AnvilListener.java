package com.rabbitcompany.epicanvils.listeners;

import com.rabbitcompany.epicanvils.EpicAnvils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class AnvilListener implements Listener {

    private final EpicAnvils epicAnvils;

    public AnvilListener(EpicAnvils plugin){
        epicAnvils = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAnvilChange(PrepareAnvilEvent event){
        ItemStack item = event.getInventory().getItem(0);
        ItemStack enchantingBook = event.getInventory().getItem(1);
        if(item == null || enchantingBook == null) return;
        if(item.getType() == Material.ENCHANTED_BOOK) return;
        if(enchantingBook.getType() != Material.ENCHANTED_BOOK) return;
        if(!(enchantingBook.getItemMeta() instanceof EnchantmentStorageMeta)) return;
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) enchantingBook.getItemMeta();
        ItemStack finalItem = event.getResult();
        if(finalItem == null) return;
        if(meta.isUnbreakable()){
            if(finalItem.getItemMeta() != null) finalItem.getItemMeta().setUnbreakable(true);
        }

        finalItem.addUnsafeEnchantments(meta.getStoredEnchants());

        if(finalItem.getType() == Material.ENCHANTED_BOOK){
            for(Enchantment enc : finalItem.getEnchantments().keySet()){
                finalItem.removeEnchantment(enc);
            }
        }

        event.setResult(finalItem);
    }
}
