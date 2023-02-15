/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain.manage;

import com.mycompany.ultimatecrops.domain.Skin;
import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asier
 */
public class ManageSkin {
    public Main plugin;
    
    public List<Skin> list;
    
    public ManageSkin(Main instance){
        this.list = new ArrayList<>();
        this.plugin = instance;
    }
    
    public boolean skinExists(Connection conexion, Skin skin) {
        List<Skin> skins = (List<Skin>) plugin.getConfig().getList("skin");

        if (skins == null) {
            skins = new ArrayList<>();
        }

        for (Skin i : skins) {
            if (i.getId().equals(skin.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean insertSkin(Connection conexion, Skin skin) {
        List<Skin> skins = (List<Skin>) plugin.getConfig().getList("skin");

        if (skins == null) {
            skins = new ArrayList<>();
        }

        for (Skin i : skins) {
            if (i.getId().equals(skin.getId())) {
                return false;
            }
        }
        
        skins.add(skin);
        plugin.getConfig().set("skin", skins);
        plugin.saveConfig();
        return true;
    }

    public Skin getSkin(Connection conexion, Skin skin) {
        List<Skin> skins = (List<Skin>) plugin.getConfig().getList("skin");

        if (skins == null) {
            skins = new ArrayList<>();
        }

        for (Skin i : skins) {
            if (i.getId().equals(skin.getId())) {
                return i;
            }
        }
        return null;
    }

    public boolean deleteSkin(Connection conexion, Skin skin) {
        List<Skin> skins = (List<Skin>) plugin.getConfig().getList("skin");

        if (skins == null) {
            skins = new ArrayList<>();
        }

        for (Skin i : skins) {
            if (i.getId().equals(skin.getId())) {
                skins.remove(i);
                plugin.getConfig().set("skin", skins);
                plugin.saveConfig();
                return true;
            }
        }
        return false;
    }

    public void getAllSkins(Connection conexion){
        
        list = (List<Skin>) plugin.getConfig().getList("skin");

        if (list == null) {
            list = new ArrayList<>();
        }
    }
    
}
