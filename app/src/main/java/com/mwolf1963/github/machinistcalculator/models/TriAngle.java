package com.mwolf1963.github.machinistcalculator.models;

public class TriAngle {
    //lengths of the sides
    private float side_A_Length;
    private float side_B_Length;
    private float side_H_Length;

    //angles
    private float angle_A;
    private float angle_B;
    private final float angle_C = 90f;

    public TriAngle(){
        //empty constructor

    }

    public float getSide_A_Length() {
        return side_A_Length ;
    }
    public void setSide_A_Length(Float side_A_Length){this.side_A_Length =side_A_Length;}


    public float getSide_B_Length() {
        return side_B_Length;
    }
    public void setSide_B_Length(Float side_B_Length){this.side_B_Length = side_B_Length;}


    public float getSide_H_Length() {
        return side_H_Length;
    }
    public void setSide_H_Length(Float side_H_Length){this.side_H_Length = side_H_Length;}


    public float getAngle_A() {
        return angle_A;
    }
    public void setAngle_A(Float angle_A){this.angle_A = angle_A;}

    public float getAngle_B() {
        return angle_B;
    }
    public void setAngle_B(Float angle_B){this.angle_B= angle_B;}


    // here down is the refactory that should be what i actually want.
    //sides with the angles
    public void calc_with_SideA_and_AngleA(float sideA, float angleA){
        this.angle_A = angleA;
        this.side_A_Length = sideA;
        //set the  remaining attr
        angle_B= 90-this.angle_A;
        side_H_Length = (float)( side_A_Length / Math.sin(Math.toRadians(this.angle_A)));
        side_B_Length = (float)Math.sqrt((side_H_Length*side_H_Length) - (side_A_Length * side_A_Length));


    }
    public void calc_with_SideA_and_AngleB(float sideA, float angleB){
        calc_with_SideA_and_AngleA(sideA, 90-angleB);

    }
    //side b
    public void calc_with_SideB_and_AngleA(float sideB, float angleA){
        this.angle_A = angleA;
        this.side_B_Length = sideB;

        this.angle_B = 90 - angleA;
        side_H_Length = (float)( side_B_Length / Math.cos(Math.toRadians(this.angle_A)));
        side_A_Length = (float)Math.sqrt((side_H_Length*side_H_Length) - (side_B_Length * side_B_Length));
    }
    public void calc_with_SideB_and_AngleB(float sideB, float angleB){
        calc_with_SideB_and_AngleA(sideB,90-angleB);
    }
    //hyp
    public void calc_with_SideH_and_AngleA(float sideH, float angleA){
        this.side_H_Length = sideH;
        this.angle_A = angleA;
        this.angle_B = 90- angleA;
        this.side_A_Length = (float)(( side_H_Length * Math.sin(Math.toRadians(this.angle_A))));
        side_B_Length = (float)Math.sqrt((side_H_Length*side_H_Length) - (side_A_Length * side_A_Length));
    }
    public void calc_with_SideH_and_AngleB(float sideH, float angleB){
        this.side_H_Length = sideH;
        this.angle_B = angleB;
        this.angle_A = 90- angleB;
        this.side_A_Length = (float)(( side_H_Length * Math.sin(Math.toRadians(this.angle_A))));
        side_B_Length = (float)Math.sqrt((side_H_Length*side_H_Length) - (side_A_Length * side_A_Length));
    }
    public void calc_with_LenA_and_LenB(float sideA, float sideB){
        //redirect to an above method
        calc_with_SideA_and_AngleA(sideA, (float) Math.toDegrees(Math.atan(sideA/sideB)));

    }
    public void calc_with_LenA_and_LenH(float sideA, float sideH){
        //redirect to an above method
        calc_with_SideA_and_AngleA(sideA, (float) Math.toDegrees(Math.asin(sideA/sideH)));


    }
    public void calc_with_LenB_and_LenH(float sideB, float sideH){
        calc_with_SideB_and_AngleA(sideB, (float)Math.toDegrees(Math.acos(sideB/sideH)));
    }

    @Override
    public String toString(){
        return  "Triangle in its Current State //n" + "Angle A: " + this.getAngle_A() + "Angle B: " + this.getAngle_B()
                + "Side A: " + this.side_A_Length + "Side B: " + this.side_B_Length
                + "Side C: " + this.side_H_Length;
    }

}
