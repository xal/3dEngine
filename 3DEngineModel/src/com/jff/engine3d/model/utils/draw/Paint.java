package com.jff.engine3d.model.utils.draw;

public class Paint {

    private Color color;
    private PaintStyle style;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PaintStyle getStyle() {
        return style;
    }

    public void setStyle(PaintStyle style) {
        this.style = style;
    }

    public enum PaintStyle {
        FILL
    }
}
