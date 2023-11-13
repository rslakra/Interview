package com.devamatre.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @created 4/29/22 12:43 PM
 */
public class InterviewTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewTest.class);

    private Interview interview;

    @Test
    public void testAltassian() {
        LOGGER.debug("+testAltassian()");
        interview = new Interview();
        assertNotNull(interview);
        LOGGER.debug("{}", interview);
        assertEquals(2023, interview.getYear());
        assertEquals("Rohtash", interview.getName());
        LOGGER.debug("-testAltassian()");
    }

}
