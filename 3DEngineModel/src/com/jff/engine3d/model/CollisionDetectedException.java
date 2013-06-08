package com.jff.engine3d.model;

import com.jff.engine3d.model.primitives.AbstractObject;

public class CollisionDetectedException extends Exception {
    private AbstractObject firstObject;
    private AbstractObject secondObject;

    public CollisionDetectedException(AbstractObject firstObject, AbstractObject secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    @Override
    public String getMessage() {
        String message = "Collision between " + firstObject + " and " + secondObject;
        return message;
    }
}
