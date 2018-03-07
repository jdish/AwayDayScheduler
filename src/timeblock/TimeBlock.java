package timeblock;

import activity.Activity;
import timeslot.TimeSlot;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by James Horne on 02/09/2016.
 */
public class TimeBlock {

    protected final List<TimeSlot> timeSlots;
    protected boolean isFilled;

    protected final Duration maxDuration;
    protected LocalTime progressiveTime;
    protected Duration remainingDurationLeft;

    public TimeBlock(LocalTime startingTime, Duration maxDuration) {
        timeSlots = new ArrayList<>();
        this.progressiveTime = startingTime;
        this.maxDuration = maxDuration;
        this.remainingDurationLeft = maxDuration;
    }

    // Pre-populated and filled time block
    public TimeBlock(LocalTime startingTime, Duration maxDuration, TimeSlot... timeSlots) {
        this.timeSlots = Arrays.asList(timeSlots);
        this.progressiveTime = startingTime;
        this.maxDuration = maxDuration;
        this.isFilled = true;
        remainingDurationLeft = Duration.ZERO;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public boolean updateRemainingDuration(Duration duration) {
        if(!isFilled) {
            Duration durationLeft = remainingDurationLeft.minus(duration);
            int durationResult = durationLeft.compareTo(Duration.ZERO);

            if (durationResult >= 0) {
                remainingDurationLeft = durationLeft;
                return true;
            }
        }
        return false;
    }

    public void addTimeSlotToBlock(Activity activity) {
        if (!isFilled) {
            timeSlots.add(new TimeSlot(activity, progressiveTime));
            progressiveTime = progressiveTime.plusMinutes(activity.getDuration().toMinutes());
            if (remainingDurationLeft.isZero()) {
                isFilled = true;
            }
        }
    }

    public boolean isFilled() {
        return isFilled;
    }

    public LocalTime getProgressiveTime() {
        return progressiveTime;
    }
}
