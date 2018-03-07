package timeslot;

import activity.Activity;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by James Horne on 02/09/2016.
 */
public class TimeSlot implements Comparable<TimeSlot> {

    private Activity activity;
    private LocalTime startTime;

    public TimeSlot(Activity activity, LocalTime startTime) {
        this.activity = activity;
        this.startTime = startTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public Duration getDuration() {
        return activity.getDuration();
    }

    @Override
    public int compareTo(TimeSlot timeSlot) {
        return this.startTime.compareTo(timeSlot.startTime);
    }

    @Override
    public String toString() {
        return startTime.format(DateTimeFormatter.ofPattern("0h:mm a"))
                .replaceAll("0([0-9]{2}:)", "$1")
                + " : " + activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimeSlot timeSlot = (TimeSlot) o;

        if (!activity.equals(timeSlot.activity)) {
            return false;
        }
        return startTime.equals(timeSlot.startTime);
    }

    @Override
    public int hashCode() {
        int result = activity.hashCode();
        result = 31 * result + startTime.hashCode();
        return result;
    }
}
