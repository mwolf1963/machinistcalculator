//TODO: this needs refactored into using live data somehow
package com.mwolf1963.github.machinistcalculator.ui.trigcalc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.NumberFormat;

import com.mwolf1963.github.machinistcalculator.R;
import com.mwolf1963.github.machinistcalculator.models.TriAngle;

public class TrigCalcFragment extends Fragment  {

    private TriAngle triAngle;

    private EditText a_length;
    private EditText b_length;
    private EditText h_length;
    private EditText a_angle;
    private EditText b_angle;

    private boolean suspenedTextChangeListeners;

    // hate to store state like this but ....
    String a_length_string;
    String b_length_string;
    String h_length_string;
    String a_angle_string;
    String b_angle_string;

    EditText last_Entered;
    EditText last_Entered_Length;


    //formatter for output
    private NumberFormat nf;
    private int precision;

    public static TrigCalcFragment newInstance() {
        return new TrigCalcFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trig_calc_fragment, container, false);
        Log.i("INFO", "onViewCreate");

        //hard coding for now until i can figure out how to make it a setting
        precision =3;
        nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(precision);

        //get the widgets
        a_length = v.findViewById(R.id.a_length_EditText);
        b_length = v.findViewById(R.id.b_length_EditText);
        h_length = v.findViewById(R.id.h_length_EditText);
        a_angle = v.findViewById(R.id.a_angle_Input_EditText);
        b_angle = v.findViewById(R.id.b_angle_input_EditText);
        suspenedTextChangeListeners = false;

        //capture the key strokes and alter the field based on the last input and the current field
        applyTextChangeListeners();

        //capture on leaving the box and store that is is the last entered
        a_length.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (! hasFocus){
                    if (tryParse(a_length)){
                        last_Entered = a_length;
                    }
                }
                if (last_Entered != null) {
                    Log.i("INFO", last_Entered.getId() + " this is last entered");
                }
            }
        });

        b_length.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (! hasFocus){
                    if (tryParse(b_length)){
                        last_Entered = b_length;
                    }
                }
                if (last_Entered != null) {
                    Log.i("INFO", last_Entered.getId() + " this is last entered");
                }
            }
        });


        //capture h
        h_length.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (! hasFocus){
                    if (tryParse(h_length)){
                        last_Entered = h_length;
                    }
                }
                if (last_Entered != null) {
                    Log.i("INFO", last_Entered.getId() + " this is last entered");
                }
            }
        });

        a_angle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (! hasFocus){
                    if (tryParse(a_angle)) {
                        if (last_Entered != b_angle) {
                            last_Entered_Length = last_Entered;
                            last_Entered = a_angle;
                        }else {
                            last_Entered = last_Entered_Length;
                        }
                    }
                }
                if (last_Entered != null) {
                    Log.i("INFO", last_Entered.getId() + " this is last entered");
                }
            }
        });

        b_angle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (! hasFocus){
                    if (tryParse(b_angle)) {
                        if (last_Entered != a_angle) {
                            last_Entered_Length = last_Entered;
                            last_Entered = b_angle;
                        } else{
                            last_Entered = last_Entered_Length;
                        }
                    }
                }
                if (last_Entered != null) {
                    Log.i("INFO", last_Entered.getId() + " this is last entered");
                }
            }
        });

        return v;

    }

    private void applyTextChangeListeners() {
        a_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("INFO", a_length.getText().toString() + " before text changed listner");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!suspenedTextChangeListeners){
                    if (s.toString().isEmpty()){
                        clearAllButLast(a_length);
                    }
                Log.i("INFO", a_length.getText().toString() + " after text changed listner");
                if(last_Entered != a_length && last_Entered != null && tryParse(a_length)) {
                    switch (last_Entered.getId()) {
                        case (R.id.a_angle_Input_EditText):
                            triAngle.calc_with_SideA_and_AngleA(Float.parseFloat(a_length.getText().toString()), Float.parseFloat(a_angle.getText().toString()));
                            break;
                        case (R.id.b_angle_input_EditText):
                            triAngle.calc_with_SideA_and_AngleB(Float.parseFloat(a_length.getText().toString()), Float.parseFloat(b_angle.getText().toString()));
                            break;
                        case (R.id.b_length_EditText):
                            triAngle.calc_with_LenA_and_LenB(Float.parseFloat(a_length.getText().toString()), Float.parseFloat(b_length.getText().toString()));
                            break;
                        case (R.id.h_length_EditText):
                            triAngle.calc_with_LenA_and_LenH(Float.parseFloat(a_length.getText().toString()), Float.parseFloat(h_length.getText().toString()));
                            break;
                    }

                    Log.i("INFO", "Triangle current state. Side A : " + triAngle.getSide_A_Length() + " Side B : " + triAngle.getSide_B_Length() + " Side H : " +
                            triAngle.getSide_H_Length() + " Angle A :" + triAngle.getAngle_A() + " Angle B : " + triAngle.getAngle_B());
                    upDateUI();
                }


                }

            }
        });
        b_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("INFO", b_length.getText().toString() + " before text changed listner");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!suspenedTextChangeListeners) {
                    if (s.toString().isEmpty()){
                        clearAllButLast(b_length);
                    }
                    Log.i("INFO", b_length.getText().toString() + " after text changed listener");
                    if (last_Entered != b_length && last_Entered != null && tryParse(b_length)) {
                        switch (last_Entered.getId()) {
                            case (R.id.a_angle_Input_EditText):
                                triAngle.calc_with_SideB_and_AngleA(Float.parseFloat(b_length.getText().toString()), Float.parseFloat(a_angle.getText().toString()));
                                break;
                            case (R.id.b_angle_input_EditText):
                                triAngle.calc_with_SideB_and_AngleB(Float.parseFloat(b_length.getText().toString()), Float.parseFloat(b_angle.getText().toString()));
                                break;
                            case (R.id.a_length_EditText):
                                triAngle.calc_with_LenA_and_LenB(Float.parseFloat(a_length.getText().toString()), Float.parseFloat(b_length.getText().toString()));
                                break;
                            case (R.id.h_length_EditText):
                                triAngle.calc_with_LenB_and_LenH(Float.parseFloat(b_length.getText().toString()), Float.parseFloat(h_length.getText().toString()));
                                break;
                        }
                        upDateUI();
                    }
                }

            }
        });
        h_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("INFO", h_length.getText().toString() + " before text changed listner");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!suspenedTextChangeListeners){
                    if (s.toString().isEmpty()){
                        clearAllButLast(h_length);
                    }
                Log.i("INFO", h_length.getText().toString() + " after text changed listner");
                if(last_Entered != h_length && last_Entered != null && tryParse(h_length)) {
                    switch (last_Entered.getId()) {
                        case (R.id.a_angle_Input_EditText):
                            triAngle.calc_with_SideH_and_AngleA(Float.parseFloat(h_length.getText().toString()), Float.parseFloat(a_angle.getText().toString()));
                            break;
                        case (R.id.b_angle_input_EditText):
                            triAngle.calc_with_SideH_and_AngleB(Float.parseFloat(h_length.getText().toString()), Float.parseFloat(b_angle.getText().toString()));
                            break;
                        case (R.id.a_length_EditText):
                            triAngle.calc_with_LenA_and_LenB(Float.parseFloat(a_length.getText().toString()), Float.parseFloat(h_length.getText().toString()));
                            break;
                        case (R.id.b_length_EditText):
                            triAngle.calc_with_LenB_and_LenH(Float.parseFloat(b_length.getText().toString()), Float.parseFloat(h_length.getText().toString()));
                            break;
                    }
                    upDateUI();
                }
                }

            }
        });
        a_angle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("INFO", a_angle.getText().toString() + " before text changed listner");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!suspenedTextChangeListeners) {
                    if (s.toString().isEmpty()){
                        clearAllButLast(a_angle);
                    }
                    Log.i("INFO", a_angle.getText().toString() + " after text changed listner");
                    if (last_Entered != a_angle && last_Entered != null && tryParse(a_angle)) {
                        switch (last_Entered.getId()) {
                            case (R.id.b_angle_input_EditText):
                                break;
                            case (R.id.a_length_EditText):
                                triAngle.calc_with_SideA_and_AngleA(Float.parseFloat(a_length.getText().toString()),Float.parseFloat(a_angle.getText().toString()));
                                break;
                            case (R.id.b_length_EditText):
                                triAngle.calc_with_SideB_and_AngleA(Float.parseFloat(b_length.getText().toString()), Float.parseFloat(a_angle.getText().toString()));
                                break;
                            case (R.id.h_length_EditText):
                                triAngle.calc_with_SideH_and_AngleA(Float.parseFloat(h_length.getText().toString()), Float.parseFloat(a_angle.getText().toString()));
                                break;
                        }
                        upDateUI();
                    }
                }
            }
        });
        b_angle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("INFO", b_angle.getText().toString() + " before text changed listner");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!suspenedTextChangeListeners) {
                    if (s.toString().isEmpty()){
                        clearAllButLast(b_angle);
                    }
                    Log.i("INFO", a_angle.getText().toString() + " after text changed listner");
                    if (last_Entered != b_angle && last_Entered != null && tryParse(b_angle)) {
                        switch (last_Entered.getId()) {
                            case (R.id.a_angle_Input_EditText):
                                break;
                            case (R.id.a_length_EditText):
                                triAngle.calc_with_SideA_and_AngleB(Float.parseFloat(a_length.getText().toString()), Float.parseFloat(b_angle.getText().toString()));
                                break;
                            case (R.id.b_length_EditText):
                                triAngle.calc_with_SideB_and_AngleB(Float.parseFloat(b_length.getText().toString()), Float.parseFloat(b_angle.getText().toString()));
                                break;
                            case (R.id.h_length_EditText):
                                triAngle.calc_with_SideH_and_AngleB(Float.parseFloat(h_length.getText().toString()), Float.parseFloat(b_angle.getText().toString()));
                                break;
                        }
                 upDateUI();
                    }
                }

            }
        });
    }

    private void clearAllButLast(EditText et) {
        suspenedTextChangeListeners = true;
        if (!a_length.hasFocus() && last_Entered != a_length){
            a_length.setText("");
        }
        if (!b_length.hasFocus() && last_Entered != b_length){
            b_length.setText("");
        }
        if (!h_length.hasFocus() && last_Entered != h_length){
            h_length.setText("");
        }
        if (!a_angle.hasFocus() && last_Entered != a_angle){
            a_angle.setText("");
        }
        if (!b_angle.hasFocus() &&  last_Entered != b_angle){
            b_angle.setText("");
        }
        suspenedTextChangeListeners = false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       triAngle = new TriAngle();
       Log.i("INFO", "onActivityCreate");

    }


    private void upDateUI(){

        if (triAngle.getAngle_A() != 0 && triAngle.getAngle_B() != 0 && triAngle.getSide_A_Length() != 0 && triAngle.getSide_B_Length() != 0 && triAngle.getSide_H_Length() != 0){
            suspenedTextChangeListeners = true;
            if (!a_angle.hasFocus()) {
                a_angle.setText(nf.format(triAngle.getAngle_A()));
            }
            if (!b_angle.hasFocus()) {
                b_angle.setText(nf.format(triAngle.getAngle_B()));
            }
            if (!a_length.hasFocus()) {
                a_length.setText(nf.format(triAngle.getSide_A_Length()));
            }
            if (!b_length.hasFocus()) {
                b_length.setText(nf.format(triAngle.getSide_B_Length()));
            }
            if (!h_length.hasFocus()) {
                h_length.setText(nf.format(triAngle.getSide_H_Length()));
            }
            suspenedTextChangeListeners = false;
        }
    }



    private boolean tryParse(EditText et){
        try{
            float num = Float.parseFloat(et.getText().toString());
            return true;
        }catch (NumberFormatException nf){
            return false;
        }catch (Exception ex){

            return false;
        }

    }
}
