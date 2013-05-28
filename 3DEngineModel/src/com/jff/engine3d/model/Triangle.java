package com.jff.engine3d.model;

public class Triangle {

    public final int firstVertexIndex;
    public final int secondVertexIndex;
    public final int thirdVertexIndex;

    public Triangle(int firstVertexIndex, int secondVertexIndex, int thirdVertexIndex) {
        this.firstVertexIndex = firstVertexIndex;
        this.secondVertexIndex = secondVertexIndex;
        this.thirdVertexIndex = thirdVertexIndex;
    }
}
