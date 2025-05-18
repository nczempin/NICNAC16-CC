package de.czempin.nicnac16.compiler;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReservedTest {

    @Test
    public void findIntReturnsIntEnum() {
        assertEquals(Reserved.INT, Reserved.find("int"));
    }

    @Test
    public void nonReservedReturnsNull() {
        assertNull(Reserved.find("notReserved"));
    }
}
