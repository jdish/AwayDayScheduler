package main;

import activity.Activity;
import org.junit.Before;
import org.junit.Test;
import timeblock.TimeBlock;
import timeblock.TimeBlockFactory;
import timeslot.TimeSlot;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by James Horne on 04/09/2016.
 */
public class TimeBlockTest {

    private TimeBlock timeBlock;

    @Before
    public void setup() throws Exception {
        timeBlock = TimeBlockFactory.createTimeBlock("MORNING");
    }

    @Test
    public void checkRemainingDurationIsUpdatedTest() throws Exception {
        assertTrue(timeBlock.updateRemainingDuration(Duration.ofMinutes(30)));
    }

    @Test
    public void checkRemainingDurationIsNotUpdatedTest() throws Exception {
        assertTrue(timeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertTrue(timeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertTrue(timeBlock.updateRemainingDuration(Duration.ofMinutes(60)));
        assertFalse(timeBlock.updateRemainingDuration(Duration.ofMinutes(30)));
    }

    @Test
    public void checkTimeSlotWithCorrectActivityIsAddedToTimeBlockTest() throws Exception {
        assertTrue(timeBlock.getTimeSlots().size() == 0);
        Activity duckHerdingAct = new Activity("Duck Herding", Duration.ofMinutes(60));
        timeBlock.addTimeSlotToBlock(duckHerdingAct);
        List<TimeSlot> timeSlots = timeBlock.getTimeSlots();
        assertTrue(timeSlots.size() == 1);
        assertTrue(timeBlock.getTimeSlots().get(0).getActivity().equals(duckHerdingAct));
    }

    @Test
    public void checkTimeSlotIsNotAddedToTimeBlockWhenAlreadyFilled() throws Exception {
        timeBlock = TimeBlockFactory.createTimeBlock("LUNCH");
        assertTrue(timeBlock.isFilled());
        Activity duckHerdingAct = new Activity("Duck Herding", Duration.ofMinutes(60));
        timeBlock.addTimeSlotToBlock(duckHerdingAct);
        List<TimeSlot> timeSlots = timeBlock.getTimeSlots();
        assertFalse(timeSlots.size() == 2);
        assertNotEquals(new TimeSlot(duckHerdingAct, LocalTime.of(12, 0)), timeSlots.get(0));
    }
}
