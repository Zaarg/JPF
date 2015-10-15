
package be.vdab;

import be.vdab.util.*;
import be.vdab.voertuigen.*;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import static be.vdab.util.Maat.*;
import static be.vdab.util.mens.Rijbewijs.*;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Autoworld exercise VDAB Java Fundamentals
 * @author Steve Schoonooghe
 * 
 * Antwoord op merk je iets raars? Ja een voertuig verdwijnt. 
 * Dit komt door de ordering naar merken, daardoor denkt de Treeset twee gelijke objecten te hebben en verdwijnt er 1 omdat een Set geen dubbels bevat.
 */
public class Main {

    private final static String VOERTUIGFILE = "wagenpark.ser";     //String for file to write third Set to
    
    public static void main(String[] args) {
    
    SortedSet<Voertuig> vroem = new TreeSet<>();
    try {                                                           //Initialize favorite cars and add to Set
        Voertuig herby = new Personenwagen("Smeetle", new Datum(13,3,1969), 53000, 4, Color.WHITE, new Mens("Jim Douglas", B),new Mens("Tennessee Steinmetz", B),new Mens("Carole Bennett"));
        Voertuig delorean = new Personenwagen("FutureCars", new Datum(3,7,1985), 42420, 2, new Color(128,128,128), new Mens("Marty McFly", B),new Mens("Dr. Emmett Brown",BE));
        Voertuig ateam = new Pickup("Black", new Datum(23,1,1983), 18888, 6, Color.BLACK, new Volume(2,2,3, meter), new Mens("BA", A,B,C,BE,CE,D,DE),new Mens("Hannibal", A,B,C,BE,CE,D,DE),new Mens("Face", A,B,C,BE,CE,D,DE),new Mens("Murdoch", A,B,C,BE,CE,D,DE));
        Voertuig kit = new Pickup("Chevolait", new Datum(26,9,1982), 66666, 4, Color.BLACK, new Volume(1,1,2, meter), new Mens("Michael Knight", A,B,C,BE));
        Voertuig optimus = new Vrachtwagen("Maf", new Datum(17,9,1984), 180300, 1, new Volume(5,10,20, meter), 10000, 6, new Mens("Optimus AI", C,CE,D,DE));
        Voertuig mack = new Vrachtwagen("Maf", new Datum(5,7,2006), 96321, 2, new Volume(3,5,10, meter), 4000,4, new Mens("Lightning McQueen", B,C,BE,C,CE,D));
        vroem.add(herby);
        vroem.add(delorean);
        vroem.add(ateam);
        vroem.add(kit);
        vroem.add(optimus);
        vroem.add(mack);
    } catch (IllegalArgumentException | MensException | DatumException | VolumeException arg) {
        System.out.println(arg.getMessage());
        System.exit(1);
    }
    System.out.println("\nVoertuigen volgens natural ordering:\n------------------------------------");             //Display First Set according to natural ordering (Numberplate)
    vroem.stream().forEach(vehikel -> System.out.println(vehikel.toString()));
    
    System.out.println("\nVoertuigen volgens dalende aankopprijs:\n---------------------------------------");       //Second set with reversed price ordering
    SortedSet<Voertuig> broem = new TreeSet<>(Voertuig.getAankoopprijsComparator().reversed());
    broem.addAll(vroem);
    broem.stream().forEach(vehikel -> System.out.println(vehikel.toString()));
    
    System.out.println("\nVoertuigen volgens merk:\n------------------------");                 //Third set with brand ordering
    SortedSet<Voertuig> groem = new TreeSet<>(Voertuig.getMerkComparator());
    groem.addAll(vroem);
    groem.stream().forEach(vehikel -> System.out.println(vehikel.toString()));
    ObjectOutputStream auto = null;                                                             //Write Set to File
    try {
        auto = new ObjectOutputStream(new FileOutputStream(VOERTUIGFILE));     
        auto.writeObject(groem);
    } catch (IOException e) {
            System.out.println(e.getMessage());
    } finally {
            if (auto != null) {
                try {
                    auto.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
    }
    
    System.out.println("\nVoertuigen na laden uit file:\n-----------------------------");       //Read Fourth Set from file and display
    ObjectInputStream camion = null;
    try {
        camion = new ObjectInputStream(new FileInputStream(VOERTUIGFILE));     
        SortedSet<Voertuig> froem = new TreeSet<>((TreeSet<Voertuig>)camion.readObject());
        froem.stream().forEach(vehikel -> System.out.println(vehikel.toString()));
    } catch (IOException | ClassNotFoundException |ClassCastException e) {
            System.out.println(e.getMessage());
    } finally {
            if (camion != null) {
                try {
                    camion.close();
                } catch (IOException eg) {
                    System.out.println(eg.getMessage());
                }
            }
    }
    
    
  }
}
