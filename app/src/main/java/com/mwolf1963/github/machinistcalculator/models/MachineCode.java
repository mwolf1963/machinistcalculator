/* Author: Matt Wolf
Date: 12/14/19
Desc: pojo to hold the code out of the db
 */

package com.mwolf1963.github.machinistcalculator.models;

public class MachineCode {
    private String code;
    private String category;
    private String codeType;
    private String notes;
    private String control;

    public MachineCode(){

    }
    public MachineCode(String control, String code, String category, String notes, String codeType){
        this.control = control;
        this.code = code;
        this.category = category;
        this.notes = notes;
        this.codeType = codeType;
    }

    public String getCode(){
        return code;
    }
    public String getCategory(){
        return category;
    }
    public String getNotes(){return notes;}
    public String getControl(){return control;}
    public String getCodeType(){return codeType;}
    public void setCodeType(String codeType){
        this.codeType = codeType;
    }
    public void setCode(String code){
        this.code = code;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public void setControl(String control){this.control = control;}
}
