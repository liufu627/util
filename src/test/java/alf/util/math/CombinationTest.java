package alf.util.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
//import org.testng.Assert;


public class CombinationTest {
    Combination c=new Combination();
    @Test
    public void bin() {
        assertEquals("1010", c.Bin(10));
        assertEquals("1111110010", c.Bin(1010));
    }

    @Test
    public void factorial() {
        int value =Combination.Factorial(3);
        assertEquals(6,value);

        value =Combination.Factorial(10);
        assertEquals(3628800,value);
    }

    @Test
    public void calcCombination() {
       int value= Combination.CalcCombination(10,3);
        assertEquals(120,value);

        value= Combination.CalcCombination(7,3);
        assertEquals(35,value);
    }

    @Test
    public void in() {
    }

    @Test
    public void getCombinations() {
        //0b10000110,m=7,n=3
        String[] value= Combination.GetCombinations(0b10000110,3);
        assertEquals(35,value.length);
        for (String i:value) {
            System.out.println(i);
        }

    }
}