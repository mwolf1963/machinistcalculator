//TODO: this needs refactored into using live data somehow
package com.mwolf1963.github.machinistcalculator.ui.speedsandfeeds;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mwolf1963.github.machinistcalculator.R;

public class SpeedsAndFeedsFragment extends Fragment implements View.OnFocusChangeListener {

    private SharedPreferences saved_values;
    private float unit_multiplier_per_min;
    private float unit_multiplier_linear;
    private SpeedsAndFeedsViewModel mViewModel;
    private EditText rpmET;
    private EditText sfmET;
    private EditText ipmET;
    private EditText fptET;
    private EditText diaET;
    private TextView sfmLabel;
    private TextView ipmLabel;
    private Spinner teethSpinner;


    public static SpeedsAndFeedsFragment newInstance() {
        return new SpeedsAndFeedsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.speeds_and_feeds_fragment, container, false);
        PreferenceManager.setDefaultValues(getContext(), R.xml.root_preferences, false);
        saved_values = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (saved_values.getBoolean(getResources().getString(R.string.units_key), false)){
            unit_multiplier_per_min = .3f;
            unit_multiplier_linear =25.4f;
        } else{
            unit_multiplier_per_min = 1;
            unit_multiplier_linear = 1;
        }

        sfmLabel = v.findViewById(R.id.sfmLabelTextView);
        ipmLabel = v.findViewById(R.id.ipmLabelTextView);
        //this is nasty and needs refactored into a more generic way of doing it
        rpmET =  v.findViewById(R.id.rpmEditText);
        rpmET.setOnFocusChangeListener(this);

        sfmET = v.findViewById(R.id.sfmEditText);
        sfmET.setOnFocusChangeListener(this);

        fptET =  v.findViewById(R.id.ftpEditText);
        fptET.setOnFocusChangeListener(this);

        ipmET = v.findViewById(R.id.ipmEditText);
        ipmET.setOnFocusChangeListener(this);

        diaET =  v.findViewById(R.id.diaEditText);
        diaET.setOnFocusChangeListener(this);

        //spinner for the number of teeth
        teethSpinner = v.findViewById(R.id.teethspinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.number_Of_Teeth, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        teethSpinner.setAdapter(adapter);
        //set on change listener


        teethSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                //incase someone clicks on the spinner and focus doesnt catch it recall the ui
//                updateUI();
                String teeth = adapterView.getItemAtPosition(i).toString();
                int numteeth = Integer.parseInt(teeth);
                mViewModel.setTeeth(numteeth);
                updateUI();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                updateUI();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new SpeedsAndFeedsViewModel();



    }
    @Override
    public void onResume(){
        super.onResume();
        if (saved_values.getBoolean(getResources().getString(R.string.units_key), false)){
            unit_multiplier_per_min = .3f;
            unit_multiplier_linear =25.4f;
            sfmLabel.setText("MPM");
            ipmLabel.setText("MMPM");
        } else{
            unit_multiplier_per_min = 1;
            unit_multiplier_linear = 1;
            sfmLabel.setText("SFM");
            ipmLabel.setText("IPM");
        }
        updateUI();
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        switch (view.getId()){
            case (R.id.rpmEditText):
                if (tryParse(rpmET)) {
                    float rpm = Float.parseFloat(rpmET.getText().toString());
                    if (mViewModel.getRpm() != rpm) {
                        mViewModel.setRpm(rpm);
                    }
                }
                break;
            case (R.id.sfmEditText):
                if (tryParse(sfmET)) {
                    float sfm = Float.parseFloat(sfmET.getText().toString());
                    if (mViewModel.getSfm() != sfm) {
                        mViewModel.setSfm(sfm);
                    }
                }
                break;
            case (R.id.ipmEditText):
                if (tryParse(ipmET)) {
                    float ipm = Float.parseFloat(ipmET.getText().toString());
                    if (mViewModel.getIpm() != ipm) {
                        mViewModel.setIpm(ipm);
                    }
                }
                break;
            case (R.id.ftpEditText):
                if (tryParse(fptET)) {
                    float fpt = Float.parseFloat(fptET.getText().toString());
                    if (mViewModel.getFpt() != fpt) {
                        mViewModel.setFpt(fpt);
                    }
                }
                break;
            case (R.id.diaEditText):
                if (tryParse(diaET)) {
                    float dia = Float.parseFloat(diaET.getText().toString());
                    if (mViewModel.getDia() != dia) {
                        mViewModel.setDia(dia);
                    }
                }
                break;

            default:
                break;
        }
        updateUI();


        Log.i("INFO", "current state : " + mViewModel.getRpm() );
        Log.i("INFO", "current state : " + mViewModel.getSfm() );
        Log.i("INFO", "current state : " + mViewModel.getFpt() );
        Log.i("INFO", "current state : " + mViewModel.getIpm() );



    }
    private void updateUI(){
         if (mViewModel.getRpm() != 0f){
            rpmET.setText(mViewModel.getRpm()+ "");
        }
         if (mViewModel.getSfm() != 0f){
             sfmET.setText(mViewModel.getSfm() * unit_multiplier_per_min+ "");
         }
        if (mViewModel.getDia() != 0f){
            diaET.setText(mViewModel.getDia() * unit_multiplier_linear + "");
        }
        if (mViewModel.getIpm() != 0f){
            ipmET.setText(mViewModel.getIpm() * unit_multiplier_linear+ "");
        }
        if (mViewModel.getFpt() != 0f){
            fptET.setText(mViewModel.getFpt()+ "");
        }

    }
    private boolean tryParse(EditText et){
        try{
            float num = Float.parseFloat(et.getText().toString());
            return true;
        }catch (NumberFormatException nf){
         return false;
        }catch (Exception ex){
            Log.i("INFO", "something went poorly..." + ex.getMessage());
            return false;
        }

    }
}
