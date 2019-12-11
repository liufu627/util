package alf.util.math;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RadixTest {

    @Test
    public void plus() throws Exception {
        int base=3;
        for(int i=0;i<10;i++) {
            Radix radix = new Radix(base,i);
            System.out.println(String.format("%03d",Integer.valueOf(radix.toString())));
            assertEquals(i, radix.toDec());
        }
    }
}