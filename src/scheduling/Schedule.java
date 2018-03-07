package scheduling;

import activity.Activity;
import timeblock.TimeBlock;
import timeblock.TimeBlockFactory;
import timeblock.TimeBlockTypes;
import timeslot.TimeSlot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by James Horne on 02/09/2016.
 */
public class Schedule {

    private List<TimeBlock> timeBlocks;

    public Schedule() {
        timeBlocks = new ArrayList<>();
        timeBlocks.add(TimeBlockFactory.createTimeBlock(TimeBlockTypes.MORNING.name()));
        timeBlocks.add(TimeBlockFactory.createTimeBlock(TimeBlockTypes.LUNCH.name()));
        timeBlocks.add(TimeBlockFactory.createTimeBlock(TimeBlockTypes.AFTERNOON.name()));
    }

    public Schedule(List<TimeBlock> timeBlocks) {
        this.timeBlocks = new ArrayList<>(timeBlocks);
    }

    public List<TimeSlot> getTimeSlots() {
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeBlocks.forEach(tb -> timeSlots.addAll(tb.getTimeSlots()));
        Collections.sort(timeSlots);
        return timeSlots;
    }

    public boolean tryToAddActivity(Activity activity) {
        boolean addedActivity = false;

        for (TimeBlock timeBlock : timeBlocks) {
            if (timeBlock.updateRemainingDuration(activity.getDuration())) {
                timeBlock.addTimeSlotToBlock(activity);
                addedActivity = true;
            }
        }

        return addedActivity;
    }

    public boolean isCreated() {
        return (int) timeBlocks.stream().filter(TimeBlock::isFilled).count() == timeBlocks.size();
    }

    public LocalTime getCurrentEndingTime() {
        LocalTime greatestTimeBlockEndTime = LocalTime.of(9, 0);
        for (TimeBlock timeBlock : timeBlocks) {
            LocalTime endTime = timeBlock.getProgressiveTime();
            if (endTime.isAfter(greatestTimeBlockEndTime)) {
                greatestTimeBlockEndTime = endTime;
            }
        }
        return greatestTimeBlockEndTime;
    }

    public void updateEndingTimeSlot(String eventName, LocalTime startTime) {
        timeBlocks.add(TimeBlockFactory.createTimeBlock(eventName, startTime));
    }
}
