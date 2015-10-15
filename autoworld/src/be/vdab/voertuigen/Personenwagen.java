/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Steve
 */
public class Personenwagen extends Voertuig implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private java.awt.Color kleur;
    private final int MAX_ZITPLAATSEN = 8;
    
    
    public Personenwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Mens mens, Mens... mensen) throws MensException,IllegalArgumentException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, mens, mensen);
        if (zitplaatsen > MAX_ZITPLAATSEN) throw new IllegalArgumentException("Personenwagen: Te groot aantal zitplaatsen");
        this.kleur = kleur;
        
    }
          
    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }
    
    public int getZitplaatsen() {
        return super.getMAX_ZITPLAATSEN();
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + this.getZitplaatsen();
    }
}
