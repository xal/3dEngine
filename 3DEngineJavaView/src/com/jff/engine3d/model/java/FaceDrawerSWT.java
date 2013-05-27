package com.jff.engine3d.model.java;


import com.jff.engine3d.model.java.main.SWTPath;
import com.jff.engine3d.model.utils.draw.AbstractFaceDrawer;
import com.jff.engine3d.model.utils.draw.Point3D;
import org.eclipse.swt.graphics.Path;

public class FaceDrawerSWT extends AbstractFaceDrawer {

    public FaceDrawerSWT() {
        this.path = new SWTPath();
    }

    public void setPath(Point3D[] t, int[] face) {

        path = new SWTPath();
        path.moveTo((float) t[face[0]].x, (float) t[face[0]].y);
        path.lineTo((float) t[face[1]].x, (float) t[face[1]].y);
        path.lineTo((float) t[face[2]].x, (float) t[face[2]].y);
        path.close();
    }

    public Path getSWTPath() {
        SWTPath swtPath = (SWTPath) path;

        Path swtPathSwtPath = swtPath.getSwtPath();
        return swtPathSwtPath;
    }
}
