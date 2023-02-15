/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.resources;

import org.bukkit.ChatColor;

/**
 *
 * @author asier
 */
public class Color {
    public static final int BLACK = '0';
    public static final int DARK_BLUE = '1';
    public static final int DARK_GREEN = '2';
    public static final int DARK_AQUA = '3';
    public static final int DARK_RED = '4';
    public static final int DARK_PURPLE = '5';
    public static final int GOLD = '6';
    public static final int GRAY = '7';
    public static final int DARK_GRAY = '8';
    public static final int BLUE = '9';
    public static final int GREEN = 'a';
    public static final int AQUA = 'b';
    public static final int RED = 'c';
    public static final int LIGHT_PURPLE = 'd';
    public static final int YELLOW = 'e';
    public static final int WHITE = 'f';
    public static final int MAGIC = 'k';
    public static final int BOLD = 'l';
    public static final int STRIKETHROUGH = 'm';
    public static final int UNDERLINE = 'n';
    public static final int ITALIC = 'o';
    public static final int RESET = 'r';
    
    private char color;
    
    public Color(char c){
        this.color = c;
    }
    
    public ChatColor getColor(){
        ChatColor ret = ChatColor.RESET;
        
        switch(color){
   
            case BLACK:
                ret = ChatColor.BLACK;
                break;
            case DARK_BLUE:
                ret = ChatColor.DARK_BLUE;
                break;
            case DARK_GREEN:
                ret = ChatColor.DARK_GREEN;
                break;
            case DARK_AQUA:
                ret = ChatColor.DARK_AQUA;
                break;
            case DARK_RED:
                ret = ChatColor.DARK_RED;
                break;
            case DARK_PURPLE:
                ret = ChatColor.DARK_PURPLE;
                break;
            case GOLD:
                ret = ChatColor.GOLD;
                break;
            case GRAY:
                ret = ChatColor.GRAY;
                break;
            case DARK_GRAY:
                ret = ChatColor.DARK_GRAY;
                break;
            case BLUE:
                ret = ChatColor.BLUE;
                break;
            case GREEN:
                ret = ChatColor.GREEN;
                break;
            case AQUA:
                ret = ChatColor.AQUA;
                break;
            case RED:
                ret = ChatColor.RED;
                break;
            case LIGHT_PURPLE:
                ret = ChatColor.LIGHT_PURPLE;
                break;
            case YELLOW:
                ret = ChatColor.YELLOW;
                break;
            case WHITE:
                ret = ChatColor.WHITE;
                break;
            case MAGIC:
                ret = ChatColor.MAGIC;
                break;
            case BOLD:
                ret = ChatColor.BOLD;
                break;
            case STRIKETHROUGH:
                ret = ChatColor.STRIKETHROUGH;
                break;
            case UNDERLINE:
                ret = ChatColor.UNDERLINE;
                break;
            case ITALIC:
                ret = ChatColor.ITALIC;
                break;
            case RESET:
                ret = ChatColor.RESET;
                break;

        }
        
        return ret;
    }
    
}
