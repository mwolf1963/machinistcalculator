package com.mwolf1963.github.machinistcalculator;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.mwolf1963.github.machinistcalculator.database.CodeBaseHelper;
import com.mwolf1963.github.machinistcalculator.database.CodeCursorWrapper;
import com.mwolf1963.github.machinistcalculator.database.GandMcodeDBSchema;
import com.mwolf1963.github.machinistcalculator.models.MachineCode;

public class CodeDB{
    private static CodeDB codeDB;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public static CodeDB get(Context context){
        if(codeDB == null){
            codeDB = new CodeDB(context);
        }

        return codeDB;
    }
    private CodeDB(Context context){
        this.context = context;
        sqLiteDatabase = new CodeBaseHelper(context).getWritableDatabase();
    }
    public List<MachineCode> getCodes(){
        List<MachineCode> codes = new ArrayList<>();
        CodeCursorWrapper cursor = queryCodes(null,null);
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                codes.add(cursor.getCode());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return codes;
    }

    public List<MachineCode> getCodes(String machine, String code){

            List<MachineCode> codes = new ArrayList<>();
            CodeCursorWrapper codeCursorWrapper = queryCodes( GandMcodeDBSchema.GandMCodeTable.Cols.CONTROL + " = ?  and "  +GandMcodeDBSchema.GandMCodeTable.Cols.CODETYPE + " = ? ", new String[]{machine, code} );
            try {
                codeCursorWrapper.moveToFirst();
                while(!codeCursorWrapper.isAfterLast()) {
                    codes.add(codeCursorWrapper.getCode());
                    codeCursorWrapper.moveToNext();
                }
            } finally {
                codeCursorWrapper.close();
            }

            return codes;
        }


    private CodeCursorWrapper queryCodes( String whereClause, String[] whereArgs) {
        Cursor cursor = sqLiteDatabase.query(GandMcodeDBSchema.GandMCodeTable.TABLE_NAME,
                null, whereClause, whereArgs, null, null, null);
        return new CodeCursorWrapper(cursor);

    }

}
