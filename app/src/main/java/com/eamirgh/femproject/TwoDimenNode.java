package com.eamirgh.femproject;

import java.io.Serializable;

/**
 * Created by eamirgh on 5/28/2017.
 */

public class TwoDimenNode implements Serializable {
    double x = 0;
    double y = 0;

    public TwoDimenNode(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
