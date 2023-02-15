/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mycompany.ultimatecrops.domain.manage.ManageCultivo;
import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 *
 * @author asier
 */
@SerializableAs("Cultivo")
public class Cultivo implements ConfigurationSerializable{
    
    public Main plugin;
    public ManageCultivo manage;
    Connection conexion = null;
    
    //VARIABLES
    private String id;
    private Descripcion itemDisplay;
    private Skin skin;
    private Settings settings;
    
    private ItemStack item;
    //CONSTRUCTORES
    
    public Cultivo(){
    
    }
    
    public Cultivo(Main instance){
        this.plugin = instance;
        manage = new ManageCultivo(plugin);
        
        this.id = null;
        this.itemDisplay = null;
        this.skin = null;
        this.settings = null;
        
    }
    public Cultivo(ItemStack item, Main instance){
        
        this.plugin = instance;
        manage = new ManageCultivo(plugin);
        
        String displayName = item.getItemMeta().getDisplayName();
        
        this.itemDisplay = new Descripcion(displayName, instance, true);
        
        this.id = "";
        this.skin = null;
        this.settings = null;
        
    }
    
    public Cultivo(String id, Main instance){
        this.plugin = instance;
        manage = new ManageCultivo(plugin);
        
        this.id = id;
        this.itemDisplay = null;
        this.skin = null;
        this.settings = null;
        
        
    }
    
    public Cultivo(String id, Descripcion itemDisplay, Skin skin, Settings settings, Main instance){
        this.plugin = instance;
        manage = new ManageCultivo(plugin);
        
        this.id = id;
        this.itemDisplay = itemDisplay;
        this.itemDisplay.colorList();
        this.itemDisplay.colorName();
        this.skin = skin;
        this.settings = settings;
        
        
    }
    
    public Cultivo(String id, Descripcion itemDisplay, Skin skin, Settings settings){
        this.plugin = Main.getInstance();
        manage = new ManageCultivo(plugin);
        
        this.id = id;
        this.itemDisplay = itemDisplay;
        this.itemDisplay.colorList();
        this.itemDisplay.colorName();
        this.skin = skin;
        this.settings = settings;
        
        
    }
    
    //getters y setters
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Descripcion getItemDisplay() {
        return itemDisplay;
    }

    public void setItemDisplay(Descripcion itemDisplay) {
        this.itemDisplay = itemDisplay;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }
    
    //METODOS

    public ItemStack generateItem() {
        String url = this.skin.getTexture();
        if(url.isEmpty())
            url = this.skin.getSkullOwner();
        
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);

        if (url == null || url.isEmpty())
            return item;
        if(url.length() <= 40){
            //se ha dado un nombre y no una textura
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwner(url);
            meta.setLore(this.itemDisplay.getColloredList());
            meta.setDisplayName(this.itemDisplay.getColloredName());
            
            item.setItemMeta(meta);
            
            this.item = item;
            
            return item;
        }

        //se ha dado una url de textura
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID(), url);
        //profile = itemMeta.getPlayerProfile();
        profile.getProperties().add(new ProfileProperty("textures", url));
        itemMeta.setPlayerProfile(profile);
        
        itemMeta.setLore(this.itemDisplay.getColloredList());
        itemMeta.setDisplayName(this.itemDisplay.getColloredName());
        
        item.setItemMeta(itemMeta);

        this.item = item;
        
        return item;
    }

    public ItemStack getItem() {
        return item;
    }
    public void setItem(ItemStack item) {
        this.item = item;
    }
    
    
    
    //DB METHODS
    public boolean exists(){
        return manage.cultivoExists(conexion, this);
    }

    public void insert(){
        manage.insertCultivo(conexion, this);
    }
    
    public Cultivo select(){
        return manage.getCultivo(conexion, this);
    }
    
    public Cultivo selectByDescripcion(){
        return manage.getCultivoByDescripcion(conexion, this);
    }
    
    public boolean delete(){
        return manage.deleteCultivo(conexion, this);
    }
    
    public void selectAll(){
        manage.getAllCultivos(conexion);
    }

    
    // ABSTRACT METHODS
    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("id", this.getId());
        result.put("itemDisplay", this.getItemDisplay());
        result.put("settings", this.getSettings());
        result.put("skin", this.getSkin());
        return result;
    }
    
    public static Cultivo deserialize(Map<String, Object> args) {
        
        String id = "";
        Descripcion itemDisplay = null;
        Settings settings = null;
        Skin skin = null;
        
        if(args.containsKey("id")){
            id = (String) args.get("id");
        }
        if(args.containsKey("itemDisplay")){
            itemDisplay = (Descripcion) args.get("itemDisplay");
        }
        if(args.containsKey("settings")){
            settings = (Settings) args.get("settings");
        }
        if(args.containsKey("skin")){
            skin = (Skin) args.get("skin");
        }
        
        return new Cultivo(id, itemDisplay, skin, settings);
    }
    
}
