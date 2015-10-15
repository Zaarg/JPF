/*
* @Autor Frank
* Dit is een oefening.
 */
package be.vdab.voertuigen.div;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import org.junit.internal.runners.statements.ExpectException;
import static org.junit.Assert.*;

public class DIVTest {

    @Test
    public void test_singleton() {
        assertSame(DIV.INSTANCE, DIV.INSTANCE);
    }

    @Test
    public void test_slechts_1_value(){
        assertEquals(1, DIV.values().length);
    }
    @Test
    public void test_niet_bestaan_setters() {
        Method[] lijst = DIV.class.getDeclaredMethods();
        boolean probleem = false;
        for (Method m : lijst) {
            probleem &= m.isAccessible() && m.getName().startsWith("set");
        }
        if (probleem) {
            fail("there is a setter method in DIV");
        }
    }

    @Test
    public void test_singleton_not_null() {
        assertNotNull(DIV.INSTANCE);
    }

    @Test
    public void test_Nummerplaat() {
        assertNotNull(DIV.INSTANCE.getNummerplaat());
    }

    @Test
    public void test_Verschillende_Nummerplaten() {
        assertNotSame(DIV.INSTANCE.getNummerplaat(), DIV.INSTANCE.getNummerplaat());
    }

    @Test
    public void test_Rotatie_Verschillende_Nummerplaat_Objecten() {
        Nummerplaat eerste = DIV.INSTANCE.getNummerplaat();
        for (int teller = 1; teller <= 998; teller++) {
            Nummerplaat plaat = DIV.INSTANCE.getNummerplaat();
        }
        Nummerplaat terugEerste = DIV.INSTANCE.getNummerplaat();
        assertNotSame(eerste, terugEerste);
        assertEquals(eerste, terugEerste);
    }

    @Test
    public void test_Rotatie_Verschillende_Nummerplaten_Waarden() {
        Set<Nummerplaat> platen=null;
        synchronized (DIV.INSTANCE) {
            platen = new HashSet<Nummerplaat>(999);
            for (int teller = 1; teller <=999; teller++) {
                platen.add(DIV.INSTANCE.getNummerplaat());
            }
        }
        assertEquals(platen.size(), 999);
    }
}
