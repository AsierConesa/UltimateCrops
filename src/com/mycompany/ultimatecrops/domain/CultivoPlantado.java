/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain;

import com.mycompany.ultimatecrops.domain.manage.ManageCultivoPlantado;
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
@SerializableAs("CultivoPlantado")
public class CultivoPlantado implements ConfigurationSerializable{
    public Main plugin;
    public ManageCultivoPlantado manage;
    Connection conexion = null;
    
    //VARIABLES
    private String uuid;
    private String fechaFin;
    private int cantidad;
    private boolean listo;
    private Cultivo cultivo;
    
    //CONSTRUCTORES
    
    public CultivoPlantado(){
    
    }
    
    public CultivoPlantado(Main instance){
        this.plugin = instance;
        manage = new ManageCultivoPlantado(plugin);
        
        this.uuid = null;
        this.fechaFin = null;
        this.cantidad = 0;
        this.listo = false;
        this.cultivo = null;
        
        
    }
    public CultivoPlantado(String uuid, Main instance){
        this.plugin = instance;
        manage = new ManageCultivoPlantado(plugin);
        
        this.uuid = uuid;
        this.fechaFin = null;
        this.cantidad = 0;
        this.listo = false;
        this.cultivo = null;
        
        
    }
    public CultivoPlantado(String uuid, String fechaFin, int cantidad, boolean listo, Cultivo cultivo, Main instance){
        this.plugin = instance;
        manage = new ManageCultivoPlantado(plugin);
        
        this.uuid = uuid;
        this.fechaFin = fechaFin;
        this.cantidad = cantidad;
        this.listo = listo;
        this.cultivo = cultivo;
        
        
    }

    private CultivoPlantado(String uuid, String fechaFin, int cantidad, boolean listo, Cultivo cultivo) {
        this.plugin = Main.getInstance();
        manage = new ManageCultivoPlantado(plugin);
        
        this.uuid = uuid;
        this.fechaFin = fechaFin;
        this.cantidad = cantidad;
        this.listo = listo;
        this.cultivo = cultivo;
    }
    
    //getters y setters

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }
    
    
    
    //DB METHODS
    public boolean exists(){
        return manage.cultivoPlantadoExists(conexion, this);
    }

    public boolean insert(){
        return manage.insertCultivoPlantado(conexion, this);
    }
    
    public CultivoPlantado select(){
        return manage.getCultivoPlantado(conexion, this);
    }
    
    public boolean delete(){
        return manage.deleteCultivoPlantado(conexion, this);
    }
    
    public void selectAll(){
        manage.getAllCultivosPlantados(conexion);
    }

    /*
    private String uuid;
    private String fechaFin;
    private int cantidad;
    private boolean listo;
    private Cultivo cultivo;
    */
    
    // ABSTRACT METHODS
    @Override
    public Map<String, Object> serialize() {
        Cultivo crop = this.getCultivo();
        crop.setItem(null);
        
        LinkedHashMap result = new LinkedHashMap();
        result.put("uuid", this.getUuid());
        result.put("fechaFin", this.getFechaFin());
        result.put("cantidad", this.getCantidad());
        result.put("listo", this.isListo());
        result.put("cultivo", crop);
        return result;
    }
    
    public static CultivoPlantado deserialize(Map<String, Object> args) {

        
        String uuid = null;
        String fechaFin = null;
        int cantidad = 0;
        boolean listo = false;
        Cultivo cultivo = null;
        
        if(args.containsKey("uuid")){
            uuid = (String) args.get("uuid");
        }
        if(args.containsKey("fechaFin")){
            fechaFin = (String) args.get("fechaFin");
        }
        if(args.containsKey("cantidad")){
            cantidad = (Integer) args.get("cantidad");
        }
        if(args.containsKey("listo")){
            listo = (Boolean) args.get("listo");
        }
        if(args.containsKey("cultivo")){
            cultivo = (Cultivo) args.get("cultivo");
        }
        return new CultivoPlantado(uuid, fechaFin, cantidad, listo, cultivo);
    }
}
