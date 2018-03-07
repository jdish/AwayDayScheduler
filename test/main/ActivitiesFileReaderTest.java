package main;

import activity.ActivitiesFileReader;
import activity.Activity;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Horne on 02/09/2016.
 */
public class ActivitiesFileReaderTest {

    private ActivitiesFileReader activitiesFileReader;

    @Test
    public void correctActivitiesOutputTest() throws Exception {
        List<Activity> activities = new ArrayList<>();

        activities.add(new Activity("Duck Herding", Duration.ofMinutes(60)));
        activities.add(new Activity("Archery", Duration.ofMinutes(45)));
        activities.add(new Activity("Learning Magic Tricks", Duration.ofMinutes(40)));
        activities.add(new Activity("Laser Clay Shooting", Duration.ofMinutes(60)));
        activities.add(new Activity("Human Table Football", Duration.ofMinutes(30)));
        activities.add(new Activity("Buggy Driving", Duration.ofMinutes(30)));
        activities.add(new Activity("Salsa & Pickles", Duration.ofMinutes(15)));
        activities.add(new Activity("2-wheeled Segways", Duration.ofMinutes(45)));
        activities.add(new Activity("Viking Axe Throwing", Duration.ofMinutes(60)));
        activities.add(new Activity("Giant Puzzle Dinosaurs", Duration.ofMinutes(30)));
        activities.add(new Activity("Giant Digital Graffiti", Duration.ofMinutes(60)));
        activities.add(new Activity("Cricket 2020", Duration.ofMinutes(60)));
        activities.add(new Activity("Wine Tasting", Duration.ofMinutes(15)));
        activities.add(new Activity("Arduino Bonanza", Duration.ofMinutes(30)));
        activities.add(new Activity("Digital Tresure Hunt", Duration.ofMinutes(60)));
        activities.add(new Activity("Enigma Challenge", Duration.ofMinutes(45)));
        activities.add(new Activity("Monti Carlo or Bust", Duration.ofMinutes(60)));
        activities.add(new Activity("New Zealand Haka", Duration.ofMinutes(30)));
        activities.add(new Activity("Time Tracker", Duration.ofMinutes(15)));
        activities.add(new Activity("Indiano Drizzle", Duration.ofMinutes(45)));

        activitiesFileReader = new ActivitiesFileReader("./resources/Activities.txt");

        List<Activity> activitiesCreated = activitiesFileReader.readAndCreateActivityList();
        assertEquals(activities, activitiesCreated);
    }
}
