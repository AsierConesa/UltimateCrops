/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain.manage;

import com.mycompany.ultimatecrops.domain.Cultivo;
import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;

/**
 *
 * @author asier
 */
public class ManageCultivo { //ID-IDDescripcion-IDSkin,Minutos,Suelo
    
    
    public Main plugin;
    
    public List<Cultivo> list;
    
    public ManageCultivo(Main instance){
        this.list = new ArrayList<>();
        this.plugin = instance;
    }
    
    public boolean cultivoExists(Connection conexion, Cultivo cultivo) {
        List<Cultivo> cultivos = (List<Cultivo>) plugin.getConfig().getList("cultivo");
        
        if(cultivos == null){
            cultivos = new ArrayList<>();
        }
        
        for(Cultivo i : cultivos){
            if(i.getId().equals(cultivo.getId())){
                return true;
            }
        }
        return false;
    }

    public boolean insertCultivo(Connection conexion, Cultivo cultivo) {
        List<Cultivo> cultivos = (List<Cultivo>) plugin.getConfig().getList("cultivo");
        if(cultivos == null){
            cultivos = new ArrayList<>();
        }
        
        for(Cultivo i : cultivos){
            if(i.getId().equals(cultivo.getId())){
                return false;
            }
        }
        
        cultivos.add(cultivo);
        plugin.getConfig().set("cultivo", cultivos);
        plugin.saveConfig();
        return true;
    }

    public Cultivo getCultivo(Connection conexion, Cultivo cultivo) {
        
        List<Cultivo> cultivos = (List<Cultivo>) plugin.getConfig().getList("cultivo");
        if(cultivos == null){
            cultivos = new ArrayList<>();
        }
        
        for(Cultivo i : cultivos){
            if(i.getId().equals(cultivo.getId())){
                return i;
            }
        }
        return null;
    }

    public boolean deleteCultivo(Connection conexion, Cultivo cultivo) {
        List<Cultivo> cultivos = (List<Cultivo>) plugin.getConfig().getList("cultivo");
        if(cultivos == null){
            cultivos = new ArrayList<>();
        }
        
        for(Cultivo i : cultivos){
            if(i.getId().equals(cultivo.getId())){
                cultivos.remove(i);
                plugin.getConfig().set("cultivo", cultivos);
                plugin.saveConfig();
                return true;
            }
        }
        return false;
    }
    
    public void getAllCultivos(Connection conexion){
        
        list = (List<Cultivo>) plugin.getConfig().getList("cultivo");
        if(list == null){
            list = new ArrayList<>();
        }
        
    }

    public Cultivo getCultivoByDescripcion(Connection conexion, Cultivo cultivo) {
        
        Bukkit.broadcastMessage(cultivo.getId());
        List<Cultivo> cultivos = (List<Cultivo>) plugin.getConfig().getList("cultivo");
        if(cultivos == null){
            cultivos = new ArrayList<>();
        }
        Bukkit.broadcastMessage(cultivo.getId());
        for(Cultivo i : cultivos){
            if(i.getItemDisplay().getId().equals(cultivo.getItemDisplay().getId())){
                return i;
            }
        }
        return null;
        
    }
}
