/*Author: Matt Wolf
Date: 12-15-19
Desc: wrapper for the cursor. modeled after the task assignment
 */

package com.mwolf1963.github.machinistcalculator.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mwolf1963.github.machinistcalculator.models.MachineCode;

public class CodeCursorWrapper extends CursorWrapper {

    public CodeCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public MachineCode getCode(){
    // fill in the code to make this work
        String code = getString(getColumnIndex(GandMcodeDBSchema.GandMCodeTable.Cols.CODE));

        String notes = getString(getColumnIndex(GandMcodeDBSchema.GandMCodeTable.Cols.NOTES));
        String control = getString(getColumnIndex(GandMcodeDBSchema.GandMCodeTable.Cols.CONTROL));
        String category = getString(getColumnIndex(GandMcodeDBSchema.GandMCodeTable.Cols.CATEGORY));


        MachineCode machineCode = new MachineCode();
        machineCode.setCode(code);
        machineCode.setCategory(category);
        machineCode.setNotes(notes);
        machineCode.setControl(control);


        return machineCode;
    }
}
