//TODO: add custom data to the database for each user
package com.mwolf1963.github.machinistcalculator.ui.gAndMCodes;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;
import com.mwolf1963.github.machinistcalculator.CodeDB;
import com.mwolf1963.github.machinistcalculator.G_and_M_codeActivity;
import com.mwolf1963.github.machinistcalculator.R;
import com.mwolf1963.github.machinistcalculator.SettingsActivity;
import com.mwolf1963.github.machinistcalculator.models.MachineCode;

public class GAndMCodesFragment extends Fragment {
    private Spinner controlSelection;
    private Spinner codeSelection;
    private CodeAdapter codeAdapter;
    private RecyclerView codeRecyclerView;
    private CodeDB codeDB;
    private boolean edit;
    private List<MachineCode> list;

    public static GAndMCodesFragment newInstance() {
        return new GAndMCodesFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i("INFO", "in fragment");
        edit = getActivity().getIntent().getExtras().getBoolean("edit");
        Log.i("INFO", "this is what is in the extra: " + edit);
        View v = inflater.inflate(R.layout.g_and_mcodes_fragment, container, false);
        codeDB = CodeDB.get(this.getContext());

        //spinners
        //load values for the machine selection
        controlSelection = v.findViewById(R.id.controlSelection);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(), R.array.machine_Control, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        controlSelection.setAdapter(adapter);

        //set on change listener
        controlSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateList();
                updateUI();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //load values for the code selection
        codeSelection = v.findViewById(R.id.codeSelection);
        final ArrayAdapter<CharSequence> adapterCode = ArrayAdapter.createFromResource(v.getContext(), R.array.code_Type, android.R.layout.simple_spinner_item);
        adapterCode.setDropDownViewResource(android.R.layout.simple_spinner_item);
        codeSelection.setAdapter(adapterCode);

        //set on change listener
        codeSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateList();
                updateUI();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        codeRecyclerView = v.findViewById(R.id.code_recycler);
        codeRecyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        updateList();
        updateUI();
        return v;
    }




    private void updateList(){
        int machine = controlSelection.getSelectedItemPosition();
        int codeType = codeSelection.getSelectedItemPosition();
        String machineTypeString = (machine == 0)? "Generic Fanuc Mill" : (machine == 1)? "Generic Fanuc Lathe": "Siemens 840FD";
        String codeTypeString = (codeType == 0)? "G":  (codeType==1)?"M": "Custom";
        list = codeDB.getCodes(machineTypeString, codeTypeString);
    }
    public void editCode(String code){

    }
    private void updateUI() {
        codeAdapter = new CodeAdapter(getContext(), list,edit);
        codeRecyclerView.setAdapter(codeAdapter);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
 }
}
