package model;

import java.util.ArrayList;
import java.util.List;

import primitives.Box;
import primitives.Cylinder;
import primitives.Tor;

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

    public static List<Triangle> triangulate(Box box, List<Point3D> vertices) {

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

    public static List<Point3D> createVertices(Cylinder cylinder) {

        List<Point3D> vertices = new ArrayList<Point3D>();

        //base lower center vertice
        Point3D center = cylinder.getCenter();
        int height = cylinder.getHeight();
        double centerX = center.getX();
        double centerY = center.getY();
        double centerZ = center.getZ();
        vertices.add(new Point3D(centerX, centerY, centerZ - height / 2));
        //base upper center vertice
        vertices.add(new Point3D(centerX, centerY, centerZ + height / 2));

        double angle = 0;
        double x;
        double y;
        double z;

        //first lower base point vertice
        double lowerRadius = cylinder.getLowerRadius();
        vertices.add(new Point3D(centerX + lowerRadius, centerY, centerZ - height / 2));

        //add vertices for lower base
        int QUALITY = 10;

        int BASE_LOWER_CENTER_INDEX = 0;
        int BASE_UPPER_CENTER_INDEX = 1;
        int FIRST_LOWER_BASE_POINT = 2;
        int FIRST_UPPER_BASE_POINT = QUALITY + FIRST_LOWER_BASE_POINT;

        for (int i = 0; i < QUALITY - 1; i++) {

            angle += 360.0 / 10;

            x = centerX + lowerRadius * Math.cos(Math.toRadians(angle));
            y = centerY + lowerRadius * Math.sin(Math.toRadians(angle));
            z = vertices.get(BASE_LOWER_CENTER_INDEX).z;

            vertices.add(new Point3D(x, y, z));
        }

        angle = 0;

        int upperBaseRadius = cylinder.getLowerRadius();
        vertices.add(new Point3D(centerX + upperBaseRadius, centerY, centerZ + height / 2));

        //add vertices for upper base
        for (int i = 0; i < QUALITY - 1; i++) {

            angle += 360.0 / QUALITY;

            x = centerX + upperBaseRadius * Math.cos(Math.toRadians(angle));
            y = centerY + upperBaseRadius * Math.sin(Math.toRadians(angle));
            z = vertices.get(BASE_UPPER_CENTER_INDEX).z;

            vertices.add(new Point3D(x, y, z));
        }

        return vertices;
    }

    public static List<Triangle> triangulate(Cylinder cylinder, List<Point3D> vertexes) {

        int QUALITY = 10;

        int BASE_LOWER_CENTER_INDEX = 0;
        int BASE_UPPER_CENTER_INDEX = 1;
        int FIRST_LOWER_BASE_POINT = 2;
        int FIRST_UPPER_BASE_POINT = QUALITY + FIRST_LOWER_BASE_POINT;


        List<Triangle> faces = new ArrayList<Triangle>();

        int size = QUALITY * 4;
        for (int i = 0; i < size; i++) {
            int index = i + 2;

            int firstIndex;
            int secondIndex;
            int thirdIndex;

            //faces of lower base
            if (i < QUALITY) {
                firstIndex = BASE_LOWER_CENTER_INDEX;
                secondIndex = index;
                thirdIndex = (i != QUALITY - 1) ? index + 1 : FIRST_LOWER_BASE_POINT;
            }
            //faces of upper base
            else if (i < QUALITY * 2) {
                firstIndex = BASE_UPPER_CENTER_INDEX;
                secondIndex = index;
                thirdIndex = (i != QUALITY * 2 - 1) ? index + 1 : FIRST_UPPER_BASE_POINT;
            }
            //faces of body from lower base
            else if (i < QUALITY * 3) {

                firstIndex = index - QUALITY;
                secondIndex = index - QUALITY * 2;
                thirdIndex = (i != QUALITY * 3 - 1) ? index - QUALITY * 2 + 1 :
                        FIRST_LOWER_BASE_POINT;

            }
            //faces of body from upper base
            else {
                firstIndex = index - QUALITY * 2;
                secondIndex = (i != QUALITY * 4 - 1) ? index - QUALITY * 2 + 1 :
                        FIRST_UPPER_BASE_POINT;
                thirdIndex = (i != QUALITY * 4 - 1) ? index - QUALITY * 3 + 1 :
                        FIRST_LOWER_BASE_POINT;
            }

            Triangle triangle = new Triangle(firstIndex, secondIndex, thirdIndex);

            faces.add(triangle);
        }

        return faces;
    }

    public static List<Point3D> createVertices(Tor tor) {
        return null;
    }

    public static List<Triangle> triangulate(Tor tor, List<Point3D> vertexes) {
        return null;
    }
}
