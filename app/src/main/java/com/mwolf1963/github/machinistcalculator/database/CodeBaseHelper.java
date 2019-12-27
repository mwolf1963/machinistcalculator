package com.mwolf1963.github.machinistcalculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.mwolf1963.github.machinistcalculator.models.MachineCode;

public class CodeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String DATABASE_NAME = "gandmcodeDataBase.db";

    public CodeBaseHelper(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + GandMcodeDBSchema.GandMCodeTable.TABLE_NAME + "("
    + GandMcodeDBSchema.GandMCodeTable.Cols.CODE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    GandMcodeDBSchema.GandMCodeTable.Cols.CONTROL + " TEXT NOT NULL REFERENCES " +
                    GandMcodeDBSchema.MachineControlTable.TABLE_NAME + " ( " + GandMcodeDBSchema.MachineControlTable.Cols.CONTROL_NAME +")," +
                    GandMcodeDBSchema.GandMCodeTable.Cols.CODE +" TEXT," +
                    GandMcodeDBSchema.GandMCodeTable.Cols.CODETYPE +" TEXT," +
                    GandMcodeDBSchema.GandMCodeTable.Cols.CATEGORY +" TEXT," +
                    GandMcodeDBSchema.GandMCodeTable.Cols.NOTES + " TEXT);"
             );
    db.execSQL("CREATE TABLE " + GandMcodeDBSchema.MachineControlTable.TABLE_NAME + "("+
            GandMcodeDBSchema.MachineControlTable.Cols.MACHINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GandMcodeDBSchema.MachineControlTable.Cols.CONTROL_NAME + "TEXT NOT NULL);"
            );
    new InitialValues(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static class InitialValues{

        private  InitialValues(SQLiteDatabase db){
        //sets up the initial database values
        List<MachineCode> machineCodes = getInitialListOfCodes();
        for (int i = 0; i < machineCodes.size(); i++){
            ContentValues values = getContentValues(machineCodes.get(i));
            db.insert(GandMcodeDBSchema.GandMCodeTable.TABLE_NAME, null, values);
        }


        }

        private ContentValues getContentValues(MachineCode code) {
            ContentValues values = new ContentValues();
            values.put(GandMcodeDBSchema.GandMCodeTable.Cols.CONTROL, code.getControl());
            values.put(GandMcodeDBSchema.GandMCodeTable.Cols.CODE, code.getCode());
            values.put(GandMcodeDBSchema.GandMCodeTable.Cols.CATEGORY, code.getCategory());
            values.put(GandMcodeDBSchema.GandMCodeTable.Cols.NOTES, code.getNotes());
            values.put(GandMcodeDBSchema.GandMCodeTable.Cols.CODETYPE, code.getCodeType());
            return values;
        }

        private List<MachineCode> getInitialListOfCodes() {
            List<MachineCode> machineCodes = new ArrayList<>();
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ,"G00 "
                    ,"Motion "
                    ," Move in a straight line at rapids speed. XYZ of endpoint ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    , "G01 "
                    ,"Motion "
                    , "Move in a straight line at last speed commanded by a (F)eedrate XYZ of endpoint ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ,"G02 "
                    ,"Motion "
                    ," Clockwise circular arc at (F)eedrate XYZ of endpoint IJK relative to center R for radius ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G03 "
                    ,"Motion "
                    ," Counter-clockwise circular arc at (F)eedrate XYZ of endpoint IJK relative to center R for radius ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G04 "
                    ,"Motion "
                    ," Dwell: Stop for a specified time. P for milliseconds X for seconds ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G05 "
                    ,"Motion "
                    ," FADAL Non-Modal Rapids ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G09 "
                    ,"Motion "
                    ," Exact stop check ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G10 "
                    ,"Compensation "
                    ," Programmable parameter input ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G15 "
                    ,"Coordinate "
                    ," Turn Polar Coordinates OFF, return to Cartesian Coordinates ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G16 "
                    ,"Coordinate "
                    ," Turn Polar Coordinates ON ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G17 "
                    ,"Coordinate "
                    ," Select X-Y plane ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G18 "
                    ,"Coordinate "
                    ," Select X-Z plane ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G19 "
                    ,"Coordinate "
                    ," Select Y-Z plane ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G20 "
                    ,"Coordinate "
                    ," Program coordinates are inches ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G21 "
                    ,"Coordinate "
                    ," Program coordinates are mm ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G27 "
                    ,"Motion "
                    ," Reference point return check ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G28 "
                    ,"Motion "
                    ," Return to home position ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G29 "
                    ,"Motion "
                    ," Return from the reference position ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    , "G30 "
                    ,"Motion "
                    , "Return to the 2nd, 3rd, and 4th reference point ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G32 "
                    ,"Canned "
                    ," Constant lead threading (like G01 synchronized with spindle) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G40 "
                    ,"Compensation "
                    ," Tool cutter compensation off (radius comp.) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G41 "
                    ,"Compensation "
                    ," Tool cutter compensation left (radius comp.) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G42 "
                    ,"Compensation "
                    ," Tool cutter compensation right (radius comp.) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G43 "
                    ,"Compensation "
                    ," Apply tool length compensation (plus) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G44 "
                    ,"Compensation "
                    ," Apply tool length compensation (minus) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G49 "
                    ,"Compensation "
                    ," Tool length compensation cancel ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G50 "
                    ,"Compensation "
                    ," Reset all scale factors to 1.0 ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G51 "
                    ,"Compensation "
                    ," Turn on scale factors ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G52 "
                    ,"Coordinate "
                    ," Local workshift for all coordinate systems: add XYZ offsets ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G53 "
                    ,"Coordinate "
                    ," Machine coordinate system (cancel work offsets) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G54 "
                    ,"Coordinate "
                    ," Work coordinate system (1st Workpiece) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G55 "
                    ,"Coordinate "
                    ," Work coordinate system (2nd Workpiece) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G56 "
                    ,"Coordinate "
                    ," Work coordinate system (3rd Workpiece) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G57 "
                    ,"Coordinate "
                    ," Work coordinate system (4th Workpiece) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G58 "
                    ,"Coordinate "
                    ," Work coordinate system (5th Workpiece) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G59 "
                    ,"Coordinate "
                    ," Work coordinate system (6th Workpiece) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G61 "
                    ,"Other "
                    ," Exact stop check mode ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G62 "
                    ,"Other "
                    ," Automatic corner override ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G63 "
                    ,"Other "
                    ," Tapping mode ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G64 "
                    ,"Other "
                    ," Best speed path ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G65 "
                    ,"Other "
                    ," Custom macro simple call ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G68 "
                    ,"Coordinate "
                    ," Coordinate System Rotation ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G69 "
                    ,"Coordinate "
                    ," Cancel Coordinate System Rotation ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G73 "
                    ,"Canned "
                    ," High speed drilling cycle (small retract) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G74 "
                    ,"Canned "
                    ," Left hand tapping cycle ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G76 "
                    ,"Canned "
                    ," Fine boring cycle ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G80 "
                    ,"Canned "
                    ," Cancel canned cycle ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G81 "
                    ,"Canned "
                    ," Simple drilling cycle ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G82 "
                    ,"Canned "
                    ," Drilling cycle with dwell (counterboring) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G83 "
                    ,"Canned "
                    ," Peck drilling cycle (full retract) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G84 "
                    ,"Canned "
                    ," Tapping cycle ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G85 "
                    ,"Canned "
                    ," Boring canned cycle, no dwell, feed out ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G86 "
                    ,"Canned "
                    ," Boring canned cycle, spindle stop, rapid out ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G87 "
                    ,"Canned "
                    ," Back boring canned cycle ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G88 "
                    ,"Canned "
                    ," Boring canned cycle, spindle stop, manual out ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G89 "
                    ,"Canned "
                    ," Boring canned cycle, dwell, feed out ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G90 "
                    ,"Coordinate "
                    ," Absolute programming of XYZ (type B and C systems) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G90.1 "
                    ,"Coordinate "
                    ," Absolute programming IJK (type B and C systems) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G91 "
                    ,"Coordinate "
                    ," Incremental programming of XYZ (type B and C systems) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G91.1 "
                    ,"Coordinate "
                    ," Incremental programming IJK (type B and C systems) ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G92 "
                    ,"Coordinate "
                    ," Offset coordinate system and save parameters ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G92 (alternate) "
                    ,"Motion "
                    ," Clamp of maximum spindle speed S ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G92.1 "
                    ,"Coordinate "
                    ," Cancel offset and zero parameters ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G92.2 "
                    ,"Coordinate "
                    ," Cancel offset and retain parameters ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G92.3 "
                    ,"Coordinate "
                    ," Offset coordinate system with saved parameters ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G94 "
                    ,"Motion "
                    ," Units per minute feed mode. Units in inches or mm. ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G95 "
                    ,"Motion "
                    ," Units per revolution feed mode. Units in inches or mm. ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G96 "
                    ,"Motion "
                    ," Constant surface speed ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G97 "
                    ,"Motion "
                    ," Cancel constant surface speed ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G98 "
                    ,"Canned "
                    ," Return to initial Z plane after canned cycle ","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Mill"
                    ," G99 "
                    ,"Canned "
                    ," Return to initial R plane after canned cycle ","G"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M00"
                    ,""
                    ,"Program stop	","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M01"
                    ,""
                    ,"Optional program stop","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M02"
                    ,""
                    ,"End of program","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M03"
                    ,""
                    ,"Spindle start forward CW","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M04"
                    ,""
                    ,"Spindle start reverse CCW","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M05"
                    ,""
                    ,"Spindle stop","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M06"
                    ,""
                    ,"Tool change","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M07"
                    ,""
                    ,"Coolant ON – Mist coolant/Coolant thru spindle","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M08"
                    ,""
                    ,"Coolant ON – Flood coolant","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M09"
                    ,""
                    ,"Coolant OFF","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M19"
                    ,""
                    ,"Spindle orientation","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M28"
                    ,""
                    ,"Return to origin","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M29"
                    ,""
                    ,"Rigid tap","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M30"
                    ,""
                    ,"End of program (Reset)","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M41"
                    ,""
                    ,"Low gear select","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M42"
                    ,""
                    ,"High gear select","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M94"
                    ,""
                    ,"Cancel mirrorimage","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M95",""
                    ,"Mirrorimage of X axis","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M96"
                    ,""
                    ,"Mirrorimage of Y axis","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M98"
                    ,""
                    ,"Subprogram call","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Mill"
                    , "M99"
                    ,""
                    ,"End of subprogram","M"));



            //Siemens 840FD
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G0"
                    ,""
                    ,"Linear interpolation with rapid traverse (rapid traverse motion)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G1"
                    ,""
                    ,"Linear interpolation with feed (linear interpolation)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G2"
                    ,""
                    ,"Circular interpolation clockwise","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G3"
                    , ""
                    ,"Circular interpolation counterclockwise","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G4"
                    , ""
                    ,"Predefined dwell time","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G9"
                    ,""
                    ,"Exact stop deceleration","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G17"
                    ,""
                    ,"Selection of working plane X/Y","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G18"
                    ,""
                    ,"Selection of working plane Z/X","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G19"
                    , ""
                    ,"Selection of working plane Y/Z","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G25"
                    ,""
                    ,"Lower working area limitation","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G26"
                    ,""
                    ,"Upper working area limitation G","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G33"
                    ,""
                    ,"Thread interpolation with constant pitch","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G34"
                    ,""
                    ,"Increase in thread pitch (progressive change)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G35"
                    ,""
                    ,"Decrease in thread pitch (degressive change)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G40"
                    , ""
                    , "Tool radius compensation OFF","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G41"
                    ,""
                    ,"Tool radius compensation to left of contour","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G42"
                    ,""
                    ,"Tool radius compensation to right of contour","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G53"
                    ,""
                    ,"Suppression of current zero offset (non-modal)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G54"
                    , ""
                    ,"1st settable zero offset","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G55"
                    ,""
                    ,"2nd settable zero offset","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G56"
                    ,""
                    ,"3rd settable zero offset","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G57"
                    ,""
                    ,"4th settable zero offset","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G58"
                    ,""
                    ,"Programmable offset replacing axially","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G59"
                    ,""
                    ,"Programmable offset replacing additive axially","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G60"
                    ,""
                    , "Exact stop deceleration","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G62"
                    ,""
                    ,"Corner deceleration at inside corners with active tool radius compensation (G41, G42)", "G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G63"
                    ,""
                    ,"Tapping with compensating chuck","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G64"
                    , ""
                    ,"Exact stop - contouring mode","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G70"
                    ,""
                    ,"Dimension in inches (lengths)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G71 "
                    , ""
                    ,"Metric dimension (lengths)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G74"
                    ,""
                    ,"Reference point approach","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G75"
                    ,""
                    , "Fixed point approach","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G90"
                    ,""
                    ,"Dimension setting, absolute ","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G91"
                    , ""
                    ,"Incremental dimension setting","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G93"
                    , ""
                    ,"Inverse-time feedrate","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G94"
                    ,""
                    ,"Linear feed F in mm/min or inch/min and °/min","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G95"
                    ,""
                    ,"Revolutional feedrate F in mm/rev or inch/rev","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G96"
                    ,""
                    ,"Constant cutting speed ON","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G97"
                    ,""
                    ,"Constant cutting speed OFF","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G110"
                    , ""
                    ,"Polar programming relative to last programmed set position","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G111"
                    , ""
                    ,"Pole programming relative to zero point of current workpiece coordinate system","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G112"
                    , ""
                    ,"Polar programming relative to last valid pole","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G140"
                    ,""
                    ,"Direction of approach WAB defined by G41/G42","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G141"
                    , ""
                    ,"Direction of approach WAB left of contour","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G142"
                    ,""
                    ,"Direction of approach WAB right of contour","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G143"
                    , ""
                    ,"Direction of approach WAB dependent on tangent","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G147"
                    , ""
                    ,"Smooth approach with straight line","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G148"
                    , ""
                    ,"Smooth retraction with straight line","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G153"
                    ,""
                    ,"Suppression of current frame incl. base frame","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G247"
                    , ""
                    ,"Smooth approach with quadrant","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G248"
                    ,""
                    ,"Smooth retraction with quadrant","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G331"
                    , ""
                    ,"Tapping","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G332"
                    ,""
                    , "Retraction (tapping)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G340"
                    ,""
                    , "Approach block spatial (depth and inplane at same time (helix)","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G341"
                    ,""
                    ,"Approach in the perpendicular axis (z), then approach in plane for smooth approach and retract","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G347"
                    , ""
                    ,"Smooth approach with semicircle","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G348"
                    ,""
                    ,"Smooth retract with semi-circle","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G450"
                    ,""
                    ,"Transition circle Tool compensation response","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G451"
                    ,""
                    ,"Intersection of equidistant paths at corners","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G460"
                    ,""
                    , "Approach/retraction behavior with TRC","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G461"
                    ,""
                    ,"Approach/retraction behavior with TRC","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G462"
                    , ""
                    ,"Approach/retraction behavior with TRC","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G500"
                    ,""
                    ,"Deactivation of all settable frames, if no value in G500","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "G505.... G599"
                    ,""
                    ,"Settable zero offset","G"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M0"
                    ,""
                    , "Programmed stop","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M1"
                    ,""
                    ,"Optional stop","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M3"
                    ,""
                    ,"Clockwise spindle rotation for master spindle","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M4"
                    ,""
                    ,"Counterclockwise spindle rotation for master spindle","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M5"
                    ,""
                    ,"Spindle stop for master spindle","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M6"
                    ,""
                    ,"Tool change","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M17"
                    ,""
                    , "End of subprogram","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M19"
                    ,""
                    ,"Spindle positions","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M30"
                    ,""
                    , "Program end","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M40"
                    ,""
                    , "Automatic gear change","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M41... M45"
                    ,""
                    ,"Gear stage 1, ..., 5","M"));
            machineCodes.add(new MachineCode("Siemens 840FD"
                    , "M70"
                    ,""
                    ,"Transition to axis operation","M"));
            //generic fanuc lathe
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G00","",	"Rapid traverse","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G01","","Linear interpolation","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G02","",	"Circular interpolation CW","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G03","",	"Circular interpolation CCW","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G04","",	"Dwell","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G09","",	"Exact stop","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G10","","Programmable data input","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G20","",	"Input in inch","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G21","",	"Input in mm","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G22","",	"Stored stroke check function on","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G23","",	"Stored stroke check function off","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G27","",	"Reference position return check","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G28","",	"Return to reference position","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G32","",	"Thread cutting","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G40","",	"Tool nose radius compensation cancel","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G41","", "Tool nose radius compensation left","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G42","",	"Tool nose radius compensation right","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G70","",	"Finish machining cycle","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G71","","Turning cycle","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G72","",	"Facing cycle","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G73","",	"Pattern repeating cycle","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G74","",	"Peck drilling cycle","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G75","",	"Grooving cycle","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G76","",	"Threading cycle","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G92","",	"Coordinate system setting or max. spindle speed setting","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G94","",	"Feed Per Minute","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G95","",	"Feed Per Revolution","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G96","",	"Constant surface speed control","G"));
            machineCodes.add(new MachineCode("Generic Fanuc Lathe","G97","",	"Constant surface speed control cancel","G"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M00","",	"Program stop","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M01","",	"Optional program stop","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M02"	,"","End of program","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M03"	,"","Spindle start forward CW","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M04"	,"","Spindle start reverse CCW","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M05"	,"","Spindle stop","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M08"	,"","Coolant on","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M09"	,"","Coolant off","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M29"	,"","Rigid tap mode","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M30","",	"End of program reset","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M40","",	"Spindle gear at middle","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M41","",	"Low Gear Select","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M42","",	"High Gear Select","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M68","",	"Hydraulic chuck close","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M69","",	"Hydraulic chuck open","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M78","",	"Tailstock advancing","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M79","",	"Tailstock reversing","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M94","",	"Mirrorimage cancel","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M95","",	"Mirrorimage of X axis","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M98"	,"","Subprogram call","M"));
            machineCodes.add( new MachineCode("Generic Fanuc Lathe", "M99"	,"","End of subprogram","M"));
            return machineCodes;
        }
    }
}
