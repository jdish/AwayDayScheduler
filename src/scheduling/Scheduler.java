package scheduling;

import activity.Activity;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by James Horne on 02/09/2016.
 */
public class Scheduler {

    private List<Activity> activities;
    private int numOfTeams;
    private Schedule[] schedules;
    private Duration totalActivitiesDuration;

    public Scheduler(List<Activity> activities, int numOfTeams) {
        schedules = new Schedule[numOfTeams];
        this.activities = activities;
        this.numOfTeams = numOfTeams;
        this.totalActivitiesDuration = Duration.ofMinutes(activities.stream().mapToLong(act -> act.getDuration().toMinutes()).sum());
    }

    public boolean isEnoughActivitiesDuration(int minNumOfActivityHours) {
        return totalActivitiesDuration.compareTo(Duration.ofHours(minNumOfActivityHours * numOfTeams)) > 0;
    }

    public Duration getTotalActivitiesDuration() {
        return totalActivitiesDuration;
    }

    public boolean createSchedules() {
        int numberOfRetriesAllowed = 10;

        while (numberOfRetriesAllowed > 0) {
            for (int i = 0; i < numOfTeams; i++) {
                schedules[i] = new Schedule();
            }

            // On first try, sort with highest duration activities at the top
            if (numberOfRetriesAllowed == 10) {
                Collections.sort(activities);
            }

            LocalTime greatestScheduleEndTime = LocalTime.of(9, 0);
            for (Activity activity : activities) {
                for (int i = 0; i < numOfTeams; i++) {
                    if (schedules[i].tryToAddActivity(activity)) {
                        LocalTime currentScheduleEndTime = schedules[i].getCurrentEndingTime();
                        if (currentScheduleEndTime.isAfter(greatestScheduleEndTime)) {
                            greatestScheduleEndTime = schedules[i].getCurrentEndingTime();
                        }
                        break;
                    }
                }
            }

            int schedulesCreatedCount = 0;
            for (int i = 0; i < numOfTeams; i++) {
                if (schedules[i].isCreated()) {
                    schedules[i].updateEndingTimeSlot("PRESENTATION", greatestScheduleEndTime);
                    ++schedulesCreatedCount;
                }
            }

            if (schedulesCreatedCount == numOfTeams) {
                return true;
            } else {
                --numberOfRetriesAllowed;
                Collections.shuffle(activities);
            }
        }
        return false;
    }

    public String getScheduleOutput() {
        StringBuilder scheduleOutput = new StringBuilder();
        for (int i = 0; i < numOfTeams; i++) {
            scheduleOutput.append("\nTeam ").append(i + 1).append(":\n");
            schedules[i].getTimeSlots().forEach(timeSlot -> scheduleOutput.append(timeSlot).append("\n"));
        }
        return scheduleOutput.toString();
    }
}
