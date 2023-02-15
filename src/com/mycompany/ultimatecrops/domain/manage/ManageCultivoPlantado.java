/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain.manage;

import com.mycompany.ultimatecrops.domain.CultivoPlantado;
import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asier
 */
public class ManageCultivoPlantado {
    
    public Main plugin;
    
    public List<CultivoPlantado> list;
    
    public ManageCultivoPlantado(Main instance){
        this.list = new ArrayList<>();
        this.plugin = instance;
    }
    
    public boolean cultivoPlantadoExists(Connection conexion, CultivoPlantado cultivoPlantado) {
        List<CultivoPlantado> cultivosPlantados = (List<CultivoPlantado>) plugin.getConfig().getList("cultivoPlantado");
        
        if(cultivosPlantados == null){
            cultivosPlantados = new ArrayList<>();
        }
        
        for(CultivoPlantado i : cultivosPlantados){
            if(i.getUuid().equals(cultivoPlantado.getUuid())){
                return true;
            }
        }
        return false;
    }

    public boolean insertCultivoPlantado(Connection conexion, CultivoPlantado cultivoPlantado) {
        List<CultivoPlantado> cultivosPlantados = (List<CultivoPlantado>) plugin.getConfig().getList("cultivoPlantado");
        if(cultivosPlantados == null){
            cultivosPlantados = new ArrayList<>();
        }
        
        for(CultivoPlantado i : cultivosPlantados){
            if(i.getUuid().equals(cultivoPlantado.getUuid())){
                return false;
            }
        }
        cultivosPlantados.add(cultivoPlantado);
        plugin.getConfig().set("cultivoPlantado", cultivosPlantados);
        plugin.saveConfig();
        return true;
    }

    public CultivoPlantado getCultivoPlantado(Connection conexion, CultivoPlantado cultivoPlantado) {
        List<CultivoPlantado> cultivosPlantados = (List<CultivoPlantado>) plugin.getConfig().getList("cultivoPlantado");
        
        if(cultivosPlantados == null){
            cultivosPlantados = new ArrayList<>();
        }
        
        for(CultivoPlantado i : cultivosPlantados){
            if(i.getUuid().equals(cultivoPlantado.getUuid())){
                return i;
            }
        }
        return null;
    }

    public boolean deleteCultivoPlantado(Connection conexion, CultivoPlantado cultivoPlantado) {
        List<CultivoPlantado> cultivosPlantados = (List<CultivoPlantado>) plugin.getConfig().getList("cultivoPlantado");
        
        if(cultivosPlantados == null){
            cultivosPlantados = new ArrayList<>();
        }
        
        for(CultivoPlantado i : cultivosPlantados){
            if(i.getUuid().equals(cultivoPlantado.getUuid())){
                cultivosPlantados.remove(i);
                plugin.getConfig().set("cultivoPlantado", cultivosPlantados);
                plugin.saveConfig();
                return true;
            }
        }
        return false;
    }
    
    public void getAllCultivosPlantados(Connection conexion){
        list = (List<CultivoPlantado>) plugin.getConfig().getList("cultivoPlantado");
        
        if(list == null){
            list = new ArrayList<>();
        }
    }
    
}
