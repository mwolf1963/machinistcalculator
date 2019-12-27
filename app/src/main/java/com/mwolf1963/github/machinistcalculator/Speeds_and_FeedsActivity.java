package com.mwolf1963.github.machinistcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mwolf1963.github.machinistcalculator.R;

import com.mwolf1963.github.machinistcalculator.ui.speedsandfeeds.SpeedsAndFeedsFragment;

public class Speeds_and_FeedsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speeds_and__feeds_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SpeedsAndFeedsFragment.newInstance())
                    .commitNow();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Log.i("INFO", "in the optionItemSelected");
        switch (menuItem.getItemId()) {
            case R.id.pref:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
