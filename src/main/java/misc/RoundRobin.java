package misc;

public class RoundRobin {
    private static void findWaitingTime(Integer[] processes, Integer n, Integer[] bt, Integer[] wt, Integer quantum) {

        Integer[] rem_bt = new Integer[n];
        System.arraycopy(bt, 0, rem_bt, 0, n);

        int t = 0; // Current time

        while (true) {
            boolean done = true;

            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false; // There is a pending process

                    if (rem_bt[i] > quantum) {
                        t += quantum;
                        rem_bt[i] -= quantum;
                    } else {
                        t = t + rem_bt[i];
                        wt[i] = t - bt[i];

                        rem_bt[i] = 0;
                    }
                }
            }

            if (done) break;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Integer[] processes, Integer n, Integer[] bt, Integer[] wt, Integer[] tat) {
        for (int i = 0; i < n; i++) tat[i] = bt[i] + wt[i];
    }

    // Method to calculate average time
    public static void findAvgTime(Integer[] processes, Integer n, Integer[] bt, Integer quantum) {
        Integer[] wt = new Integer[n], tat = new Integer[n];
        int total_wt = 0, total_tat = 0;

        findWaitingTime(processes, n, bt, wt, quantum);
        findTurnAroundTime(processes, n, bt, wt, tat);

        System.out.println("PN " + " B " + " WT " + " TAT");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];

            System.out.println(" " + (i + 1) + "\t\t" + bt[i] + "\t " + wt[i] + "\t\t " + tat[i]);
        }

        System.out.println("Average waiting time = " + (float) total_wt / (float) n);
        System.out.println("Average turn around time = " + (float) total_tat / (float) n);
    }

    // Driver Method
    public static void main(String[] args) {
        // process id's
        Integer[] processes = {1, 2, 3};
        Integer n = processes.length;

        // Burst time of all processes
        Integer[] burst_time = {10, 5, 8};

        // Time quantum
        int quantum = 2;
        findAvgTime(processes, n, burst_time, quantum);
    }
}

