package com.rslakra.interview.walmart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FindMaxDistanceBetweenFlowersTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(FindMaxDistanceBetweenFlowersTest.class);

    /**
     * Tests <code>getMaxDistance()</code> method.
     */
    @Test
    public void testGetMaxDistance() {
        LOGGER.debug("+testGetMaxDistance()");
        FindMaxDistanceBetweenFlowers instance = new FindMaxDistanceBetweenFlowers();
        String[] flowers = new String[]{
                "Acacia", "Acanthus", "Amaranth", "American", "Cactus", "Angelica", "Arum", "Aspen", "Barberry",
                "Basil", "Acacia", "Broom", "Cactus", "Basil", "Bramble"
        };

        assertEquals(10, instance.getMaxDistance(flowers));
        LOGGER.debug("-testGetMaxDistance()");
    }
}
