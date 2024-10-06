package com.company.inventory.repaso;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    Calculadora cal;

    @BeforeAll
    public static void primero(){
        System.out.println("primero");
    }

    @AfterAll
    public static void ultimo(){
        System.out.println("ultimo");
    }

    @AfterEach
    public void ultimoPorCadaPrueba(){
        System.out.println("ultimo por cada prueba");
    }

    @BeforeEach
    public void primeroPorCadaPrueba(){
        System.out.println("primero por cada prueba");
        cal = new Calculadora();
    }

    @Test
    @DisplayName("Prueba de sumar calculadora")
    public void Sumar(){
        assertEquals(5,cal.sumar(3,2));
        assertFalse(cal.sumar(2,2) == 5);
    }

    @Test
    @Disabled("se ha deshabilitado por efectos educativos")
    public void Restar(){
        assertEquals(4,cal.restar(6,2));
    }

    @Test
    public void Multiplicar(){
        assertEquals(25,cal.multiplicar(5, 5));
    }

    @Test
    public void Dividir(){
        assertTrue(cal.dividir(10, 2) == 5);
    }



}
