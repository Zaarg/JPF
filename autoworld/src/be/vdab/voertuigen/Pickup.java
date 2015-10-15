/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import java.awt.Color;

/**
 *
 * @author Steve
 */
public class Pickup extends Personenwagen implements Laadbaar{

    private Volume laadvolume;
    
    public Pickup(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Volume laadvolume,Mens mens, Mens... mensen) throws MensException,IllegalArgumentException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, kleur, mens, mensen);
        setLaadvolume(laadvolume);
    }

    @Override
    public Volume getLaadvolume() {
        return this.laadvolume;
    }

    @Override
    public void setLaadvolume(Volume laadvolume) throws IllegalArgumentException {
        if (laadvolume.getVolume()>=0) {
            this.laadvolume = laadvolume;
        } else throw new IllegalArgumentException("Pickup: Geen negatief laadvolume toegelaten");
    }

    @Override
    public String toString() {
        return super.toString() + " " + laadvolume;
    }
    
    
}
