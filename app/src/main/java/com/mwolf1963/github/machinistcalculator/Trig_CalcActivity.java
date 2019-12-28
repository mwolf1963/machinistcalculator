package com.mwolf1963.github.machinistcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
            case R.id.edit_G_and_M_code:
                Intent intent = new Intent(this, G_and_M_codeActivity.class);

                intent.putExtra("edit", true);
                startActivity(intent);
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
