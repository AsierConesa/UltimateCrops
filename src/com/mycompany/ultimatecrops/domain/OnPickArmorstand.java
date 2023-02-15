/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.mycompany.ultimatecrops.view.Main;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author asier
 */
public class OnPickArmorstand implements Listener{
    
    Main instance;
    ArmorStand armorStand;
    
    public OnPickArmorstand(Main instance) {
        this.instance = instance;
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void PickHelmetFromStand(PlayerArmorStandManipulateEvent e) {
        Player player = e.getPlayer();
        if(player.getInventory().getItemInMainHand().getAmount() == 0){
            armorStand = e.getRightClicked();
            String uuid = armorStand.getUniqueId().toString();
            CultivoPlantado cultivoPlantado = new CultivoPlantado(uuid ,instance);
            if(cultivoPlantado.exists()){
                e.setCancelled(true);
                
                cultivoPlantado = cultivoPlantado.select();
                
                int cantidad = cultivoPlantado.getCantidad();
                Cultivo cultivo = cultivoPlantado.getCultivo();
                
                cultivo.generateItem();
                ItemStack item = cultivo.getItem();
                item.setAmount(cantidad);
                
                player.setItemInHand(item);
                
                armorStand.remove();
                cultivoPlantado.delete();
            }
        }
        
    }
}
