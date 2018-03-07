package activity;

import java.time.Duration;

/**
 * Created by James Horne on 02/09/2016.
 */
public class Activity implements Comparable<Activity> {

    private String name;
    private Duration duration;
    private boolean showDuration;

    public Activity(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
        this.showDuration = true;
    }

    public Activity(String name, Duration duration, boolean showDuration) {
        this.name = name;
        this.duration = duration;
        this.showDuration = showDuration;
    }

    public String getName() {
        return name;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Activity activity) { // Sort desc order
        return -1 * this.duration.compareTo(activity.getDuration());
    }

    @Override
    public String toString() {
        String durationStr = " " + duration.toMinutes() + "min";

        if (!showDuration) {
            durationStr = "";
        } else if (duration.equals(Duration.ofMinutes(15))) {
            durationStr = " sprint";
        }

        return name + durationStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Activity activity = (Activity) o;

        if (showDuration != activity.showDuration) {
            return false;
        }
        if (!name.equals(activity.name)) {
            return false;
        }

        return duration.equals(activity.duration);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + duration.hashCode();
        result = 31 * result + (showDuration ? 1 : 0);
        return result;
    }
}
