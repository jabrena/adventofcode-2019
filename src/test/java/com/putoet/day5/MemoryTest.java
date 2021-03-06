package com.putoet.day5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.putoet.day7.ExceptionTester.ia;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MemoryTest {
    @Test
    public void testCreateMemory() {
        final List<Integer> integerList = List.of(1,9,10,3,2,3,11,0,99,30,40,50);
        final Memory memory = Memory.of(integerList);

        assertEquals(memory.size(), integerList.size());
        assertEquals(List.of(1,9,10,3,2,3,11,0,99,30,40,50), memory.dump());

        IllegalArgumentException ia = ia(() -> Memory.of(null));
        assertNotNull(ia);
        assertEquals("No memory", ia.getMessage());

        ia = ia(() -> Memory.of(Collections.emptyList()));
        assertNotNull(ia);
        assertEquals("No memory", ia.getMessage());
    }

    @Test
    public void testAccessMemory() {
        final Memory memory = Memory.of(List.of(1,9,10,3,2,3,11,0,99,30,40,50));

        assertEquals(memory.peek(Address.of(0)), Integer.valueOf(1));
        assertEquals(memory.peek(Address.of(11)), Integer.valueOf(50));

        memory.poke(Address.of(9), 31);
        assertEquals(memory.peek(Address.of(9)), Integer.valueOf(31));

        IllegalArgumentException ia = ia( () -> memory.peek(Address.of(12)));
        assertNotNull(ia);
        assertEquals("Invalid memory address 12", ia.getMessage());
    }
}
