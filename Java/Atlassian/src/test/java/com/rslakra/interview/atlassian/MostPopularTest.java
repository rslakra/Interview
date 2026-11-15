package com.rslakra.interview.atlassian;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @created 4/29/22 12:43 PM
 */
public class MostPopularTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(MostPopularTest.class);

    private MostPopular instance;

    @Test
    public void testInterviewMap() {
        LOGGER.debug("+testInterviewMap()");
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
        LOGGER.debug("-testInterviewMap()");
    }

    @Test
    public void testMostPopular() {
        LOGGER.debug("+testMostPopular()");
        MostPopular instance = mock(InterviewMap.class);
        when(instance.mostPopular()).thenReturn(1);
        assertEquals(1, instance.mostPopular());
        verify(instance).mostPopular();
        LOGGER.debug("-testMostPopular()");
    }

    @Test
    public void testIncreasePopularity() {
        LOGGER.debug("+testIncreasePopularity()");
        MostPopular instance = mock(InterviewMap.class);
        /**
         * Void methods can be used with Mockito’s doNothing(), doThrow(), and doAnswer() methods, making mocking and verifying intuitive:
         */
        doNothing().when(instance).increasePopularity(isA(Integer.class));
        Integer id = Integer.valueOf(1);
        when(instance.mostPopular()).thenReturn(1);
        instance.increasePopularity(id);
        assertEquals(1, instance.mostPopular());

        verify(instance, times(1)).increasePopularity(id);
        verify(instance, times(1)).mostPopular();
        LOGGER.debug("-testIncreasePopularity()");
    }

    @Test
    public void testDecreasePopularity() {
        LOGGER.debug("+testDecreasePopularity()");
        MostPopular instance = mock(InterviewMap.class);
        /**
         * Void methods can be used with Mockito’s doNothing(), doThrow(), and doAnswer() methods, making mocking and verifying intuitive:
         */
        doNothing().when(instance).decreasePopularity(isA(Integer.class));
        Integer id = Integer.valueOf(1);
        when(instance.mostPopular()).thenReturn(1);
        instance.decreasePopularity(id);
        assertEquals(1, instance.mostPopular());

        verify(instance, times(1)).decreasePopularity(id);
        verify(instance, times(1)).mostPopular();
        LOGGER.debug("-testDecreasePopularity()");
    }


}
