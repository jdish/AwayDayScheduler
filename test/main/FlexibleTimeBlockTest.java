package main;

import org.junit.Before;
import org.junit.Test;
import timeblock.TimeBlock;
import timeblock.TimeBlockFactory;

import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by James Horne on 03/09/2016.
 */
public class FlexibleTimeBlockTest {

    private TimeBlock partialFillTimeBlock;

    @Before
    public void setup() throws Exception {
        partialFillTimeBlock = TimeBlockFactory.createTimeBlock("AFTERNOON");
    }

    @Test
    public void checkRemainingDurationIsUpdatedWithin1HourOfMaxDurationTest() throws Exception {
        assertTrue(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertTrue(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertTrue(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertTrue(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(30)));
        assertTrue(partialFillTimeBlock.isFilled());
    }

    @Test
    public void checkRemainingDurationIsNotUpdatedTest() throws Exception {
        assertTrue(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertTrue(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertTrue(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertTrue(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertFalse(partialFillTimeBlock.updateRemainingDuration(Duration.ofMinutes(30)));
        assertTrue(partialFillTimeBlock.isFilled());
    }
}
