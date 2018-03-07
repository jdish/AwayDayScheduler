package timeblock;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by James Horne on 02/09/2016.
 */
public class FlexibleTimeBlock extends TimeBlock {

    private Duration toleranceBeforeMaxInMins;
    private boolean isFilledWithinTolerance;

    protected FlexibleTimeBlock(LocalTime startingTime, Duration maxDuration, Duration toleranceBeforeMaxInMins) {
        super(startingTime, maxDuration);
        this.toleranceBeforeMaxInMins = toleranceBeforeMaxInMins;
    }

    @Override
    public boolean updateRemainingDuration(Duration duration) {
        if (!isFilled) {
            Duration durationLeft = remainingDurationLeft.minus(duration);
            int durationResult = durationLeft.compareTo(Duration.ZERO);

            if (durationResult >= 0) {
                remainingDurationLeft = durationLeft;

                if (durationLeft.compareTo(toleranceBeforeMaxInMins) < 0) {
                    isFilledWithinTolerance = true;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFilled() {
        return isFilledWithinTolerance || isFilled;
    }
}
