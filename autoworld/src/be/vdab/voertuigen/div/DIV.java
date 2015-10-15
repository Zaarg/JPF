/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

/**
 *
 * @author steve.schoonooghe
 */
public enum DIV {

    INSTANCE;
    private int nummer=0;
    
    private DIV(){
    }

    public Nummerplaat getNummerplaat(){
                
        if (nummer==999) nummer=0;            
        return new Nummerplaat(String.format("AAA%03d", ++nummer));
    }

}
