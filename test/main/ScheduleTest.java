package main;

import activity.Activity;
import org.junit.Before;
import scheduling.Schedule;
import org.junit.Test;
import timeblock.TimeBlockFactory;
import timeslot.TimeSlot;

import java.time.Duration;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by James Horne on 02/09/2016.
 */
public class ScheduleTest {

    private Schedule schedule;

    @Before
    public void setup() throws Exception {
        schedule = new Schedule(Collections.singletonList(TimeBlockFactory.createTimeBlock("MORNING")));
    }

    @Test
    public void checkActivityIsAddedToTimeBlockTest() throws Exception {
        Activity duckHerdingAct = new Activity("Duck Herding", Duration.ofMinutes(60));
        assertTrue(schedule.tryToAddActivity(duckHerdingAct));
        assertTrue(schedule.getTimeSlots().size() == 1);
        for (TimeSlot timeSlot : schedule.getTimeSlots()) {
            assertEquals(timeSlot.getActivity(), duckHerdingAct);
        }
    }

    @Test
    public void checkActivityIsNotAddedToScheduleWhenFullTest() throws Exception {
        assertTrue(schedule.tryToAddActivity(new Activity("Duck Herding", Duration.ofMinutes(60))));
        assertTrue(schedule.tryToAddActivity(new Activity("Viking Axe Throwing", Duration.ofMinutes(60))));
        assertTrue(schedule.tryToAddActivity(new Activity("Digital Tresure Hunt", Duration.ofMinutes(60))));
        Activity montiCarloAct = new Activity("Monti Carlo or Bust", Duration.ofMinutes(60));
        assertFalse(schedule.tryToAddActivity(montiCarloAct));
        assertFalse(schedule.getTimeSlots().size() == 4);
        for (TimeSlot timeSlot : schedule.getTimeSlots()) {
            assertNotEquals(timeSlot.getActivity(), montiCarloAct);
        }
    }

    @Test
    public void checkActivityIsNotAddedToScheduleWhenDurationIsTooGreatTest() throws Exception {
        assertTrue(schedule.tryToAddActivity(new Activity("Duck Herding", Duration.ofMinutes(60))));
        assertTrue(schedule.tryToAddActivity(new Activity("Viking Axe Throwing", Duration.ofMinutes(60))));
        assertTrue(schedule.tryToAddActivity(new Activity("Arduino Bonanza", Duration.ofMinutes(30))));
        Activity montiCarloAct = new Activity("Monti Carlo or Bust", Duration.ofMinutes(60));
        assertFalse(schedule.tryToAddActivity(montiCarloAct));
        assertFalse(schedule.getTimeSlots().size() == 4);
        for (TimeSlot timeSlot : schedule.getTimeSlots()) {
            assertNotEquals(timeSlot.getActivity(), montiCarloAct);
        }
    }

    @Test
    public void checkScheduleIsCreatedTest() throws Exception {
        assertTrue(schedule.tryToAddActivity(new Activity("Duck Herding", Duration.ofMinutes(60))));
        assertTrue(schedule.tryToAddActivity(new Activity("Viking Axe Throwing", Duration.ofMinutes(60))));
        assertTrue(schedule.tryToAddActivity(new Activity("Digital Tresure Hunt", Duration.ofMinutes(60))));
        assertTrue(schedule.isCreated());
    }

    @Test
    public void checkScheduleIsNotYetCreatedTest() throws Exception {
        assertTrue(schedule.tryToAddActivity(new Activity("Duck Herding", Duration.ofMinutes(60))));
        assertTrue(schedule.tryToAddActivity(new Activity("Viking Axe Throwing", Duration.ofMinutes(60))));
        assertTrue(schedule.tryToAddActivity(new Activity("Arduino Bonanza", Duration.ofMinutes(30))));
        assertFalse(schedule.isCreated());
    }

}
