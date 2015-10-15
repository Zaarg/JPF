/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 *
 * @author Steve
 */
public abstract class Voertuig implements Serializable, Comparable<Voertuig>{
    private static final long serialVersionUID = 1L;
    private final Nummerplaat nummerplaat;
    private String merk;
    private Datum datumEersteIngebruikname;
    private int aankoopprijs;
    private final int MAX_ZITPLAATSEN;
    private Mens bestuurder;
    private SortedSet<Mens> inzitters=new TreeSet<>(); //niet duidelijk hoe uniek mensen zijn, dus dubbels toegelaten met een List, Linked handig voor addFirst
    private static Rijbewijs[] toegestaneRijbewijzen = Rijbewijs.values();
    
    public Voertuig(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Mens bestuurder, Mens...mensen) throws MensException, IllegalArgumentException {
        setMerk(merk);
        this.datumEersteIngebruikname = datumEersteIngebruikname;
        setAankoopprijs(aankoopprijs);
        if (zitplaatsen >=1) {
            this.MAX_ZITPLAATSEN = zitplaatsen;
        } else {
            throw new IllegalArgumentException("Voertuig: Minimum 1 zitplaats nodig");
        }
        setBestuurder(bestuurder);
        setInzitters(mensen);
        this.nummerplaat = DIV.INSTANCE.getNummerplaat();
    }
    
    public void setMerk(String merk) {
        this.merk = merk;
    }
    
    public void setAankoopprijs(int aankoopprijs) throws IllegalArgumentException {
        if (aankoopprijs > 0) {
            this.aankoopprijs = aankoopprijs;
        } else {
            throw new IllegalArgumentException("Voertuig: Negatieve prijs onmogelijk");
        }
    }
          
    public void setBestuurder(Mens bestuurder) throws MensException {
        if (Collections.disjoint(Arrays.asList(getToegestaneRijbewijzen()),Arrays.asList(bestuurder.getRijbewijs()))) { //Is er geen overlap tussen toegestaan en bezit?
            if (inzitters.isEmpty()) {                                                                                  //Nee en nog geen inzitters dan error voor geen bestuurder
                throw new MensException("Voertuig: Minstens 1 bestuurder met geldig rijbewijs nodig!");
            } else {
                throw new MensException("Voertuig: Bestuurder met geldig rijbewijs nodig!");                                      //Geen overlap maar wel al inzitters betekent dat er al een 
            }                                                                                                           //bestuurder moet zijn, anders geen inzitters.
        } else {
            this.bestuurder = bestuurder;                                                                               //Er is een geldig rijbewijs => wordt bestuurder en deel van
            inzitters.add(bestuurder);                                                                                  //inzitters
        }
        if (inzitters.size() > MAX_ZITPLAATSEN) throw new MensException("Voertuig: Teveel mensen voor dit voertuig!");        //Teveel volk in de auto na instappen bestuurder?
    }
           
    protected Rijbewijs[] getToegestaneRijbewijzen() {
            return toegestaneRijbewijzen;
    }
    
    public void setInzitters(Mens[] mensen) throws MensException {
        Collections.addAll(inzitters,mensen);
        if (inzitters.size() > MAX_ZITPLAATSEN) throw new MensException("Voertuig: Teveel mensen voor dit voertuig!");
    }
    
    public void addIngezetene(Mens inzitter) throws MensException {
        if ( (inzitters.size() < MAX_ZITPLAATSEN) || (inzitters.contains(inzitter)) ){
            inzitters.add(inzitter);
        } else {
            throw new MensException("Voertuig: Geen vrije zitplaatsen meer");
        }
    }
    
    public boolean isIngezetene(Mens inzitter) {
        return inzitters.contains(inzitter);
    }
        
    public Nummerplaat getNummerplaat() {
        return nummerplaat;
    }

    public String getMerk() {
        return merk;
    }

    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruikname;
    }

    public void setDatumEersteIngebruikname(Datum datumEersteIngebruikname) {
        this.datumEersteIngebruikname = datumEersteIngebruikname;
    }

    public int getAankoopprijs() {
        return aankoopprijs;
    }

    protected int getMAX_ZITPLAATSEN() {
        return MAX_ZITPLAATSEN;
    }
      
    public SortedSet<Mens> getInzitters() {
        return inzitters;
    }
    
    public Mens getBestuurder() {
        return bestuurder;
    }
    
    public ArrayList<Mens> getIngezeteneExclusiefBestuurder() {
        ArrayList<Mens> inzittenden = new ArrayList<>(inzitters);
        inzittenden.remove(this.getBestuurder());
        return inzittenden;
    }
    
    public ArrayList<Mens> getIngezetenen() {
        ArrayList<Mens> inzittenden = new ArrayList<>(inzitters);
        return inzittenden;
    }
    
    //Hash, Equals en toString
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nummerplaat);
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
        final Voertuig other = (Voertuig) obj;
        if (!Objects.equals(this.nummerplaat, other.nummerplaat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String s = nummerplaat +" "+ merk +" "+ datumEersteIngebruikname +" "+ aankoopprijs +" "+ bestuurder;
        if (getIngezeteneExclusiefBestuurder().isEmpty()) {
            return s;
        } else {
            return s + " "+getIngezeteneExclusiefBestuurder();
        }
    }

    //Compare overrides, lamdas should qualify as anon. innerclass?
    @Override
    public int compareTo(Voertuig vroem) {
        return this.getNummerplaat().compareTo(vroem.getNummerplaat());
    }
    
    //Works for sorting but NOT serializable?? YES with explicit cast in return
    public static Comparator<Voertuig> getMerkComparator() {
        return (Comparator<Voertuig> & Serializable)(Voertuig vroem1, Voertuig vroem2) -> vroem1.getMerk().compareTo(vroem2.getMerk());   
    }
    
    public static Comparator<Voertuig> getAankoopprijsComparator() {
        return (Comparator<Voertuig> & Serializable)(Voertuig vroem1, Voertuig vroem2) -> vroem1.getAankoopprijs()-vroem2.getAankoopprijs();   
    }
        
    /*public static Comparator<Voertuig> getMerkComparator() {
        return new ComparatorMerk();
    }
    
    public static class ComparatorMerk implements Comparator<Voertuig>,Serializable {
        private static final long serialVersionUID = 1L;
        @Override
        public int compare(Voertuig vroem1, Voertuig vroem2) {
            return vroem1.getMerk().compareTo(vroem2.getMerk());
        }
    }
    
    public static Comparator<Voertuig> getAankoopprijsComparator() {
        return new ComparatorPrijs();
    }
    
    public static class ComparatorPrijs implements Comparator<Voertuig>,Serializable {
        private static final long serialVersionUID = 1L;
        @Override
        public int compare(Voertuig vroem1, Voertuig vroem2) {
            return vroem1.getAankoopprijs()-vroem2.getAankoopprijs();
        }
    }   */
    
}
