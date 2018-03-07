package main;

import activity.Activity;
import scheduling.Scheduler;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by James Horne on 02/09/2016.
 */
public class SchedulerTest {

    private Scheduler scheduler;

    private List<Activity> createDefaultActivities() {
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

        return activities;
    }

    @Test
    public void justEnoughActivitiesDurationTest() throws Exception {
        scheduler = new Scheduler(createDefaultActivities(), 2);
        assertTrue(scheduler.isEnoughActivitiesDuration(6));
    }

    @Test
    public void notEnoughActivitiesDurationTest() throws Exception {
        List<Activity> activities = new ArrayList<>();

        activities.add(new Activity("Duck Herding", Duration.ofMinutes(60)));
        activities.add(new Activity("Archery", Duration.ofMinutes(45)));
        activities.add(new Activity("Learning Magic Tricks", Duration.ofMinutes(40)));
        activities.add(new Activity("Laser Clay Shooting", Duration.ofMinutes(60)));
        activities.add(new Activity("Human Table Football", Duration.ofMinutes(30)));
        activities.add(new Activity("Buggy Driving", Duration.ofMinutes(30)));

        scheduler = new Scheduler(activities, 2);
        assertFalse(scheduler.isEnoughActivitiesDuration(6));
    }

    @Test
    public void notEnoughActivitiesDurationWithTooManyTeamsTest() throws Exception {
        scheduler = new Scheduler(createDefaultActivities(), 4);
        assertFalse(scheduler.isEnoughActivitiesDuration(6));
    }

    @Test
    public void createSchedulesSuccessfullyTest() throws Exception {
        scheduler = new Scheduler(createDefaultActivities(), 2);
        assertTrue(scheduler.createSchedules());
    }

    @Test
    public void correctTotalDurationTest() throws Exception {
        scheduler = new Scheduler(createDefaultActivities(), 2);
        assertEquals(scheduler.getTotalActivitiesDuration(), Duration.ofMinutes(835));
    }

    @Test
    public void willNotCreateSchedulesWithImpossibleTimesTest() throws Exception {
        for (int i = 0; i < 10000; i++) {
            List<Activity> activities = new ArrayList<>();
            activities.add(new Activity("Duck Herding", Duration.ofMinutes(59)));
            activities.add(new Activity("Archery", Duration.ofMinutes(44)));
            activities.add(new Activity("Learning Magic Tricks", Duration.ofMinutes(39)));
            activities.add(new Activity("Laser Clay Shooting", Duration.ofMinutes(59)));
            activities.add(new Activity("Human Table Football", Duration.ofMinutes(59)));
            activities.add(new Activity("Buggy Driving", Duration.ofMinutes(29)));
            activities.add(new Activity("Salsa & Pickles", Duration.ofMinutes(14)));
            activities.add(new Activity("2-wheeled Segways", Duration.ofMinutes(44)));
            activities.add(new Activity("Viking Axe Throwing", Duration.ofMinutes(59)));
            activities.add(new Activity("Giant Puzzle Dinosaurs", Duration.ofMinutes(29)));
            activities.add(new Activity("Giant Digital Graffiti", Duration.ofMinutes(59)));
            activities.add(new Activity("Cricket 2020", Duration.ofMinutes(59)));
            activities.add(new Activity("Wine Tasting", Duration.ofMinutes(14)));
            activities.add(new Activity("Arduino Bonanza", Duration.ofMinutes(29)));
            activities.add(new Activity("Digital Tresure Hunt", Duration.ofMinutes(59)));
            activities.add(new Activity("Enigma Challenge", Duration.ofMinutes(44)));
            activities.add(new Activity("Monti Carlo or Bust", Duration.ofMinutes(59)));
            activities.add(new Activity("New Zealand Haka", Duration.ofMinutes(24)));
            activities.add(new Activity("Time Tracker", Duration.ofMinutes(14)));
            activities.add(new Activity("Indiano Drizzle", Duration.ofMinutes(44)));
            scheduler = new Scheduler(activities, 2);
            assertFalse(scheduler.createSchedules());
        }
    }

    @Test
    public void correctScheduleOutputWithAn5PMEndTimeTest() throws Exception {
        String scheduleOutput =
                "\nTeam 1:\n" +
                        "09:00 AM : Duck Herding 60min\n" +
                        "10:00 AM : Laser Clay Shooting 60min\n" +
                        "11:00 AM : Viking Axe Throwing 60min\n" +
                        "12:00 PM : Lunch Break 60min\n" +
                        "01:00 PM : Giant Digital Graffiti 60min\n" +
                        "02:00 PM : Cricket 2020 60min\n" +
                        "03:00 PM : Digital Tresure Hunt 60min\n" +
                        "04:00 PM : Monti Carlo or Bust 60min\n" +
                        "05:00 PM : Staff Motivation Presentation\n" +
                        "\n" +
                        "Team 2:\n" +
                        "09:00 AM : Archery 45min\n" +
                        "09:45 AM : 2-wheeled Segways 45min\n" +
                        "10:30 AM : Enigma Challenge 45min\n" +
                        "11:15 AM : Indiano Drizzle 45min\n" +
                        "12:00 PM : Lunch Break 60min\n" +
                        "01:00 PM : Learning Magic Tricks 40min\n" +
                        "01:40 PM : Human Table Football 30min\n" +
                        "02:10 PM : Buggy Driving 30min\n" +
                        "02:40 PM : Giant Puzzle Dinosaurs 30min\n" +
                        "03:10 PM : Arduino Bonanza 30min\n" +
                        "03:40 PM : New Zealand Haka 30min\n" +
                        "04:10 PM : Salsa & Pickles sprint\n" +
                        "04:25 PM : Wine Tasting sprint\n" +
                        "04:40 PM : Time Tracker sprint\n" +
                        "05:00 PM : Staff Motivation Presentation\n";

        scheduler = new Scheduler(createDefaultActivities(), 2);
        scheduler.createSchedules();
        assertEquals(scheduleOutput, scheduler.getScheduleOutput());
    }

    @Test
    public void correctScheduleOutputWithAnAfter4PMAndBefore5PMEndTimeTest() throws Exception {
        String scheduleOutput =
                "\nTeam 1:\n" +
                        "09:00 AM : Duck Herding 60min\n" +
                        "10:00 AM : Laser Clay Shooting 60min\n" +
                        "11:00 AM : Cricket 2020 60min\n" +
                        "12:00 PM : Lunch Break 60min\n" +
                        "01:00 PM : Digital Tresure Hunt 60min\n" +
                        "02:00 PM : Monti Carlo or Bust 60min\n" +
                        "03:00 PM : Viking Axe Throwing 55min\n" +
                        "03:55 PM : Giant Digital Graffiti 55min\n" +
                        "04:55 PM : Staff Motivation Presentation\n" +
                        "\n" +
                        "Team 2:\n" +
                        "09:00 AM : Archery 45min\n" +
                        "09:45 AM : 2-wheeled Segways 45min\n" +
                        "10:30 AM : Enigma Challenge 45min\n" +
                        "11:15 AM : Indiano Drizzle 45min\n" +
                        "12:00 PM : Lunch Break 60min\n" +
                        "01:00 PM : Learning Magic Tricks 40min\n" +
                        "01:40 PM : Human Table Football 30min\n" +
                        "02:10 PM : Buggy Driving 30min\n" +
                        "02:40 PM : Giant Puzzle Dinosaurs 30min\n" +
                        "03:10 PM : Arduino Bonanza 30min\n" +
                        "03:40 PM : New Zealand Haka 30min\n" +
                        "04:10 PM : Salsa & Pickles sprint\n" +
                        "04:25 PM : Wine Tasting sprint\n" +
                        "04:40 PM : Time Tracker sprint\n" +
                        "04:55 PM : Staff Motivation Presentation\n";

        List<Activity> activities = new ArrayList<>();

        activities.add(new Activity("Duck Herding", Duration.ofMinutes(60)));
        activities.add(new Activity("Archery", Duration.ofMinutes(45)));
        activities.add(new Activity("Learning Magic Tricks", Duration.ofMinutes(40)));
        activities.add(new Activity("Laser Clay Shooting", Duration.ofMinutes(60)));
        activities.add(new Activity("Human Table Football", Duration.ofMinutes(30)));
        activities.add(new Activity("Buggy Driving", Duration.ofMinutes(30)));
        activities.add(new Activity("Salsa & Pickles", Duration.ofMinutes(15)));
        activities.add(new Activity("2-wheeled Segways", Duration.ofMinutes(45)));
        activities.add(new Activity("Viking Axe Throwing", Duration.ofMinutes(55)));
        activities.add(new Activity("Giant Puzzle Dinosaurs", Duration.ofMinutes(30)));
        activities.add(new Activity("Giant Digital Graffiti", Duration.ofMinutes(55)));
        activities.add(new Activity("Cricket 2020", Duration.ofMinutes(60)));
        activities.add(new Activity("Wine Tasting", Duration.ofMinutes(15)));
        activities.add(new Activity("Arduino Bonanza", Duration.ofMinutes(30)));
        activities.add(new Activity("Digital Tresure Hunt", Duration.ofMinutes(60)));
        activities.add(new Activity("Enigma Challenge", Duration.ofMinutes(45)));
        activities.add(new Activity("Monti Carlo or Bust", Duration.ofMinutes(60)));
        activities.add(new Activity("New Zealand Haka", Duration.ofMinutes(30)));
        activities.add(new Activity("Time Tracker", Duration.ofMinutes(15)));
        activities.add(new Activity("Indiano Drizzle", Duration.ofMinutes(45)));

        scheduler = new Scheduler(activities, 2);
        scheduler.createSchedules();
        System.out.println(scheduler.getScheduleOutput());
        assertEquals(scheduler.getScheduleOutput(), scheduleOutput);
    }
}
