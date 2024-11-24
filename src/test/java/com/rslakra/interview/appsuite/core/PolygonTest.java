package com.rslakra.interview.appsuite.core;

import com.rslakra.appsuite.core.shape.Polygon;
import com.rslakra.appsuite.core.shape.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Rohtash Lakra
 * @created 10/13/24 4:07 PM
 */
public class PolygonTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(PolygonTest.class);

    /**
     * Tests <code>getSides()</code> method.
     */
    @Test
    public void testSides() {
        LOGGER.debug("+testSides()");
        Polygon instance = mock(Triangle.class);
        int sides = 3;
        when(instance.getSides()).thenReturn(sides);
        assertEquals(sides, instance.getSides());
        verify(instance).getSides();
        LOGGER.debug("-testSides()");
    }

}
