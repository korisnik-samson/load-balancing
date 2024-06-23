import scheduling.RoundRobin;
import scheduling.RoundRobin_;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void arrayListParser() {
        ArrayList<Integer> processes = new ArrayList<>(Arrays.asList(1, 2, 3));
        Integer n = processes.size();

        ArrayList<Integer> bt = new ArrayList<>(Arrays.asList(10, 5, 8));
        Integer quantum = 2;

        RoundRobin_.findAvgTime(processes, n, bt, quantum);
    }

    public static void arrayParser() {
        Integer[] processes = {1, 2, 3};
        Integer n = processes.length;

        Integer[] bt = {10, 5, 8};
        Integer quantum = 2;

        RoundRobin.findAvgTime(processes, n, bt, quantum);
    }

    public static void main(String[] args) {
        arrayListParser();
        arrayParser();
    }
}
