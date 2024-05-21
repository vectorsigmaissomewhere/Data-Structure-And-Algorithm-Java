// The activity selection problem is a problem of selecting the maximum number 
//' of activities that don't overlap, given start and end times.
import java.util.Arrays;
import java.util.Comparator;

class Activity {
    int start, end;

    Activity(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class ActivitySelection {
    public static void activitySelection(Activity[] activities) {
        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

        int n = activities.length;
        System.out.println("Selected activities:");
        
        int i = 0;
        System.out.println("Activity: (" + activities[i].start + ", " + activities[i].end + ")");
        
        for (int j = 1; j < n; j++) {
            if (activities[j].start >= activities[i].end) {
                System.out.println("Activity: (" + activities[j].start + ", " + activities[j].end + ")");
                i = j;
            }
        }
    }

    public static void main(String[] args) {
        Activity[] activities = { new Activity(1, 3), new Activity(2, 4), new Activity(3, 5), new Activity(0, 6),
                new Activity(5, 7), new Activity(8, 9), new Activity(5, 9) };
        activitySelection(activities);
    }
}

/*
Selected activities:
Activity: (1, 3)
Activity: (3, 5)
Activity: (5, 7)
Activity: (8, 9)
*/

