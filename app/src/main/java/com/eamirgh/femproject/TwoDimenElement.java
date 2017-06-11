package com.eamirgh.femproject;

import org.ejml.simple.SimpleMatrix;

/**
 * Created by eamirgh on 5/28/2017.
 */

public class TwoDimenElement {

    TwoDimenNode nodei;
    TwoDimenNode nodej;
    TwoDimenNode nodek;

    public SimpleMatrix matrixN (){
        double [][] cons = CalcNodeConstant(nodei,nodej,nodek);
        double [] consI = new double[3];
        double [] consJ = new double[3];
        double [] consK = new double[3];
        for (int i = 0; i < 3; i++) {
            consI [i]= cons[0][i];
            consJ [i]= cons[1][i];
            consK [i]= cons[2][i];
            }
        double area = CalcElementArea();
        double ni ,nj , nk;

        ni = CalcElementNfunctionValue(nodei,consI,area);
        nj = CalcElementNfunctionValue(nodej,consI,area);
        nk = CalcElementNfunctionValue(nodek,consI,area);

        SimpleMatrix matN = new SimpleMatrix(2,6,true,ni,0,nj,0,nk,0,0,ni,0,nj,0,nk);
        return matN;
    }
    public SimpleMatrix matrixB (){
        double [][] cons = CalcNodeConstant(nodei,nodej,nodek);
        double [] consI = new double[3];
        double [] consJ = new double[3];
        double [] consK = new double[3];
        for (int i = 0; i < 3; i++) {
            consI [i]= cons[0][i];
            consJ [i]= cons[1][i];
            consK [i]= cons[2][i];
            }
        double area = CalcElementArea();

        SimpleMatrix matB = new SimpleMatrix(3,6,true,consI[1],0,consJ[1],0,consK[1],0,0,consI[2],0,consJ[2],0,consK[2],consI[2],consI[1],consJ[2],consJ[1],consK[2],consK[1]);
        matB = matB.divide(2.0*area);
        return matB;
    }
    public SimpleMatrix matrixD (double e , double v){
        SimpleMatrix matD = new SimpleMatrix(3,3,true,1,v,0,v,1,0,0,0,(1-v)/2.0);
        matD = matD.divide((1.0+v*v)/e);
        return matD;
    }

    public double CalcElementNfunctionValue (double x , double y ,double [] consts, double A){
        double ans = 0;
        ans = (consts[0] + consts[1]*x + consts[2]*y)/(2.0*A);
        return ans;
    }

    public double CalcElementNfunctionValue (TwoDimenNode node,double [] consts, double A){
        double ans = 0;
        ans = (consts[0] + consts[1]*node.getX() + consts[2]*node.getY())/(2.0*A);
        return ans;
    }

    public double [][] CalcNodeConstant(TwoDimenNode nodea ,TwoDimenNode nodeb ,TwoDimenNode nodec){

        double [][] ans = new double[3][3];
        double ai,bi,ci;
        double aj,bj,cj;
        double ak,bk,ck;

        ai=CalcnodeAConstant(nodeb,nodec);
        bi=CalcnodeBConstant(nodeb,nodec);
        ci=CalcnodeCConstant(nodeb,nodec);

        ans [0][0] = ai;
        ans [0][1] = bi;
        ans [0][2] = ci;

        aj=CalcnodeAConstant(nodea,nodec);
        bj=CalcnodeBConstant(nodea,nodec);
        cj=CalcnodeCConstant(nodea,nodec);

        ans [1][0] = aj;
        ans [1][1] = bj;
        ans [1][2] = cj;

        ak=CalcnodeAConstant(nodea,nodeb);
        bk=CalcnodeBConstant(nodea,nodeb);
        ck=CalcnodeCConstant(nodea,nodeb);

        ans [2][0] = ak;
        ans [2][1] = bk;
        ans [2][2] = ck;

        return ans;
    }

    public double CalcnodeAConstant (TwoDimenNode nodea, TwoDimenNode nodeb){
        //for ai node i : nodea = nodej , nodeb = nodek
        double a = 0;
        a = nodea.getX()*nodeb.getY()-nodea.getY()*nodeb.getX();
        return a;
    }

    public double CalcnodeBConstant (TwoDimenNode nodea, TwoDimenNode nodeb){
        //for bi node i : nodea = nodej , nodeb = nodek
        double b = 0;
        b = nodea.getY() - nodeb.getY();
        return b;
    }

    public double CalcnodeCConstant (TwoDimenNode nodea, TwoDimenNode nodeb){
        //for ci node i : nodea = nodej , nodeb = nodek
        double c = 0;
        c = nodeb.getX()- nodea.getX();
        return c;
    }

    public double CalcElementAreaViaHeron(TwoDimenElement twoDimenElement){
        double area = 0;
        double s = 0;

        TwoDimenNode ni=twoDimenElement.getNodei();
        TwoDimenNode nj=twoDimenElement.getNodej();
        TwoDimenNode nk=twoDimenElement.getNodek();

        double i = CalcElementSideLenght(nk,nj);
        double j = CalcElementSideLenght(nk,ni);
        double k = CalcElementSideLenght(ni,nj);

        s = (i+j+k)/2.0;

        area = Math.sqrt(s*(s-i)*(s-j)*(s-j));

        return area;
    }

    public double CalcElementArea(TwoDimenElement twoDimenElement){

        double area = 0;

        TwoDimenNode ni = twoDimenElement.getNodei();
        TwoDimenNode nj = twoDimenElement.getNodej();
        TwoDimenNode nk = twoDimenElement.getNodek();

        area = 0.5*(ni.getX()*nj.getY() + nj.getX()*nk.getY() + nk.getX()*ni.getY() - ni.getX()*nk.getY() - nj.getX()*ni.getY() - nk.getX()*nj.getY());

        return area;
    }

    public double CalcElementArea(){

        double area = 0;

        area = 0.5*(nodei.getX()*nodej.getY() + nodej.getX()*nodek.getY() + nodek.getX()*nodei.getY() - nodei.getX()*nodek.getY() - nodej.getX()*nodei.getY() - nodek.getX()*nodej.getY());

        return area;
    }

    public double CalcElementSideLenght(TwoDimenNode a,TwoDimenNode b){
        double lenght=0;
        lenght = Math.sqrt(Math.abs(Math.pow(a.getX()-b.getX(),2)-Math.pow(a.getY()-b.getY(),2)));
        return lenght;
    }

    public TwoDimenElement(TwoDimenNode nodei, TwoDimenNode nodej, TwoDimenNode nodek) {
        this.nodei = nodei;
        this.nodej = nodej;
        this.nodek = nodek;
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

    public TwoDimenNode getNodek() {
        return nodek;
    }

    public void setNodek(TwoDimenNode nodek) {
        this.nodek = nodek;
    }
}
