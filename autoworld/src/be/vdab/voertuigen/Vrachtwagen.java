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

/**
 *
 * @author Steve
 */
public class Vrachtwagen extends Voertuig implements Laadbaar{
    
    private Volume laadvolume;
    private int maximaalToegelatenMassa;
    private int aantalAssen;
    private final int MAX_ZITPLAATSEN = 3;

    public Vrachtwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Volume laadvolume, int maximaalToegelatenMassa, int aantalAssen, Mens bestuurder, Mens... mensen) throws MensException,IllegalArgumentException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, mensen);
        if (zitplaatsen > MAX_ZITPLAATSEN) throw new IllegalArgumentException("Vrachtwagen: Te groot aantal zitplaatsen");
        setLaadvolume(laadvolume);
        setMaximaalToegelatenMassa(maximaalToegelatenMassa);
        setAantalAssen(aantalAssen);
    }

    public int getMaximaalToegelatenMassa() {
        return maximaalToegelatenMassa;
    }

    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa) throws IllegalArgumentException {
        if (maximaalToegelatenMassa >= 0) {
            this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        } else throw new IllegalArgumentException("Vrachtwagen: Geen negatieve massacapaciteit toegelaten");
    }

    public int getAantalAssen() {
        return aantalAssen;
    }

    public void setAantalAssen(int aantalAssen) throws IllegalArgumentException {
        if (aantalAssen >= 2) {
            this.aantalAssen = aantalAssen;
        } else throw new IllegalArgumentException("Vrachtwagen: Minstens 2 assen nodig");
    }
    
    @Override
    public Volume getLaadvolume() {
        return this.laadvolume;
    }

    @Override
    public void setLaadvolume(Volume laadvolume) throws IllegalArgumentException {
        if (laadvolume.getVolume()>=0) {
            this.laadvolume = laadvolume;
        } else throw new IllegalArgumentException("Vrachtwagen: Geen negatief laadvolume toegelaten");
    }

    @Override
    public String toString() {
        return super.toString() + " assen:"+aantalAssen+", maximaal toegelaten massa:"+maximaalToegelatenMassa+", laadvolume:"+laadvolume;
    }
    
    
}
