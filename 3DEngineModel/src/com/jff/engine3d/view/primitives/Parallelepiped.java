package com.jff.engine3d.view.primitives;

import com.jff.engine3d.view.utils.draw.Constants;
import com.jff.engine3d.view.utils.draw.Coordinates;
import com.jff.engine3d.view.utils.draw.Point3D;

public class Parallelepiped extends Primitive {
	
	private double width;
	private double length;
	private double height;
	
	public Parallelepiped(Coordinates coordinates, double width, double length, double height) {
		super(coordinates);
		
		this.width = width;
		this.length = length;
		this.height = height;

		int maxSize = (int) Math.max(Math.max(length, height), width) + Constants.BACKGROUND_PADDING;
		this.viewWidth = maxSize;
		this.viewHeight = maxSize;
		
		initialize();
	}

	public Parallelepiped() {
		super();
		this.viewWidth = 100;
		this.viewHeight = 100;
	}

	@Override
	public void initializeVertices() {
		
		double plusX = this.center.getX() + width / 2;
		double plusY = this.center.getY() + length / 2;
		double plusZ = this.center.getZ() + height / 2;
		
		double minusX = this.center.getX() - width / 2;
		double minusY = this.center.getY() - length / 2;
		double minusZ = this.center.getZ() - height / 2;
		
		vertices.add(new Point3D(minusX, plusY, minusZ, 1));
	    vertices.add(new Point3D(plusX, plusY, minusZ, 1));
	    vertices.add(new Point3D(plusX, minusY, minusZ, 1));
	    vertices.add(new Point3D(minusX, minusY, minusZ, 1));
	    vertices.add(new Point3D(minusX, plusY, plusZ, 1));
	    vertices.add(new Point3D(plusX, plusY, plusZ, 1));
	    vertices.add(new Point3D(plusX, minusY, plusZ, 1));
	    vertices.add(new Point3D(minusX, minusY, plusZ, 1));
	}
	
	@Override
	public void initializeFaces() {
		
		faces = new int[][] {{0, 1, 2}, {0, 2, 3},
		   		 			 {1, 5, 6}, {1, 6, 2}, 
		   		 			 {5, 4, 7}, {5, 7, 6}, 
		   		 			 {4, 0, 3}, {4, 3, 7}, 
		   		 			 {0, 4, 5}, {0, 5, 1}, 
		   		 			 {3, 2, 6}, {3, 6, 7}};
	}

	@Override
	public void scale(Coordinates offset) {
		super.scale(offset);
		
		this.width *= offset.getX();
		this.length *= offset.getY();
		this.height *= offset.getZ();
	}
}
