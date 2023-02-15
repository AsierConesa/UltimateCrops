/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.mycompany.ultimatecrops.resources.Time;
import com.mycompany.ultimatecrops.view.Main;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author asier
 */
public class EverySecond {
    Main instance;
    
    public EverySecond(Main instance){
        this.instance = instance;
    }
    
    public void start(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {
            for(Player all : Bukkit.getServer().getOnlinePlayers()) {
                for (Entity entity : all.getNearbyEntities(50, 20, 50)) {
                    if (entity.getType() == EntityType.ARMOR_STAND) {
                        ArmorStand armorstand = (ArmorStand) entity;
                        CultivoPlantado cultivoPlantado = new CultivoPlantado(armorstand.getUniqueId().toString(), instance);
                        
                        if(cultivoPlantado.exists()){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            cultivoPlantado = cultivoPlantado.select();
                            String str = cultivoPlantado.getFechaFin();
                            str = str.replace("T", " ");
                            if(str.length()>16) {
                                str = str.substring(0, 19);
                            }
                            LocalDateTime futuro = LocalDateTime.parse(str, formatter);
                            LocalDateTime ahora = LocalDateTime.now();
                            
                            
                            if(ahora.isAfter(futuro) && !cultivoPlantado.isListo()) {
                                //El cultivo ha crecido, modifica su cantidad
                                Cultivo cultivo = cultivoPlantado.getCultivo();
                                Settings settings = cultivo.getSettings();
                                
                                long random = (long) ((Math.random())*101);	
                                int prob2 = settings.getProb2();
                                int prob3 = settings.getProb3()+prob2;
                                int prob4 = settings.getProb4()+prob3;
                                
                                int amount = 1;
                                
                                if(random <= prob2)
                                    amount = 2;
                                else if(random <= prob3)
                                    amount = 3;
                                else{
                                    amount = 4;
                                }
                                
                                cultivoPlantado.setCantidad(amount);
                                armorstand.setCustomName(ChatColor.GOLD+""+ChatColor.BOLD+"Ready!");
                                ItemStack itemStack = armorstand.getHelmet();
                                itemStack.setAmount(amount);
                                armorstand.setHelmet(itemStack);
                                
                                cultivoPlantado.setListo(true);
                                cultivoPlantado.insert();
                                
                            }
                            else if (cultivoPlantado.isListo()){
                                if(armorstand.getCustomName().equals(ChatColor.GOLD+""+ChatColor.BOLD+"Ready!")){
                                    armorstand.setCustomName(ChatColor.YELLOW+""+ChatColor.BOLD+"Ready!");
                                }
                                else{
                                    armorstand.setCustomName(ChatColor.GOLD+""+ChatColor.BOLD+"Ready!");
                                }
                            }          
                            else{
                                //Aun no ha crecido, actualiza el tiempo
                                Duration diferencia = Duration.between(ahora, futuro);
                                String time = Time.getTimeRemaining(diferencia);
                                
                                armorstand.setCustomName(time);
                            }
                        }
                    }
                }
            }
        }, 20L, 20L); //Delay de 1 segundo
    }
}
