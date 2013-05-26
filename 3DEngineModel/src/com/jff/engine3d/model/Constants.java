package com.jff.engine3d.model;

import android.graphics.Color;

public class Constants {
	
	/**
	 * Primitives names
	 */
	public static final String PARALLELEPIPED_NAME = "Parallelepiped";
	public static final String CILINDER_NAME = "Cilinder";
	public static final String CONE_NAME = "Cone";
	public static final String ROLLER_NAME = "Roller";
    public static final String HALF_FRUST_CONE_NAME = "Half frusted cone";
	
	/**
	 * Dead zone of finger moving
	 */
	public static final int FINGER_MOVING_DEAD_ZONE = 5;
	 
	/**
	 * Quality of rotate-primitives (number of vertices of circle)
	 */
	public static final int QUALITY = 10;
	
	/**
	 * Color of background face when primitive is enabled
	 */
	public static int BACKGROUND_ENABLED_COLOR = Color.argb(70, 129, 176, 244);
	
	/**
	 * Padding of background face
	 */
	public static int BACKGROUND_PADDING = 40;
}
