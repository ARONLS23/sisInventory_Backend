package com.company.inventory.repaso;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AssertArrayEqualsTest {

    @Test
    public void miTest(){
        String [] array1 = {"aa", "bb"};
        String [] array2 = {"aa", "bb"};
        String [] array3 = {"aa", "bb", "cc"};

        assertArrayEquals(array1, array2);
        assertArrayEquals(array1, array2);
    }

}
