package com.eamirgh.femproject;

/**
 * Created by eamirgh on 6/2/2017.
 */

class Beam {
    double youngsModulus,poissonsRatio,b,h,t;// b:width h:height t:thickness

    double CalculateArea(){
        double area = 0.0;
        area = b*h -((h-t)*(b-t));
        return area;
    }

    double CalculateI(){
        double i = 0.0;
        i = (b*Math.pow(h,3.0)/12.0) - ((b-t)*Math.pow((h-t),3.0)/12.0); //for a square surface
        return i;
    }

    public Beam(double youngsModulus, double poissonsRatio, double b, double h, double t) {
        this.youngsModulus = youngsModulus;
        this.poissonsRatio = poissonsRatio;
        this.b = b;
        this.h = h;
        this.t = t;
    }

    public double getYoungsModulus() {
        return youngsModulus;
    }

    public void setYoungsModulus(double youngsModulus) {
        this.youngsModulus = youngsModulus;
    }

    public double getPoissonsRatio() {
        return poissonsRatio;
    }

    public void setPoissonsRatio(double poissonsRatio) {
        this.poissonsRatio = poissonsRatio;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }
}
