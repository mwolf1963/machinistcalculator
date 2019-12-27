package com.mwolf1963.github.machinistcalculator.ui.gAndMCodes;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.mwolf1963.github.machinistcalculator.models.MachineCode;

public class CodeAdapter extends RecyclerView.Adapter<CodeHolder>{
    private List<MachineCode> codes;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private boolean edit ;

    public CodeAdapter(Context context, List<MachineCode> codes, boolean edit){
        this.mContext = context;
        this.codes = codes;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.edit =edit;
    }
    @NonNull
    @Override
    public CodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CodeHolder(mLayoutInflater,parent, edit);
    }
    @Override
    public void onBindViewHolder(@NonNull CodeHolder holder, int position) {
        MachineCode code = codes.get(position);
        holder.bind(code);
    }
    @Override
    public int getItemCount() {
        return codes.size();
    }
}

