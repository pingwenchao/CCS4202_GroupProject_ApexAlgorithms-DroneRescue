package apexalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Main execution class.
 * Simulates the Seri Kembangan disaster scenario and compares Greedy vs. DP.
 */
public class Main {
    public static void main(String[] args) {
        // Hardware constraint established at UPM Bukit Ekspo
        int DRONE_MAX_PAYLOAD = 30;

        // Initializing the localized mission dataset
        List<Mission> disasterMissions = new ArrayList<>();
        disasterMissions.add(new Mission("M1", "Klinik Kesihatan", 16, 160)); // High ratio trap
        disasterMissions.add(new Mission("M2", "Taman Sri Serdang", 15, 135));
        disasterMissions.add(new Mission("M3", "Sungai Kuyoh", 15, 135));
        disasterMissions.add(new Mission("M4", "KTM Serdang", 25, 100));
        disasterMissions.add(new Mission("M5", "South City Plaza", 8, 40));

        System.out.println("==================================================");
        System.out.println("  SERI KEMBANGAN DRONE RESCUE SCHEDULING SYSTEM  ");
        System.out.println("==================================================");
        System.out.println("Drone Max Payload Limit: " + DRONE_MAX_PAYLOAD + " kg\n");

        /* ---------------------------------------------------------
         * Execution 1: Greedy Algorithm
         * ---------------------------------------------------------*/
        GreedyScheduler greedy = new GreedyScheduler();
        long greedyStartTime = System.nanoTime();
        List<Mission> greedyResults = greedy.schedule(disasterMissions, DRONE_MAX_PAYLOAD);
        long greedyEndTime = System.nanoTime();

        printResults("GREEDY ALGORITHM (Heuristic Baseline)", greedyResults, (greedyEndTime - greedyStartTime));

        /* ---------------------------------------------------------
         * Execution 2: Dynamic Programming (DP)
         * ---------------------------------------------------------*/
        DPScheduler dp = new DPScheduler();
        long dpStartTime = System.nanoTime();
        List<Mission> dpResults = dp.schedule(disasterMissions, DRONE_MAX_PAYLOAD);
        long dpEndTime = System.nanoTime();

        printResults("DYNAMIC PROGRAMMING (Global Optimal)", dpResults, (dpEndTime - dpStartTime));
    }

    /**
     * Helper method to format and print the scheduling results.
     */
    private static void printResults(String algorithmName, List<Mission> selectedMissions, long executionTimeNs) {
        System.out.println(">>> " + algorithmName);
        int totalValue = 0;
        int totalWeight = 0;

        for (Mission m : selectedMissions) {
            System.out.println("    - " + m.toString());
            totalValue += m.getPriorityValue();
            totalWeight += m.getWeight();
        }

        System.out.println("    -------------------------");
        System.out.println("    Total Weight Used : " + totalWeight + " kg");
        System.out.println("    Total Value Score : " + totalValue);
        System.out.println("    Execution Time    : " + (executionTimeNs / 1_000_000.0) + " ms\n");
    }
}