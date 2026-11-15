package com.rslakra.common;

import com.rslakra.common.shape.Decagon;
import com.rslakra.common.shape.Dodecagon;
import com.rslakra.common.shape.Hendecagon;
import com.rslakra.common.shape.Heptagon;
import com.rslakra.common.shape.Hexagon;
import com.rslakra.common.shape.Nonagon;
import com.rslakra.common.shape.Octagon;
import com.rslakra.common.shape.Pentagon;
import com.rslakra.common.shape.Quadrilateral;
import com.rslakra.common.shape.Triangle;

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
