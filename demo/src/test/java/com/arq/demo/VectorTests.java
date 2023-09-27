package com.arq.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author Prof. Frank J. Affonso
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VectorTests {
    
    private int v1[];
    private int v2[];    
    
    public VectorTests() {
    }
            
    @BeforeEach    
    public void setup() {
        System.out.println("Inicializando os vetores");
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        
        this.v1 = new int[3];
        this.v1 = a;        
        this.v2 = new int[3];
        this.v2 = b;
    }
    
    @AfterEach
    public void finalize() {
        System.out.println("Anulando os vetores");
        this.v1 = null;
        this.v2 = null;
    }

    @Order(2)
    @Test    
    public void testEqual() {        
        boolean expResult = true;        
        boolean result = Vector.equal(this.v1, this.v2);
        assertEquals(expResult, result);        
    }
    
    @Order(1)
    @Test    
    public void testSize() {        
        boolean expResult = true;        
        boolean result = Vector.size(this.v1.length, this.v2.length);        
        assertEquals(expResult, result);        
    }    
}

