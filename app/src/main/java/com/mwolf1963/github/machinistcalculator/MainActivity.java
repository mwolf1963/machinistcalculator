package com.mwolf1963.github.machinistcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mwolf1963.github.machinistcalculator.R;

public class MainActivity extends AppCompatActivity {

    private boolean edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = false;
        //calling up each layout to make sure that they look ok
        Button sfbutton = findViewById(R.id.speedsAndFeedsButton);
        sfbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Speeds_and_FeedsActivity.class);
                in.putExtra("caller","Speeds_and_FeedsActivity");

                startActivity(in);
            }
        });
        Button trigCalButton = findViewById(R.id.trigCalculatorButton);
        trigCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Trig_CalcActivity.class));
            }
        });
        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HelpActivity.class));
            }
        });
        Button gmCode = findViewById(R.id.gAndMcodesButton);
        gmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("INFO", "in the main not blowing up");
                Intent intent = new Intent(getApplicationContext(), G_and_M_codeActivity.class);
                intent.putExtra("edit", edit);
                startActivity(intent);
            }
        });


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
            case R.id.edit_G_and_M_code:
                Intent intent = new Intent(this, G_and_M_codeActivity.class);
                edit = true;
                intent.putExtra("edit", edit);
                startActivity(intent);
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
