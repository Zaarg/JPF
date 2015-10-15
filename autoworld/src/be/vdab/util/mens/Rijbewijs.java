/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

/**
 *
 * @author steve.schoonooghe
 */
public enum Rijbewijs { 
    
    A, B, BE 
        
    {@Override
    public String toString() {return "B+E";} 
    }
    
    , C, CE 
    
    {@Override
    public String toString() {return "C+E";}
    }
    
    , D, DE

    {@Override
    public String toString() {return "D+E";} 
    };
}

    

