package com.eamirgh.femproject;

import org.ejml.simple.SimpleMatrix;

/**
 * Created by eamirgh on 6/2/2017.
 */

public class BeamElement {

    TwoDimenNode nodei,nodej;
    Beam beam;

    double BeamLenght(){
        double lenght=0.0;
        lenght = Math.sqrt(Math.abs(Math.pow(nodei.getX()-nodej.getX(),2.0)-Math.pow(nodei.getY()-nodej.getY(),2.0)));
        return lenght;
    }

    SimpleMatrix LandaMatrix(){
        double alphaAngle = Math.toRadians(AlphaCalculator());
        double betaAngle =  Math.toRadians(BetaCalculator());
        SimpleMatrix landaMat = new SimpleMatrix(6,6,true,Math.cos(alphaAngle),Math.cos(betaAngle),0.0,0.0,0.0,0.0,
                                                            -Math.cos(betaAngle),Math.cos(alphaAngle),0.0,0.0,0.0,0.0,
                                                            0.0,0.0,1.0,0.0,0.0,0.0,
                                                            0.0,0.0,0.0,Math.cos(alphaAngle),Math.cos(betaAngle),0.0,
                                                            0.0,0.0,0.0,-Math.cos(betaAngle),Math.cos(alphaAngle),0.0,
                                                            0.0,0.0,0.0,0.0,0.0,1.0);
        return landaMat;
    }

    SimpleMatrix StifnessMatrix(){
        double youngsModulus = beam.getYoungsModulus();
        double a = beam.CalculateArea();
        double l = BeamLenght();
        double i = beam.CalculateI();
        SimpleMatrix kMat = new SimpleMatrix(6,6,true,
                            a,0.0,0.0,-a,0.0,0.0,
                            0.0,12.0*i/Math.pow(l,2.0),6.0*i/l,0.0,-12.0*i/Math.pow(l,2.0),6.0*i/l,
                            0.0,6.0*i/l,4.0*i,0.0,-6.0*i/l,2.0*i,
                            -a,0.0,0.0,a,0.0,0.0,
                            0.0,-12.0*i/Math.pow(l,2.0),-6.0*i/l,0.0,12.0*i/Math.pow(l,2.0),-6.0*i/l,
                            0.0,6.0*i/l,2.0*i,0.0,6.0*i/l,4.0*i);
        kMat = kMat.scale(youngsModulus/l);
        return kMat;
    }

    double AlphaCalculator(){
        double y = nodej.getY()-nodei.getY();
        double x = nodej.getX()-nodei.getX();
        return Math.toDegrees(Math.atan(y/x));
    }
    double BetaCalculator(){
        return 90.0-AlphaCalculator();
    }

    public BeamElement(TwoDimenNode nodei, TwoDimenNode nodej, Beam beam) {
        this.nodei = nodei;
        this.nodej = nodej;
        this.beam = beam;
    }

    public TwoDimenNode getNodei() {
        return nodei;
    }

    public void setNodei(TwoDimenNode nodei) {
        this.nodei = nodei;
    }

    public TwoDimenNode getNodej() {
        return nodej;
    }

    public void setNodej(TwoDimenNode nodej) {
        this.nodej = nodej;
    }

    public Beam getBeam() {
        return beam;
    }

    public void setBeam(Beam beam) {
        this.beam = beam;
    }
}
