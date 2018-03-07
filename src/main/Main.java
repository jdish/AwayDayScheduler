package main;

import activity.ActivitiesFileReader;
import activity.Activity;
import scheduling.Scheduler;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String textFileDir;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loadCorrectFile = false;
        textFileDir = "";

        System.out.println("*****Welcome to the Away Day Scheduler*****");

        while (!loadCorrectFile) {
            loadCorrectFile = promptUserToLoadCorrectFile(scanner);
        }

        try {
            ActivitiesFileReader activitiesFileReader = new ActivitiesFileReader(textFileDir);
            System.out.println(textFileDir + " has been loaded successfully.");
            System.out.println("How many teams do you have for the away day?");
            int numOfTeams = scanner.nextInt();
            tryToOutputSchedules(activitiesFileReader, numOfTeams);
        }
        catch (IOException iOE) {
            System.out.println("Sorry there has been error while processing the activities file: " + textFileDir);
            if (iOE instanceof NoSuchFileException) {
                System.out.println("Please make sure file location is correct and try again.");
            }
            else {
                System.out.println("Please correct file formatting and try again.");
            }
            iOE.printStackTrace();
        } catch (Exception e) {
            System.out.println("Sorry there has been an error while scheduling. Please contact system admin.\n");
            e.printStackTrace();
        }
    }

    public static boolean promptUserToLoadCorrectFile(Scanner scanner) {
        System.out.println("Please enter the directory for the txt file with the list of the activities" +
                "(e.g. C:\\Users\\JamesH\\Documents\\activities.txt): ");
        textFileDir = scanner.next();

        System.out.println("Load " + textFileDir + "(y/n)? ");
        String answer = scanner.next();

        if ("y".equalsIgnoreCase(answer)) {
            return true;
        } else if (!("n".equalsIgnoreCase(answer))) {
            System.out.println("You have enter an invalid character! Please try again.");
        }
        return false;
    }

    public static void tryToOutputSchedules(ActivitiesFileReader activitiesFileReader, int numOfTeams) {
        List<Activity> fullActivityList = activitiesFileReader.readAndCreateActivityList();
        Scheduler scheduler = new Scheduler(fullActivityList, numOfTeams);

        int minHoursOfActivityTime = 6;
        if (scheduler.isEnoughActivitiesDuration(minHoursOfActivityTime)) {
            if (scheduler.createSchedules()) {
                System.out.println("Here is the schedule for the " + numOfTeams + " teams:");
                System.out.println(scheduler.getScheduleOutput());
            } else {
                System.out.println("Sorry we cannot make a schedule for the " + numOfTeams
                        + " teams you requested.\nPlease try again with different set of activities.");
            }
        } else {
            System.out.println("Sorry there is not enough activity time in the day "
                    + "to create the schedules for " + numOfTeams + " teams you requested. "
                    + "You have " + scheduler.getTotalActivitiesDuration().toMinutes() + " mins of activity time.");
        }
    }
}
