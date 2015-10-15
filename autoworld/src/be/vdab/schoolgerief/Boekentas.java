/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Steve
 */
public class Boekentas implements Serializable,Laadbaar {
    
    private static final long serialVersionUID = 1L;
    private Volume laadvolume;
    private String kleur;

    public Boekentas(String kleur, Volume laadvolume) {
        setLaadvolume(laadvolume);
        setKleur(kleur);
    }
    
    @Override
    public Volume getLaadvolume() {
        return this.laadvolume;
    }

    @Override
    public void setLaadvolume(Volume volume) throws IllegalArgumentException {
        if (volume != null) {
            this.laadvolume = volume;
        } else {
            throw new IllegalArgumentException("Boekentas: Laadvolume is een verplicht veld.");
        }
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        if (kleur != null) {
            this.kleur = kleur;
        } else {
            throw new IllegalArgumentException("Boekentas: Kleur is een verplicht veld");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.laadvolume);
        hash = 83 * hash + Objects.hashCode(this.kleur);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Boekentas other = (Boekentas) obj;
        if (!Objects.equals(this.laadvolume, other.laadvolume)) {
            return false;
        }
        if (!Objects.equals(this.kleur, other.kleur)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "boekentas " + kleur + " " + laadvolume;
    }
    
}
