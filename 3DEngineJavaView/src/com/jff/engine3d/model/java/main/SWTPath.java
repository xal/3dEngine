package com.jff.engine3d.model.java.main;

import com.jff.engine3d.model.utils.draw.AbstractPath;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;

public class SWTPath extends AbstractPath {

    private Path swtPath;

    public SWTPath() {
        swtPath = new Path(Display.getCurrent());
    }

    @Override
    public void moveTo(float x, float y) {
        swtPath.moveTo(x, y);
    }

    @Override
    public void lineTo(float x, float y) {
        swtPath.lineTo(x, y);
    }

    @Override
    public void close() {
        swtPath.close();
    }

    public Path getSwtPath() {
        return swtPath;
    }
}
