package Algorithms.GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivitySelection {

    record Activity(int startTime,int finishTime) implements Comparable<Activity>{

        @Override
        public int compareTo(Activity activity) {
            return Integer.compare(this.finishTime, activity.finishTime);
        }

    }

    public static void main(String[] args) {

        int[] startTimes = { 1, 3, 0, 5, 8, 5, 6, 11 , 13 };
        int[] finishTimes = { 2, 4, 6, 7, 9, 9, 10, 13, 14 };

        Activity[] activities = new Activity[startTimes.length];


        for (int i = 0; i < activities.length; i++) {
            activities[i] = new Activity(startTimes[i],finishTimes[i]);
        }

        /*
        int maxActivity = getMaxActivity(activities);
        System.out.println("Max Activity Count : " + maxActivity);
        */

        getMaxActivitySolution(activities);

    }

    private static int getMaxActivity(Activity[] activities) {

        int maxActivityCount = 0;

        Arrays.sort(activities);

        int time = activities[0].finishTime;
        maxActivityCount++;

        for (int i = 1; i < activities.length; i++) {
            if (activities[i].startTime >= time) {
                maxActivityCount++;
                time = activities[i].finishTime;
            }
        }

        return maxActivityCount;

    }

    private static void getMaxActivitySolution(Activity[] activities) {

        int maxActivityCount = 0;

        Arrays.sort(activities);

        ArrayList<Activity> selectedActivities = new ArrayList<>();

        int time = -1;

        for (Activity activity : activities) {
            if (activity.startTime >= time) {
                maxActivityCount++;
                time = activity.finishTime;
                selectedActivities.add(activity);
            }
        }

        System.out.println("Max Activity Count : " + maxActivityCount);

        System.out.println("Selected Activities : ");
        for (Activity activity : selectedActivities)
            System.out.println("\tStart time : " + activity.startTime + "\t Finish time : " + activity.finishTime );

    }

}
