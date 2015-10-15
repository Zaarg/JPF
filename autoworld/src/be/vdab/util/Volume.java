/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Steve
 */
public final class Volume implements Serializable, Comparable<Volume>{
    
    private static final long serialVersionUID = 1L;
    private final int HOOGTE;
    private final int BREEDTE;
    private final int DIEPTE;
    private final Maat MAAT;

    public Volume(int HOOGTE, int BREEDTE, int DIEPTE, Maat MAAT) throws VolumeException {
        this.HOOGTE = HOOGTE;
        this.BREEDTE = BREEDTE;
        this.DIEPTE = DIEPTE;
        this.MAAT = MAAT;
        if (this.getVolume()<0) throw new VolumeException("Volume: Negatieve volumes onmogelijk");
    }
    
    public long getVolume() throws VolumeException {    //Gives volume in cm³
        long volume = HOOGTE*BREEDTE*DIEPTE;
        if (volume < 0) throw new VolumeException("Volume: Negatieve volumes onmogelijk");
        switch (this.MAAT) {
            case meter: volume *= 1000;
            case decimeter: volume *= 1000;
        }
        return volume;
    }

    public int getHoogte() {
        return HOOGTE;
    }

    public int getBreedte() {
        return BREEDTE;
    }

    public int getDiepte() {
        return DIEPTE;
    }

    public Maat getMaat() {
        return MAAT;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volume other = (Volume) obj;
        if (this.HOOGTE != other.HOOGTE) {
            return false;
        }
        if (this.BREEDTE != other.BREEDTE) {
            return false;
        }
        if (this.DIEPTE != other.DIEPTE) {
            return false;
        }
        if (this.MAAT != other.MAAT) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.HOOGTE;
        hash = 97 * hash + this.BREEDTE;
        hash = 97 * hash + this.DIEPTE;
        hash = 97 * hash + Objects.hashCode(this.MAAT);
        return hash;
    }

    @Override
    public int compareTo(Volume vol) {
        return (int)(this.getVolume()-vol.getVolume());
    }

    @Override
    public String toString() {
        return String.format("%d(h)x%d(b)x%d(d) %s", HOOGTE, BREEDTE, DIEPTE, MAAT);
    }
    
    
    
}
