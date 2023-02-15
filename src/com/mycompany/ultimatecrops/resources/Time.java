/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.resources;

import java.time.Duration;
import org.bukkit.ChatColor;

/**
 *
 * @author asier
 */
public class Time {
    public static String getTimeRemaining(Duration diferencia){
                                    
        long segundos = diferencia.toSeconds();

        return formatTime(segundos);
        
    }
    
    public static String formatTime(long segundos){
        
        int minutos = (int) Math.floor(segundos/60);

        segundos = segundos-(minutos*60);

        int horas = (int) Math.floor(minutos/60);

        minutos = minutos-(horas*60);

        int dias = (int) Math.floor(horas/24);

        horas = horas-(dias*24);

        String time = "";

        if(dias==0) {
            if(horas<10 && minutos<10 && segundos<10) {
                    time = ""+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(horas<10 && minutos<10 && !(segundos<10)) {
                    time = ""+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(horas<10 && !(minutos<10) && segundos<10) {
                    time = ""+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && minutos<10 && segundos<10) {
                    time = ""+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && !(minutos<10) && segundos<10) {
                    time = ""+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && minutos<10 && !(segundos<10)) {
                    time = ""+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(horas<10 && !(minutos<10) && !(segundos<10)) {
                    time = ""+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(!(horas<10) && !(minutos<10) && !(segundos<10)) {
                    time = ""+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
        }
        else if(dias==1) {
            if(horas<10 && minutos<10 && segundos<10) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Day "+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":0"+ChatColor.AQUA+segundos;
            }
            else if(horas<10 && minutos<10 && !(segundos<10)) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Day "+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(horas<10 && !(minutos<10) && segundos<10) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Day "+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && minutos<10 && segundos<10) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Day "+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && !(minutos<10) && segundos<10) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Day "+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && minutos<10 && !(segundos<10)) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Day "+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(horas<10 && !(minutos<10) && !(segundos<10)) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Day "+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(!(horas<10) && !(minutos<10) && !(segundos<10)) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Day "+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }

        }
        else if (dias > 1){
            if(horas<10 && minutos<10 && segundos<10) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Days "+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(horas<10 && minutos<10 && !(segundos<10)) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Days "+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(horas<10 && !(minutos<10) && segundos<10) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Days "+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && minutos<10 && segundos<10) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Days "+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && !(minutos<10) && segundos<10) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Days "+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+segundos;
            }
            else if(!(horas<10) && minutos<10 && !(segundos<10)) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Days "+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+"0"+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(horas<10 && !(minutos<10) && !(segundos<10)) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Days "+ChatColor.AQUA+"0"+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
            else if(!(horas<10) && !(minutos<10) && !(segundos<10)) {
                    time = ""+ChatColor.WHITE+dias+ChatColor.GOLD+" Days "+ChatColor.AQUA+""+horas+ChatColor.WHITE+":"+ChatColor.AQUA+minutos+ChatColor.WHITE+":"+ChatColor.AQUA+segundos;
            }
        }
        return time;
    }
    
    
    
    public static String formatTime(int minutos){
        
        int horas = (int) Math.floor(minutos/60);

        minutos = minutos-(horas*60);

        int dias = (int) Math.floor(horas/24);

        horas = horas-(dias*24);

        String time = "";

        if(dias != 0){
            time = time + dias+"d ";
        }
        if(horas != 0){
            time = time + horas+"h ";
        }
        if(minutos != 0){
            time = time + minutos+"m ";
        }
        
        return time;
    }
}
