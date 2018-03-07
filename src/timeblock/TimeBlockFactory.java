package timeblock;

import activity.Activity;
import timeslot.TimeSlot;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by James Horne on 03/09/2016.
 */
public abstract class TimeBlockFactory {

    private enum ActivityTypes {
        LUNCH_BREAK("Lunch Break"),
        PRESENTATION("Staff Motivation Presentation");

        private final String activityName;

        ActivityTypes(String activity) {
            this.activityName = activity;
        }
    }

    public static TimeBlock createTimeBlock(String timeBlockType) {
        if (timeBlockType == null) {
            return null;
        }

        if (timeBlockType.equalsIgnoreCase(TimeBlockTypes.MORNING.name())) {
            return new TimeBlock(LocalTime.of(9, 0), Duration.ofHours(3));

        } else if (timeBlockType.equalsIgnoreCase(TimeBlockTypes.LUNCH.name())) {
            return new TimeBlock(LocalTime.of(12, 0),
                    Duration.ofMinutes(60),
                    new TimeSlot(new Activity(ActivityTypes.LUNCH_BREAK.name(), Duration.ofMinutes(60)), LocalTime.of(12, 0)));

        } else if (timeBlockType.equalsIgnoreCase(TimeBlockTypes.AFTERNOON.name())) {
            return new FlexibleTimeBlock(LocalTime.of(13, 0), Duration.ofHours(4), Duration.ofMinutes(60));

        }

        return null;
    }

    public static TimeBlock createTimeBlock(String timeBlockType, LocalTime startTime) {
        if (timeBlockType == null) {
            return null;
        }

        if (timeBlockType.equalsIgnoreCase(TimeBlockTypes.PRESENTATION.name())) {
            return new TimeBlock(startTime,
                    Duration.ofMinutes(0),
                    new TimeSlot(new Activity(ActivityTypes.PRESENTATION.name(), Duration.ofMinutes(0), false), startTime));
        }

        return null;
    }
}
