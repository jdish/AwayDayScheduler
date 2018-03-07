package activity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by James Horne on 02/09/2016.
 */
public class ActivitiesFileReader {

    private List<String> activityLines;

    public ActivitiesFileReader(String textFileDir) throws IOException {
        this.activityLines = Files.readAllLines(Paths.get(textFileDir));
    }

    public List<Activity> readAndCreateActivityList() {
        List<Activity> activities = new ArrayList<>();

        for (String line : activityLines) {
            // Remove all potential whitespaces at beginning and end of line
            line = line.replaceAll("^\\s+", "").replaceAll("\\s+$", "");

            // Split on the space between activity name and duration
            String[] activityInputs = line.split("\\s+(?=\\S*$)");
            Duration duration = Duration.ofMinutes(0);

            for (String input : activityInputs) {
                if ("sprint".equals(input)) {
                    duration = Duration.ofMinutes(15);
                } else if (Pattern.matches("[0-9]+min", input)) {
                    duration = Duration.ofMinutes(Integer.parseInt(input.split("m")[0]));
                }
            }
            activities.add(new Activity(activityInputs[0], duration));
        }

        return activities;
    }

}
