/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.mycompany.ultimatecrops.resources.Time;
import com.mycompany.ultimatecrops.view.Main;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author asier
 */
public class Comandos implements CommandExecutor{
    
    private Main plugin;
    public Comandos(Main instance){
        this.plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        
        if(label.equalsIgnoreCase("crop")){
            
            if(sender instanceof Player){
                Player player = (Player) sender;
                
                if(args.length == 0){
                    player.sendMessage("Use /crop help to see the full command list");
                }
                if(args.length > 0){
                
                    switch(args[0].toLowerCase()){
                        case "help" -> {
                            if(player.hasPermission("crop.command.help")){
                                player.sendMessage(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"/crop help"+ChatColor.LIGHT_PURPLE+"\n - Shows the full list of commands");
                                player.sendMessage(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"/crop tutorial"+ChatColor.LIGHT_PURPLE+"\n - Shows the list of steps to get started");
                                player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"/crop createCrop (id) (display) (skin) (settings)"+ChatColor.RED+"\n - Creates a crop with the given data");
                                player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"/crop editCrop (id) (display) (skin) (settings)"+ChatColor.RED+"\n - Edits a crop setting the given data to it");
                                player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"/crop deleteCrop (id)"+ChatColor.RED+"\n - Deletes the referenced crop");
                                player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"/crop createDisplay (id) (displayName)"+ChatColor.YELLOW+"\n - Create a new display");
                                player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"/crop editDisplay (id) addLine (line)"+ChatColor.YELLOW+"\n - adds the given line to the lore");
                                player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"/crop editDisplay (id) deleteLine (index)"+ChatColor.YELLOW+"\n - removes the given index line from the lore");
                                player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"/crop editDisplay (id) setLine (index) (line)"+ChatColor.YELLOW+"\n - sets the given line to the given index");
                                player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"/crop editDisplay (id) insertLine (index) (line)"+ChatColor.YELLOW+"\n - sets the given line to the given index and move the rest one index up");
                                player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"/crop deleteDisplay (id)"+ChatColor.YELLOW+"\n - Delete the referenced display");
                                player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"/crop copyDisplay (copyfrom) (copyto) (newDisplayName)"+ChatColor.YELLOW+"\n - Copies the first display and creates a new display with the same data");
                                player.sendMessage(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"/crop createSkin (id) (Player | Texture)"+ChatColor.AQUA+"\n - Creates a new skin with the given player name or texture");
                                player.sendMessage(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"/crop editSkin (id) (Player | Texture)"+ChatColor.AQUA+"\n - Edits a new skin with the given data");
                                player.sendMessage(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"/crop deleteSkin (id)"+ChatColor.AQUA+"\n - Deletes the referenced skin");
                                player.sendMessage(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"/crop createSettings (id) (Minutes) (Soil) (%2) (%3) (%4)"+ChatColor.GREEN+"\n - Creates settings with the given data");
                                player.sendMessage(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"/crop editSettings (id) (Minutes) (Soil) (%2) (%3) (%4)"+ChatColor.GREEN+"\n - Edits settings with the given data");
                                player.sendMessage(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"/crop deleteSettings (id)"+ChatColor.GREEN+"\n - Delete the referenced settings");
                                player.sendMessage(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"/crop copySettings (copyfrom) (copyto)"+ChatColor.GREEN+"\n - Copies the first settings and creates a new settings with the same data");
                                player.sendMessage(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"/crop give (crop) [player]"+ChatColor.LIGHT_PURPLE+"\n - Gives the player the referenced crop");
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        case "tutorial" -> {
                            if(player.hasPermission("crop.command.tutorial")){
                                player.sendMessage(ChatColor.LIGHT_PURPLE+""+ChatColor.UNDERLINE+"In this tutorial we are going to show you how to create your first crop");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"To create a"+ ChatColor.RED + " crop"+ChatColor.DARK_PURPLE+" you'll need a "+ChatColor.YELLOW+"Display "+ChatColor.AQUA + ",Skin "+ChatColor.DARK_PURPLE+"and "+ChatColor.GREEN+"Settings");
                                player.sendMessage(ChatColor.GREEN+"Try to create Settings with: ");
                                player.sendMessage(ChatColor.GREEN+"/crop createSettings (id) (Grow Minutes) (Soil) (Chance to collect 2) (Chance to collect 3) (Chance to collect 4)");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"The id could be anything you want, but use something representative");
                                player.sendMessage(ChatColor.AQUA+"Try to create a Skin with: ");
                                player.sendMessage(ChatColor.AQUA+"/crop createSkin (id) (PlayerName | Texture)");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"You can both, write any player's name to get his head texture or write a custom texture");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"Here is a website if you want to get some custom textures: ");
                                player.sendMessage(ChatColor.DARK_BLUE+"https://minecraft-heads.com/custom-heads/search?searchword=fruit");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"a Display contains the DisplayName and the lore of the crop");
                                player.sendMessage(ChatColor.YELLOW+"Try to create a Display with: ");
                                player.sendMessage(ChatColor.YELLOW+"/crop createDisplay (id) (displayName)");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"With /crop editCrop (id) addLine you could add lines to the lore");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"but with /crop editCrop (id) generateLore (settings) we will generate it for you");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"If you want to write collored lines write '&' and a number/character, this website might help you");
                                player.sendMessage(ChatColor.DARK_BLUE+"https://minecraft.tools/en/color-code.php");
                                player.sendMessage(ChatColor.RED+"Try to create a crop with the previous data you've created: ");
                                player.sendMessage(ChatColor.RED+"/crop createCrop (id) (displayId) (skinId) (settingsId)");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"done! now use /crop give (cropId) to get your crop");
                                player.sendMessage(ChatColor.LIGHT_PURPLE+"It's time to create your own crops!");
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        case "createcrop" -> {
                            if(player.hasPermission("crop.command.createcrop")){
                                if(args.length == 5){
                                    try{
                                        Descripcion descripcion = new Descripcion(args[2], plugin);
                                        descripcion = descripcion.select();
                                        descripcion.colorName();
                                        descripcion.colorList();

                                        Skin skin = new Skin(args[3], plugin);
                                        skin = skin.select();

                                        Settings settings = new Settings(args[4], plugin);
                                        settings = settings.select();

                                        Cultivo crop = new Cultivo(args[1], descripcion, skin, settings, plugin);

                                        if(!crop.exists()){

                                            crop.insert();
                                            player.sendMessage(ChatColor.GREEN+"The Crop has been created successfully");
                                        }else{
                                            player.sendMessage(ChatColor.RED+"That crop already exists, try another id");
                                        }

                                    } catch(Exception e){
                                        player.sendMessage(ChatColor.RED+"Given data is not correct or doesn't exists");
                                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+e.toString());
                                    }


                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop createCrop (id) (display) (skin) (settings)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "editcrop" -> {
                            if(player.hasPermission("crop.command.editCrop")){
                                if(args.length == 5){
                                    try{
                                        Descripcion descripcion = new Descripcion(args[2], plugin);
                                        descripcion = descripcion.select();
                                        descripcion.colorName();
                                        descripcion.colorList();

                                        Skin skin = new Skin(args[3], plugin);
                                        skin = skin.select();

                                        Settings settings = new Settings(args[4], plugin);
                                        settings = settings.select();

                                        Cultivo crop = new Cultivo(args[1], descripcion, skin, settings, plugin);

                                        if(!crop.exists()){

                                            player.sendMessage(ChatColor.RED+"That crop doesn't exists, you can create it with /crop createCrop");
                                        }else{
                                            crop.delete();
                                            crop.insert();

                                            player.sendMessage(ChatColor.GREEN+"The crop has been modified successfully");
                                        }

                                    } catch(Exception e){
                                        player.sendMessage(ChatColor.RED+"Given data is not correct or doesn't exists");
                                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+e.toString());
                                    }


                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editCrop (id) (display) (skin) (settings)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "deletecrop" -> {
                            if(player.hasPermission("crop.command.deletecrop")){
                                if(args.length == 2){
                                    Cultivo c = new Cultivo(args[1], plugin);

                                    if(c.delete())
                                        player.sendMessage(ChatColor.GREEN+"The crop has been deleted");
                                    else
                                        player.sendMessage(ChatColor.RED+"That crop doesn't exists");
                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop deleteCrop (id)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "createdisplay" -> {
                            if(player.hasPermission("crop.command.createdisplay")){
                                if(args.length >= 3){
                                    String name = "";
                                    for(int i = 2; i < args.length ; i++){
                                        name = name + " " + args[i];
                                    }

                                    Descripcion lore = new Descripcion(args[1], name, plugin);
                                    if(!lore.exists()){

                                        if(!lore.isNameUsed()){
                                            lore.insert();
                                            player.sendMessage(ChatColor.GREEN+"The display has been created successfully");
                                        }
                                        else{
                                            player.sendMessage(ChatColor.GREEN+"Another display with that displayName already exists, you can't do that");
                                        }
                                    }
                                    else{
                                        player.sendMessage(ChatColor.RED+"That id is actually in use");
                                    }

                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop createDisplay (id) (displayName)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "editdisplay" -> {
                            if(player.hasPermission("crop.command.editdisplay")){
                                boolean success = false;
                                if(args.length>2){
                                    Descripcion lore = new Descripcion(args[1], plugin);
                                    lore = lore.select();
                                    if(lore.exists()){
                                        String prevID = lore.getId();
                                        String prevName = lore.getName();
                                        List<String> prevList = lore.getList();

                                        switch(args[2].toLowerCase()){

                                            //case "setId" -> {
                                            //    if(args.length == 4){
                                            //        lore.delete();
                                            //        lore.setId(args[3]);
                                            //        lore.setName(prevName);
                                            //        lore.setList(prevList);
                                            //        player.sendMessage(ChatColor.GREEN+"Lore's name has been updated");
                                            //        success = true;
                                            //    }
                                            //    else
                                            //        player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) setName (id)");
                                            //}
                                            case "setdisplayname" -> {
                                                if(args.length >= 4){
                                                    String name = "";
                                                    for(int i = 3; i < args.length ; i++){
                                                        name = name + " " + args[i];
                                                    }
                                                    Descripcion testLoreExists = new Descripcion(name, plugin, true);
                                                    if(!testLoreExists.isNameUsed()){
                                                        lore.delete();
                                                        lore.setId(prevID);
                                                        lore.setName(name);
                                                        lore.setList(prevList);
                                                        
                                                        player.sendMessage(ChatColor.GREEN+"Lore's display name has been updated");
                                                        success = true;
                                                    }
                                                    else{
                                                        player.sendMessage(ChatColor.GREEN+"Another display with that displayName already exists, you can't do that");
                                                    }
                                                }
                                                else
                                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) setDisplayName (displayName)");
                                            }
                                            case "addline" -> {
                                                if(args.length >= 4){

                                                    if(args[3].equals("") || args[3] == null){
                                                        lore.delete();
                                                        lore.setId(prevID);
                                                        lore.setName(prevName);
                                                        lore.setList(prevList);
                                                        
                                                        lore.addLine("&0.");
                                                        player.sendMessage(ChatColor.GREEN+"A new empty line was added to the lore");
                                                        success = true;
                                                    }
                                                    else{
                                                        String line = "";
                                                        for(int i = 3; i<args.length; i++){
                                                            line = line + args[i] + " ";
                                                        }
                                                        lore.delete();
                                                        lore.setId(prevID);
                                                        lore.setName(prevName);
                                                        lore.setList(prevList);
                                                        
                                                        lore.addLine(line);
                                                        player.sendMessage(ChatColor.GREEN+"A new line was added to the lore");
                                                        success = true;
                                                    }


                                                }
                                                else if(args.length == 3){
                                                    lore.delete();
                                                    lore.setId(prevID);
                                                    lore.setName(prevName);
                                                    lore.setList(prevList);

                                                    lore.addLine("&0.");
                                                    player.sendMessage(ChatColor.GREEN+"A new empty line was added to the lore");
                                                    success = true;
                                                }
                                                else
                                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) addLine (line)");
                                            }
                                            case "deleteline" -> {
                                                try{

                                                    if(args.length == 4){
                                                        //ERROR INDEX MUY LARGO, EN PRINCIPIO ESTÁ ARREGLADO PERO PRUEBA PLS
                                                        if(prevList.size()>Integer.valueOf(args[3])){
                                                            lore.delete();
                                                            lore.setId(prevID);
                                                            lore.setName(prevName);
                                                            lore.setList(prevList);
                                                            lore.removeLine(Integer.valueOf(args[3]));
                                                            player.sendMessage(ChatColor.GREEN+"The line has been removed from the lore");
                                                            success = true;
                                                        }
                                                        else{
                                                            player.sendMessage(ChatColor.RED+"Index to large, max: "+(prevList.size()-1));
                                                        }

                                                    }
                                                    else
                                                        player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) removeLine (index)");

                                                }catch(NumberFormatException e){
                                                    player.sendMessage(ChatColor.RED+"index must be a number");
                                                }
                                            }
                                            case "setline" -> {
                                                try{

                                                    if(args.length >= 5){
                                                        String line = "";
                                                        for(int i = 4; i<args.length; i++){
                                                            line = line + args[i] + " ";
                                                        }

                                                        if(prevList.size()>Integer.valueOf(args[3])){
                                                            lore.delete();
                                                            lore.setId(prevID);
                                                            lore.setName(prevName);
                                                            lore.setList(prevList);
                                                            lore.setLine(Integer.valueOf(args[3]),line);
                                                            player.sendMessage(ChatColor.GREEN+"A new line was added to the lore");
                                                            success = true;
                                                        }
                                                        else{
                                                            player.sendMessage(ChatColor.RED+"Index to large, max: "+(prevList.size()-1));
                                                        }

                                                    }
                                                    else
                                                        player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) setLine (index) (line)");

                                                }catch(NumberFormatException e){
                                                    player.sendMessage(ChatColor.RED+"index must be a number");
                                                }
                                            }
                                            case "insertline" -> {
                                                try{

                                                    if(args.length >= 5){
                                                        String line = "";
                                                        for(int i = 4; i<args.length; i++){
                                                            line = line + args[i] + " ";
                                                        }

                                                        if(prevList.size()>Integer.valueOf(args[3])){
                                                            lore.delete();
                                                            lore.setId(prevID);
                                                            lore.setName(prevName);
                                                            lore.setList(prevList);
                                                            lore.insertLine(Integer.valueOf(args[3]),line);
                                                            player.sendMessage(ChatColor.GREEN+"A new line was added to the lore");
                                                            success = true;
                                                        }
                                                        else{
                                                            player.sendMessage(ChatColor.RED+"Index to large, max: "+(prevList.size()-1));
                                                        }

                                                    }
                                                    else
                                                        player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) insertLine (index) (line)");

                                                }catch(NumberFormatException e){
                                                    player.sendMessage(ChatColor.RED+"index must be a number");
                                                }
                                            }
                                            case "generatelore" -> {
                                                try{

                                                    if(args.length == 4){

                                                        Settings settings = new Settings(args[3] ,plugin);
                                                        if(settings.exists()){
                                                            settings = settings.select();
                                                            int minutos = settings.getMinutos();
                                                            Material suelo = settings.getSuelo();
                                                            int prob2 = settings.getProb2();
                                                            int prob3 = settings.getProb3();
                                                            int prob4 = settings.getProb4();

                                                            String formattedTime = Time.formatTime(minutos);

                                                            lore.delete();
                                                            lore.setId(prevID);
                                                            lore.setName(prevName);
                                                            lore.setList(new ArrayList<>());
                                                            lore.addLine("&3Can be planted in: &f"+suelo.toString().toLowerCase().replace("_", " "));
                                                            lore.addLine("&5Growth time: &b"+formattedTime);
                                                            lore.addLine("&0.");
                                                            lore.addLine("&b&lChances:");
                                                            lore.addLine("&7Collect 2: &3"+prob2+"%");
                                                            lore.addLine("&7Collect 3: &b"+prob3+"%");
                                                            lore.addLine("&7Collect 4: &6"+prob4+"%");
                                                            player.sendMessage(ChatColor.GREEN+"A lore has been autogenerated");
                                                            success = true;
                                                        }
                                                        else{
                                                            player.sendMessage(ChatColor.RED+"That settings doesn't exists");
                                                        }

                                                    }
                                                    else
                                                        player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) generateLore (settings)");

                                                }catch(NumberFormatException e){
                                                    player.sendMessage(ChatColor.RED+"index must be a number");
                                                }
                                            }
                                            default -> {
                                                player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) (setName|setDisplayName|addLine|removeLine|setLine)");
                                            }                                    }

                                        //INSERTA EL LORE EN LA BBDD
                                        if(success)
                                            lore.insert();
                                    }
                                    else{
                                        player.sendMessage(ChatColor.RED+"That lore doesn't exists");
                                    }


                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editDisplay (id) (setName|setDisplayname|addLine|RemoveLine|setLine)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "deletedisplay" -> {
                            if(player.hasPermission("crop.command.deletedisplay")){
                                if(args.length == 2){
                                    Descripcion d = new Descripcion(args[1], plugin);

                                    if(d.delete())
                                        player.sendMessage(ChatColor.GREEN+"The display has been deleted");
                                    else
                                        player.sendMessage(ChatColor.RED+"That display doesn't exists");
                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop deleteDisplay (id)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        
                        case "copydisplay" -> {
                            if(player.hasPermission("crop.command.copydisplay")){
                                if(args.length == 4){
                                    Descripcion descripcion1 = new Descripcion(args[1] ,plugin);
                                    if(descripcion1.exists()){
                                        descripcion1 = descripcion1.select();
                                        Descripcion descripcion2 = new Descripcion(args[2] ,plugin);
                                        if(!descripcion2.exists()){
                                            
                                            descripcion2.setName(args[3]);
                                            descripcion2.setList(descripcion1.getList());
                                            
                                            if(!descripcion2.isNameUsed()){
                                                descripcion2.insert();
                                                player.sendMessage(ChatColor.GREEN+"The display has been copied successfully");
                                            }
                                            else{
                                                player.sendMessage(ChatColor.RED+"Another display with that displayName already exists, you can't do that");
                                            }
                                            
                                        }
                                        else{
                                            player.sendMessage(ChatColor.RED+"That id you want to copy to already exists");
                                        }
                                    }
                                    else{
                                        player.sendMessage(ChatColor.RED+"That id you want to copy from doesn't exists");
                                    }
                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop copyDisplay (copyfrom) (copyto) (newDisplayName)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        
                        case "createskin" -> {
                            if(player.hasPermission("crop.command.createskin")){
                                if(args.length == 3){
                                    Skin skin;
                                    skin = new Skin(args[1] ,args[2], plugin);
                                    if(!skin.exists()){
                                        skin.insert();
                                        player.sendMessage(ChatColor.GREEN+"The Skin has been created successfully");
                                    }
                                    else{
                                        player.sendMessage(ChatColor.RED+"That name is actually in use");
                                    }

                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop createSkin (id) ((HeadOwner) | (Texture))");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "editskin" -> {
                            if(player.hasPermission("crop.command.editskin")){
                                if(args.length == 3){
                                    Skin skin;
                                    skin = new Skin(args[1] ,args[2], plugin);
                                    if(!skin.exists()){
                                        player.sendMessage(ChatColor.RED+"That skin doesn't exists, you can create it with /crop createSkin");
                                    }
                                    else{
                                        skin.delete();
                                        skin.insert();

                                        player.sendMessage(ChatColor.GREEN+"The skin has been modified successfully");
                                    }

                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop editSkin (id) ((HeadOwner) | (Texture))");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        case "deleteskin" -> {
                            if(player.hasPermission("crop.command.deleteskin")){
                                if(args.length == 2){
                                    Skin s = new Skin(args[1], plugin);

                                    if(s.delete())
                                        player.sendMessage(ChatColor.GREEN+"The skin has been deleted");
                                    else
                                        player.sendMessage(ChatColor.RED+"That skin doesn't exists");
                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop deleteSkin (id)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                            
                        }
                        
                        case "createsettings" -> {
                            if(player.hasPermission("crop.command.createsettings")){
                                if(args.length == 7){
                                    try{
                                        Settings settings;
                                        String id = args[1];
                                        int minutos = Integer.parseInt(args[2]);
                                        Material suelo = Material.matchMaterial(args[3]);
                                        int prob2 = Integer.parseInt(args[4]);
                                        int prob3 = Integer.parseInt(args[5]);
                                        int prob4 = Integer.parseInt(args[6]);

                                        if(prob2+prob3+prob4 == 100){
                                            settings = new Settings(id ,minutos, suelo, prob2, prob3, prob4, plugin);
                                            if(!settings.exists()){
                                                settings.insert();
                                                player.sendMessage(ChatColor.GREEN+"The Settings has been created successfully");
                                            }
                                            else{
                                                player.sendMessage(ChatColor.RED+"That name is actually in use");
                                            }
                                        }
                                        else{
                                            player.sendMessage(ChatColor.RED+"All probabilities must add up to 100");
                                        }


                                    }
                                    catch(NumberFormatException e){
                                        player.sendMessage(ChatColor.RED+"The introduced data is not correct");
                                    }


                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop createSettings (id) (Grow Minutes) (Soil Type) (Chance to 2) (Chance to 3) (Chance to 4)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "editsettings" -> {
                            if(player.hasPermission("crop.command.editsettings")){
                                if(args.length == 7){
                                    try{
                                        Settings settings;
                                        String id = args[1];
                                        int minutos = Integer.parseInt(args[2]);
                                        Material suelo = Material.matchMaterial(args[3]);
                                        int prob2 = Integer.parseInt(args[4]);
                                        int prob3 = Integer.parseInt(args[5]);
                                        int prob4 = Integer.parseInt(args[6]);

                                        if(prob2+prob3+prob4 == 100){
                                            settings = new Settings(id ,minutos, suelo, prob2, prob3, prob4, plugin);
                                            if(!settings.exists()){
                                                player.sendMessage(ChatColor.RED+"That settings doesn't exists, you can create it with /crop createSettings");
                                            }
                                            else{
                                                settings.delete();
                                                settings.insert();

                                                player.sendMessage(ChatColor.GREEN+"The settings has been modified successfully");
                                            }
                                        }
                                        else{
                                            player.sendMessage(ChatColor.RED+"All probabilities must add up to 100");
                                        }


                                    }
                                    catch(Exception e){
                                        player.sendMessage(ChatColor.RED+"The introduced data is not correct");
                                    }


                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop createSettings (id) (Grow Minutes) (Soil Type) (Chance to 2) (Chance to 3) (Chance to 4)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "deletesettings" -> {
                            if(player.hasPermission("crop.command.deletesettings")){
                                if(args.length == 2){
                                    Settings s = new Settings(args[1], plugin);

                                    if(s.delete())
                                        player.sendMessage(ChatColor.GREEN+"The settings has been deleted");
                                    else
                                        player.sendMessage(ChatColor.RED+"That settings doesn't exists");
                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop deleteSettings (id)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                            
                        }
                        
                        case "copysettings" -> {
                            if(player.hasPermission("crop.command.copysettings")){
                                if(args.length == 3){
                                    Settings settings1 = new Settings(args[1] ,plugin);
                                    if(settings1.exists()){
                                        settings1 = settings1.select();
                                        Settings settings2 = new Settings(args[2] ,plugin);
                                        if(!settings2.exists()){
                                            settings2.setMinutos(settings1.getMinutos());
                                            settings2.setProb2(settings1.getProb2());
                                            settings2.setProb3(settings1.getProb3());
                                            settings2.setProb4(settings1.getProb4());
                                            settings2.setSuelo(settings1.getSuelo());
                                            
                                            settings2.insert();
                                            player.sendMessage(ChatColor.GREEN+"The Settings has been copied successfully");
                                        }
                                        else{
                                            player.sendMessage(ChatColor.RED+"That id you want to copy to already exists");
                                        }
                                    }
                                    else{
                                        player.sendMessage(ChatColor.RED+"That id you want to copy from doesn't exists");
                                    }
                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop copySettings (copyfrom) (copyto)");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                        
                        case "give" -> {
                            if(player.hasPermission("crop.command.give")){
                                if(args.length == 2 || args.length == 3){
                                    //contruye un cultivo
                                    Cultivo crop = new Cultivo(args[1], plugin);

                                    //saca de la BBDD Los valores de ese cultivo dado
                                    crop = crop.select();

                                    //genera la cabeza 
                                    crop.generateItem();

                                    //dásela al jugador
                                    if(args.length == 3){
                                        Player referencedPlayer = Bukkit.getPlayer(args[2]);
                                        boolean exists = false;
                                        for(Player p : Bukkit.getOnlinePlayers()){
                                            if(p.equals(referencedPlayer)){
                                                p.getInventory().addItem(crop.getItem());
                                                exists = true;
                                            }
                                        }
                                        if(!exists){
                                            player.sendMessage(ChatColor.RED+"The player "+args[2]+" could not be founded");
                                        }
                                    }
                                    else{
                                        player.getInventory().addItem(crop.getItem());
                                        player.sendMessage(ChatColor.GREEN+"crop given to " + ChatColor.AQUA + "["+player.getName()+"]");
                                    }
                                }
                                else{
                                    player.sendMessage(ChatColor.RED+"incorrect syntax, use: /crop give (crop) [player]");
                                }
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                            
                        }
                        default -> {
                            if(player.hasPermission("crop.command")){
                                player.sendMessage(ChatColor.RED+"unknown command, use /crop help");
                            }
                            else{
                                player.sendMessage(ChatColor.RED+"You don't have permission to execute that command");
                            }
                        }
                    }
                
                }
                
            
            }
            else{
                sender.sendMessage("This command cannot be executed in the console");
            }
        
        
        }
        return true;
    }
}
