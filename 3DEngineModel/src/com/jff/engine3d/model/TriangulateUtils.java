package com.jff.engine3d.model;

import com.jff.engine3d.model.primitives.Box;
import com.jff.engine3d.model.utils.draw.Point3D;

import java.util.ArrayList;
import java.util.List;

public class TriangulateUtils {


    public static List<Point3D> createVertices(Box box) {


        Point3D center = box.getCenter();

        int width = box.getWidth();
        int height = box.getHeight();
        int length = box.getLength();

        double plusX = center.getX() + width / 2;
        double plusY = center.getY() + length / 2;
        double plusZ = center.getZ() + height / 2;

        double minusX = center.getX() - width / 2;
        double minusY = center.getY() - length / 2;
        double minusZ = center.getZ() - height / 2;

        List<Point3D> vertices = new ArrayList<Point3D>();
        vertices.add(new Point3D(minusX, plusY, minusZ));
        vertices.add(new Point3D(plusX, plusY, minusZ));
        vertices.add(new Point3D(plusX, minusY, minusZ));
        vertices.add(new Point3D(minusX, minusY, minusZ));
        vertices.add(new Point3D(minusX, plusY, plusZ));
        vertices.add(new Point3D(plusX, plusY, plusZ));
        vertices.add(new Point3D(plusX, minusY, plusZ));
        vertices.add(new Point3D(minusX, minusY, plusZ));
        return vertices;
    }

    public static List<Triangle> triangulateBox(Box box, List<Point3D> vertices) {

        List<Triangle> triangles = new ArrayList<Triangle>();


        triangles.add(createTriangle(vertices, 0, 1, 2));
        triangles.add(createTriangle(vertices, 0, 2, 3));
        triangles.add(createTriangle(vertices, 1, 5, 6));
        triangles.add(createTriangle(vertices, 1, 6, 2));
        triangles.add(createTriangle(vertices, 5, 4, 7));
        triangles.add(createTriangle(vertices, 5, 7, 6));
        triangles.add(createTriangle(vertices, 4, 0, 3));
        triangles.add(createTriangle(vertices, 4, 3, 7));
        triangles.add(createTriangle(vertices, 0, 4, 5));
        triangles.add(createTriangle(vertices, 0, 5, 1));
        triangles.add(createTriangle(vertices, 3, 2, 6));
        triangles.add(createTriangle(vertices, 3, 6, 7));

        return triangles;
    }

    private static Triangle createTriangle(List<Point3D> vertices,
                                           int firstVertexIndex, int secondVertexIndex,
                                           int thirdVertexIndex) {

        Triangle triangle = new Triangle(firstVertexIndex, secondVertexIndex, thirdVertexIndex);

        return triangle;
    }
}
