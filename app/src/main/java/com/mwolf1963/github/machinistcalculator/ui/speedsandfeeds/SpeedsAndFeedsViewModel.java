package com.mwolf1963.github.machinistcalculator.ui.speedsandfeeds;


public class SpeedsAndFeedsViewModel {

    private float rpm;
    private float sfm;
    private float ipm;
    private float fpt;
    private float dia;
    private int teeth;


    public float getRpm() {
        return rpm;
    }

    public void setRpm(float rpm) {
        this.rpm = rpm;
        if (dia !=0f){
            sfm = (this.rpm* dia)/3.82f;
        }
        if(fpt != 0f){
            ipm = teeth * fpt;
        }
    }

    public float getSfm() {
        return sfm;
    }

    public void setSfm(float sfm) {
        this.sfm = sfm;
        if(dia != 0f){
            rpm = (this.sfm*3.82f)/dia;
        }
        if (fpt != 0f){
            ipm = teeth * fpt * rpm;
        }
        if (dia == 0f && dia != 0){
            dia = (rpm*dia)/this.sfm;
        }
    }

    public float getIpm() {
        return ipm;
    }

    public void setIpm(float ipm) {
        this.ipm = ipm;
       if (rpm != 0){
           fpt = (this.ipm/rpm)/(float)teeth;
       }
    }
    public float getFpt() {
        return fpt;
    }

    public void setFpt(float fpt) {
        this.fpt = fpt;
        if(rpm != 0f ){
            ipm = this.fpt * rpm * teeth;
        }
    }

    public float getDia() {
        return dia;
    }

    public void setDia(float dia) {
        this.dia = dia;
        if(sfm != 0 && rpm ==0f){
            rpm = (sfm*3.82f)/ dia;
        }
        else if(sfm == 0 && rpm !=0f){
            sfm = (rpm * this.dia)/3.82f;
        }
        else if(sfm != 0){
            rpm = (sfm*3.82f)/ dia;
        }
        if (ipm != 0f){
            ipm = rpm * teeth * fpt;
        }


    }

    public int getTeeth() {
        return teeth;
    }

    public void setTeeth(int teeth) {
        this.teeth = teeth;
        if (fpt != 0f && rpm != 0f ){
            ipm = fpt * rpm *this.teeth;
        }
        if (fpt != 0 && rpm != 0){
            ipm =  fpt * this.teeth * rpm;
        }


    }
}
