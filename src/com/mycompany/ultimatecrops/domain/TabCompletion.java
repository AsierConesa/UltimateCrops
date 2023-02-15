/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

/**
 *
 * @author asier
 */
public class TabCompletion implements TabCompleter{

    private Main plugin;
    private Connection conexion = null;
    
    List<String> materiales;
    
    public TabCompletion(Main instance){
        this.plugin = instance;
        
        materiales = new ArrayList<>();
        Material[] arrayMaterial = Material.values();
        for(Material m : arrayMaterial){
            materiales.add(m.toString());
        }
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> ret = new ArrayList<>();
        
        List<String> filter = new ArrayList<>();
        if(label.equalsIgnoreCase("crop")){
            Player player = (Player) sender;
            if(player.hasPermission("crop.tabcomplete")){
            
                if(args.length == 1){
            
                
                    filter.add("help");
                    filter.add("tutorial");
                    filter.add("copyDisplay");
                    filter.add("copySettings");
                    filter.add("createCrop");
                    filter.add("createDisplay");
                    filter.add("createSettings");
                    filter.add("createSkin");
                    filter.add("deleteCrop");
                    filter.add("deleteDisplay");
                    filter.add("deleteSettings");
                    filter.add("deleteSkin");
                    filter.add("editCrop");
                    filter.add("editDisplay");
                    filter.add("editSettings");
                    filter.add("editSkin");
                    filter.add("give");
                    
                    
                    for(String s : filter){
                        if(s.startsWith(args[0])){
                            ret.add(s);
                        }
                    }
                    
                }
                else if(args.length >= 2){

                    switch(args[0]){
                        
                        case "createCrop" -> {
                        // /crop createCrop name lore skin time suelo
                        
                            if(args.length == 3){

                                Descripcion lore = new Descripcion(plugin);
                                lore.selectAll();
                                List<Descripcion> todos = lore.manage.list;
                                for(Descripcion d : todos){
                                    if(d.getId().startsWith(args[2])){
                                        ret.add(d.getId());
                                    }
                                }
                            }
                            else if(args.length == 4){
                                Skin skin = new Skin(plugin);
                                skin.selectAll();
                                List<Skin> todos = skin.manage.list;
                                for(Skin s : todos){
                                    if(s.getId().startsWith(args[3])){
                                        ret.add(s.getId());
                                    }
                                }                                  
                            }
                            else if(args.length == 5){
                                
                                Settings settings = new Settings(plugin);
                                settings.selectAll();
                                List<Settings> todos = settings.manage.list;
                                for(Settings s : todos){
                                    if(s.getId().startsWith(args[4])){
                                        ret.add(s.getId());
                                    }
                                }  
                                
                                //PARA TODOS LOS POSIBLES SUELOS
                                /*
                                
                                */
                            }
                        }
                        case "createDisplay" -> {
                            // NO ACTION NEEDED
                        }
                        case "createSkin" -> {
                            // NO ACTION NEEDED
                        }
                        case "createSettings" -> {
                            // /createSettings (id) minutos suelo prob2 prob3 prob4
                            if(args.length == 4){
                                for(String m : materiales){
                                    if(m.startsWith(args[3].toUpperCase()) && Material.matchMaterial(m).isBlock()){
                                        ret.add(m);
                                    }
                                }
                            }
                        }
                        
                        case "editSettings" -> {
                            // /editSettings (id) minutos suelo prob2 prob3 prob4
                            if(args.length == 4){
                                for(String m : materiales){
                                    if(m.startsWith(args[3].toUpperCase()) && Material.matchMaterial(m).isBlock()){
                                        ret.add(m);
                                    }
                                }
                            }
                            else if(args.length == 2){
                                Settings settings = new Settings(plugin);
                                settings.selectAll();
                                List<Settings> todos = settings.manage.list;
                                for(Settings s : todos){
                                    if(s.getId().startsWith(args[1])){
                                        ret.add(s.getId());
                                    }
                                }
                            }
                        }
                        
                        case "deleteCrop" -> {
                            if(args.length == 2){
                                //cargar lista de cultivos existentes
                                Cultivo cultivo = new Cultivo(plugin);
                                cultivo.selectAll();

                                List<Cultivo> todos = cultivo.manage.list;
                                for(Cultivo c : todos){
                                    if(c.getId().startsWith(args[1])){
                                        ret.add(c.getId());
                                    }

                                }
                            }
                            //ENDED
                            
                        }
                        case "deleteDisplay" -> {
                            if(args.length == 2){
                                //cargar lista de lores existentes
                                Descripcion lore = new Descripcion(plugin);
                                lore.selectAll();

                                List<Descripcion> todos = lore.manage.list;
                                for(Descripcion d : todos){
                                    if(d.getId().startsWith(args[1])){
                                        ret.add(d.getId());
                                    }
                                }
                            }
                            //ENDED
                        }
                        
                        
                        case "copyDisplay" -> {
                            if(args.length == 2){
                                //cargar lista de lores existentes
                                Descripcion lore = new Descripcion(plugin);
                                lore.selectAll();

                                List<Descripcion> todos = lore.manage.list;
                                for(Descripcion d : todos){
                                    if(d.getId().startsWith(args[1])){
                                        ret.add(d.getId());
                                    }
                                }
                            }
                            //ENDED
                        }
                        
                        
                        case "deleteSkin" -> {
                            if(args.length == 2){
                                //cargar lista de skins existentes
                                Skin skin = new Skin(plugin);
                                skin.selectAll();

                                List<Skin> todos = skin.manage.list;
                                for(Skin s : todos){
                                    if(s.getId().startsWith(args[1])){
                                        ret.add(s.getId());
                                    }
                                }
                            }
                            //ENDED
                        }
                        
                        case "deleteSettings" -> {
                            if(args.length == 2){
                                //cargar lista de settings existentes
                                Settings settings = new Settings(plugin);
                                settings.selectAll();

                                List<Settings> todos = settings.manage.list;
                                for(Settings s : todos){
                                    if(s.getId().startsWith(args[1])){
                                        ret.add(s.getId());
                                    }
                                }
                            }
                            //ENDED
                        }
                        
                        case "copySettings" -> {
                            if(args.length == 2){
                                //cargar lista de settings existentes
                                Settings settings = new Settings(plugin);
                                settings.selectAll();

                                List<Settings> todos = settings.manage.list;
                                for(Settings s : todos){
                                    if(s.getId().startsWith(args[1])){
                                        ret.add(s.getId());
                                    }
                                }
                            }
                            //ENDED
                        }
                        
                        case "editCrop" -> {
                            if(args.length == 2){
                                //cargar lista de cultivos existentes
                                Cultivo cultivo = new Cultivo(plugin);
                                cultivo.selectAll();

                                List<Cultivo> todos = cultivo.manage.list;
                                for(Cultivo c : todos){
                                    if(c.getId().startsWith(args[1])){
                                        ret.add(c.getId());
                                    }
                                }
                            }
                            else if(args.length == 3){

                                Descripcion lore = new Descripcion(plugin);
                                lore.selectAll();
                                List<Descripcion> todos = lore.manage.list;
                                for(Descripcion d : todos){
                                    if(d.getId().startsWith(args[2])){
                                        ret.add(d.getId());
                                    }
                                }
                            }
                            else if(args.length == 4){
                                Skin skin = new Skin(plugin);
                                skin.selectAll();
                                List<Skin> todos = skin.manage.list;
                                for(Skin s : todos){
                                    if(s.getId().startsWith(args[3])){
                                        ret.add(s.getId());
                                    }
                                }                                  
                            }
                            else if(args.length == 5){
                                
                                Settings settings = new Settings(plugin);
                                settings.selectAll();
                                List<Settings> todos = settings.manage.list;
                                for(Settings s : todos){
                                    if(s.getId().startsWith(args[4])){
                                        ret.add(s.getId());
                                    }
                                }  
                                
                                //PARA TODOS LOS POSIBLES SUELOS
                                /*
                                
                                */
                            }
                            //NOT IMPLEMENTED YET
                        }
                        case "editDisplay" -> {
                            if(args.length == 2){
                                //cargar lista de lores existentes
                                Descripcion lore = new Descripcion(plugin);
                                lore.selectAll();

                                List<Descripcion> todos = lore.manage.list;
                                for(Descripcion d : todos){
                                    if(d.getId().startsWith(args[1])){
                                        ret.add(d.getId());
                                    }
                                }
                            }
                            else if(args.length == 3){
                                //filter.add("setId");
                                filter.add("setDisplayName");
                                filter.add("addLine");
                                filter.add("deleteLine");
                                filter.add("setLine");
                                filter.add("insertLine");
                                filter.add("generateLore");
                                

                                for(String s : filter){
                                    if(s.startsWith(args[2])){
                                        ret.add(s);
                                    }
                                }
                            }
                            else if(args.length == 4){
                                if(args[2].equalsIgnoreCase("generateLore")){
                                    Settings settings = new Settings(plugin);
                                    settings.selectAll();

                                    List<Settings> todos = settings.manage.list;
                                    for(Settings s : todos){
                                        if(s.getId().startsWith(args[3])){
                                            ret.add(s.getId());
                                        }
                                    }
                                }
                            }
                            
                        }
                        case "give" -> {
                            if(args.length == 2){
                                //cargar lista de cultivos existentes
                                Cultivo cultivo = new Cultivo(plugin);
                                cultivo.selectAll();

                                List<Cultivo> todos = cultivo.manage.list;
                                for(Cultivo c : todos){
                                    if(c.getId().startsWith(args[1])){
                                        ret.add(c.getId());
                                    }
                                }
                            }
                            else if(args.length == 3){
                                //cargar lista de jugadores online
                                Bukkit.getOnlinePlayers();

                                
                                for(Player p : Bukkit.getOnlinePlayers()){
                                    if(p.getName().startsWith(args[2])){
                                        ret.add(p.getName());
                                    }
                                }
                            }
                            //ENDED
                        }
                        case "help" -> {
                            // NO ACTION NEEDED
                        }
                        
                    }
                    
                }
                
            }
            
                
                
            
        
        }
        
        return ret;
    }
    
    
}
