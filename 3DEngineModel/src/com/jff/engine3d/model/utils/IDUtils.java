package com.jff.engine3d.model.utils;

public class IDUtils {
    public static int generateID() {
        int id = (int) System.currentTimeMillis();

        return id;
    }
}
