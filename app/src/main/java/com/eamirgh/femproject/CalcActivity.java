package com.eamirgh.femproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.ejml.simple.SimpleMatrix;

import static java.lang.Math.pow;

public class CalcActivity extends AppCompatActivity {

    double ST42_YOUNGS_MODULUS = 210.0* pow(10.0,9);  //Young's modulus
    double ST44_YOUNGS_MODULUS = 200.0* pow(10.0,9);    //Young's modulus
    double ST37_YOUNGS_MODULUS = 210.0* pow(10.0,9);    //Young's modulus
    double POISSONS_RATIO = 0.3;   //Poisson's ratio

    Beam horizontalBeam = new Beam(ST44_YOUNGS_MODULUS,POISSONS_RATIO,0.06,0.1,0.003);
    Beam verticalBeam = new Beam(ST42_YOUNGS_MODULUS,POISSONS_RATIO,0.06,0.06,0.003);
    Beam tiltBeam = new Beam(ST37_YOUNGS_MODULUS,POISSONS_RATIO,0.04,0.06,0.002);

    TwoDimenNode node1 = new TwoDimenNode(0.0,0.0);
    TwoDimenNode node2 = new TwoDimenNode(1.2,0.0);
    TwoDimenNode node3 = new TwoDimenNode(1.7,0.0);
    TwoDimenNode node4 = new TwoDimenNode(1.2+1.0/3.0,-8.0/30.0);
    TwoDimenNode node5 = new TwoDimenNode(0.0,-0.6);
    TwoDimenNode node6 = new TwoDimenNode(1.7,-0.4);
    TwoDimenNode node7 = new TwoDimenNode(1.2,-0.8);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        horizontalBeam = (Beam) getIntent().getSerializableExtra("HBeam");
        verticalBeam = (Beam) getIntent().getSerializableExtra("VBeam");
        tiltBeam = (Beam) getIntent().getSerializableExtra("TBeam");

        node1 = (TwoDimenNode) getIntent().getSerializableExtra("n1");
        node2 = (TwoDimenNode) getIntent().getSerializableExtra("n2");
        node3 = (TwoDimenNode) getIntent().getSerializableExtra("n3");
        node4 = (TwoDimenNode) getIntent().getSerializableExtra("n4");
        node5 = (TwoDimenNode) getIntent().getSerializableExtra("n5");
        node6 = (TwoDimenNode) getIntent().getSerializableExtra("n6");
        node7 = (TwoDimenNode) getIntent().getSerializableExtra("n7");

        TableLayout tableLayout;


        BeamElement beamElement1 = new BeamElement(node1, node2, horizontalBeam);
        BeamElement beamElement2 = new BeamElement(node2, node3, horizontalBeam);
        BeamElement beamElement3 = new BeamElement(node1, node5, verticalBeam);
        BeamElement beamElement4 = new BeamElement(node1, node7, tiltBeam);
        BeamElement beamElement5 = new BeamElement(node2, node7, verticalBeam);
        BeamElement beamElement6 = new BeamElement(node2, node4, tiltBeam);
        BeamElement beamElement7 = new BeamElement(node3, node4, tiltBeam);
        BeamElement beamElement8 = new BeamElement(node3, node6, verticalBeam);
        BeamElement beamElement9 = new BeamElement(node4, node7, tiltBeam);
        BeamElement beamElement10 = new BeamElement(node4, node6, tiltBeam);
        BeamElement beamElement11 = new BeamElement(node5, node7, tiltBeam);
        BeamElement beamElement12 = new BeamElement(node7, node6, tiltBeam);

        SimpleMatrix localStiffnessMatrixElement1 = beamElement1.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement2 = beamElement2.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement3 = beamElement3.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement4 = beamElement4.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement5 = beamElement5.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement6 = beamElement6.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement7 = beamElement7.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement8 = beamElement8.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement9 = beamElement9.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement10 = beamElement10.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement11 = beamElement11.StifnessMatrix();
        SimpleMatrix localStiffnessMatrixElement12 = beamElement12.StifnessMatrix();

        SimpleMatrix rotMatrixElement1 = beamElement1.LandaMatrix();
        SimpleMatrix rotMatrixElement2 = beamElement2.LandaMatrix();
        SimpleMatrix rotMatrixElement3 = beamElement3.LandaMatrix();
        SimpleMatrix rotMatrixElement4 = beamElement4.LandaMatrix();
        SimpleMatrix rotMatrixElement5 = beamElement5.LandaMatrix();
        SimpleMatrix rotMatrixElement6 = beamElement6.LandaMatrix();
        SimpleMatrix rotMatrixElement7 = beamElement7.LandaMatrix();
        SimpleMatrix rotMatrixElement8 = beamElement8.LandaMatrix();
        SimpleMatrix rotMatrixElement9 = beamElement9.LandaMatrix();
        SimpleMatrix rotMatrixElement10 = beamElement10.LandaMatrix();
        SimpleMatrix rotMatrixElement11 = beamElement11.LandaMatrix();
        SimpleMatrix rotMatrixElement12 = beamElement12.LandaMatrix();

        SimpleMatrix globalStiffnessMatrixElement1 = (rotMatrixElement1.transpose().mult(localStiffnessMatrixElement1)).mult(rotMatrixElement1);
        SimpleMatrix globalStiffnessMatrixElement2 = (rotMatrixElement2.transpose().mult(localStiffnessMatrixElement2)).mult(rotMatrixElement2);
        SimpleMatrix globalStiffnessMatrixElement3 = (rotMatrixElement3.transpose().mult(localStiffnessMatrixElement3)).mult(rotMatrixElement3);
        SimpleMatrix globalStiffnessMatrixElement4 = (rotMatrixElement4.transpose().mult(localStiffnessMatrixElement4)).mult(rotMatrixElement4);
        SimpleMatrix globalStiffnessMatrixElement5 = (rotMatrixElement5.transpose().mult(localStiffnessMatrixElement5)).mult(rotMatrixElement5);
        SimpleMatrix globalStiffnessMatrixElement6 = (rotMatrixElement6.transpose().mult(localStiffnessMatrixElement6)).mult(rotMatrixElement6);
        SimpleMatrix globalStiffnessMatrixElement7 = (rotMatrixElement7.transpose().mult(localStiffnessMatrixElement7)).mult(rotMatrixElement7);
        SimpleMatrix globalStiffnessMatrixElement8 = (rotMatrixElement8.transpose().mult(localStiffnessMatrixElement8)).mult(rotMatrixElement8);
        SimpleMatrix globalStiffnessMatrixElement9 = (rotMatrixElement9.transpose().mult(localStiffnessMatrixElement9)).mult(rotMatrixElement9);
        SimpleMatrix globalStiffnessMatrixElement10 = (rotMatrixElement10.transpose().mult(localStiffnessMatrixElement10)).mult(rotMatrixElement10);
        SimpleMatrix globalStiffnessMatrixElement11 = (rotMatrixElement11.transpose().mult(localStiffnessMatrixElement11)).mult(rotMatrixElement11);
        SimpleMatrix globalStiffnessMatrixElement12 = (rotMatrixElement12.transpose().mult(localStiffnessMatrixElement12)).mult(rotMatrixElement12);

        SimpleMatrix globalStiffnessMatrix = new SimpleMatrix(21,21,true,
// row: #1
                globalStiffnessMatrixElement1.get(0,0) + globalStiffnessMatrixElement3.get(0,0) + globalStiffnessMatrixElement4.get(0,0),//u1
                globalStiffnessMatrixElement1.get(0,1) + globalStiffnessMatrixElement3.get(0,1) + globalStiffnessMatrixElement4.get(0,1),//v1
                globalStiffnessMatrixElement1.get(0,2) + globalStiffnessMatrixElement3.get(0,2) + globalStiffnessMatrixElement4.get(0,2),//t1
                globalStiffnessMatrixElement1.get(0,3),//u2
                globalStiffnessMatrixElement1.get(0,4),//v2
                globalStiffnessMatrixElement1.get(0,5),//t2
                0,//u3
                0,//v3
                0,//t3
                0,//u4
                0,//v4
                0,//t4
                globalStiffnessMatrixElement3.get(0,3),//u5
                globalStiffnessMatrixElement3.get(0,4),//v5
                globalStiffnessMatrixElement3.get(0,5),//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement4.get(0,3),//u7
                globalStiffnessMatrixElement4.get(0,4),//v7
                globalStiffnessMatrixElement4.get(0,5),//t7
//next row #2
                globalStiffnessMatrixElement1.get(1,0) + globalStiffnessMatrixElement3.get(1,0) + globalStiffnessMatrixElement4.get(1,0),//u1
                globalStiffnessMatrixElement1.get(1,1) + globalStiffnessMatrixElement3.get(1,1) + globalStiffnessMatrixElement4.get(1,1),//v1
                globalStiffnessMatrixElement1.get(1,2) + globalStiffnessMatrixElement3.get(1,2) + globalStiffnessMatrixElement4.get(1,2),//t1
                globalStiffnessMatrixElement1.get(1,3),//u2
                globalStiffnessMatrixElement1.get(1,4),//v2
                globalStiffnessMatrixElement1.get(1,5),//t2
                0,//u3
                0,//v3
                0,//t3
                0,//u4
                0,//v4
                0,//t4
                globalStiffnessMatrixElement3.get(1,3),//u5
                globalStiffnessMatrixElement3.get(1,4),//v5
                globalStiffnessMatrixElement3.get(1,5),//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement4.get(1,3),//u7
                globalStiffnessMatrixElement4.get(1,4),//v7
                globalStiffnessMatrixElement4.get(1,5),//t7
//next row #3
                globalStiffnessMatrixElement1.get(2,0) + globalStiffnessMatrixElement3.get(2,0) + globalStiffnessMatrixElement4.get(2,0),//u1
                globalStiffnessMatrixElement1.get(2,1) + globalStiffnessMatrixElement3.get(2,1) + globalStiffnessMatrixElement4.get(2,1),//v1
                globalStiffnessMatrixElement1.get(2,2) + globalStiffnessMatrixElement3.get(2,2) + globalStiffnessMatrixElement4.get(2,2),//t1
                globalStiffnessMatrixElement1.get(2,3),//u2
                globalStiffnessMatrixElement1.get(2,4),//v2
                globalStiffnessMatrixElement1.get(2,5),//t2
                0,//u3
                0,//v3
                0,//t3
                0,//u4
                0,//v4
                0,//t4
                globalStiffnessMatrixElement3.get(2,3),//u5
                globalStiffnessMatrixElement3.get(2,4),//v5
                globalStiffnessMatrixElement3.get(2,5),//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement4.get(2,3),//u7
                globalStiffnessMatrixElement4.get(2,4),//v7
                globalStiffnessMatrixElement4.get(2,5),//t7
//next row #4
                globalStiffnessMatrixElement1.get(3,0),//u1
                globalStiffnessMatrixElement1.get(3,1),//v1
                globalStiffnessMatrixElement1.get(3,2),//t1
                globalStiffnessMatrixElement1.get(3,3) + globalStiffnessMatrixElement2.get(0,0) + globalStiffnessMatrixElement5.get(0,0) + globalStiffnessMatrixElement6.get(0,0),//u2
                globalStiffnessMatrixElement1.get(3,4) + globalStiffnessMatrixElement2.get(0,1) + globalStiffnessMatrixElement5.get(0,1) + globalStiffnessMatrixElement6.get(0,1),//v2
                globalStiffnessMatrixElement1.get(3,5) + globalStiffnessMatrixElement2.get(0,2) + globalStiffnessMatrixElement5.get(0,2) + globalStiffnessMatrixElement6.get(0,2),//t2
                globalStiffnessMatrixElement2.get(0,3),//u3
                globalStiffnessMatrixElement2.get(0,4),//v3
                globalStiffnessMatrixElement2.get(0,5),//t3
                globalStiffnessMatrixElement6.get(0,3),//u4
                globalStiffnessMatrixElement6.get(0,4),//v4
                globalStiffnessMatrixElement6.get(0,5),//t4
                0,//u5
                0,//v5
                0,//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement5.get(0,3),//u7
                globalStiffnessMatrixElement5.get(0,4),//v7
                globalStiffnessMatrixElement5.get(0,5),//t7
//next row #5
                globalStiffnessMatrixElement1.get(4,0),//u1
                globalStiffnessMatrixElement1.get(4,1),//v1
                globalStiffnessMatrixElement1.get(4,2),//t1
                globalStiffnessMatrixElement1.get(4,3) + globalStiffnessMatrixElement2.get(1,0) + globalStiffnessMatrixElement5.get(1,0) + globalStiffnessMatrixElement6.get(1,0),//u2
                globalStiffnessMatrixElement1.get(4,4) + globalStiffnessMatrixElement2.get(1,1) + globalStiffnessMatrixElement5.get(1,1) + globalStiffnessMatrixElement6.get(1,1),//v2
                globalStiffnessMatrixElement1.get(4,5) + globalStiffnessMatrixElement2.get(1,2) + globalStiffnessMatrixElement5.get(1,2) + globalStiffnessMatrixElement6.get(1,2),//t2
                globalStiffnessMatrixElement2.get(1,3),//u3
                globalStiffnessMatrixElement2.get(1,4),//v3
                globalStiffnessMatrixElement2.get(1,5),//t3
                globalStiffnessMatrixElement6.get(1,3),//u4
                globalStiffnessMatrixElement6.get(1,4),//v4
                globalStiffnessMatrixElement6.get(1,5),//t4
                0,//u5
                0,//v5
                0,//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement5.get(1,3),//u7
                globalStiffnessMatrixElement5.get(1,4),//v7
                globalStiffnessMatrixElement5.get(1,5),//t7
//next row #6
                globalStiffnessMatrixElement1.get(5,0),//u1
                globalStiffnessMatrixElement1.get(5,1),//v1
                globalStiffnessMatrixElement1.get(5,2),//t1
                globalStiffnessMatrixElement1.get(5,3) + globalStiffnessMatrixElement2.get(2,0) + globalStiffnessMatrixElement5.get(2,0) + globalStiffnessMatrixElement6.get(2,0),//u2
                globalStiffnessMatrixElement1.get(5,4) + globalStiffnessMatrixElement2.get(2,1) + globalStiffnessMatrixElement5.get(2,1) + globalStiffnessMatrixElement6.get(2,1),//v2
                globalStiffnessMatrixElement1.get(5,5) + globalStiffnessMatrixElement2.get(2,2) + globalStiffnessMatrixElement5.get(2,2) + globalStiffnessMatrixElement6.get(2,2),//t2
                globalStiffnessMatrixElement2.get(2,3),//u3
                globalStiffnessMatrixElement2.get(2,4),//v3
                globalStiffnessMatrixElement2.get(2,5),//t3
                globalStiffnessMatrixElement6.get(2,3),//u4
                globalStiffnessMatrixElement6.get(2,4),//v4
                globalStiffnessMatrixElement6.get(2,5),//t4
                0,//u5
                0,//v5
                0,//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement5.get(2,3),//u7
                globalStiffnessMatrixElement5.get(2,4),//v7
                globalStiffnessMatrixElement5.get(2,5),//t7
//next row #7
                0,//u1
                0,//v1
                0,//t1
                globalStiffnessMatrixElement2.get(3,3),//u2
                globalStiffnessMatrixElement2.get(3,4),//v2
                globalStiffnessMatrixElement2.get(3,5),//t2
                globalStiffnessMatrixElement2.get(3,3) + globalStiffnessMatrixElement8.get(0,0) + globalStiffnessMatrixElement7.get(0,0),//u3
                globalStiffnessMatrixElement2.get(3,4) + globalStiffnessMatrixElement8.get(0,1) + globalStiffnessMatrixElement7.get(0,1),//v3
                globalStiffnessMatrixElement2.get(3,5) + globalStiffnessMatrixElement8.get(0,2) + globalStiffnessMatrixElement7.get(0,2),//t3
                globalStiffnessMatrixElement7.get(0,3),//u4
                globalStiffnessMatrixElement7.get(0,4),//v4
                globalStiffnessMatrixElement7.get(0,5),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement8.get(0,3),//u6
                globalStiffnessMatrixElement8.get(0,4),//v6
                globalStiffnessMatrixElement8.get(0,5),//t6
                0,//u7
                0,//v7
                0,//t7
//next row #8
                0,//u1
                0,//v1
                0,//t1
                globalStiffnessMatrixElement2.get(4,3),//u2
                globalStiffnessMatrixElement2.get(4,4),//v2
                globalStiffnessMatrixElement2.get(4,5),//t2
                globalStiffnessMatrixElement2.get(4,3) + globalStiffnessMatrixElement8.get(1,0) + globalStiffnessMatrixElement7.get(1,0),//u3
                globalStiffnessMatrixElement2.get(4,4) + globalStiffnessMatrixElement8.get(1,1) + globalStiffnessMatrixElement7.get(1,1),//v3
                globalStiffnessMatrixElement2.get(4,5) + globalStiffnessMatrixElement8.get(1,2) + globalStiffnessMatrixElement7.get(1,2),//t3
                globalStiffnessMatrixElement7.get(1,3),//u4
                globalStiffnessMatrixElement7.get(1,4),//v4
                globalStiffnessMatrixElement7.get(1,5),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement8.get(1,3),//u6
                globalStiffnessMatrixElement8.get(1,4),//v6
                globalStiffnessMatrixElement8.get(1,5),//t6
                0,//u7
                0,//v7
                0,//t7
//next row #9
                0,//u1
                0,//v1
                0,//t1
                globalStiffnessMatrixElement2.get(5,3),//u2
                globalStiffnessMatrixElement2.get(5,4),//v2
                globalStiffnessMatrixElement2.get(5,5),//t2
                globalStiffnessMatrixElement2.get(5,3) + globalStiffnessMatrixElement8.get(2,0) + globalStiffnessMatrixElement7.get(2,0),//u3
                globalStiffnessMatrixElement2.get(5,4) + globalStiffnessMatrixElement8.get(2,1) + globalStiffnessMatrixElement7.get(2,1),//v3
                globalStiffnessMatrixElement2.get(5,5) + globalStiffnessMatrixElement8.get(2,2) + globalStiffnessMatrixElement7.get(2,2),//t3
                globalStiffnessMatrixElement7.get(2,3),//u4
                globalStiffnessMatrixElement7.get(2,4),//v4
                globalStiffnessMatrixElement7.get(2,5),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement8.get(2,3),//u6
                globalStiffnessMatrixElement8.get(2,4),//v6
                globalStiffnessMatrixElement8.get(2,5),//t6
                0,//u7
                0,//v7
                0,//t7
//next row #10
                0,//u1
                0,//v1
                0,//t1
                globalStiffnessMatrixElement6.get(3,3),//u2
                globalStiffnessMatrixElement6.get(3,4),//v2
                globalStiffnessMatrixElement6.get(3,5),//t2
                globalStiffnessMatrixElement7.get(3,3),//u3
                globalStiffnessMatrixElement7.get(3,4),//v3
                globalStiffnessMatrixElement7.get(3,5),//t3
                globalStiffnessMatrixElement9.get(0,0) + globalStiffnessMatrixElement10.get(0,0) + globalStiffnessMatrixElement6.get(3,3) + globalStiffnessMatrixElement7.get(3,3),//u4
                globalStiffnessMatrixElement9.get(0,1) + globalStiffnessMatrixElement10.get(0,1) + globalStiffnessMatrixElement6.get(3,4) + globalStiffnessMatrixElement7.get(3,4),//v4
                globalStiffnessMatrixElement9.get(0,2) + globalStiffnessMatrixElement10.get(0,2) + globalStiffnessMatrixElement6.get(3,5) + globalStiffnessMatrixElement7.get(3,5),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement10.get(0,3),//u6
                globalStiffnessMatrixElement10.get(0,4),//v6
                globalStiffnessMatrixElement10.get(0,5),//t6
                globalStiffnessMatrixElement9.get(0,3),//u7
                globalStiffnessMatrixElement9.get(0,4),//v7
                globalStiffnessMatrixElement9.get(0,5),//t7
//next row #11
                0,//u1
                0,//v1
                0,//t1
                globalStiffnessMatrixElement6.get(4,3),//u2
                globalStiffnessMatrixElement6.get(4,4),//v2
                globalStiffnessMatrixElement6.get(4,5),//t2
                globalStiffnessMatrixElement7.get(4,3),//u3
                globalStiffnessMatrixElement7.get(4,4),//v3
                globalStiffnessMatrixElement7.get(4,5),//t3
                globalStiffnessMatrixElement9.get(1,0) + globalStiffnessMatrixElement10.get(1,0) + globalStiffnessMatrixElement6.get(4,3) + globalStiffnessMatrixElement7.get(4,3),//u4
                globalStiffnessMatrixElement9.get(1,1) + globalStiffnessMatrixElement10.get(1,1) + globalStiffnessMatrixElement6.get(4,4) + globalStiffnessMatrixElement7.get(4,4),//v4
                globalStiffnessMatrixElement9.get(1,2) + globalStiffnessMatrixElement10.get(1,2) + globalStiffnessMatrixElement6.get(4,5) + globalStiffnessMatrixElement7.get(4,5),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement10.get(1,3),//u6
                globalStiffnessMatrixElement10.get(1,4),//v6
                globalStiffnessMatrixElement10.get(1,5),//t6
                globalStiffnessMatrixElement9.get(1,3),//u7
                globalStiffnessMatrixElement9.get(1,4),//v7
                globalStiffnessMatrixElement9.get(1,5),//t7
//next row #12
                0,//u1
                0,//v1
                0,//t1
                globalStiffnessMatrixElement6.get(5,3),//u2
                globalStiffnessMatrixElement6.get(5,4),//v2
                globalStiffnessMatrixElement6.get(5,5),//t2
                globalStiffnessMatrixElement7.get(5,3),//u3
                globalStiffnessMatrixElement7.get(5,4),//v3
                globalStiffnessMatrixElement7.get(5,5),//t3
                globalStiffnessMatrixElement9.get(2,0) + globalStiffnessMatrixElement10.get(2,0) + globalStiffnessMatrixElement6.get(5,3) + globalStiffnessMatrixElement7.get(5,3),//u4
                globalStiffnessMatrixElement9.get(2,1) + globalStiffnessMatrixElement10.get(2,1) + globalStiffnessMatrixElement6.get(5,4) + globalStiffnessMatrixElement7.get(5,4),//v4
                globalStiffnessMatrixElement9.get(2,2) + globalStiffnessMatrixElement10.get(2,2) + globalStiffnessMatrixElement6.get(5,5) + globalStiffnessMatrixElement7.get(5,5),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement10.get(2,3),//u6
                globalStiffnessMatrixElement10.get(2,4),//v6
                globalStiffnessMatrixElement10.get(2,5),//t6
                globalStiffnessMatrixElement9.get(2,3),//u7
                globalStiffnessMatrixElement9.get(2,4),//v7
                globalStiffnessMatrixElement9.get(2,5),//t7
//next row #13
                globalStiffnessMatrixElement3.get(3,3),//u1
                globalStiffnessMatrixElement3.get(3,4),//v1
                globalStiffnessMatrixElement3.get(3,5),//t1
                0,//u2
                0,//v2
                0,//t2
                0,//u3
                0,//v3
                0,//t3
                0,//u4
                0,//v4
                0,//t4
                globalStiffnessMatrixElement3.get(3,3) + globalStiffnessMatrixElement11.get(0,0),//u5
                globalStiffnessMatrixElement3.get(3,4) + globalStiffnessMatrixElement11.get(0,1),//v5
                globalStiffnessMatrixElement3.get(3,5) + globalStiffnessMatrixElement11.get(0,2),//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement11.get(0,3),//u7
                globalStiffnessMatrixElement11.get(0,4),//v7
                globalStiffnessMatrixElement11.get(0,5),//t7
//next row #14
                globalStiffnessMatrixElement3.get(4,3),//u1
                globalStiffnessMatrixElement3.get(4,4),//v1
                globalStiffnessMatrixElement3.get(4,5),//t1
                0,//u2
                0,//v2
                0,//t2
                0,//u3
                0,//v3
                0,//t3
                0,//u4
                0,//v4
                0,//t4
                globalStiffnessMatrixElement3.get(4,3) + globalStiffnessMatrixElement11.get(1,0),//u5
                globalStiffnessMatrixElement3.get(4,4) + globalStiffnessMatrixElement11.get(1,1),//v5
                globalStiffnessMatrixElement3.get(4,5) + globalStiffnessMatrixElement11.get(1,2),//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement11.get(1,3),//u7
                globalStiffnessMatrixElement11.get(1,4),//v7
                globalStiffnessMatrixElement11.get(1,5),//t7
//next row #15
                globalStiffnessMatrixElement3.get(5,3),//u1
                globalStiffnessMatrixElement3.get(5,4),//v1
                globalStiffnessMatrixElement3.get(5,5),//t1
                0,//u2
                0,//v2
                0,//t2
                0,//u3
                0,//v3
                0,//t3
                0,//u4
                0,//v4
                0,//t4
                globalStiffnessMatrixElement3.get(5,3) + globalStiffnessMatrixElement11.get(2,0),//u5
                globalStiffnessMatrixElement3.get(5,4) + globalStiffnessMatrixElement11.get(2,1),//v5
                globalStiffnessMatrixElement3.get(5,5) + globalStiffnessMatrixElement11.get(2,2),//t5
                0,//u6
                0,//v6
                0,//t6
                globalStiffnessMatrixElement11.get(2,3),//u7
                globalStiffnessMatrixElement11.get(2,4),//v7
                globalStiffnessMatrixElement11.get(2,5),//t7
//next row #16
                0,//u1
                0,//v1
                0,//t1
                0,//u2
                0,//v2
                0,//t2
                globalStiffnessMatrixElement8.get(3,0),//u3
                globalStiffnessMatrixElement8.get(3,1),//v3
                globalStiffnessMatrixElement8.get(3,2),//t3
                globalStiffnessMatrixElement10.get(3,0),//u4
                globalStiffnessMatrixElement10.get(3,1),//v4
                globalStiffnessMatrixElement10.get(3,2),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement10.get(3,3) + globalStiffnessMatrixElement8.get(3,3) + globalStiffnessMatrixElement12.get(0,0),//u6
                globalStiffnessMatrixElement10.get(3,4) + globalStiffnessMatrixElement8.get(3,4) + globalStiffnessMatrixElement12.get(0,1),//v6
                globalStiffnessMatrixElement10.get(3,5) + globalStiffnessMatrixElement8.get(3,5) + globalStiffnessMatrixElement12.get(0,2),//t6
                globalStiffnessMatrixElement12.get(0,3),//u7
                globalStiffnessMatrixElement12.get(0,4),//v7
                globalStiffnessMatrixElement12.get(0,5),//t7
//next row #17
                0,//u1
                0,//v1
                0,//t1
                0,//u2
                0,//v2
                0,//t2
                globalStiffnessMatrixElement8.get(4,0),//u3
                globalStiffnessMatrixElement8.get(4,1),//v3
                globalStiffnessMatrixElement8.get(4,2),//t3
                globalStiffnessMatrixElement10.get(4,0),//u4
                globalStiffnessMatrixElement10.get(4,1),//v4
                globalStiffnessMatrixElement10.get(4,2),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement10.get(4,3) + globalStiffnessMatrixElement8.get(4,3) + globalStiffnessMatrixElement12.get(1,0),//u6
                globalStiffnessMatrixElement10.get(4,4) + globalStiffnessMatrixElement8.get(4,4) + globalStiffnessMatrixElement12.get(1,1),//v6
                globalStiffnessMatrixElement10.get(4,5) + globalStiffnessMatrixElement8.get(4,5) + globalStiffnessMatrixElement12.get(1,2),//t6
                globalStiffnessMatrixElement12.get(1,3),//u7
                globalStiffnessMatrixElement12.get(1,4),//v7
                globalStiffnessMatrixElement12.get(1,5),//t7
//next row #18
                0,//u1
                0,//v1
                0,//t1
                0,//u2
                0,//v2
                0,//t2
                globalStiffnessMatrixElement8.get(5,0),//u3
                globalStiffnessMatrixElement8.get(5,1),//v3
                globalStiffnessMatrixElement8.get(5,2),//t3
                globalStiffnessMatrixElement10.get(5,0),//u4
                globalStiffnessMatrixElement10.get(5,1),//v4
                globalStiffnessMatrixElement10.get(5,2),//t4
                0,//u5
                0,//v5
                0,//t5
                globalStiffnessMatrixElement10.get(5,3) + globalStiffnessMatrixElement8.get(5,3) + globalStiffnessMatrixElement12.get(2,0),//u6
                globalStiffnessMatrixElement10.get(5,4) + globalStiffnessMatrixElement8.get(5,4) + globalStiffnessMatrixElement12.get(2,1),//v6
                globalStiffnessMatrixElement10.get(5,5) + globalStiffnessMatrixElement8.get(5,5) + globalStiffnessMatrixElement12.get(2,2),//t6
                globalStiffnessMatrixElement12.get(2,3),//u7
                globalStiffnessMatrixElement12.get(2,4),//v7
                globalStiffnessMatrixElement12.get(2,5),//t7
//next row #19
                globalStiffnessMatrixElement4.get(3,0),//u1
                globalStiffnessMatrixElement4.get(3,1),//v1
                globalStiffnessMatrixElement4.get(3,2),//t1
                globalStiffnessMatrixElement5.get(3,0),//u2
                globalStiffnessMatrixElement5.get(3,1),//v2
                globalStiffnessMatrixElement5.get(3,2),//t2
                0,//u3
                0,//v3
                0,//t3
                globalStiffnessMatrixElement9.get(3,0),//u4
                globalStiffnessMatrixElement9.get(3,1),//v4
                globalStiffnessMatrixElement9.get(3,2),//t4
                globalStiffnessMatrixElement11.get(3,0),//u5
                globalStiffnessMatrixElement11.get(3,1),//v5
                globalStiffnessMatrixElement11.get(3,2),//t5
                globalStiffnessMatrixElement12.get(3,0),//u6
                globalStiffnessMatrixElement12.get(3,1),//v6
                globalStiffnessMatrixElement12.get(3,2),//t6
                globalStiffnessMatrixElement12.get(3,3) + globalStiffnessMatrixElement11.get(3,3) + globalStiffnessMatrixElement9.get(3,3) + globalStiffnessMatrixElement5.get(3,3) + globalStiffnessMatrixElement4.get(3,3),//u7
                globalStiffnessMatrixElement12.get(3,4) + globalStiffnessMatrixElement11.get(3,4) + globalStiffnessMatrixElement9.get(3,4) + globalStiffnessMatrixElement5.get(3,4) + globalStiffnessMatrixElement4.get(3,4),//v7
                globalStiffnessMatrixElement12.get(3,5) + globalStiffnessMatrixElement11.get(3,5) + globalStiffnessMatrixElement9.get(3,5) + globalStiffnessMatrixElement5.get(3,5) + globalStiffnessMatrixElement4.get(3,5),//t7
//next row #20
                globalStiffnessMatrixElement4.get(4,0),//u1
                globalStiffnessMatrixElement4.get(4,1),//v1
                globalStiffnessMatrixElement4.get(4,2),//t1
                globalStiffnessMatrixElement5.get(4,0),//u2
                globalStiffnessMatrixElement5.get(4,1),//v2
                globalStiffnessMatrixElement5.get(4,2),//t2
                0,//u3
                0,//v3
                0,//t3
                globalStiffnessMatrixElement9.get(4,0),//u4
                globalStiffnessMatrixElement9.get(4,1),//v4
                globalStiffnessMatrixElement9.get(4,2),//t4
                globalStiffnessMatrixElement11.get(4,0),//u5
                globalStiffnessMatrixElement11.get(4,1),//v5
                globalStiffnessMatrixElement11.get(4,2),//t5
                globalStiffnessMatrixElement12.get(4,0),//u6
                globalStiffnessMatrixElement12.get(4,1),//v6
                globalStiffnessMatrixElement12.get(4,2),//t6
                globalStiffnessMatrixElement12.get(4,3) + globalStiffnessMatrixElement11.get(4,3) + globalStiffnessMatrixElement9.get(4,3) + globalStiffnessMatrixElement5.get(4,3) + globalStiffnessMatrixElement4.get(4,3),//u7
                globalStiffnessMatrixElement12.get(4,4) + globalStiffnessMatrixElement11.get(4,4) + globalStiffnessMatrixElement9.get(4,4) + globalStiffnessMatrixElement5.get(4,4) + globalStiffnessMatrixElement4.get(4,4),//v7
                globalStiffnessMatrixElement12.get(4,5) + globalStiffnessMatrixElement11.get(4,5) + globalStiffnessMatrixElement9.get(4,5) + globalStiffnessMatrixElement5.get(4,5) + globalStiffnessMatrixElement4.get(4,5),//t7
//next row #21
                globalStiffnessMatrixElement4.get(5,0),//u1
                globalStiffnessMatrixElement4.get(5,1),//v1
                globalStiffnessMatrixElement4.get(5,2),//t1
                globalStiffnessMatrixElement5.get(5,0),//u2
                globalStiffnessMatrixElement5.get(5,1),//v2
                globalStiffnessMatrixElement5.get(5,2),//t2
                0,//u3
                0,//v3
                0,//t3
                globalStiffnessMatrixElement9.get(5,0),//u4
                globalStiffnessMatrixElement9.get(5,1),//v4
                globalStiffnessMatrixElement9.get(5,2),//t4
                globalStiffnessMatrixElement11.get(5,0),//u5
                globalStiffnessMatrixElement11.get(5,1),//v5
                globalStiffnessMatrixElement11.get(5,2),//t5
                globalStiffnessMatrixElement12.get(5,0),//u6
                globalStiffnessMatrixElement12.get(5,1),//v6
                globalStiffnessMatrixElement12.get(5,2),//t6
                globalStiffnessMatrixElement12.get(5,3) + globalStiffnessMatrixElement11.get(5,3) + globalStiffnessMatrixElement9.get(5,3) + globalStiffnessMatrixElement5.get(5,3) + globalStiffnessMatrixElement4.get(5,3),//u7
                globalStiffnessMatrixElement12.get(5,4) + globalStiffnessMatrixElement11.get(5,4) + globalStiffnessMatrixElement9.get(5,4) + globalStiffnessMatrixElement5.get(5,4) + globalStiffnessMatrixElement4.get(5,4),//v7
                globalStiffnessMatrixElement12.get(5,5) + globalStiffnessMatrixElement11.get(5,5) + globalStiffnessMatrixElement9.get(5,5) + globalStiffnessMatrixElement5.get(5,5) + globalStiffnessMatrixElement4.get(5,5));//t7

        SimpleMatrix externalForcesMatrix = new SimpleMatrix(21,1,true,
                0.0,-5000.0*(0.003*1.2/2.0),0.0,
                0.0,-5000.0*(0.003*1.7/2.0),0.0,
                0.0,-5000.0*(0.003*0.5/2.0),0.0,
                0.0,0.0,0.0,
                -3000.0*Math.sqrt(3.0)/2.0,-3000.0/2.0,0.0,
                0.0,0.0,0.0,
                0.0,-5000.0,0.0);
        SimpleMatrix ansMatrix = globalStiffnessMatrix.solve(externalForcesMatrix);
        SimpleMatrix ansysMatrix = new SimpleMatrix(21,1,true,
                -0.11051*pow(10,-3),-0.14671*pow(10,-2),0.77572*pow(10,-3),
                -0.64035*pow(10,-4),-0.34332*pow(10,-3),0.77374*pow(10,-3),
                0.0000    ,-0.22815*pow(10,-4),0.22897*pow(10,-3),
                0.17456*pow(10,-4),-0.68288*pow(10,-4),0.37755*pow(10,-3),
                0.21379*pow(10,-3),-0.14745*pow(10,-2),0.43457*pow(10,-3),
                0.0000 , 0.0000,0.0000  ,
                0.42841*pow(10,-3),-0.39472*pow(10,-3),0.82303*pow(10,-3));
        SimpleMatrix internalForceSolverMatrix = new SimpleMatrix(21,1,true,
                ansMatrix.get(0,0),
                ansMatrix.get(1,0),
                ansMatrix.get(2,0),
                ansMatrix.get(3,0),
                ansMatrix.get(4,0),
                ansMatrix.get(5,0),
                0,
                0,
                ansMatrix.get(8,0),
                ansMatrix.get(9,0),
                ansMatrix.get(10,0),
                ansMatrix.get(11,0),
                ansMatrix.get(12,0),
                ansMatrix.get(13,0),
                ansMatrix.get(14,0),
                0,
                0,
                ansMatrix.get(17,0),
                ansMatrix.get(18,0),
                ansMatrix.get(19,0),
                ansMatrix.get(20,0));

        SimpleMatrix element1Stress = new SimpleMatrix(6,1,true,
                ansMatrix.get(0,0),
                ansMatrix.get(1,0),
                ansMatrix.get(2,0),
                ansMatrix.get(3,0),
                ansMatrix.get(4,0),
                ansMatrix.get(5,0));

        SimpleMatrix element9Stress = new SimpleMatrix(6,1,true,
                ansMatrix.get(9,0),
                ansMatrix.get(10,0),
                ansMatrix.get(11,0),
                ansMatrix.get(18,0),
                ansMatrix.get(19,0),
                ansMatrix.get(20,0));

        double axialStrain1 = -(element1Stress.get(0,0)-element1Stress.get(3,0))/beamElement1.BeamLenght();
        double axialStrain9 = -(element9Stress.get(0,0)-element9Stress.get(3,0))/beamElement9.BeamLenght();

        double axialStress1 = axialStrain1*beamElement1.getBeam().getYoungsModulus()/1000.0;
        double axialStress9 = axialStrain9*beamElement9.getBeam().getYoungsModulus()/1000.0;


        int ansSize = ansMatrix.getNumElements();

        tableLayout = (TableLayout) findViewById(R.id.utable);

        Toast.makeText(this,axialStress1  + "*" + axialStress9, Toast.LENGTH_SHORT).show();

        for (int i =0 ; i<(ansSize+3) ; i++){
            TableRow row = new TableRow(CalcActivity.this);
            if (i<ansSize) {
                if (i % 2 == 0) {
                    row.setBackgroundColor(Color.parseColor("#EEEEEE"));
                } else {
                    row.setBackgroundColor(Color.parseColor("#B0BEC5"));
                }
                for (int j = 0; j < 3; j++) {

                    String uvt = "";
                    if (j == 0) {
                        TextView txt = new TextView(CalcActivity.this);

                        if (i % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#2196F3"));
                            uvt = "u" + (i / 3 + 1) + ": ";
                        }
                        if ((i + 2) % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#E91E63"));
                            uvt = "v" + (i / 3 + 1) + ": ";
                        }
                        if ((i + 1) % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#111111"));
                            uvt = "θ" + (i / 3 + 1) + ": ";
                        }
                        txt.setText(uvt);
                        txt.setGravity(Gravity.LEFT);
                        txt.setTextSize((float) 20.0);
                        txt.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        row.addView(txt);
                    } else {
                        TextView txt = new TextView(CalcActivity.this);
                        if (j==1){
                            String ansValue = String.valueOf(internalForceSolverMatrix.get(i, 0)*1000.0);

                            txt.setText(ansValue);
                        }else {
                            String ansValue = String.valueOf(ansysMatrix.get(i, 0)*1000.0);
                            txt.setText(ansValue);
                        }


                        if (i % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#2196F3"));
                            uvt = "sigma" + (i + 1);
                        }
                        if ((i + 2) % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#E91E63"));
                            uvt = "j" + (i + -1);
                        }
                        if ((i + 1) % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#111111"));
                            uvt = "t" + (i + -2);
                        }
                        if (j < 2) {
                            txt.setGravity(Gravity.CENTER);
                        } else {
                            txt.setGravity(Gravity.CENTER);
                        }
                        txt.setTextSize((float) 17.0);
                        row.addView(txt);
                    }


                }

                tableLayout.addView(row);
            }else {

                for (int j = 0; j < 3; j++) {

                    String uvt = "";
                    if (j == 0) {
                        TextView txt = new TextView(CalcActivity.this);

                        if (i % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#2196F3"));
                            uvt = "σCD:";
                        }
                        if ((i + 2) % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#E91E63"));
                            uvt = "σAB:";
                        }
                        if ((i + 1) % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#111111"));
                            uvt = "###";
                        }
                        txt.setText(uvt);
                        txt.setGravity(Gravity.LEFT);
                        txt.setTextSize((float) 20.0);
                        txt.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        row.addView(txt);
                    } else {
                        String ansValue = "";
                        if (i==21){
                            if (j==1)
                                ansValue = String.valueOf(axialStress1+"");
                            else
                                ansValue = 7.7363*10.0 +"";
                        }if (i==22){
                            if (j==1)
                                ansValue = String.valueOf(axialStress9+"");
                            else
                                ansValue = -1.7025*1000.0+"";
                        }if (i==23) {
                            if (j==1)
                                ansValue = "Android";
                            else
                                ansValue = "ANSYS 18.0";
                        }
                        TextView txt = new TextView(CalcActivity.this);
                        txt.setText(ansValue);

                        if (i % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#2196F3"));
                        }
                        if ((i + 2) % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#E91E63"));
                        }
                        if ((i + 1) % 3 == 0) {
                            txt.setTextColor(Color.parseColor("#111111"));
                        }
                        txt.setGravity(Gravity.CENTER);
                        txt.setTextSize((float) 17.0);
                        row.addView(txt);
                    }


                }
                tableLayout.addView(row);
            }
        }


    }


}
