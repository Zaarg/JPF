/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


/**
 *
 * @author Steve
 */
public class Mens implements Serializable, Comparable<Mens>{
    private static final long serialVersionUID = 1L;
    private String naam;
    private Set<Rijbewijs> rijbewijzen;

    public Mens(String naam, Rijbewijs...rijbewijslijst) {
        this.naam = naam;
        this.rijbewijzen = new TreeSet<>(Arrays.asList(rijbewijslijst)); 
    }  

    public String getNaam() {
        return naam;
    }

    public Rijbewijs[] getRijbewijs() {
        Rijbewijs[] bewijzen = rijbewijzen.toArray(new Rijbewijs[rijbewijzen.size()]);
        return bewijzen;
    }

    @Override
    public String toString() {
        if (!rijbewijzen.isEmpty()) {
            return naam+Arrays.toString(getRijbewijs()).replace("[","(").replace("]",")");
        } else {
            return naam;
        }
    }
    
    @Override
    public int compareTo(Mens m) {
        return naam.compareTo(m.getNaam());
    }

    @Override
    public int hashCode() {
        return naam.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mens other = (Mens) obj;
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        if (!Objects.equals(this.rijbewijzen, other.rijbewijzen)) {
            return false;
        }
        return true;
    }
    
    
}
