/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;

/**
 *
 * @author steve.schoonooghe
 */
public final class Datum implements Serializable,Comparable<Datum>{
    
    private static final long serialVersionUID = 1L;
    private static final int[] LENGTEMAANDEN = {31,28,31,30,31,30,31,31,30,31,30,31};
    private int dag;
    private int maand;
    private int jaar;

    public Datum(int dag, int maand, int jaar) throws DatumException {
        if (jaar<1583 || jaar>4099) {
            throw new DatumException("Datum: Jaar out of range");
        } else {
            this.jaar = jaar;
        }
        if (maand<1 || maand>12) {
            throw new DatumException("Datum: Maand out of range");
        } else {
            this.maand = maand;
        }
        if (dag<1 || ((maand != 2) && (dag > LENGTEMAANDEN[maand-1])) || ( (maand == 2) && (dag > 28 + extraSchrikkeljaarDag(jaar)) ) ) {
            throw new DatumException("Datum: Dag out of range");
        } else {
            this.dag = dag;
        }
    }
    
    private int extraSchrikkeljaarDag(int jaar) {
        return ( ( (jaar %4 == 0) && (jaar %100 != 0) ) || (jaar %400==0))? 1 : 0;
    }
    
    public int getDag() {
        return dag;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    } 
    
    @Override
    public int compareTo(Datum d) {
        int datumInt = jaar*10000 + maand*100 + dag;                   //Integer.parseInt(String.format("%04d%02d%02d", jaar, maand, dag));
        int dInt = d.getJaar()*10000 + d.getMaand()*100 + d.getDag();  //Integer.parseInt(String.format("%04d%02d%02d", d.getJaar(), d.getMaand(), d.getDag()));
        return datumInt-dInt;
    }
        
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dag, maand, jaar);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.dag;
        hash = 59 * hash + this.maand;
        hash = 59 * hash + this.jaar;
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
        final Datum other = (Datum) obj;
        if (this.dag != other.dag) {
            return false;
        }
        if (this.maand != other.maand) {
            return false;
        }
        if (this.jaar != other.jaar) {
            return false;
        }
        return true;
    }
    
    
}
