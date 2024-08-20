package Algorithms.GreedyAlgorithms;

import java.util.Arrays;

public class JobSequencing {

    private record Job(int id, int deadline, int profit) implements Comparable<Job> {

        @Override
        public int compareTo(Job job) {
            return job.profit - this.profit;
        }

    }

    private record JobSequenceSolution(int[] jobSequence, int profit) {


        public static void print(JobSequenceSolution jobSequenceSolution) {
            System.out.println("*******    Maximum profit of job sequence  =  " + jobSequenceSolution.profit + "    *******");
            System.out.println("*******    Job sequence is     *******");
            printArray(jobSequenceSolution.jobSequence);
        }

    }


    public static void main(String[] args) {

        /*

        Job[] jobs = {
            new Job(1, 9, 15),
            new Job(2, 2, 2),
            new Job(3, 5, 18),
            new Job(4, 7, 1),
            new Job(5, 4, 25),
            new Job(6, 2, 20),
            new Job(7, 5, 8),
            new Job(8, 7, 10),
            new Job(9, 4, 12),
            new Job(10, 3, 5)
        };

         */


        Job[] jobs = {
                new Job(2, 2, 15),
                new Job(4, 3, 5),
                new Job(3, 1, 10),
                new Job(1, 2, 20),
                new Job(5, 3, 1)
        };




        JobSequenceSolution jobSequenceSolution = getJobSequenceSolution(jobs);

        JobSequenceSolution.print(jobSequenceSolution);

        System.out.println("\n             ********************\n");

        System.out.println("Maximum profit = " + getMaxProfit(jobs));


    }

    public static int getMaxProfit (Job[] jobs) {
        Arrays.sort(jobs);

        int maxProfit = 0;

        int[] jobSequence = new int[jobs.length];

        for (int i = 0; i < jobs.length; i++)
            jobSequence[i] = -1;

        for (Job job : jobs) {

            int index = job.deadline - 1;

            while (index >= 0) {

                if (jobSequence[index] == -1) {
                    jobSequence[index] = job.id;
                    maxProfit += job.profit;
                    break;
                }

                index--;

            }

        }

        return maxProfit;

    }


    public static JobSequenceSolution getJobSequenceSolution(Job[] jobs) {

        Arrays.sort(jobs);

        int maxProfit = 0;

        int[] jobSequence = new int[jobs.length];

        for (int i = 0; i < jobs.length; i++)
            jobSequence[i] = -1;

        for (Job job : jobs) {

            int index = job.deadline - 1;

            while (index >= 0) {

                if (jobSequence[index] == -1) {
                    jobSequence[index] = job.id;
                    maxProfit += job.profit;
                    break;
                }

                index--;

            }

        }

        return new JobSequenceSolution(deleteNegativeValues(jobSequence), maxProfit);

    }

    private static int[] deleteNegativeValues(int[] array) {

        int index = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == -1) {
                index = i;
                break;
            }
        }

        int[] newArray = new int[index];

        System.arraycopy(array, 0, newArray, 0, newArray.length);

        return newArray;

    }

    private static void printArray (int[] array) {

        for (int element : array)
            System.out.print(element + " ");

        System.out.println();

    }

}
