package model;

public class PerspectiveCameraProjection extends AbstractCameraProjection {


    public PerspectiveCameraProjection(CameraSettings cameraSettings) {
        super(ProjectionType.PERSPECTIVE, cameraSettings);
    }

    @Override
    public Point3D convertToCameraCoordinateSystem(Point3D point3D) {
        Point3D converted = super.convertToCameraCoordinateSystem(point3D);


        double focalLength = cameraSettings.getFocalLength();
        double ratio = focalLength / converted.z;

        double x = ratio * converted.x;
        double y = ratio * converted.y;
        double z = converted.z;

        converted.x = x;
        converted.y = y;
        converted.z = z;

        return converted;
    }

    @Override
    public float distanceFromCameraToPoint(Point3D point3D) {
        float distance;

        Point3D fromCoordinates = cameraSettings.getRealFromCoordinates();
        distance = fromCoordinates.distanceTo(point3D);

        return distance;
    }

    @Override
    public boolean isObjectInCamera(SceneObject object) {
        return true;
    }


}
