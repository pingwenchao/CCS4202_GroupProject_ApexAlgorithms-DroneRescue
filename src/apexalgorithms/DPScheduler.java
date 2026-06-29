package apexalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Scheduler implementing the Dynamic Programming (DP) paradigm.
 * Utilizes a 2D tabulation matrix to guarantee the global optimal solution.
 */
public class DPScheduler {

    /**
     * Executes the dynamic programming 0/1 knapsack process.
     * * @param missions   The list of available rescue missions.
     * @param maxPayload The physical payload limit of the drone.
     * @return A list of optimally selected missions.
     */
    public List<Mission> schedule(List<Mission> missions, int maxPayload) {
        int n = missions.size();
        // Matrix to store the maximum value at each subproblem state: dp[item_index][current_capacity]
        int[][] dp = new int[n + 1][maxPayload + 1];

        // Step 1: Build the DP table bottom-up using the recurrence relation
        for (int i = 1; i <= n; i++) {
            Mission currentMission = missions.get(i - 1);
            int w = currentMission.getWeight();
            int v = currentMission.getPriorityValue();

            for (int weightLimit = 1; weightLimit <= maxPayload; weightLimit++) {
                if (w <= weightLimit) {
                    // Recurrence relation: Max(Exclude current item, Include current item)
                    dp[i][weightLimit] = Math.max(dp[i - 1][weightLimit], dp[i - 1][weightLimit - w] + v);
                } else {
                    // Item exceeds current weight limit, must exclude
                    dp[i][weightLimit] = dp[i - 1][weightLimit];
                }
            }
        }

        // Step 2: Backtracking to find the exact missions that were selected
        List<Mission> selectedMissions = new ArrayList<>();
        int currentCapacity = maxPayload;

        // Trace back from the bottom-right corner of the matrix
        for (int i = n; i > 0 && currentCapacity > 0; i--) {
            // If the value came from the top row, the item was excluded.
            // If it's different, the item was included.
            if (dp[i][currentCapacity] != dp[i - 1][currentCapacity]) {
                Mission includedMission = missions.get(i - 1);
                selectedMissions.add(includedMission);
                currentCapacity -= includedMission.getWeight(); // Deduct used capacity
            }
        }

        return selectedMissions;
    }
}