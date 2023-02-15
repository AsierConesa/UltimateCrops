/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.domain.manage;

import com.mycompany.ultimatecrops.domain.Descripcion;
import com.mycompany.ultimatecrops.view.Main;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author asier
 */
public class ManageDescripcion {

    public Main plugin;

    public List<Descripcion> list;

    public ManageDescripcion(Main plugin) {
        this.list = new ArrayList<>();
        this.plugin = plugin;
    }

    public boolean descripcionExists(Connection connection, Descripcion descripcion) {
        List<Descripcion> descripciones = (List<Descripcion>) plugin.getConfig().getList("descripcion");

        if (descripciones == null) {
            descripciones = new ArrayList<>();
        }

        for (Descripcion i : descripciones) {
            if (i.getId().equals(descripcion.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean descripcionNameUsed(Connection connection, Descripcion descripcion) {
        List<Descripcion> descripciones = (List<Descripcion>) plugin.getConfig().getList("descripcion");

        if (descripciones == null) {
            descripciones = new ArrayList<>();
        }

        for (Descripcion i : descripciones) {
            if (i.getName().equals(descripcion.getName())) {
                return true;
            }
        }
        return false;
    }

    private int newIDLore(Connection connection) {
        List<Descripcion> descripciones = (List<Descripcion>) plugin.getConfig().getList("descripcion");

        if (descripciones == null) {
            descripciones = new ArrayList<>();
        }

        Collections.sort(descripciones, (Descripcion d1, Descripcion d2) -> d2.getId().compareTo(d1.getId()));

        if (!descripciones.isEmpty()) {
            return Integer.parseInt(descripciones.get(0).getId());
        }
        return 0;
    }

    private boolean insertLoreLines(Connection connection, Descripcion descripcion) {

        List<Descripcion> descripciones = (List<Descripcion>) plugin.getConfig().getList("descripcion");

        if (descripciones == null) {
            descripciones = new ArrayList<>();
        }

        List<Descripcion> descripcionesMod = descripciones;
        
        for (Descripcion i : descripciones) {
            if (i.getId().equals(descripcion.getId())) {
                descripcionesMod.remove(i);
                i.setList(descripcion.getList());
                descripcionesMod.add(i);
                plugin.getConfig().set("descripcion", descripcionesMod);
                plugin.saveConfig();
                return true;
            }
        }
        return false;

    }

    public boolean insertDescription(Connection connection, Descripcion descripcion) {

        List<Descripcion> descripciones = (List<Descripcion>) plugin.getConfig().getList("descripcion");

        if (descripciones == null) {
            descripciones = new ArrayList<>();
        }

        for (Descripcion i : descripciones) {
            if (i.getId().equals(descripcion.getId())) {
                return false;
            }
        }
        
        descripciones.add(descripcion);
        plugin.getConfig().set("descripcion", descripciones);
        plugin.saveConfig();
        return false;

    }

    public boolean deleteDescription(Connection connection, Descripcion descripcion) {
        List<Descripcion> descripciones = (List<Descripcion>) plugin.getConfig().getList("descripcion");

        if (descripciones == null) {
            descripciones = new ArrayList<>();
        }

        for (Descripcion i : descripciones) {
            if (i.getId().equals(descripcion.getId())) {
                descripciones.remove(i);
                plugin.getConfig().set("descripcion", descripciones);
                plugin.saveConfig();
                return true;
            }
        }
        return false;
    }

    public Descripcion getDescription(Connection connection, Descripcion descripcion) {

        List<Descripcion> descripciones = (List<Descripcion>) plugin.getConfig().getList("descripcion");

        if (descripciones == null) {
            descripciones = new ArrayList<>();
        }

        for (Descripcion i : descripciones) {
            if (i.getId().equals(descripcion.getId())) {
                return i;
            }
        }
        return null;
        
    }

    public void getAllDescriptions(Connection connection) {

        list = (List<Descripcion>) plugin.getConfig().getList("descripcion");

        if (list == null) {
            list = new ArrayList<>();
        }

    }
}
