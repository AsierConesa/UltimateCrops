/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.mycompany.ultimatecrops.domain.manage.ManageDescripcion;
import com.mycompany.ultimatecrops.resources.Color;
import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

/**
 *
 * @author asier
 */
@SerializableAs("Descripcion")
public class Descripcion implements ConfigurationSerializable{
    //THE LORE CLASS
   
    public Main plugin;
    public ManageDescripcion manage;
    Connection conexion;
    
    
    //VARIABLES
    private String id;
    private String name;
    private String coloredName;
    private List<String> list;
    private List<String> coloredList;
    //CONSTRUCTORS
    
    public Descripcion(){
    
    }
    
    public Descripcion(Main plugin){
        this.plugin = plugin;
        
        manage = new ManageDescripcion(plugin);
    }
    
    public Descripcion(String id, Main plugin){
        this.plugin = plugin;
        
        manage = new ManageDescripcion(plugin);
        
        this.id = id;
        this.name = "";
        this.coloredName = "";
        this.list = new ArrayList<>();
        this.coloredList = new ArrayList<>();
    }
    
    public Descripcion(String id, String display, Main plugin){
        this.plugin = plugin;
        
        manage = new ManageDescripcion(plugin);
        
        this.id = id;
        this.name = display;
        this.coloredName = "";
        this.list = new ArrayList<>();
        this.coloredList = new ArrayList<>();
    }
    
    public Descripcion(String displayName, Main plugin, boolean isByName){
        this.plugin = plugin;
        manage = new ManageDescripcion(plugin);
        
        selectAll();
        
        boolean existe = false;
        for(Descripcion d : manage.list){
            d.colorName();
            if(d.coloredName.equals(displayName)){
                this.id = d.id;
                this.name = d.name;
                this.coloredName = d.coloredName;
                this.list = new ArrayList<>();
                this.coloredList = new ArrayList<>();
                
                existe = true;
            }
        }
        if(!existe){
            this.id = "";
            this.name = "";
            this.coloredName = "";
            this.list = new ArrayList<>();
            this.coloredList = new ArrayList<>();
        }
        
        
    }

    private Descripcion(String id, String name, List<String> list) {
        this.plugin = Main.getInstance();
        this.manage = new ManageDescripcion(this.plugin);
        
        this.id = id;
        this.name = name;
        this.list = list;
    }
    
    //GETTERS AND SETTERS
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public String getColloredName(){
        return coloredName;
    }
    
    public List<String> getColloredList(){
        return coloredList;
    }
    //METODOS
    
    public void addLine(String s){
        list.add(s);
    }
    
    public void setLine(int index, String line){
        list.set(index, line);
    }
    public void insertLine(int index, String line){
        List<String> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(index == i){
                newList.add(line);
            }
            newList.add(list.get(i));
            
        }
        this.list = newList;
    }
    
    public void removeLine(int index){
        list.remove(index);
    }
    
    //METODO PARA COLOREAR EL LORE
    public void colorList(){
        coloredList = new ArrayList<>();
        for(int i = 0; i<list.size(); i++){
            String linea = list.get(i);
            String[] tramos = linea.split("&");
            linea = tramos[0];
            
            for(int j = 1; j<tramos.length; j++){
                char c = tramos[j].charAt(0);
                String text = tramos[j].substring(1);
                Color color = new Color(c);
                
                linea = linea+color.getColor()+text;
                
            }
            coloredList.add(linea);
        }
    }
    
    //METODO PARA COLOREAR EL NOMBRE
    public void colorName(){
        String linea = this.name;
        String[] tramos = linea.split("&");
        linea = tramos[0];
            
        for(int i = 1; i<tramos.length; i++){
            char c = tramos[i].charAt(0);
            String text = tramos[i].substring(1);
            Color color = new Color(c);

            linea = linea+color.getColor()+text;

        }
        this.coloredName = linea;
    }
    
    
    //MANAGE METHODS
    
    public Descripcion select(){
        return manage.getDescription(conexion, this);
    }
    
    public boolean insert(){
        return manage.insertDescription(conexion, this);
    }
    
    public boolean exists(){
        return manage.descripcionExists(conexion, this);
    }
    
    public boolean isNameUsed(){
        return manage.descripcionNameUsed(conexion, this);
    }
    
    public boolean delete(){
        return manage.deleteDescription(conexion, this);
    }
    
    public void selectAll(){
        manage.getAllDescriptions(conexion);
    }

    
    // ABSTRACT METHODS
    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("id", this.getId());
        result.put("name", this.getName());
        result.put("list", this.getList());
        return result;
    }
    
    public static Descripcion deserialize(Map<String, Object> args) {
        
        String id = "";
        String name = "";
        List<String> list = new ArrayList<>();
        
        if(args.containsKey("id")){
            id = (String) args.get("id");
        }
        if(args.containsKey("name")){
            name = (String) args.get("name");
        }
        if(args.containsKey("list")){
            list = (List<String>) args.get("list");
        }
        return new Descripcion(id, name, list);
    }
    
}
