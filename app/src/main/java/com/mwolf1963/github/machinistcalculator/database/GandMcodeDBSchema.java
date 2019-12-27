package com.mwolf1963.github.machinistcalculator.database;

public class GandMcodeDBSchema {

    public static final class GandMCodeTable{
        public static final String TABLE_NAME = "g_and_m_codes";
        public static final class Cols{
            public static final String CODE_ID = "code_id";
            public static final String CODE = "code";
            public static final String CODETYPE = "code_type";
            public static final String NOTES ="notes";
            public static final String CATEGORY = "category";
            public static final String CONTROL = "control";
        }
    }

    public static final class MachineControlTable{
        public static final String TABLE_NAME = "machine_control";
        public static final class Cols{
            public static final String MACHINE_ID = "machine_id";

            public static final String CONTROL_NAME = "control_name";

        }
    }
}
