/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain.manage;

import com.mycompany.ultimatecrops.domain.Settings;
import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asier
 */
public class ManageSettings {
    public Main plugin;
    
    public List<Settings> list;
    
    public ManageSettings(Main instance){
        this.list = new ArrayList<>();
        this.plugin = instance;
    }
    
    public boolean settingsExists(Connection conexion, Settings settings) {
        List<Settings> opciones = (List<Settings>) plugin.getConfig().getList("settings");

        if (opciones == null) {
            opciones = new ArrayList<>();
        }

        for (Settings i : opciones) {
            if (i.getId().equals(settings.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean insertSettings(Connection conexion, Settings settings) {
        List<Settings> opciones = (List<Settings>) plugin.getConfig().getList("settings");

        if (opciones == null) {
            opciones = new ArrayList<>();
        }

        for (Settings i : opciones) {
            if (i.getId().equals(settings.getId())) {
                return false;
            }
        }
        
        opciones.add(settings);
        plugin.getConfig().set("settings", opciones);
        plugin.saveConfig();
        return false;
    }

    public Settings getSettings(Connection conexion, Settings settings) {
        List<Settings> opciones = (List<Settings>) plugin.getConfig().getList("settings");

        if (opciones == null) {
            opciones = new ArrayList<>();
        }

        for (Settings i : opciones) {
            if (i.getId().equals(settings.getId())) {
                return i;
            }
        }
        return null;
    }

    public boolean deleteSettings(Connection conexion, Settings settings) {
        List<Settings> opciones = (List<Settings>) plugin.getConfig().getList("settings");

        if (opciones == null) {
            opciones = new ArrayList<>();
        }

        for (Settings i : opciones) {
            if (i.getId().equals(settings.getId())) {
                opciones.remove(i);
                plugin.getConfig().set("settings", opciones);
                plugin.saveConfig();
                return true;
            }
        }
        return false;
    }
    
    public void getAllSettings(Connection conexion){
        
        list = (List<Settings>) plugin.getConfig().getList("settings");

        if (list == null) {
            list = new ArrayList<>();
        }
        
    }
}
