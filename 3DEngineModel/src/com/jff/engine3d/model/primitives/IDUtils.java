package com.jff.engine3d.model.primitives;

public class IDUtils {
    public static int generateID() {
        int id = (int) System.currentTimeMillis();

        return id;
    }
}
