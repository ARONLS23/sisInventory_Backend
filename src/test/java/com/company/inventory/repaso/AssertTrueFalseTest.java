package com.company.inventory.repaso;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTrueFalseTest {

    @Test
    public void miTest1(){
        assertTrue(true);
        assertFalse(false);
    }

    @Test
    public void miTest2(){
        boolean expression1 = (4 == 4);
        boolean expression2 = (4 == 2);

        assertTrue(expression1);
        assertFalse(expression2);
    }


}
