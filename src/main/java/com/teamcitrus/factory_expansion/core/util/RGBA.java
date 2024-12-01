package com.teamcitrus.factory_expansion.core.util;

public class RGBA {

    private int R;
    private int G;
    private int B;
    private int A;

    public RGBA(int R, int G, int B, int A) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.A = A;
    }

    public int red() {
        return R;
    }

    public int green() {
        return G;
    }

    public int blue() {
        return B;
    }

    public int alpha() {
        return A;
    }

    public RGBA shade(float bias) {
        this.R = (int) (this.R / bias);
        this.G = (int) (this.G / bias);
        this.B = (int) (this.B / bias);
        return this;
    }
}
