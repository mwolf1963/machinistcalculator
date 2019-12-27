package com.mwolf1963.github.machinistcalculator;

import org.junit.Test;

import mwolf1963.com.machinistcalculator.models.TriAngle;


import static org.junit.Assert.*;

public class TrigCalculatorTests {


    @Test
    public void test_Side_A_Side_H() {
        float expected = 4;
        float delta = .001f;
        TriAngle tri = new TriAngle();
        tri.calc_with_LenA_and_LenH(3,5);
        assertEquals(expected, tri.getSide_B_Length(), delta);

    }

    @Test
    public void test_Angle_B_side_A() {
        float expected = 1;
        float delta = .001f;
        TriAngle tri = new TriAngle();
        tri.calc_with_SideB_and_AngleA(1f,45f);
        assertEquals(expected, tri.getSide_A_Length(), delta);
    }
    @Test
    public void test_Angle_B_side_B() {
        float expected = 1;
        float delta = .001f;
        TriAngle tri = new TriAngle();
        tri.calc_with_SideB_and_AngleB(1f,45f);
        assertEquals(expected, tri.getSide_A_Length(), delta);
    }


}
