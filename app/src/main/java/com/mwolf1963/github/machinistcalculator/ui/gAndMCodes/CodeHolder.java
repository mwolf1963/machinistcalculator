package com.mwolf1963.github.machinistcalculator.ui.gAndMCodes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mwolf1963.github.machinistcalculator.R;
import com.mwolf1963.github.machinistcalculator.models.MachineCode;

public class CodeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView codeTextView;
        private TextView category;
        private TextView notes;
        private Button edit_button;
        private MachineCode machineCode;
        private boolean edit;

        public CodeHolder(LayoutInflater inflater, ViewGroup parent, boolean edit) {
            super(inflater.inflate(R.layout.code_view_layout,parent,false));
            codeTextView = itemView.findViewById(R.id.code);
            category = itemView.findViewById(R.id.category);
            notes = itemView.findViewById(R.id.notes);
            edit_button = itemView.findViewById(R.id.edit_button);
            edit_button.setOnClickListener(this);
            this.edit = edit;
            if(edit){
             edit_button.setVisibility(View.VISIBLE);
            }else{
                edit_button.setVisibility(View.GONE);
            }
        }
        public void bind(MachineCode code){
            machineCode = code;
            codeTextView.setText(code.getCode());
            category.setText(code.getCategory());
            notes.setText(code.getNotes());

        }

        @Override
        public void onClick(View v) {
            if(edit){
                Log.i("INFO", "you clicked: " + v.getId() +" " + codeTextView.getText());

            }

        }
    }

