/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.mycompany.ultimatecrops.view.Main;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author asier
 */
public class OnDropItem implements Listener{
    
    //Variables
    Player player;
    Item item;
    ItemStack itemStack;
    public Main instance;
    
    public OnDropItem(Main instance){
        this.instance = instance;
    }
    
    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onDropItem(PlayerDropItemEvent e) {

        player = e.getPlayer();
        item = e.getItemDrop();
        itemStack = item.getItemStack();
        
        if(itemStack.getType() == Material.PLAYER_HEAD){
            
            if(itemStack.hasItemMeta()){
                String name = itemStack.getItemMeta().getDisplayName();
                
                Cultivo cultivos = new Cultivo(instance);
                
                cultivos.selectAll();
                
                for(Cultivo cultivo : cultivos.manage.list){
                    cultivo.getItemDisplay().colorName();
                    if(cultivo.getItemDisplay().getColloredName().equals(name)){
                        new BukkitRunnable(){

                            int countdown = 8;
                            Material sueloCultivo = cultivo.getSettings().getSuelo();
                            
                            @Override
                            public void run(){

                                if(countdown <= 0 || !player.isOnline() ){ //si esto pasa 
                                    this.cancel(); //sal del bucle
                                    return;
                                }
                                
                                Location loc = item.getLocation();
	    			loc.setY(loc.getY() -0.5);
                                if(loc.getBlock().getType().equals(sueloCultivo)){
                                    Collection<Entity> entities = e.getItemDrop().getWorld().getNearbyEntities(loc, 1.5, 1.5, 1.5);
                                    Iterator<Entity> iterator = entities.iterator();
                                    boolean isFounded = false;
                                    while (iterator.hasNext() && !isFounded) {
                                        if(iterator.next().getType().toString().equalsIgnoreCase("ARMOR_STAND")) {
                                            isFounded = true;
                                        }
                                    }
                                    
                                    if(!isFounded){
                                        loc.setY(loc.getBlockY()-0.5);
                                        
                                        ArmorStand armorStand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                                        
                                        armorStand.setCustomName("...");
                                        armorStand.setCustomNameVisible(true);
                                        
                                        
                                        int customHeadNum = itemStack.getAmount();
                                        itemStack.setAmount(1);
                                        ItemStack singleItem = new ItemStack(itemStack);
                                        armorStand.setHelmet(itemStack);
                                        itemStack.setAmount(customHeadNum-1);
                                        armorStand.setVisible(false);
                                        armorStand.setInvulnerable(true);
                                        armorStand.setGravity(false);
                                        armorStand.setSilent(true);
                                        LocalDateTime currentTime = LocalDateTime.now();
                                        LocalDateTime future = currentTime.plusMinutes(cultivo.getSettings().getMinutos()); 
                                        
                                        
                                        String uuid = armorStand.getUniqueId().toString();
                                        String fechaFin = future.toString();
                                        int cantidad = 1;
                                        boolean listo = false;
                                        
                                        Cultivo cultivo = new Cultivo(singleItem, instance);
                                        
                                        cultivo = cultivo.selectByDescripcion();
                                        
                                        CultivoPlantado cultivoPlantado = new CultivoPlantado(uuid, fechaFin, cantidad, listo, cultivo, instance);
                                        if(!cultivoPlantado.insert()){
                                            player.sendMessage(ChatColor.RED+"An internal error has occurred, please collect the crop");
                                        }
                                        
                                        this.cancel(); //sal del bucle
                                        
                                        
                                        
                                        
                                    }
                                }
                                
                                
                                countdown--; 
                            }
                        }.runTaskTimer(instance, 0, 5); //Repeating task with 0 ticks initial delay, run once per 5 ticks. Make sure you pass a valid instance of your plugin.

                        break;
                    }
                }
                
            }
            
        }
        
        //CONTENIDO A IMPLEMENTAR
        //1: Detecta el item que has tirado es una cabeza de jugador
        //2: Detecta el nombre del item tirado
        //3: Busca en la BBDD a ver si hay algun item con ese nombre
        //4: Deja durante 2 segundos que el item esté comprobando el bloque de debajo para ver si coincide con su bloque para crecer
        //5: Crea un armor stand y ponle como cabeza dicho item
        //6: Guarda ese armor stand en la BBDD para poder guardar su fecha de crecimiento
    }
    
}
