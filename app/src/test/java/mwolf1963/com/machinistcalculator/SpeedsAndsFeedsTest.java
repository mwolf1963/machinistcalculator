package mwolf1963.com.machinistcalculator;

import org.junit.Test;

import mwolf1963.com.machinistcalculator.ui.speedsandfeeds.SpeedsAndFeedsViewModel;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SpeedsAndsFeedsTest {
    @Test
    public void test_rpm() {
        //arrange
        float expected = 2292f;
        //delta we will allow
        float delta = .01f;

        SpeedsAndFeedsViewModel testrpm = new SpeedsAndFeedsViewModel();
        testrpm.setDia(.5f);
        testrpm.setSfm(300);

        //assert
        assertEquals(expected, testrpm.getRpm(), delta );
    }
    @Test
    public void test_sfm() {
        //arrange
        float expected = 2292f;
        //delta we will allow
        float delta = .01f;

        SpeedsAndFeedsViewModel test_sfm = new SpeedsAndFeedsViewModel();
        test_sfm.setDia(.5f);
        test_sfm.setSfm(300f);

        //assert
        assertEquals(expected, test_sfm.getRpm(), delta );
    }

    @Test
    public void test_ipm() {
        //arrange
        float expected = 22.92f;
        //delta we will allow
        float delta = .01f;

        SpeedsAndFeedsViewModel test_ipm = new SpeedsAndFeedsViewModel();
        test_ipm.setDia(.5f);
        test_ipm.setRpm(2292);
        test_ipm.setTeeth(4);
        test_ipm.setFpt(.0025f);

        //assert
        assertEquals(expected, test_ipm.getIpm(), delta );
    }



}