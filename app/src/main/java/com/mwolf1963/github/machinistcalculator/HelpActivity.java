package com.mwolf1963.github.machinistcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mwolf1963.github.machinistcalculator.R;

import com.mwolf1963.github.machinistcalculator.ui.help.HelpFragment;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HelpFragment.newInstance())
                    .commitNow();
        }
    }
}
