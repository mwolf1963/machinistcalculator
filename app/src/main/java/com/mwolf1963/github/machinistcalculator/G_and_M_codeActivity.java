package com.mwolf1963.github.machinistcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.mwolf1963.github.machinistcalculator.R;

import com.mwolf1963.github.machinistcalculator.ui.gAndMCodes.GAndMCodesFragment;

public class G_and_M_codeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("INFO","in the oncreate activity.... still not blowing up");
        setContentView(R.layout.g_and_mcodes_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.g_and_m_container, GAndMCodesFragment.newInstance())
                    .commitNow();
        }


    }
}
