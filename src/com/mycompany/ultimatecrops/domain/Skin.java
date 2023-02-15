/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.mycompany.ultimatecrops.domain.manage.ManageSkin;
import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

/**
 *
 * @author asier
 */

@SerializableAs("Skin")
public class Skin implements ConfigurationSerializable{ //CLASE INMUTABLE
    
    public Main plugin;
    public ManageSkin manage;
    Connection conexion = null;
    
    
    //VARIABLES
    private String id = "";
    
    private String texture = "";
    
    private String skullOwner = "";
    
    //CONTRUCTORES
    
    public Skin(){
    
    }
    
    public Skin(Main instance) {
        this.plugin = instance;
        this.manage = new ManageSkin(instance);
        this.id = null;
    }
    
    public Skin(String id, Main instance){
        this.plugin = instance;
        this.manage = new ManageSkin(instance);
        this.id = id;
        
    }
    
    public Skin(String id, String texture, Main instance){
        
        this.plugin = instance;
        this.manage = new ManageSkin(plugin);
        this.id = id;
        if(texture.length() <= 40){
            //es un jugador
            this.skullOwner = texture;
        }
        else{
            this.texture = texture;
        }
        
        
    }

    private Skin(String id, String texture, String skullOwner) {
        this.id = id;
        this.skullOwner = skullOwner;
        this.texture = texture;
    }
    
    public ManageSkin getManage() {
        return manage;
    }

    public String getId() {
        return id;
    }

    public String getTexture() {
        return texture;
    }

    public String getSkullOwner() {
        return skullOwner;
    }

    //DB METHODS
    public boolean exists() {
        return manage.skinExists(conexion, this);
    }

    public void insert() {
        manage.insertSkin(conexion, this);
    }
    
    public Skin select(){
        return manage.getSkin(conexion, this);
    }
    
    public boolean delete(){
        return manage.deleteSkin(conexion, this);
    }
    
    public void selectAll(){
        manage.getAllSkins(conexion);
    }

    // ABSTRACT METHODS
    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("id", this.getId());
        result.put("texture", this.getTexture());
        result.put("skullOwner", this.getSkullOwner());
        return result;
    }
    
    public static Skin deserialize(Map<String, Object> args) {

        
        String id = "";
        String texture = "";
        String skullOwner = "";
        
        if(args.containsKey("id")){
            id = (String) args.get("id");
        }
        if(args.containsKey("texture")){
            texture = (String) args.get("texture");
        }
        if(args.containsKey("skullOwner")){
            skullOwner = (String) args.get("skullOwner");
        }
        return new Skin(id, texture, skullOwner);
    }
    
}
