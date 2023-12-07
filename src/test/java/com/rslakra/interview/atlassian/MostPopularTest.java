package com.rslakra.interview.atlassian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rohtash Lakra
 * @created 4/29/22 12:43 PM
 */
public class MostPopularTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(MostPopularTest.class);

    private MostPopular instance;

    @Test
    public void testAltassian() {
        LOGGER.debug("+testAltassian()");
        instance = new InterviewMap();
        assertNotNull(instance);
        instance.increasePopularity(7);
        instance.increasePopularity(7);
        instance.increasePopularity(8);
        int result = instance.mostPopular();
        LOGGER.debug("result:{}", result);
        assertEquals(7, result);

        instance.increasePopularity(8);
        instance.increasePopularity(8);
        result = instance.mostPopular();
        LOGGER.debug("result:{}", result);
        assertEquals(8, result);

        instance.decreasePopularity(8);
        instance.decreasePopularity(8);
        result = instance.mostPopular();
        LOGGER.debug("result:{}", result);
        assertEquals(7, result);

        instance.decreasePopularity(7);
        instance.decreasePopularity(7);
        instance.decreasePopularity(8);
        // returns -1 since there is no content with popularity greater than 0
        result = instance.mostPopular();
        LOGGER.debug("result:{}", result);
        assertEquals(-1, result);
        LOGGER.debug("-testAltassian()");
    }

}
