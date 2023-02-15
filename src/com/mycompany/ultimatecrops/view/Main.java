/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.view;

import com.mycompany.ultimatecrops.domain.Comandos;
import com.mycompany.ultimatecrops.domain.Cultivo;
import com.mycompany.ultimatecrops.domain.CultivoPlantado;
import com.mycompany.ultimatecrops.domain.Descripcion;
import com.mycompany.ultimatecrops.domain.EverySecond;
import com.mycompany.ultimatecrops.domain.OnDropItem;
import com.mycompany.ultimatecrops.domain.OnPickArmorstand;
import com.mycompany.ultimatecrops.domain.Settings;
import com.mycompany.ultimatecrops.domain.Skin;
import com.mycompany.ultimatecrops.domain.TabCompletion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author asier
 */
public class Main extends JavaPlugin{
    
    static {
        ConfigurationSerialization.registerClass(Cultivo.class, "Cultivo");
        ConfigurationSerialization.registerClass(Descripcion.class, "Descripcion");
        ConfigurationSerialization.registerClass(Settings.class, "Settings");
        ConfigurationSerialization.registerClass(Skin.class, "Skin");
        ConfigurationSerialization.registerClass(CultivoPlantado.class, "CultivoPlantado");
    }
    
    public static Main instance;
    
    public static Descripcion lore;
    PluginDescriptionFile pluginFile = getDescription();
    String tagPluginName = ChatColor.AQUA+"["+ChatColor.WHITE+pluginFile.getName()+ChatColor.AQUA+"] ";
    
            
    @Override
    public void onLoad() {
        taggedLog(ChatColor.AQUA, "UltimateCrops se ha cargado");
    }

    @Override
    public void onEnable() {
        instance = this;

        this.getCommand("crop").setExecutor(new Comandos(this));
        this.getCommand("crop").setTabCompleter(new TabCompletion(this));
        getServer().getPluginManager().registerEvents(new OnDropItem(this), this);
        getServer().getPluginManager().registerEvents(new OnPickArmorstand(this), this);

        EverySecond everySecond = new EverySecond(instance);
        everySecond.start();

        taggedLog(ChatColor.AQUA, "UltimateCrops se ha activado");
    }

    @Override
    public void onDisable() {
        taggedLog(ChatColor.AQUA, "UltimateCrops se ha desactivado");
    }

    public void taggedLog(ChatColor color, String mensaje){
        Bukkit.getConsoleSender().sendMessage(tagPluginName+color+mensaje);
    }
     
    public static Main getInstance(){
        return instance;
    }
}
