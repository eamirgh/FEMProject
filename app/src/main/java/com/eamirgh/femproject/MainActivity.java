package com.eamirgh.femproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {

    EditText edhBeamW,edhBeamH,edhBeamE,edhBeamT,edhBeamP,
            edvBeamW,edvBeamH,edvBeamE,edvBeamT,edvBeamP,
            edtBeamW,edtBeamH,edtBeamE,edtBeamT,edtBeamP,
            edNode1X,edNode1Y,
            edNode2X,edNode2Y,
            edNode3X,edNode3Y,
            edNode4X,edNode4Y,
            edNode5X,edNode5Y,
            edNode6X,edNode6Y,
            edNode7X,edNode7Y;

    String svalhBeamW,svalhBeamH,svalhBeamE,svalhBeamT,svalhBeamP,
            svalvBeamW,svalvBeamH,svalvBeamE,svalvBeamT,svalvBeamP,
            svaltBeamW,svaltBeamH,svaltBeamE,svaltBeamT,svaltBeamP,
            svalNode1X,svalNode1Y,
            svalNode2X,svalNode2Y,
            svalNode3X,svalNode3Y,
            svalNode4X,svalNode4Y,
            svalNode5X,svalNode5Y,
            svalNode6X,svalNode6Y,
            svalNode7X,svalNode7Y;

    double valhBeamW,valhBeamH,valhBeamE,valhBeamT,valhBeamP,
            valvBeamW,valvBeamH,valvBeamE,valvBeamT,valvBeamP,
            valtBeamW,valtBeamH,valtBeamE,valtBeamT,valtBeamP,
            valNode1X,valNode1Y,
            valNode2X,valNode2Y,
            valNode3X,valNode3Y,
            valNode4X,valNode4Y,
            valNode5X,valNode5Y,
            valNode6X,valNode6Y,
            valNode7X,valNode7Y;

    Button btn;

    Beam horizontalBeam,verticalBeam,tiltBeam;

    TwoDimenNode node1,node2,node3,node4,node5,node6,node7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edhBeamW = (EditText) findViewById(R.id.hbeamwidth);
        edvBeamW = (EditText) findViewById(R.id.vbeamwidth);
        edtBeamW = (EditText) findViewById(R.id.tbeamwidth);

        edhBeamH = (EditText) findViewById(R.id.hbeamheaight);
        edvBeamH = (EditText) findViewById(R.id.vbeamheaight);
        edtBeamH = (EditText) findViewById(R.id.tbeamheaight);

        edhBeamE = (EditText) findViewById(R.id.hbeammudulus);
        edvBeamE = (EditText) findViewById(R.id.vbeammudulus);
        edtBeamE = (EditText) findViewById(R.id.tbeammudulus);

        edhBeamT = (EditText) findViewById(R.id.hbeamt);
        edvBeamT = (EditText) findViewById(R.id.vbeamt);
        edtBeamT = (EditText) findViewById(R.id.tbeamt);

        edhBeamP = (EditText) findViewById(R.id.hbeampuasson);
        edvBeamP = (EditText) findViewById(R.id.vbeampuasson);
        edtBeamP = (EditText) findViewById(R.id.tbeampuasson);

        edNode1X = (EditText) findViewById(R.id.node1x);
        edNode1Y = (EditText) findViewById(R.id.node1y);

        edNode2X = (EditText) findViewById(R.id.node2x);
        edNode2Y = (EditText) findViewById(R.id.node2y);

        edNode3X = (EditText) findViewById(R.id.node3x);
        edNode3Y = (EditText) findViewById(R.id.node3y);

        edNode4X = (EditText) findViewById(R.id.node4x);
        edNode4Y = (EditText) findViewById(R.id.node4y);

        edNode5X = (EditText) findViewById(R.id.node5x);
        edNode5Y = (EditText) findViewById(R.id.node5y);

        edNode6X = (EditText) findViewById(R.id.node6x);
        edNode6Y = (EditText) findViewById(R.id.node6y);

        edNode7X = (EditText) findViewById(R.id.node7x);
        edNode7Y = (EditText) findViewById(R.id.node7y);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GETTING HORIZONTAL BEAM DATA PASSING DEFAULT TO EMPTY ONES
                svalhBeamW = edhBeamW.getText().toString();
                if (svalhBeamW == null || svalhBeamW.isEmpty()){
                    valhBeamW = 0.06;
                }else {
                    valhBeamW = Double.parseDouble(svalhBeamW);
                }
                svalhBeamH = edhBeamH.getText().toString();
                if (svalhBeamH == null || svalhBeamH.isEmpty()){
                    valhBeamH = 0.1;
                }else {
                    valhBeamH = Double.parseDouble(svalhBeamH);
                }
                svalhBeamE = edhBeamE.getText().toString();
                if (svalhBeamE == null || svalhBeamE.isEmpty()){
                    valhBeamE = 200.0* pow(10.0,9);
                }else {
                    valhBeamE = Double.parseDouble(svalhBeamE);
                }
                svalhBeamT = edhBeamT.getText().toString();
                if (svalhBeamT == null || svalhBeamT.isEmpty()){
                    valhBeamT = 0.003;
                }else {
                    valhBeamT = Double.parseDouble(svalhBeamT);
                }
                svalhBeamP = edhBeamP.getText().toString();
                if (svalhBeamP == null || svalhBeamP.isEmpty()){
                    valhBeamP = 0.3;
                }else {
                    valhBeamP = Double.parseDouble(svalhBeamP);
                }
                //Vertical Beams:
                svalvBeamW = edvBeamW.getText().toString();
                if (svalvBeamW == null || svalvBeamW.isEmpty()){
                    valvBeamW = 0.06;
                }else {
                    valvBeamW = Double.parseDouble(svalvBeamW);
                }
                svalvBeamH = edvBeamH.getText().toString();
                if (svalvBeamH == null || svalvBeamH.isEmpty()){
                    valvBeamH = 0.06;
                }else {
                    valvBeamH = Double.parseDouble(svalvBeamH);
                }
                svalvBeamE = edvBeamE.getText().toString();
                if (svalvBeamE == null || svalvBeamE.isEmpty()){
                    valvBeamE = 210.0* pow(10.0,9);
                }else {
                    valvBeamE = Double.parseDouble(svalvBeamE);
                }
                svalvBeamT = edvBeamT.getText().toString();
                if (svalvBeamT == null || svalvBeamT.isEmpty()){
                    valvBeamT = 0.003;
                }else {
                    valvBeamT = Double.parseDouble(svalvBeamT);
                }
                svalvBeamP = edvBeamP.getText().toString();
                if (svalvBeamP == null || svalvBeamP.isEmpty()){
                    valvBeamP = 0.3;
                }else {
                    valvBeamP = Double.parseDouble(svalvBeamP);
                }
                //Tilt Beams:
                svaltBeamW = edtBeamW.getText().toString();
                if (svaltBeamW == null || svaltBeamW.isEmpty()){
                    valtBeamW = 0.04;
                }else {
                    valtBeamW = Double.parseDouble(svaltBeamW);
                }
                svaltBeamH = edtBeamH.getText().toString();
                if (svaltBeamH == null || svaltBeamH.isEmpty()){
                    valtBeamH = 0.06;
                }else {
                    valtBeamH = Double.parseDouble(svaltBeamH);
                }
                svaltBeamE = edtBeamE.getText().toString();
                if (svaltBeamE == null || svaltBeamE.isEmpty()){
                    valtBeamE = 210.0* pow(10.0,9);
                }else {
                    valtBeamE = Double.parseDouble(svaltBeamE);
                }
                svaltBeamT = edtBeamT.getText().toString();
                if (svaltBeamT == null || svaltBeamT.isEmpty()){
                    valtBeamT = 0.002;
                }else {
                    valtBeamT = Double.parseDouble(svaltBeamT);
                }
                svaltBeamP = edtBeamP.getText().toString();
                if (svaltBeamP == null || svaltBeamP.isEmpty()){
                    valtBeamP = 0.3;
                }else {
                    valtBeamP = Double.parseDouble(svaltBeamP);
                }

                /*
                *
                * implementing nodes:
                *
                 */
                svalNode1X = edNode1X.getText().toString();
                if (svalNode1X == null || svalNode1X.isEmpty()){
                    valNode1X = 0.0;
                }else {
                    valNode1X = Double.parseDouble(svalNode1X);
                }
                svalNode1Y = edNode1Y.getText().toString();
                if (svalNode1Y == null || svalNode1Y.isEmpty()){
                    valNode1Y = 0.0;
                }else {
                    valNode1Y = Double.parseDouble(svalNode1Y);
                }
                //Node2
                svalNode2X = edNode2X.getText().toString();
                if (svalNode2X == null || svalNode2X.isEmpty()){
                    valNode2X = 1.2;
                }else {
                    valNode2X = Double.parseDouble(svalNode2X);
                }
                svalNode2Y = edNode2Y.getText().toString();
                if (svalNode2Y == null || svalNode2Y.isEmpty()){
                    valNode2Y = 0.0;
                }else {
                    valNode2Y = Double.parseDouble(svalNode2Y);
                }
                //Node3
                svalNode3X = edNode3X.getText().toString();
                if (svalNode3X == null || svalNode3X.isEmpty()){
                    valNode3X = 1.7;
                }else {
                    valNode3X = Double.parseDouble(svalNode3X);
                }
                svalNode3Y = edNode3Y.getText().toString();
                if (svalNode3Y == null || svalNode3Y.isEmpty()){
                    valNode3Y = 0.0;
                }else {
                    valNode3Y = Double.parseDouble(svalNode3Y);
                }
                //Node4
                svalNode4X = edNode4X.getText().toString();
                if (svalNode4X == null || svalNode4X.isEmpty()){
                    valNode4X = 1.2+1.0/3.0;
                }else {
                    valNode4X = Double.parseDouble(svalNode4X);
                }
                svalNode4Y = edNode4Y.getText().toString();
                if (svalNode4Y == null || svalNode4Y.isEmpty()){
                    valNode4Y = -8.0/30.0;
                }else {
                    valNode4Y = Double.parseDouble(svalNode4Y);
                }
                //Node5
                svalNode5X = edNode5X.getText().toString();
                if (svalNode5X == null || svalNode5X.isEmpty()){
                    valNode5X = 0.0;
                }else {
                    valNode5X = Double.parseDouble(svalNode5X);
                }
                svalNode5Y = edNode5Y.getText().toString();
                if (svalNode5Y == null || svalNode5Y.isEmpty()){
                    valNode5Y = -0.6;
                }else {
                    valNode5Y = Double.parseDouble(svalNode5Y);
                }
                //Node6
                svalNode6X = edNode6X.getText().toString();
                if (svalNode6X == null || svalNode6X.isEmpty()){
                    valNode6X = 1.7;
                }else {
                    valNode6X = Double.parseDouble(svalNode6X);
                }
                svalNode6Y = edNode6Y.getText().toString();
                if (svalNode6Y == null || svalNode6Y.isEmpty()){
                    valNode6Y = -0.4;
                }else {
                    valNode6Y = Double.parseDouble(svalNode6Y);
                }
                svalNode7X = edNode7X.getText().toString();
                if (svalNode7X == null || svalNode7X.isEmpty()){
                    valNode7X = 1.2;
                }else {
                    valNode7X = Double.parseDouble(svalNode7X);
                }
                svalNode7Y = edNode7Y.getText().toString();
                if (svalNode7Y == null || svalNode7Y.isEmpty()){
                    valNode7Y = -0.8;
                }else {
                    valNode7Y = Double.parseDouble(svalNode7Y);
                }
                /*
                *
                * *********************************
                * ************  Beams *************
                * *********************************
                *
                 */

                horizontalBeam = new Beam(valhBeamE,valhBeamP,valhBeamW,valhBeamH,valhBeamT);
                verticalBeam = new Beam(valvBeamE,valvBeamP,valvBeamW,valvBeamH,valvBeamT);
                tiltBeam = new Beam(valtBeamE,valtBeamP,valtBeamW,valtBeamH,valtBeamT);

                /*
                *
                * *********************************
                * ************  Nodes *************
                * *********************************
                *
                 */
                node1 = new TwoDimenNode(valNode1X,valNode1Y);
                node2 = new TwoDimenNode(valNode2X,valNode2Y);
                node3 = new TwoDimenNode(valNode3X,valNode3Y);
                node4 = new TwoDimenNode(valNode4X,valNode4Y);
                node5 = new TwoDimenNode(valNode5X,valNode5Y);
                node6 = new TwoDimenNode(valNode6X,valNode6Y);
                node7 = new TwoDimenNode(valNode7X,valNode7Y);

                Intent intent = new Intent(MainActivity.this,CalcActivity.class);
                intent.putExtra("HBeam",horizontalBeam);
                intent.putExtra("VBeam",verticalBeam);
                intent.putExtra("TBeam",tiltBeam);
                intent.putExtra("n1", node1);
                intent.putExtra("n2", node2);
                intent.putExtra("n3", node3);
                intent.putExtra("n4", node4);
                intent.putExtra("n5", node5);
                intent.putExtra("n6", node6);
                intent.putExtra("n7", node7);
                startActivity(intent);
            }
        });

    }


}
