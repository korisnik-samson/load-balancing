package scheduling;

import java.util.ArrayList;

public class RoundRobin_ {
    private static void findWaitingTime(ArrayList<Integer> processes, Integer n, ArrayList<Integer> bt, ArrayList<Integer> wt,
                                        Integer quantum) {
        ArrayList<Integer> rem_bt = new ArrayList<>(bt);
        System.arraycopy(bt.toArray(), 0, rem_bt.toArray(), 0, n);

        int t = 0; // Current time

        while (true) {
            boolean done = true;

            for (int i = 0; i < n; i++) {
                if (rem_bt.get(i) > 0) {
                    done = false; // There is a pending process

                    if (rem_bt.get(i) > quantum) {
                        t += quantum;
                        rem_bt.set(i, rem_bt.get(i) - quantum);
                    } else {
                        t = t + rem_bt.get(i);
                        wt.set(i, t - bt.get(i));

                        rem_bt.set(i, 0);
                    }
                }
            }

            if (done) break;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(ArrayList<Integer> processes, Integer n, ArrayList<Integer> bt, ArrayList<Integer> wt,
                                   ArrayList<Integer> tat) {
        for (int i = 0; i < n; i++) tat.set(i, bt.get(i) + wt.get(i));
    }

    // Method to calculate average time
    public static void findAvgTime(ArrayList<Integer> processes, Integer n, ArrayList<Integer> bt, Integer quantum) {
        ArrayList<Integer> wt = new ArrayList<>(n);
        ArrayList<Integer> tat = new ArrayList<>(n);
        int total_wt = 0, total_tat = 0;

        findWaitingTime(processes, n, bt, wt, quantum);
        findTurnAroundTime(processes, n, bt, wt, tat);

        System.out.println("PN " + " B " + " WT " + " TAT");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt.get(i);
            total_tat = total_tat + tat.get(i);

            System.out.println(" " + (i + 1) + "\t\t" + bt.get(i) + "\t " + wt.get(i) + "\t\t " + tat.get(i));
        }

        System.out.println("Average waiting time = " + (float) total_wt / (float) n);
        System.out.println("Average turn around time = " + (float) total_tat / (float) n);
    }
}
