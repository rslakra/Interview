package com.rslakra.appsuite.core;

import com.rslakra.appsuite.core.shape.*;

public enum Shapes {
    INSTANCE;

    /**
     *
     * @param shapeType
     * @return
     */
    Shape create(ShapeType shapeType) {
        switch (shapeType) {
            case TRIANGLE:
                return new Triangle();
            case QUADRILATERAL:
                return new Quadrilateral();
            case PENTAGON:
                return new Pentagon();
            case HEXAGON:
                return new Hexagon();
            case HEPTAGON:
                return new Heptagon();
            case OCTAGON:
                return new Octagon();
            case NONAGON:
                return new Nonagon();
            case DECAGON:
                return new Decagon();
            case HENDECAGON:
                return new Hendecagon();
            case DODECAGON:
                return new Dodecagon();

            default:
                return null;
        }
    }

}
