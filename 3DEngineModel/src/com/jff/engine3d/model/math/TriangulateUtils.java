package com.jff.engine3d.model.math;

import com.jff.engine3d.model.entities.Point3D;
import com.jff.engine3d.model.entities.SpherePoint3D;
import com.jff.engine3d.model.entities.Triangle;
import com.jff.engine3d.model.primitives.Box;
import com.jff.engine3d.model.primitives.Cylinder;
import com.jff.engine3d.model.primitives.FrustumCone;
import com.jff.engine3d.model.primitives.Tor;

import java.util.ArrayList;
import java.util.List;

public class TriangulateUtils {


    private static final int QUALITY = 10;

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

    public static List<Triangle> triangulate(Box box, List<Point3D> vertices, int indexOffset) {

        List<Triangle> triangles = new ArrayList<Triangle>();


        triangles.add(createTriangle(vertices, indexOffset + 0, indexOffset + 1, indexOffset + 2));
        triangles.add(createTriangle(vertices, indexOffset + 0, indexOffset + 2, indexOffset + 3));
        triangles.add(createTriangle(vertices, indexOffset + 1, indexOffset + 5, indexOffset + 6));
        triangles.add(createTriangle(vertices, indexOffset + 1, indexOffset + 6, indexOffset + 2));
        triangles.add(createTriangle(vertices, indexOffset + 5, indexOffset + 4, indexOffset + 7));
        triangles.add(createTriangle(vertices, indexOffset + 5, indexOffset + 7, indexOffset + 6));
        triangles.add(createTriangle(vertices, indexOffset + 4, indexOffset + 0, indexOffset + 3));
        triangles.add(createTriangle(vertices, indexOffset + 4, indexOffset + 3, indexOffset + 7));
        triangles.add(createTriangle(vertices, indexOffset + 0, indexOffset + 4, indexOffset + 5));
        triangles.add(createTriangle(vertices, indexOffset + 0, indexOffset + 5, indexOffset + 1));
        triangles.add(createTriangle(vertices, indexOffset + 3, indexOffset + 2, indexOffset + 6));
        triangles.add(createTriangle(vertices, indexOffset + 3, indexOffset + 6, indexOffset + 7));

        return triangles;
    }

    public static List<Triangle> triangulate(Box box, List<Point3D> vertices) {
        return triangulate(box, vertices, 0);
    }

    public static List<Triangle> triangulate(Cylinder cylinder, List<Point3D> vertices) {
        return triangulate(cylinder, vertices, 0);
    }

    public static List<Triangle> triangulate(FrustumCone frustumCone, List<Point3D> vertices) {
        return triangulate(frustumCone, vertices, 0);
    }

    public static List<Triangle> triangulate(Tor tor, List<Point3D> vertices) {
        return triangulate(tor, vertices, 0);
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
        double height = cylinder.getHeight();
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

    public static List<Triangle> triangulate(Cylinder cylinder, List<Point3D> vertexes, int indexOffset) {


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

            firstIndex += indexOffset;
            secondIndex += indexOffset;
            thirdIndex += indexOffset;

            Triangle triangle = new Triangle(firstIndex, secondIndex, thirdIndex);

            faces.add(triangle);
        }

        return faces;
    }

    public static List<Point3D> createVertices(Tor tor) {

        Point3D center = tor.getCenter();

        int thicknessRadius = tor.getThicknessRadius();
        int circleRadius = tor.getCircleRadius();

        List<Point3D> vertexes = new ArrayList<Point3D>();

        int thicknessSegmentsCount = 360 / (QUALITY * 4);
        int circleSegmentsCount = thicknessSegmentsCount * 2;

        double thicknessSegmentStep = 360 / thicknessSegmentsCount;
        double circleSegmentStep = 360 / circleSegmentsCount;

        for (int i = 0; i < thicknessSegmentsCount; i++) {

            double currentThicknessStep = i * thicknessSegmentStep;


            SpherePoint3D currentThicknessSpherePoint = new SpherePoint3D();
            currentThicknessSpherePoint.r = thicknessRadius;
            currentThicknessSpherePoint.theta = Math.PI / 2;
            currentThicknessSpherePoint.phi = Math.toRadians(currentThicknessStep);

            Point3D thicknessPoint3D = SpherePoint3D.toCartesian(currentThicknessSpherePoint);


            double zOffset = thicknessPoint3D.x;
            double radiusOffset = thicknessPoint3D.y;
            double currentRadius = circleRadius + radiusOffset;

            for (int j = 0; j < circleSegmentsCount; j++) {
                double currentCircleStep = j * circleSegmentStep;

                SpherePoint3D currentCircleSpherePoint = new SpherePoint3D();
                currentCircleSpherePoint.r = currentRadius;
                currentCircleSpherePoint.theta = Math.PI / 2;
                currentCircleSpherePoint.phi = Math.toRadians(currentCircleStep);

                Point3D circlePoint3D = SpherePoint3D.toCartesian(currentCircleSpherePoint);

                circlePoint3D.z += zOffset;

                circlePoint3D.x += center.x;
                circlePoint3D.y += center.y;
                circlePoint3D.z += center.z;

                vertexes.add(circlePoint3D);


            }
        }


        return vertexes;
    }

    public static List<Triangle> triangulate(Tor tor, List<Point3D> vertexes, int indexOffset) {
        int thicknessRadius = tor.getThicknessRadius();
        int circleRadius = tor.getCircleRadius();

        List<Triangle> triangles = new ArrayList<Triangle>();

        int thicknessSegmentsCount = 360 / (QUALITY * 4);
        int circleSegmentsCount = thicknessSegmentsCount * 2;

        double thicknessSegmentStep = 360 / thicknessSegmentsCount;
        double circleSegmentStep = 360 / circleSegmentsCount;

        for (int i = 0; i < thicknessSegmentsCount; i++) {

            for (int j = 0; j < circleSegmentsCount; j++) {

                Triangle triangle;

                triangle = createFirstTorTriangle(indexOffset, thicknessSegmentsCount, circleSegmentsCount, i, j);
                triangles.add(triangle);
                triangle = createSecondTorTriangle(indexOffset, thicknessSegmentsCount, circleSegmentsCount, i, j);
                triangles.add(triangle);
            }
        }


        return triangles;

    }

    private static Triangle createFirstTorTriangle(int indexOffset, int thicknessSegmentsCount, int circleSegmentsCount, int i, int j) {
        int baseIndex = i * circleSegmentsCount;

        int firstIndex = j;
        int secondIndex = (j + 1) % circleSegmentsCount;
        int thirdIndex = j + circleSegmentsCount;

        firstIndex += baseIndex;
        secondIndex += baseIndex;
        thirdIndex += baseIndex;

        thirdIndex %= thicknessSegmentsCount * circleSegmentsCount;

        firstIndex += indexOffset;
        secondIndex += indexOffset;
        thirdIndex += indexOffset;

        return new Triangle(firstIndex, secondIndex, thirdIndex);
    }

    private static Triangle createSecondTorTriangle(int indexOffset, int thicknessSegmentsCount, int circleSegmentsCount, int i, int j) {
        int baseIndex = i * circleSegmentsCount;

        int secondIndex = (j + 1) % circleSegmentsCount;
        int firstIndex = secondIndex + circleSegmentsCount;
        int thirdIndex = j + circleSegmentsCount;


        firstIndex += baseIndex;
        secondIndex += baseIndex;
        thirdIndex += baseIndex;

        thirdIndex %= thicknessSegmentsCount * circleSegmentsCount;
        firstIndex %= thicknessSegmentsCount * circleSegmentsCount;

        firstIndex += indexOffset;
        secondIndex += indexOffset;
        thirdIndex += indexOffset;

        return new Triangle(firstIndex, secondIndex, thirdIndex);
    }

    public static List<Point3D> createVertices(FrustumCone frustumCone) {

        Point3D center = frustumCone.getCenter();
        int BASE_LOWER_CENTER_INDEX = 0;
        int BASE_UPPER_CENTER_INDEX = 1;
        int FIRST_LOWER_BASE_POINT = 2;


        int FIRST_UPPER_BASE_POINT = QUALITY / 2 + FIRST_LOWER_BASE_POINT + 1;

        //base lower center vertice
        List<Point3D> vertexes = new ArrayList<Point3D>();
        double cilinderHeight = frustumCone.getHeight();
        double lowerBaseRadius = frustumCone.getLowerRadius();
        double upperBaseRadius = frustumCone.getUpperRadius();
        vertexes.add(new Point3D(center.getX(), center.getY(), center.getZ() - cilinderHeight / 2));
        //base upper center vertice
        vertexes.add(new Point3D(center.getX(), center.getY(), center.getZ() + cilinderHeight / 2));

        double angle = 0;
        double x;
        double y;
        double z;

        //first lower base point vertexe
        vertexes.add(new Point3D(center.getX() + lowerBaseRadius, center.getY(), center.getZ() - cilinderHeight / 2));

        //add vertexes for lower base
        for (int i = 0; i < (QUALITY); i++) {

            angle += 360.0 / QUALITY;

            x = center.getX() + lowerBaseRadius * Math.cos(Math.toRadians(angle));
            y = center.getY() + lowerBaseRadius * Math.sin(Math.toRadians(angle));
            z = vertexes.get(BASE_LOWER_CENTER_INDEX).z;

            vertexes.add(new Point3D(x, y, z));
        }

        angle = 0;
        //first upper base point vertice
        vertexes.add(new Point3D(center.getX() + upperBaseRadius, center.getY(), center.getZ() + cilinderHeight / 2));

        //add vertexes for upper base
        for (int i = 0; i < (QUALITY); i++) {

            angle += 360.0 / QUALITY;

            x = center.getX() + upperBaseRadius * Math.cos(Math.toRadians(angle));
            y = center.getY() + upperBaseRadius * Math.sin(Math.toRadians(angle));
            z = vertexes.get(BASE_UPPER_CENTER_INDEX).z;

            vertexes.add(new Point3D(x, y, z));
        }

        return vertexes;
    }


    public static List<Triangle> triangulate(FrustumCone frustumCone, List<Point3D> vertexes, int indexOffset) {

        int BASE_LOWER_CENTER_INDEX = 0;
        int BASE_UPPER_CENTER_INDEX = 1;
        int FIRST_LOWER_BASE_POINT = 2;
        int FIRST_UPPER_BASE_POINT = QUALITY + FIRST_LOWER_BASE_POINT + 1;

        int size = QUALITY * 4 + 4;
        List<Triangle> faces = new ArrayList<Triangle>(size);


        for (int i = 0; i < size; i++) {

            int first;
            int second;
            int third;

            int index = i + 2;
            //faces of lower base
            int limitLowerCase = QUALITY;
            int limitUpperCase = QUALITY * 2;
            int limitBoydLowerCase = QUALITY * 3;
            int limitBoydUpperCase = QUALITY * 2 * 2;
            if (i <= limitLowerCase) {
                first = BASE_LOWER_CENTER_INDEX;
                second = index;
                third = (i != limitLowerCase) ? index + 1 : FIRST_LOWER_BASE_POINT;
            }
            //faces of upper base
            else if (i <= limitUpperCase + 1) {
                first = BASE_UPPER_CENTER_INDEX;
                second = index;
                third = (i != limitUpperCase + 1) ? index + 1 : FIRST_UPPER_BASE_POINT;
            }
            //faces of body from lower base
            else if (i <= limitBoydLowerCase + 2) {

                first = index - limitLowerCase - 1;
                second = index - limitUpperCase - 2;
                third = (i != limitBoydLowerCase + 2) ? index - limitUpperCase - 1 :
                        FIRST_LOWER_BASE_POINT;

            }
            //faces of body from upper base
            else {
                first = (i != limitBoydUpperCase + 3) ? index - limitBoydLowerCase - 2 :
                        FIRST_LOWER_BASE_POINT;
                second = index - limitUpperCase - 2;
                third = (i != limitBoydUpperCase + 3) ? index - limitUpperCase - 1 :
                        FIRST_UPPER_BASE_POINT;
            }

            first += indexOffset;
            second += indexOffset;
            third += indexOffset;

            Triangle triangle = new Triangle(first, second, third);
            faces.add(triangle);
        }

        return faces;
    }

}
