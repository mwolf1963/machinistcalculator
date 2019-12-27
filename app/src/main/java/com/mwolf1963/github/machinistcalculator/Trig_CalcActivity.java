package com.mwolf1963.github.machinistcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mwolf1963.github.machinistcalculator.R;

import com.mwolf1963.github.machinistcalculator.ui.trigcalc.TrigCalcFragment;


public class Trig_CalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trig__calc_activity);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TrigCalcFragment.newInstance())
                    .commitNow();
        }
    }
}
